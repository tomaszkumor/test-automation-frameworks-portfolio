package listeners;

import driver.BaseDriver;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.*;

import static utils.logger.Log4J.log;
import static utils.screenshot.Screenshot.attachScreenshot;

public class DriverListener implements ITestListener, IInvokedMethodListener, WebDriverListener {
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            try {
                attachScreenshot();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void beforeClick(WebElement element) {
        try {
            attachScreenshot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebDriverListener.super.beforeClick(element);
    }

    @Override
    public void afterClick(WebElement element) {
        try {
            attachScreenshot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebDriverListener.super.afterClick(element);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            attachScreenshot();
            log.info("FAILURE");
            LogEntries les = BaseDriver.getWebDriver().getDriver().manage().logs().get(LogType.PERFORMANCE);

            for (LogEntry le : les) {
                JSONObject json = new JSONObject(le.getMessage());
                JSONObject json2 = json.getJSONObject("message");
                JSONObject json3 = json2.getJSONObject("params");

                try {
                    if ((int) json3.get("statusCode") < 600 && (int) json3.get("statusCode") > 399) {
                        log.info(json);
                    }
                } catch (Exception e) {
                }
            }

            log.info("Count: " + les.getAll().size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
