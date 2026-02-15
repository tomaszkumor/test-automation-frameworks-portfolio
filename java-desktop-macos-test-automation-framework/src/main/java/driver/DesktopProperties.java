package driver;

import static config.TestConfig.CONFIG;

public class DesktopProperties {
    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("macos.debug"));
    }

    public static String getApplication() {
        return CONFIG.getProperty("macos.application");
    }
}