package driver;

import static config.TestConfig.CONFIG;

public class DesktopProperties {
    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("macos.debug"));
    }
}