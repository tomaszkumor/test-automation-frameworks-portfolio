package driver;

import io.appium.java_client.mac.Mac2Driver;
import io.appium.java_client.mac.options.Mac2Options;
import lombok.SneakyThrows;

import java.net.URL;

import static utils.logger.Log4J.log;

public class BaseDriver {
    private static BaseDriver webDriverInstance;
    private static final ThreadLocal<Mac2Driver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static BaseDriver getWebDriver() {
        if (webDriverInstance == null) {
            webDriverInstance = new BaseDriver();
        }

        return webDriverInstance;
    }

    @SneakyThrows
    public BaseDriver setDriver() {
        Mac2Options capabilitiesMac = new DesktopCapabilitiesManager().setMacCapabilities();
        Mac2Driver mac2Driver = new Mac2Driver(new URL("http://127.0.0.1:4723"), capabilitiesMac);

        DRIVER_THREAD_LOCAL.set(mac2Driver);
        log.info("mac2 driver on local environment has been set.");

        return this;
    }

    public Mac2Driver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public void unload() {
        DRIVER_THREAD_LOCAL.remove();
    }
}
