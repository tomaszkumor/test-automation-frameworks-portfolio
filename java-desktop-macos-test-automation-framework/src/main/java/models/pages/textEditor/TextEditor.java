package models.pages.textEditor;

import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Step;
import models.pages.scaleModal.ScaleModal;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class TextEditor extends TextEditorLocators {
    public TextEditor() {
        check.isElementDisplayed(toolbar, 15);
        log.info("New document window has been displayed.");
    }

    public TextEditor hideDocumentOptions() {
        click.clickOnVisibleElement(documentOptionsButton, 15);
        log.info("Hide document options button has been clicked.");

        By documentOptionsBarLocator = By.xpath("//XCUIElementTypeRadioButton[@label = 'Format' and @value = '0']");
        check.isNumberOfElementsEqualTo(documentOptionsBarLocator, 1, 50, 5);
        log.info("Document options bar is gone.");

        return this;
    }

    @Step("Click on scale button")
    public ScaleModal clickOnScaleButton() {
        click.clickOnVisibleElement(scaleButton, 15);
        log.info("Scale button has been clicked.");

        return new ScaleModal();
    }

    @Step("Check scale")
    public TextEditor checkScale(DesktopModel desktopModel) {
        String expectedScale = getExpectedDocumentScaleFromDataProvider(desktopModel);
        String actualScale = get.getAttributeFromElement(scaleButton, "title");

        assertThat(actualScale)
                .as("Scale check after edit")
                .isEqualTo(expectedScale);

        log.info("Actual scale meets expected value.");

        return this;
    }

    @Step("Click on document name label")
    public TextEditor clickOnDocumentNameLabel() {
        check.isElementDisplayed(documentNameLabel, 15);
        log.info("Document label has been displayed.");

        int elementX = documentNameLabel.getLocation().getX();
        int elementY = documentNameLabel.getLocation().getY();
        int elementWidth = documentNameLabel.getSize().getWidth();
        int elementHeight = documentNameLabel.getSize().getHeight();

        int centerX = elementX + (int) (0.2 * elementWidth);
        int centerY = elementY + elementHeight / 2;

        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "mouse");
        Sequence sequence = new Sequence(mouse, 0);

        sequence.addAction(mouse.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, centerY));
        sequence.addAction(new Pause(mouse, Duration.ofSeconds(5)));
        sequence.addAction(mouse.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(mouse.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getWebDriver().getDriver().perform(Collections.singletonList(sequence));

        log.info("Document name label has been clicked.");

        return this;
    }

    @Step("Check document name before edit")
    public TextEditor checkDocumentNameBeforeEdit() {
        checkDocumentName("bez nazwy", "before");

        return this;
    }

    @Step("Check document name after edit")
    public TextEditor checkDocumentNameAfterEdit(DesktopModel desktopModel) {
        String expectedDocumentName = getExpectedFileNameFromDataProvider(desktopModel);
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

    private String getExpectedFileNameFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getFileName();
    }

    private String getExpectedDocumentScaleFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getDocumentScale();
    }
}
