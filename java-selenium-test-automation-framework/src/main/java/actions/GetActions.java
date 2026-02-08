package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
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
    public String getValueFromElementInShadowRoot(String cssSelector) {
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.id("mc-aoc"))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(elementInShadowRoot)).getAttribute("value");
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getValueFromElementInShadowRoot(String cssSelector, String shadowRootContainer) {
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(elementInShadowRoot)).getAttribute("value");
        } catch (Exception e) {
            throw new Exception("Unable to get value from an element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getTextFromElementInShadowRoot(String cssSelector, String shadowRootContainer) {
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            return new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(elementInShadowRoot)).getText();
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

    public String getCurrentUrl() {
        return getWebDriver().getDriver().getCurrentUrl();
    }
}
