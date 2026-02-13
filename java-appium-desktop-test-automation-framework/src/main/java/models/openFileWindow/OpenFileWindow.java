package models.openFileWindow;

import models.selectThemeWindow.SelectThemeWindow;

import static utils.logger.Log4J.log;

public class OpenFileWindow extends OpenFileWindowLocators {
    public OpenFileWindow() {
        check.isElementDisplayed(openFileWindow, 15);
        log.info("Open file window has been displayed.");
    }

    public SelectThemeWindow clickOnNewDocumentButton() {
        click.clickOnElement(newDocumentButton, 15);
        log.info("New document button has been clicked.");

        return new SelectThemeWindow();
    }
}
