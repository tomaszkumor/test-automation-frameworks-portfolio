package driver;

import static config.TestConfig.CONFIG;

public class MobileProperties {
    public static String getMobileSystem() {
        return CONFIG.getProperty("mobile.system").toLowerCase();
    }

    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("mobile.debug"));
    }
}