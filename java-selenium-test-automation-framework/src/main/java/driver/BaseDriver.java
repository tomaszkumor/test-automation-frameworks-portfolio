package driver;

import config.TestStackProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import listeners.DriverListener;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

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
            case "web" -> runDesktopTests();
            case "mobile" -> runMobileTests();
        }

        return this;
    }

    private void runMobileTests() {
        String mobileSystem = MobileProperties.getMobileSystem();
        switch (mobileSystem) {
            case "android" -> runMobileTestsOnAndroid();
            case "ios" -> runMobileTestsOnIOS();
        }
    }

    private void runDesktopTests() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web" -> {
                if (WebProperties.isGridMode()) {
                    runDesktopTestsRemotely();
                } else {
                    runDesktopTestsLocally();
                }
            }
        }
    }

    @SneakyThrows
    private void runMobileTestsOnAndroid() {
        UiAutomator2Options capabilitiesAndroid = new MobileCapabilitiesManager().setAndroidCapabilities();
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilitiesAndroid);

        DRIVER_THREAD_LOCAL.set(androidDriver);
        log.info("Android driver on local environment has been set.");
    }

    @SneakyThrows
    private void runMobileTestsOnIOS() {
        XCUITestOptions capabilitiesIOS = new MobileCapabilitiesManager().setIOSCapabilities();
        IOSDriver iOSDriver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilitiesIOS);

        DRIVER_THREAD_LOCAL.set(iOSDriver);
        log.info("iOS driver on local environment has been set.");
    }

    @SneakyThrows
    public void runDesktopTestsRemotely() {
        String browser = WebProperties.getBrowser();
        Capabilities capabilities = switch (browser) {
            case "chrome" -> new WebCapabilitiesManager().setChromeOptionsRemote();
            case "firefox" -> new WebCapabilitiesManager().setFirefoxOptionsRemote();
            case "safari" -> new WebCapabilitiesManager().setSafariOptionsRemote();
            default -> throw new IllegalArgumentException("Unsupported browser");
        };
//
        DriverListener listener = new DriverListener();
        RemoteWebDriver originalDriver = new RemoteWebDriver(new URL("xxx"), capabilities);

        DRIVER_THREAD_LOCAL.set(new EventFiringDecorator(listener).decorate(originalDriver));
        log.info("Chrome driver on GRID has been set.");
    }

    public void runDesktopTestsLocally() {
        String browser = WebProperties.getBrowser();
        switch (browser) {
            case "chrome" -> {
                DRIVER_THREAD_LOCAL.set(new ChromeDriver(new WebCapabilitiesManager().setChromeOptions()));
                log.info("Chrome driver on local environment has been set.");
            }
            case "firefox" -> {
                DRIVER_THREAD_LOCAL.set(new FirefoxDriver(new WebCapabilitiesManager().setFirefoxOptions()));
                log.info("Firefox driver on local environment has been set.");
            }
            case "safari" -> {
                DRIVER_THREAD_LOCAL.set(new SafariDriver(new WebCapabilitiesManager().setSafariOptions()));
                log.info("Safari driver on local environment has been set.");
            }
        }


    }

    public WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public void unload() {
        DRIVER_THREAD_LOCAL.remove();
    }

    public static AndroidDriver getAndroidDriver() {
        return ((AndroidDriver) getWebDriver().getDriver());
    }

    public static IOSDriver getIOSDriver() {
        return ((IOSDriver) getWebDriver().getDriver());
    }
}
