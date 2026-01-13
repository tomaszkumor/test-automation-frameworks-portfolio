package driver;

import config.TestStackProperties;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;

public class MobileCapabilitiesManager {
    public UiAutomator2Options setAndroidCapabilities() {
        File app = new File(TestStackProperties.getAndroidPath());
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp(app.getAbsolutePath())
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setDeviceName("Pixel 8")
                .setLanguage("PL")
                .setLocale("PL")
                .setAutoGrantPermissions(true);

        options.setCapability("autoAcceptsAlerts", true);

        return options;
    }

    public XCUITestOptions setIOSCapabilities() {
        File app = new File(TestStackProperties.getIosPath());
        XCUITestOptions options = new XCUITestOptions()
                .setApp(app.getAbsolutePath())
                .setAutomationName("XCUITest")
                .setPlatformName("iOS")
                .setDeviceName("iPhone 16 Pro")
                .setLanguage("PL")
                .setLocale("PL")
                .setUdid("id")
                .autoAcceptAlerts()
                .setAutoAcceptAlerts(true)
                .setPlatformVersion("18.6");

        options.setCapability("locationServicesEnabled", true);
        options.setCapability("locationServicesAuthorized", true);

        return options;
    }
}
