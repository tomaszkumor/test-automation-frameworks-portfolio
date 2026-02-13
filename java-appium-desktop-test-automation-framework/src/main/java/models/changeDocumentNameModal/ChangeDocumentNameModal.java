package models.changeDocumentNameModal;

import models.textEditor.TextEditor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class ChangeDocumentNameModal extends ChangeDocumentNameModalLocators {
    public ChangeDocumentNameModal() {
        check.isElementDisplayed(changeNameModal, 15);
        log.info("Change document name modal is displayed.");
    }

    public ChangeDocumentNameModal changeName(String expectedDocumentName) {
        checkDocumentNameBeforeEdit();
        changeDocumentName(expectedDocumentName);
        checkDocumentNameAfterEdit(expectedDocumentName);

        return this;
    }

    public TextEditor closeChangeNameModal() {
        Actions actions = new Actions(getWebDriver().getDriver());
        actions.sendKeys(Keys.ENTER).perform();

        log.info("Escape button has been clicked.");

        check.didElementDisappear(changeNameModal, 3);
        log.info("Change name modal is not displayed.");

        return new TextEditor();
    }

    private void changeDocumentName(String expectedDocumentName) {
        changeDocumentAttributeValue(documentNameInput, expectedDocumentName, "document name");
    }

    private void changeDocumentAttributeValue(WebElement element, String expectedValue, String inputName) {
        send.sendKeysToWebElementWithNoLeave(element, expectedValue);
        log.info("{} has been sent to {} input", expectedValue, inputName);
    }

    private void checkDocumentNameBeforeEdit() {
        checkDocumentName("bez nazwy", "before");
    }

    private void checkDocumentNameAfterEdit(String expectedDocumentName) {
        checkDocumentName(expectedDocumentName, "after");
    }

    private void checkDocumentName(String expectedDocumentName, String stage) {
        String actualDocumentName = get.getValueFromElement(documentNameInput);

        assertThat(actualDocumentName)
                .as("Document name {} edit check.", stage)
                .isEqualTo(expectedDocumentName);

        log.info("Document name {} edit meets expected value.", stage);
    }
}