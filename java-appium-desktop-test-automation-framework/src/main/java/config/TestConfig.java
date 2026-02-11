package config;

public class TestConfig {
    private static final String PROPERTIES_PATH = "src/main/resources/";
    public static final ConfigManager CONFIG = loadConfig();

    private static ConfigManager loadConfig() {
        ConfigManager config = new ConfigManager(PROPERTIES_PATH + "BasicSettings.yaml");
        String environment = config.getProperty("environment");
        config.loadFileSettings(PROPERTIES_PATH + "settings/" + String.format("%sSettings.yaml", environment.toUpperCase()));
        config.loadFileSettings(PROPERTIES_PATH + "users/" + String.format("%sUsers.yaml", environment.toUpperCase()));

        return config;
    }
}
