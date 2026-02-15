package models.numbers.systemMenuBar.pagesModal;

import io.qameta.allure.Step;
import models.numbers.settingsWindow.SettingsWindow;

import static utils.logger.Log4J.log;

public class NumbersModal extends NumbersModalLocators {
    public NumbersModal() {
        check.isElementDisplayed(numbersModal, 15);
        log.info("Numbers modal is displayed.");
    }

    @Step("Click on settings button")
    public SettingsWindow clickOnSettingsButton() {
        click.clickOnVisibleElement(settingsButton, 15);
        log.info("Settings button has been clicked.");

        return new SettingsWindow();
    }
}
