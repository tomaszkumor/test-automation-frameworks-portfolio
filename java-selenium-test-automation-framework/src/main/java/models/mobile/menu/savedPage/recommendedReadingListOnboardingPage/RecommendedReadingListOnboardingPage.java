package models.mobile.menu.savedPage.recommendedReadingListOnboardingPage;

import io.qameta.allure.Step;
import models.mobile.menu.savedPage.readingListPage.ReadingListPage;

import static utils.logger.Log4J.log;

public class RecommendedReadingListOnboardingPage extends RecommendedReadingListOnboardingPageLocators {
    public RecommendedReadingListOnboardingPage() {
        check.isElementDisplayed(chooseBasedOnSavedArticlesButton, 15);
        log.info("Recommended reading list onboarding page is displayed.");
    }

    @Step("Tap on choose based on saved articles button")
    public RecommendedReadingListOnboardingPage tapOnChooseBasedOnSavedArticlesButton() {
        mobile.tapOnElement(chooseBasedOnSavedArticlesButton, 15);
        log.info("Choose based on saved articles button has been tapped.");

        return this;
    }

    @Step("Tap on next button")
    public ReadingListPage tapOnNextButton() {
        mobile.tapOnElement(nextButton, 15);
        log.info("Next button has been tapped.");

        return new ReadingListPage();
    }
}
