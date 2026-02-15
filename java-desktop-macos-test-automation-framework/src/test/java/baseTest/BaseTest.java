package baseTest;

import config.TestStackProperties;
import driver.DesktopCapabilitiesManager;
import driver.DesktopProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.Duration;

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

    @AfterMethod()
    public void afterMethod() {
        try {
            if (!DesktopProperties.isDebugMode()) {
                sendCommandQUsingAppleScript();
            }

            killWebDriverAgent();
        } catch (Exception e) {
            log.warn("Problem during afterMethod cleanup", e);
        } finally {
            terminateDriver();
        }
    }

    private void killWebDriverAgent() {
        try {
            new ProcessBuilder("pkill", "-f", "WebDriverAgentMac")
                    .inheritIO()
                    .start()
                    .waitFor();
            log.info("All WebDriverAgentMac processes killed successfully.");
        } catch (IOException | InterruptedException e) {
            log.warn("Failed to kill WebDriverAgentMac processes", e);
        }
    }

    public void sendCommandQUsingAppleScript() {
        String bundleId = getBundleId();
        String script = "tell application id \"" + bundleId + "\" to quit";

        try {
            new ProcessBuilder("osascript", "-e", script)
                    .inheritIO()
                    .start()
                    .waitFor();

            log.info("Close script has been executed.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void terminateDriver() {
        if (getWebDriver().getDriver() != null) {
            getWebDriver().getDriver().quit();
            log.info("Mac2 driver has been terminated.");
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
        return DesktopCapabilitiesManager.setApplicationName().toUpperCase();
    }

    private boolean isHeadlessMode() {
        return DesktopProperties.isDebugMode();
    }

    private String getBundleId() {
        return DesktopCapabilitiesManager.setApplicationBundleId().toUpperCase();
    }
}