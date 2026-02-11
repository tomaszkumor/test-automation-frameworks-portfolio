package config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static utils.logger.Log4J.log;

public class ConfigManager {
    private final Yaml yaml = new Yaml();
    private final HashMap<String, Object> config = new HashMap<>();

    public ConfigManager(String fileName) {
        loadFileSettings(fileName);
    }

    public void loadFileSettings(String fileName) {
        try (InputStream stream = new FileInputStream(fileName)) {
            updateMap(config, yaml.load(stream));
        } catch (IOException e) {
            log.warn(String.format("cannot load file %s", fileName));
            throw new RuntimeException();
        }
    }

    private void updateMap(Map<String, Object> destMap, Map<String, Object> sourceMap) {
        sourceMap.forEach((key, sourceValue) -> {
            Object destValue = destMap.get(key);
            if (sourceValue instanceof Map && destValue instanceof Map) {
                updateMap((Map<String, Object>) destValue, (Map<String, Object>) sourceValue);
            } else {
                destMap.put(key, sourceValue);
            }
        });
    }

    private String findProperty(Map<String, Object> config, String propertyName) {
        int dotPos = propertyName.indexOf('.');
        if (dotPos < 0) {
            return String.valueOf(config.get(propertyName));
        } else {
            String key = propertyName.substring(0, dotPos);
            String reminder = propertyName.substring(dotPos + 1);
            Map<String, Object> subMap = getSubMap(config, key);
            if (subMap != null) {
                return findProperty(subMap, reminder);
            } else {
                return null;
            }
        }
    }

    private Map<String, Object> getSubMap(Map<String, Object> config, String key) {
        Object value = config.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        } else {
            return new HashMap<>();
        }
    }

    public String getProperty(String propertyName) {
        String systemProperty = System.getProperty(propertyName);
        if (systemProperty != null) {
            return systemProperty;
        } else {
            return findProperty(config, propertyName);
        }
    }
}
