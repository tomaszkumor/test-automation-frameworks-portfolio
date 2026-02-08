package actions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.customExpectedCondition.CustomExpectedCondition;

import java.time.Duration;
import java.util.List;

import static driver.BaseDriver.getWebDriver;

public class CheckActions extends BaseActions {
    public boolean isElementDisplayed(WebElement element, int duration) {
        return isElementDisplayed(element, 500, duration);
    }

    public boolean isElementDisplayed(WebElement element, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementDisplayedInShadowRoot(String cssSelector, int duration) {
        return isElementDisplayedInShadowRoot(cssSelector, 500, duration);
    }

    public boolean isElementDisplayedInShadowRoot(String cssSelector, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.id("mc-aoc"))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.visibilityOf(elementInShadowRoot));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementDisplayedInShadowRoot(String cssSelector, String shadowRootContainer, int duration) {
        return isElementDisplayedInShadowRoot(cssSelector, shadowRootContainer, 500, duration);
    }

    public boolean isElementDisplayedInShadowRoot(String cssSelector, String shadowRootContainer, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.visibilityOf(elementInShadowRoot));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isTextDisplayedInShadowRoot(String cssSelector, String shadowRootContainer, String text, int duration) {
        return isTextDisplayedInShadowRoot(cssSelector, shadowRootContainer, text, 500, duration);
    }

    public boolean isTextDisplayedInShadowRoot(String cssSelector, String shadowRootContainer, String text, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.textToBePresentInElement(elementInShadowRoot, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isTextDisplayed(WebElement element, String text, int duration) {
        return isTextDisplayed(element, text, 500, duration);
    }

    public boolean isTextDisplayed(WebElement element, String text, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementEnabled(WebElement element, int duration) {
        return isElementEnabled(element, 500, duration);
    }

    public boolean isElementEnabled(WebElement element, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementEnabledInShadowRoot(String cssSelector, int duration) {
        return isElementEnabledInShadowRoot(cssSelector, 500, duration);
    }

    public boolean isElementEnabledInShadowRoot(String cssSelector, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.id("mc-aoc"))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.elementToBeClickable(elementInShadowRoot));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementEnabledInShadowRoot(String cssSelector, String shadowRootContainer, int duration) {
        return isElementEnabledInShadowRoot(cssSelector, shadowRootContainer, 500, duration);
    }

    public boolean isElementEnabledInShadowRoot(String cssSelector, String shadowRootContainer, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            WebElement elementInShadowRoot = shadowRoot.findElement(By.cssSelector(cssSelector));

            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.elementToBeClickable(elementInShadowRoot));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementDisabled(WebElement element, int duration) {
        return isElementDisabled(element, 500, duration);
    }

    public boolean isElementDisabled(WebElement element, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean didElementDisappear(WebElement element, int duration) {
        return didElementDisappear(element, 500, duration);
    }

    public boolean didElementDisappear(WebElement element, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isAttributeEqualTo(WebElement element, String attribute, String expectedValue, int duration) {
        return isAttributeEqualTo(element, attribute, expectedValue, 500, duration);
    }

    public boolean isAttributeEqualTo(WebElement element, String attribute, String expectedValue, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.attributeToBe(element, attribute, expectedValue));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isAttributeGone(WebElement element, String attribute, String expectedValue, int duration) {
        return isAttributeGone(element, attribute, expectedValue, 500, duration);
    }

    public boolean isAttributeGone(WebElement element, String attribute, String expectedValue, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            return wait.until(d -> element.getAttribute(attribute) == expectedValue);
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isAttributePresent(WebElement element, String attribute, String expectedValueToBePresent, int duration) {
        return isAttributePresent(element, attribute, expectedValueToBePresent, 500, duration);
    }

    public boolean isAttributePresent(WebElement element, String attribute, String expectedValueToBePresent, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.attributeContains(element, attribute, expectedValueToBePresent));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isAttributeNotPresent(WebElement element, String attribute, String expectedValueToBePresent, int duration) {
        return isAttributeNotPresent(element, attribute, expectedValueToBePresent, 500, duration);
    }

    public boolean isAttributeNotPresent(WebElement element, String attribute, String expectedValueToBePresent, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(element, attribute, expectedValueToBePresent)));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isElementPresentByLocator(By locator, int duration) {
        return isElementPresentByLocator(locator, 500, duration);
    }

    public boolean isElementPresentByLocator(By locator, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean areElementPresentByLocator(By locator, int duration) {
        return areElementPresentByLocator(locator, 500, duration);
    }

    public boolean areElementPresentByLocator(By locator, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isNumberOfElementsGreaterThan(By locator, int numberOfElements, int duration) {
        return isNumberOfElementsGreaterThan(locator, numberOfElements, 500, duration);
    }

    public boolean isNumberOfElementsGreaterThan(By locator, int numberOfElements, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberOfElements));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isNumberOfElementsLessThan(By locator, int numberOfElements, int duration) {
        return isNumberOfElementsLessThan(locator, numberOfElements, 500, duration);
    }

    public boolean isNumberOfElementsLessThan(By locator, int numberOfElements, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberOfElements));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isNumberOfElementsEqualTo(By locator, int numberOfElements, int duration) {
        return isNumberOfElementsEqualTo(locator, numberOfElements, 500, duration);
    }

    public boolean isNumberOfElementsEqualTo(By locator, int numberOfElements, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberOfElements));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isNumberOfElementsEqualToInShadowRoot(String cssSelector, String shadowRootContainer, int numberOfElements) {
        return isNumberOfElementsEqualToInShadowRoot(cssSelector, shadowRootContainer, numberOfElements, 15);
    }

    public boolean isNumberOfElementsEqualToInShadowRoot(String cssSelector, String shadowRootContainer, int numberOfElements, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebElement host = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(getWebDriver().getDriver().findElement(By.tagName(shadowRootContainer))));
            JavascriptExecutor jse = (JavascriptExecutor) getWebDriver().getDriver();
            SearchContext shadowRoot = (SearchContext) jse.executeScript("return arguments[0].shadowRoot", host);
            List<WebElement> elementsInShadowRoot = shadowRoot.findElements(By.cssSelector(cssSelector));

            return elementsInShadowRoot.size() == numberOfElements;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean doesUrlContain(String url, int duration) {
        return doesUrlContain(url, 500, duration);
    }

    public boolean doesUrlContain(String url, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.urlContains(url));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isNumberOfWindowsEqualTo(int expectedNumberOfWindows, int duration) {
        return isNumberOfWindowsEqualTo(expectedNumberOfWindows, 500, duration);
    }

    public boolean isNumberOfWindowsEqualTo(int expectedNumberOfWindows, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until((ExpectedCondition<Boolean>) driver -> driver.getWindowHandles().size() == expectedNumberOfWindows);

            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean isUrlEqualTo(String url, int duration) {
        return isUrlEqualTo(url, 500, duration);
    }

    public boolean isUrlEqualTo(String url, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.urlToBe(url));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }

    public boolean didElementStopMoving(WebElement element, int interval, int duration) {
        implicitlyWaitChangeDuration(0);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(CustomExpectedCondition.elementStopsMoving(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            setDefaultImplicitlyWait();
        }
    }
}