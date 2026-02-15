package models.pages.systemMenuBar.pagesModal;

import io.qameta.allure.Step;
import models.pages.settingsWindow.SettingsWindow;

import static utils.logger.Log4J.log;

public class PagesModal extends PagesModalLocators {
    public PagesModal() {
        check.isElementDisplayed(pagesModal, 15);
        log.info("Page modal is displayed.");
    }

    @Step("Click on settings button")
    public SettingsWindow clickOnSettingsButton() {
        click.clickOnVisibleElement(settingsButton, 15);
        log.info("Settings button has been clicked.");

        return new SettingsWindow();
    }
}
