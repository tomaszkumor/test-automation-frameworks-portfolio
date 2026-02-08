package baseTest;

import config.TestStackProperties;
import driver.ApiProperties;
import driver.MobileProperties;
import driver.WebProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.SneakyThrows;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        log.info("FRAMEWORK: SELENIUM + APPIUM + RESTASSURED");
    }

    @BeforeMethod
    public void beforeMethod() {
        String platform = getPlatform();
        logAll(platform);

        switch (platform) {
            case "web" -> {
                getWebDriver().setDriver();
                getWebDriver().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
                prepareBrowserForTests();
                runBrowserWithUrl();
            }
            case "mobile" -> {
                getWebDriver().setDriver();
                getWebDriver().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
            }
            case "api" -> {}
            default -> throw new IllegalArgumentException("Unsupported platform");
        }
    }

    @SneakyThrows
    @BeforeClass
    public void beforeClass() {
        String platform = getPlatform();
        switch (platform) {
            case "mobile" -> Runtime.getRuntime().exec("adb shell pm clear org.wikipedia.alpha");
        }
    }

    @AfterMethod
    public void afterMethod() {
        closeDriver();
    }

    private void closeDriver() {
        String platform = getPlatform();
        switch (platform) {
            case "web" -> {
                if (!WebProperties.isDebugMode()) {
                    terminateDriver();
                    log.info("Driver has been terminated.");
                } else {
                    log.info("Driver has not been terminated due to debug mode.");
                }
            }
            case "mobile" -> {
                if (!MobileProperties.isDebugMode()) {
                    terminateApplication();
                    terminateDriver();
                    log.info("Mobile driver has been terminated.");
                } else {
                    log.info("Mobile driver has not been terminated due to debug mode.");
                }
            }
        }
    }

    private void terminateDriver() {
        getWebDriver().getDriver().quit();
    }

    private void terminateApplication() {
        String applicationName = getApplicationName();
        String mobileSystem = getMobileSystem();
        switch (mobileSystem) {
            case "android" -> ((AndroidDriver) getWebDriver().getDriver()).terminateApp(applicationName);
            case "ios" -> ((IOSDriver) getWebDriver().getDriver()).terminateApp(applicationName);
        }
    }

    private void runBrowserWithUrl() {
        String url = TestStackProperties.getWebUrl();
        getWebDriver().getDriver().get(url);

        log.info("Browser has been opened.");
    }

    private void prepareBrowserForTests() {
        String browserName = WebProperties.getBrowser();
        switch (browserName) {
            case "chrome" -> prepareChromeBrowserForTests();
            case "firefox" -> prepareFirefoxBrowserForTests();
            case "safari" -> prepareSafariBrowserForTests();
        }
    }

    private void prepareChromeBrowserForTests() {
        getWebDriver().getDriver().manage().deleteAllCookies();
    }

    private void prepareSafariBrowserForTests() {
        getWebDriver().getDriver().manage().window().maximize();
    }

    private void prepareFirefoxBrowserForTests() {
        getWebDriver().getDriver().manage().deleteAllCookies();
        getWebDriver().getDriver().manage().window().maximize();
    }

    private void logAll(String platform) {
        logAllForEachPlatform(platform);

        switch (platform) {
            case "web" -> logAllForWeb();
            case "mobile" -> logAllForMobile();
            case "api" -> logAllForApi();
            default -> throw new IllegalArgumentException("Unsupported platform");
        }
    }

    private void logAllForEachPlatform(String platform) {
        log.info("ENV: " + TestStackProperties.getEnvironment());
        log.info("PLATFORM: " + platform.toUpperCase());
    }

    private void logAllForWeb() {
        log.info("BROWSER: " + WebProperties.getBrowser().toUpperCase());
        log.info("HEADLESS MODE: " + WebProperties.isHeadlessMode());
        log.info("GRID MODE: " + WebProperties.isGridMode());
        log.info("DEBUG MODE: " + WebProperties.isDebugMode());
    }

    private void logAllForApi() {
        log.info("DEBUG MODE: " + ApiProperties.isDebugMode());
    }

    private void logAllForMobile() {
        String system = MobileProperties.getMobileSystem();
        log.info("SYSTEM: " + system);
        log.info("DEBUG MODE: " + MobileProperties.isDebugMode());

        switch (system) {
            case "android" -> log.info("ANDROID PATH: " + TestStackProperties.getAndroidPath());
            case "ios" -> log.info("IOS PATH: " + TestStackProperties.getIosPath());
        }
    }

    private String getPlatform() {
        return TestStackProperties.getPlatform();
    }

    private String getMobileSystem() {
        return MobileProperties.getMobileSystem();
    }

    private String getApplicationName() {
        return TestStackProperties.getApplicationPackage();
    }
}
