package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewOnboardingPage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.introModal.IntroModal;

import static utils.logger.Log4J.log;

public class YearInReviewOnboardingPage extends YearInReviewOnboardingPageLocators {
    public YearInReviewOnboardingPage() {
        check.isElementDisplayed(getStartedButton, 15);
        log.info("Year in review onboarding page is displayed");
    }

    @Step("Tap on get started button")
    public IntroModal tapOnGetStartedButton() {
        mobile.tapOnElement(getStartedButton, 15);
        log.info("Get started button has been tapped.");

        return new IntroModal();
    }
}
