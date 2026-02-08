package baseTest;

import config.TestStackProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import playwrightFactory.ApiProperties;
import playwrightFactory.WebProperties;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;
import static utils.logger.Log4J.log;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web" -> {
                getPlaywrightInstance().setPlaywright();
                getPlaywrightInstance().setBrowser();
            }
            case "api" -> {
                getPlaywrightInstance().setPlaywright();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        logAll();
        runBrowser();
    }

    @AfterMethod
    public void afterMethod() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web" -> {
                if (WebProperties.isDebugMode()) {
                    getPlaywrightInstance().getPage().pause();
                } else {
                    getPlaywrightInstance().getPage().close();
                    getPlaywrightInstance().getBrowserContext().close();
                    getPlaywrightInstance().unloadPage();
                    getPlaywrightInstance().unloadBrowserContext();
                }
            }
        }
    }

    @AfterSuite
    public void afterSuite() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web" -> {
                if (!WebProperties.isDebugMode()) {
                    getPlaywrightInstance().getBrowser().close();
                    getPlaywrightInstance().getPlaywright().close();
                    getPlaywrightInstance().unloadBrowser();
                    getPlaywrightInstance().unloadLaunchOptions();
                    getPlaywrightInstance().unloadPlaywright();
                }
            }
            case "api" -> {
                getPlaywrightInstance().getPlaywright().close();
                getPlaywrightInstance().unloadPlaywright();
            }
        }
    }

    public void runBrowser() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web" -> {
                getPlaywrightInstance().setBrowserContext();
                getPlaywrightInstance().setPage();
                getPlaywrightInstance().goUrl();
            }
        }
    }

    private void logAll() {
        String platform = getPlatform();
        logAllForEachPlatform(platform);

        switch (platform) {
            case "web" -> logAllForWeb();
            case "api" -> logAllForApi();
        }
    }

    private void logAllForEachPlatform(String platform) {
        log.info("ENV: " + TestStackProperties.getEnvironment());
        log.info("FRAMEWORK: " + platform.toUpperCase());
    }

    private void logAllForWeb() {
        log.info("ENGINE: " + WebProperties.getEngine().toUpperCase());
        log.info("HEADLESS MODE: " + WebProperties.isHeadlessMode());
        log.info("REMOTE MODE: " + WebProperties.isRemoteMode());
        log.info("DEBUG MODE: " + WebProperties.isDebugMode());
    }

    private void logAllForApi() {
        log.info("DEBUG MODE: " + ApiProperties.isDebugMode());
    }

    private String getPlatform() {
        return TestStackProperties.getPlatform();
    }
}
