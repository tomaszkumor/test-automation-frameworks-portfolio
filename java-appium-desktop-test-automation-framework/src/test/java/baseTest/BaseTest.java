package baseTest;

import config.TestStackProperties;
import driver.DesktopProperties;
import io.appium.java_client.mac.Mac2Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.Map;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        log.info("FRAMEWORK: APPIUM");
    }

    @BeforeMethod
    public void beforeMethod() {
        logAll();
        setDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            if (!DesktopProperties.isDebugMode()) {
                terminateApplication();
            }
        } catch (Exception e) {
            log.warn("Failed to terminate application", e);
        } finally {
            terminateDriver();
        }
    }


    private void terminateApplication() {
        String bundleId = getBundleId();

        if (getWebDriver().getDriver() instanceof Mac2Driver mac2Driver) {
            mac2Driver.executeScript(
                    "macos: terminateApp",
                    Map.of("bundleId", bundleId)
            );
        }
    }

    private void terminateDriver() {
        if (getWebDriver().getDriver() != null) {
            getWebDriver().getDriver().quit();
            log.info("mac driver has been terminated.");
        }
    }

    private void logAll() {
        log.info("ENV: " + getEnvironment());
        log.info("PLATFORM: " + getPlatform());
        log.info("APPLICATION: " + getApplicationName());
        log.info("DEBUG MODE: " + isHeadlessMode());
    }

    private void setDriver() {
        getWebDriver().setDriver();
        getWebDriver().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private String getPlatform() {
        return TestStackProperties.getPlatform().toUpperCase();
    }

    private String getEnvironment() {
        return TestStackProperties.getEnvironment().toUpperCase();
    }

    private String getApplicationName() {
        return TestStackProperties.getApplicationName().toUpperCase();
    }

    private boolean isHeadlessMode() {
        return DesktopProperties.isDebugMode();
    }

    private String getBundleId() {
        return TestStackProperties.getBundleId();
    }
}
