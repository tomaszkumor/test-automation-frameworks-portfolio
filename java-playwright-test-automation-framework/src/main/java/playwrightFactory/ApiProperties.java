package playwrightFactory;

import static config.TestConfig.CONFIG;

public class ApiProperties {
    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("api.debug"));
    }
}