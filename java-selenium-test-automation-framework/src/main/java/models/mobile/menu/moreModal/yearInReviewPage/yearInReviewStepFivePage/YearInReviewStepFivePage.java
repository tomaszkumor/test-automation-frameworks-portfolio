package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepFivePage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepSixPage.YearInReviewStepSixPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class YearInReviewStepFivePage extends YearInReviewStepFivePageLocators {
    public YearInReviewStepFivePage() {
        check.isElementDisplayed(donateButton, 15);
        log.info("Year in review step five page is displayed.");
    }

    @Step("Check description")
    public YearInReviewStepFivePage checkDescription() {
        By titleLocator = By.xpath("(//android.widget.ScrollView/android.widget.TextView)[1]");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Wikipedia was edited 324 times per minute";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "Articles are collaboratively created and improved using reliable sources. All of us have knowledge to share, learn how to participate.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public YearInReviewStepSixPage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new YearInReviewStepSixPage();
    }

    @Step("Swipe to year in review step six page")
    public YearInReviewStepSixPage swipeToYearInReviewStepSixPage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Year in review step six' page.");

        return new YearInReviewStepSixPage();
    }
}
