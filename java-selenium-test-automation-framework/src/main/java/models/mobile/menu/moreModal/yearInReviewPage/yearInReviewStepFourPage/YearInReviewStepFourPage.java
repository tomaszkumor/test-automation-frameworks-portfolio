package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepFourPage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepFivePage.YearInReviewStepFivePage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class YearInReviewStepFourPage extends YearInReviewStepFourPageLocators {
    public YearInReviewStepFourPage() {
        check.isElementDisplayed(donateButton, 15);
        log.info("Year in review step four page is displayed.");
    }

    @Step("Check description")
    public YearInReviewStepFourPage checkDescription() {
        By titleLocator = By.xpath("(//android.widget.ScrollView/android.widget.TextView)[1]");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Editors on the official Wikipedia apps made 78 952 894 edits";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "The heart and soul of Wikipedia is our global community of volunteer contributors, donors, and billions of readers like yourself – all united to share unlimited access to reliable information.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public YearInReviewStepFivePage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new YearInReviewStepFivePage();
    }

    @Step("Swipe to year in review step five page")
    public YearInReviewStepFivePage swipeToYearInReviewStepFivePage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Year in review step five' page.");

        return new YearInReviewStepFivePage();
    }
}
