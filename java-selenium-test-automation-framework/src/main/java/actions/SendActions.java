package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.BaseDriver.getWebDriver;

public class SendActions extends BaseActions {
    @SneakyThrows
    public void sendKeysToWebElement(WebElement element, String text) {
        new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));

        try {
            element.clear();
            element.sendKeys(text);
            element.sendKeys(Keys.TAB);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void sendKeysToWebElementWithNoLeave(WebElement element, String text) {
        new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));

        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void sendKeysToInvisibleWebElement(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void sendKeysToWebElementInShadowRoot(String cssSelector, String text) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.id("mc-aoc"))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(elementInShadowRoot));

            elementInShadowRoot.clear();
            elementInShadowRoot.sendKeys(text);
            elementInShadowRoot.sendKeys(Keys.TAB);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to element: " + e.getMessage());
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    @SneakyThrows
    public void sendKeysToWebElementInShadowRoot(String cssSelector, String shadowRootContainer, String text) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(elementInShadowRoot));

            elementInShadowRoot.clear();
            elementInShadowRoot.sendKeys(text);
            elementInShadowRoot.sendKeys(Keys.TAB);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to element: " + e.getMessage());
        } finally {
            setDefaultImplicitlyWait();
        }
    }
}
