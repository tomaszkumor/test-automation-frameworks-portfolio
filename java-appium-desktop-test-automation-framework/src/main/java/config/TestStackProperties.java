package config;

import static config.TestConfig.CONFIG;

public class TestStackProperties {
    private static final String ENVIRONMENT = "environment";
    private static final String PLATFORM = "platform";
    private static final String APPLICATION_PATH = "application_path";
    private static final String APPLICATION_NAME = "application_name";
    private static final String BUNDLE_ID = "bundle_id";

    public static String getEnvironment() {
        return CONFIG.getProperty(ENVIRONMENT);
    }

    public static String getPlatform() {
        return CONFIG.getProperty(PLATFORM);
    }

    public static String getApplicationPath() {
        return CONFIG.getProperty(APPLICATION_PATH);
    }

    public static String getApplicationName() {
        return CONFIG.getProperty(APPLICATION_NAME);
    }

    public static String getBundleId() {
        return CONFIG.getProperty(BUNDLE_ID);
    }
}