package actions;

import lombok.SneakyThrows;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
}
