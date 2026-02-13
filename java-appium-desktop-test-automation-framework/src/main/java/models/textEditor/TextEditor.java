package models.textEditor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class TextEditor extends TextEditorLocators {
    public TextEditor() {
        check.isElementDisplayed(toolbar, 15);
        log.info("New document window has been displayed.");
    }

    public TextEditor clickOnDocumentNameLabel() {
        By locator = By.xpath("//XCUIElementTypeWindow/XCUIElementTypeStaticText");
check.isNumberOfElementsEqualTo(locator, 1 ,50, 15);
        WebElement element = getWebDriver().getDriver().findElement(locator);
        Actions actions = new Actions(getWebDriver().getDriver());
        actions.moveToElement(element)
                .pause(Duration.ofSeconds(5))
                .click()
                .sendKeys(Keys.ESCAPE)
                .perform();

        log.info("Document name label has been clicked.");

        return this;
    }

    public TextEditor checkDocumentNameBeforeEdit() {
        checkDocumentName("bez nazwy", "before");

        return this;
    }

    public TextEditor checkDocumentNameAfterEdit(String expectedDocumentName) {
        checkDocumentName(expectedDocumentName, "after");

        return this;
    }

    private void checkDocumentName(String expectedDocumentName, String stage) {
        String actualDocumentName = get.getValueFromElement(documentNameLabel);
        assertThat(actualDocumentName)
                .as("Document name before edit check")
                .isEqualTo(expectedDocumentName);

        log.info("Document name {} edit meets expected value.", stage);
    }
}
