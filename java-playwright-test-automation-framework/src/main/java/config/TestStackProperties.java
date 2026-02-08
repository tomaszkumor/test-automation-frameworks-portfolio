package config;

import static config.TestConfig.CONFIG;

public class TestStackProperties {
    private static final String ENVIRONMENT = "environment";
    private static final String PLATFORM = "platform";
    private static final String WEB_URL = "web_url";
    private static final String API_URL = "api_url";
    private static final String REMOTE_BROWSER_WS_URL = "remote_browser_ws_url";

    public static String getEnvironment() {
        return CONFIG.getProperty(ENVIRONMENT);
    }

    public static String getPlatform() {
        return CONFIG.getProperty(PLATFORM);
    }

    public static String getWebUrl() {
        return CONFIG.getProperty(WEB_URL);
    }

    public static String getApiUrl() {
        return CONFIG.getProperty(API_URL);
    }

    public static String getRemoteBrowserWsUrl() {
        return CONFIG.getProperty(REMOTE_BROWSER_WS_URL);
    }
}