package driver;

import static config.TestConfig.CONFIG;

public class WebProperties {
    public static String getBrowser() {
        return CONFIG.getProperty("web.browser").toLowerCase();
    }

    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.debug"));
    }

    public static boolean isHeadlessMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.headless"));
    }

    public static boolean isGridMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.grid"));
    }
}