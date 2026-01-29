package models.mobile.menu.savedPage.readingListPage.settingsModal;

import io.qameta.allure.Step;
import models.mobile.menu.savedPage.recommendedReadingListSettingsPage.RecommendedReadingListSettingsPage;

import static utils.logger.Log4J.log;

public class SettingsModal extends SettingsModalLocators {
    public SettingsModal() {
        check.isElementDisplayed(customizeButton, 15);
        log.info("Settings modal is displayed.");
    }

    @Step("Tap on customize button")
    public RecommendedReadingListSettingsPage tapOnCustomizeButton() {
        mobile.tapOnElement(customizeButton, 15);
        log.info("Customize button has been tapped.");

        return new RecommendedReadingListSettingsPage();
    }
}
