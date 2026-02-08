package models.mobile.menu.moreModal;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.settingsPage.SettingsPage;

import static utils.logger.Log4J.log;

public class MoreModal extends MoreModalLocators {
    public MoreModal() {
        check.isElementDisplayed(moreModalContainer, 15);
        log.info("More modal window is displayed.");
    }

    @Step("Tap on settings button")
    public SettingsPage tapOnSettingsButton() {
        mobile.tapOnElement(settingsButton, 15);
        log.info("Settings button has been tapped.");

        return new SettingsPage();
    }
}
