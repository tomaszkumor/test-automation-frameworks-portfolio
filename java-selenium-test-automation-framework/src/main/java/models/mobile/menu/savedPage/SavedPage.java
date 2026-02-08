package models.mobile.menu.savedPage;

import io.qameta.allure.Step;
import models.mobile.menu.savedPage.readingListPage.ReadingListPage;
import models.mobile.menu.savedPage.readingListPage.settingsModal.SettingsModal;
import models.mobile.menu.savedPage.recommendedReadingListOnboardingPage.RecommendedReadingListOnboardingPage;
import models.mobile.menu.savedPage.specificResultsListPage.SpecificResultsListPage;

import static utils.logger.Log4J.log;

public class SavedPage extends SavedPageLocators {
    public SavedPage() {
        check.isElementDisplayed(filtersButton, 15);
        log.info("Saved page is displayed.");
    }

    @Step("Tap on specific saved results list")
    public SpecificResultsListPage tapOnSpecificSavedResultsList() {
        mobile.tapOnElement(specificList, 15);
        log.info("Specific list button has been tapped.");

        return new SpecificResultsListPage();
    }

    @Step("Tap on get started button")
    public RecommendedReadingListOnboardingPage tapOnGetStartedButton() {
        mobile.tapOnElement(getStartedButton, 15);
        log.info("Get started button has been tapped.");

        return new RecommendedReadingListOnboardingPage();
    }

    @Step("Tap on discover list button")
    public ReadingListPage tapOnDiscoverListButton() {
        mobile.tapOnElement(discoverListButton, 15);
        log.info("Discover list button has been tapped.");

        return new ReadingListPage();
    }
}
