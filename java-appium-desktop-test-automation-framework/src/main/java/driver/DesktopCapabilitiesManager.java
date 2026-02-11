package driver;

import config.TestStackProperties;
import io.appium.java_client.mac.options.Mac2Options;

public class DesktopCapabilitiesManager {
    public Mac2Options setMacCapabilities() {
        String applicationPath = TestStackProperties.getApplicationPath();
        Mac2Options options = new Mac2Options()
                .setPlatformName("macOS")
                .setAutomationName("mac2")
                .setBundleId("com.apple.Pages");

        options.setCapability("deviceName", "Mac");
        options.setCapability("app", applicationPath);

        return options;
    }
}
