package models.mobile.menu.savedPage.readingListPage;

import io.qameta.allure.Step;
import utils.tipKiller.TipKiller;
import models.mobile.menu.savedPage.readingListPage.settingsModal.SettingsModal;

import static utils.logger.Log4J.log;

public class ReadingListPage extends ReadingListPageLocators {
    public ReadingListPage() {
        closeTip();
        check.isElementDisplayed(bellButton, 15);
        log.info("Reading list page is displayed.");
    }

    @Step("Tap on settings button")
    public SettingsModal tapOnSettingsButton() {
        mobile.tapOnElement(settingsButton, 15);
        log.info("Settings button has been tapped.");

        return new SettingsModal();
    }

    private void closeTip() {
        TipKiller.closeSpecificSearchResultPageTip(closeTip);
    }
}
