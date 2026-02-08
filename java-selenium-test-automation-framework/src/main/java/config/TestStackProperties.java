package config;

import static config.TestConfig.CONFIG;

public class TestStackProperties {
    private static final String ENVIRONMENT = "environment";
    private static final String PLATFORM = "platform";
    private static final String WEB_URL = "web_url";
    private static final String API_URL = "api_url";
    private static final String ANDROID_PATH = "android_path";
    private static final String IOS_PATH = "ios_path";
    private static final String APPLICATION_PACKAGE = "application_package";
    private static final String APPLICATION_ACTIVITY = "application_activity";

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

    public static String getAndroidPath() {
        return CONFIG.getProperty(ANDROID_PATH);
    }

    public static String getIosPath() {
        return CONFIG.getProperty(IOS_PATH);
    }

    public static String getApplicationPackage() {
        return CONFIG.getProperty(APPLICATION_PACKAGE);
    }

    public static String getApplicationActivity() {
        return CONFIG.getProperty(APPLICATION_ACTIVITY);
    }
}