package driver;

import config.TestStackProperties;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;
import java.time.Duration;

public class MobileCapabilitiesManager {
    public UiAutomator2Options setAndroidCapabilities() {
        String applicationPackage = TestStackProperties.getApplicationPackage();
        String applicationActivity = TestStackProperties.getApplicationActivity();
        File app = new File(TestStackProperties.getAndroidPath());
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp(app.getAbsolutePath())
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setDeviceName("Pixel 8")
                .setLanguage("PL")
                .setLocale("PL")
                .setUdid("R58N6235FSP")
                .setAppPackage(applicationPackage)
                .setAppActivity(applicationActivity)
                .setNoReset(true)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(300))
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(120))
                .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120));

        options.setCapability("autoAcceptsAlerts", true);

        return options;
    }

    public XCUITestOptions setIOSCapabilities() {
        String applicationPackage = TestStackProperties.getApplicationPackage();
        File app = new File(TestStackProperties.getIosPath());
        XCUITestOptions options = new XCUITestOptions()
                .setApp(app.getAbsolutePath())
                .setAutomationName("XCUITest")
                .setPlatformName("iOS")
                .setDeviceName("iPhone 16 Pro")
                .setLanguage("PL")
                .setLocale("PL")
                .setUdid("id")
                .setBundleId(applicationPackage)
                .autoAcceptAlerts()
                .setAutoAcceptAlerts(true)
                .setPlatformVersion("18.6");

        options.setCapability("locationServicesEnabled", true);
        options.setCapability("locationServicesAuthorized", true);

        return options;
    }
}
