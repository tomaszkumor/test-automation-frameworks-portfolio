package driver;

import config.TestStackProperties;
import io.appium.java_client.mac.options.Mac2Options;

import java.net.ServerSocket;

public class DesktopCapabilitiesManager {
    public Mac2Options setMacCapabilities() {
        String applicationBundleId = setApplicationBundleId();
        int freePort = getFreePort();
        Mac2Options options = new Mac2Options()
                .setPlatformName("macOS")
                .setAutomationName("mac2")
                .setBundleId(applicationBundleId);

        options.setCapability("deviceName", "Mac");
        options.setCapability("systemPort", freePort);
        options.setCapability("newCommandTimeout", 120);

        return options;
    }

    public static String setApplicationBundleId() {
        String applicationBundleId = DesktopProperties.getApplication().toLowerCase();

        return switch(applicationBundleId) {
            case "pages" -> TestStackProperties.getBundleIdPages();
            case "numbers" -> TestStackProperties.getBundleIdNumbers();
            default -> null;
        };
    }

    public static String setApplicationName() {
        String applicationBundleId = DesktopProperties.getApplication().toLowerCase();

        return switch(applicationBundleId) {
            case "pages" -> TestStackProperties.getApplicationNamePages();
            case "numbers" -> TestStackProperties.getApplicationNameNumbers();
            default -> null;
        };
    }

    private int getFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        } catch (Exception e) {
            throw new RuntimeException("No free port", e);
        }
    }
}
