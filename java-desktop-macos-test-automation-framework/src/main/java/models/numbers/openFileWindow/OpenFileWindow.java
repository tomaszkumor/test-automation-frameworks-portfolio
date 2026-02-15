package models.numbers.openFileWindow;

import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Step;
import models.numbers.selectThemeWindow.SelectThemeWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class OpenFileWindow extends OpenFileWindowLocators {
    public OpenFileWindow() {
        check.isElementDisplayed(openFileWindow, 15);
        log.info("Open file window has been displayed.");
    }

    @Step("Click on new document button")
    public SelectThemeWindow clickOnNewDocumentButton() {
        click.clickOnElement(newDocumentButton, 15);
        log.info("New document button has been clicked.");

        return new SelectThemeWindow();
    }

    @Step("Delete file if necessary")
    public OpenFileWindow deleteFileIfNecessary(DesktopModel desktopModel) {
        String fileName = getExpectedFileNameFromDataProvider(desktopModel);

        boolean isPresent = checkIfFileIsPresent(fileName, true);
        if (isPresent) {
            contextClickOnFileToRemove(fileName);
            clickOnRemoveButton();
            checkIfFileIsPresent(fileName, false);
        }

        return this;
    }

    private String getExpectedFileNameFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getFileName();
    }

    private boolean checkIfFileIsPresent(String fileName, boolean shouldBePresent) {
        By fileLocator = getFileLocator(fileName);

        if(shouldBePresent) {
            return check.isNumberOfElementsGreaterThan(fileLocator, 0, 50, 3);
        } else {
            return check.isNumberOfElementsEqualTo(fileLocator, 0, 50, 3);
        }
    }

    private void contextClickOnFileToRemove(String fileName) {
        By fileLocator = getFileLocator(fileName);
        WebElement fileToRemove = getWebDriver().getDriver().findElement(fileLocator);

        click.rightClickOnElement(fileToRemove, 15);
        log.info("File to remove has been context clicked.");
    }

    private void clickOnRemoveButton() {
        click.clickOnVisibleElement(removeButton, 15);
        log.info("Remove button has been clicked.");
    }

    private By getFileLocator(String fileName) {
        return By.xpath("//XCUIElementTypeImage[@label = '" + fileName + "']");
    }
}
