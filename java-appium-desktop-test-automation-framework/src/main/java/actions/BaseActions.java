package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public abstract class BaseActions {
    public void waitForPageLoaded(int duration) {
        WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForPageLoaded() {
        waitForPageLoaded(15);
    }

    public void setDefaultImplicitlyWait() {
        getWebDriver().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void implicitlyWaitChangeDuration(int duration) {
        getWebDriver().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
    }

    public void switchToNewTab(int index) {
        List<String> tabs = new ArrayList(getWebDriver().getDriver().getWindowHandles());
        if(tabs.size() > 1) {
            try {
                new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                        .until((ExpectedCondition<Boolean>) webDriver -> (tabs.size() == index));
            } catch(Exception e) {
                tabs.forEach(log::info);
            }
            getWebDriver().getDriver().switchTo().window(tabs.get(index));
        }
    }

    public void closeSecondTabAndGetBackToFirstTab() {
        List<String> tabs = new ArrayList(getWebDriver().getDriver().getWindowHandles());
        getWebDriver().getDriver().close();
        getWebDriver().getDriver().switchTo().window(tabs.get(0));
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) getWebDriver().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void refreshPage() {
        getWebDriver().getDriver().navigate().refresh();
    }
}
