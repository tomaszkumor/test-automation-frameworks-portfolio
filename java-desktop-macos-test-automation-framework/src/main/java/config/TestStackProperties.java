package config;

import static config.TestConfig.CONFIG;

public class TestStackProperties {
    private static final String ENVIRONMENT = "environment";
    private static final String PLATFORM = "platform";
    private static final String APPLICATION_NAME_PAGES = "application_name_pages";
    private static final String BUNDLE_ID_PAGES = "bundle_id_pages";
    private static final String APPLICATION_NAME_NUMBERS = "application_name_numbers";
    private static final String BUNDLE_ID_NUMBERS = "bundle_id_numbers";

    public static String getEnvironment() {
        return CONFIG.getProperty(ENVIRONMENT);
    }

    public static String getPlatform() {
        return CONFIG.getProperty(PLATFORM);
    }

    public static String getApplicationNamePages() {
        return CONFIG.getProperty(APPLICATION_NAME_PAGES);
    }

    public static String getBundleIdPages() {
        return CONFIG.getProperty(BUNDLE_ID_PAGES);
    }

    public static String getApplicationNameNumbers() {
        return CONFIG.getProperty(APPLICATION_NAME_NUMBERS);
    }

    public static String getBundleIdNumbers() {
        return CONFIG.getProperty(BUNDLE_ID_NUMBERS);
    }
}