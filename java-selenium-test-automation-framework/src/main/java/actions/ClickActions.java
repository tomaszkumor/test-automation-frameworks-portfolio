package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.BaseDriver.getWebDriver;
import static utils.screenshot.Screenshot.attachScreenshot;

public class ClickActions extends BaseActions {
    @SneakyThrows
    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            throw new Exception("Unable to click on element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void rightClick(WebElement element) {
        try {
            Actions actions = new Actions(getWebDriver().getDriver());
            actions.contextClick(element).perform();
        } catch (Exception e) {
            throw new Exception("Unable to right click on element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void doubleClickOnElement(WebElement element) {
        try {
            Actions actions = new Actions(getWebDriver().getDriver());
            actions.doubleClick(element).perform();
        } catch (Exception e) {
            throw new Exception("Unable to double click on element: " + e.getMessage());
        }
    }

    public void clickOnElement(WebElement element, int duration) {
        click(waitForElementToBeClickable(element, duration));
    }

    public void clickOnVisibleElement(WebElement element, int duration) {
        click(waitForVisibilityOfElement(element, duration));
    }

    public void clickOnElementAndSendKeys(WebElement element, String text, int duration) {
        click(waitForVisibilityOfElement(element, duration));
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }

    public void clickOnEnabledElement(WebElement element, int duration) {
        click(waitForElementToBeEnabled(element, duration));
    }

    public void clickOnEnabledElementInShadowRoot(String cssSelector, int duration) {
        click(waitForElementToBeEnabledInShadowRoot(cssSelector, duration));
    }

    public void clickOnEnabledElementInShadowRoot(String cssSelector, String shadowRootContainer, int duration) {
        click(waitForElementToBeEnabledInShadowRoot(cssSelector, shadowRootContainer, duration));
    }

    public void rightClickOnElement(WebElement element, int duration) {
        rightClick(waitForElementToBeClickable(element, duration));
    }

    @SneakyThrows
    private WebElement waitForVisibilityOfElement(WebElement element, int duration) {
        try {
            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Unable to click on element: " + e.getMessage());
        }
    }

    @SneakyThrows
    private WebElement waitForElementToBeClickable(WebElement element, int duration) {
        try {
            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(ExpectedConditions.elementToBeClickable(element));
            return element;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Unable to click on element: " + e.getMessage());
        }
    }

    @SneakyThrows
    private WebElement waitForElementToBeEnabled(WebElement element, int duration) {
        try {
            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(driver -> element.isEnabled());
            return element;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Element is disabled: " + e.getMessage());
        }
    }

    @SneakyThrows
    private WebElement waitForElementToBeEnabledInShadowRoot(String cssSelector, int duration) {
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.id("mc-aoc"))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(driver -> elementInShadowRoot.isEnabled());

            return elementInShadowRoot;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Element is disabled: " + e.getMessage());
        }
    }

    @SneakyThrows
    private WebElement waitForElementToBeEnabledInShadowRoot(String cssSelector, String shadowRootContainer, int duration) {
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(driver -> elementInShadowRoot.isEnabled());

            return elementInShadowRoot;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Element is disabled: " + e.getMessage());
        }
    }
}
