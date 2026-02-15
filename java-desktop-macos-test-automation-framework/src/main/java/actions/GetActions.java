package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.BaseDriver.getWebDriver;

public class GetActions extends BaseActions {
    @SneakyThrows
    public String getTextFromElement(WebElement element) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            throw new Exception("Unable to get text from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getTextFromElement(By locator) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
        } catch (Exception e) {
            throw new Exception("Unable to get text from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getValueFromElement(WebElement element) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getAttributeFromElement(WebElement element, String attributeName) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element)).getAttribute(attributeName);
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getValueFromElement(WebElement element, String attribute) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getValueFromElement(By locator) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("value");
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getValueFromElement(By locator, String attribute) {
        try {
            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attribute);
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public WebElement getWebElementIfElementIsPresentByLocator(By locator, int duration) {
        return getWebElementIfElementIsPresentByLocator(locator, 500, duration);
    }

    @SneakyThrows
    public WebElement getWebElementIfElementIsPresentByLocator(By locator, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new Exception("Unable to get WebElement located as '" + locator + "': " + e.getMessage());
        } finally {
            setDefaultImplicitlyWait();
        }
    }
}
