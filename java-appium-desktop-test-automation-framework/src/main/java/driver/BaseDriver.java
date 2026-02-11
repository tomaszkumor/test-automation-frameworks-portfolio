package driver;

import config.TestStackProperties;
import io.appium.java_client.mac.Mac2Driver;
import io.appium.java_client.mac.options.Mac2Options;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static utils.logger.Log4J.log;

public class BaseDriver {
    private static BaseDriver webDriverInstance;
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static BaseDriver getWebDriver() {
        if (webDriverInstance == null) {
            webDriverInstance = new BaseDriver();
        }

        return webDriverInstance;
    }

    public BaseDriver setDriver() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "macos" -> runDesktopTestsOnMacOS();
        }

        return this;
    }

    @SneakyThrows
    private void runDesktopTestsOnMacOS() {
        Mac2Options capabilitiesMac = new DesktopCapabilitiesManager().setMacCapabilities();
        Mac2Driver mac2Driver = new Mac2Driver(new URL("http://127.0.0.1:4723"), capabilitiesMac);

        DRIVER_THREAD_LOCAL.set(mac2Driver);
        log.info("mac2 driver on local environment has been set.");
    }

    public WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public void unload() {
        DRIVER_THREAD_LOCAL.remove();
    }

    public static Mac2Driver getMac2Driver() {
        return ((Mac2Driver) getWebDriver().getDriver());
    }
}
