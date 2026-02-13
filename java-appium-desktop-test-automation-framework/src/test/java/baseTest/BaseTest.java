package baseTest;

import config.TestStackProperties;
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
//TODO: Wprowadz takie zabezpieczenie w before class ze przed testem podjeta jest proba ewentualnego zamkniecia aplikacji jesli jakas instancja jest jeszcze uruchomiona
    @BeforeMethod
    public void beforeMethod() {
        logAll();
        setDriver();
    }
//todo: zrob zabezpieczenie przed tym (uruchamiam test i dostaje to): Could not start a new session. Response code 500. Message: The port #10100 at 127.0.0.1 is busy
    @AfterMethod()
    public void afterMethod() {
        if (DesktopProperties.isDebugMode()) {
            return;
        }

        try {
            sendCommandQUsingAppleScript();
        } catch (Exception e) {
            log.warn("Failed to terminate application", e);
        } finally {
            terminateDriver();
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
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