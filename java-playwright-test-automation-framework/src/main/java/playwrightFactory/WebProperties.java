package playwrightFactory;

import static config.TestConfig.CONFIG;

public class WebProperties {
    public static boolean isDebugMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.debug"));
    }

    public static boolean isHeadlessMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.headless"));
    }

    public static boolean isRemoteMode() {
        return Boolean.parseBoolean(CONFIG.getProperty("web.remote"));
    }

    public static String getEngine() {
        return String.valueOf(CONFIG.getProperty("web.engine"));
    }
}