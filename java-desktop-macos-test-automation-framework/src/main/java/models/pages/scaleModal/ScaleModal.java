package models.pages.scaleModal;

import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Step;
import models.pages.textEditor.TextEditor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class ScaleModal extends ScaleModalLocators {
    public ScaleModal() {
        check.isElementDisplayed(scaleModal, 15);
        log.info("Scale modal has been displayed.");
    }

    @Step("Select scale")
    public TextEditor selectScale(DesktopModel desktopModel) {
        String expectedScale = getExpectedDocumentScaleFromDataProvider(desktopModel);
        By scaleLocator = By.xpath("//XCUIElementTypeMenuButton[@label = 'Zoom']//XCUIElementTypeMenuItem[@title = '" + expectedScale + "']");
        check.isNumberOfElementsEqualTo(scaleLocator, 1, 50, 5);

        WebElement scaleButton = getWebDriver().getDriver().findElement(scaleLocator);
        click.clickOnVisibleElement(scaleButton, 15);
        log.info("{} scale button has been clicked.", expectedScale);

        return new TextEditor();
    }

    private String getExpectedDocumentScaleFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getDocumentScale();
    }
}
