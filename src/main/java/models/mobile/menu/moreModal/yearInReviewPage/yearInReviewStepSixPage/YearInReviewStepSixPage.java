package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepSixPage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepSevenPage.YearInReviewStepSevenPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class YearInReviewStepSixPage extends YearInReviewStepSixPageLocators {
    public YearInReviewStepSixPage() {
        check.isElementDisplayed(donateButton, 15);
        log.info("Year in review step six page is displayed.");
    }

    @Step("Check description")
    public YearInReviewStepSixPage checkDescription() {
        By titleLocator = By.xpath("(//android.widget.ScrollView/android.widget.TextView)[1]");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Unlock your contributor reward for next year!";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "We're glad Wikipedia was part of your 2025! Unlock a special reward in your 2026 Year in Review by becoming a contributor—whether by editing Wikipedia or by donating from the app to the Wikimedia Foundation, the nonprofit behind it. If Wikipedia is useful to you, please consider donating to help sustain its future and keep it free, ad-free, trustworthy, and accessible to all. If you donated elsewhere, your support is deeply appreciated.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public YearInReviewStepSevenPage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new YearInReviewStepSevenPage();
    }

    @Step("Swipe to year in review step seven page")
    public YearInReviewStepSevenPage swipeToYearInReviewStepSevenPage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Year in review step seven' page.");

        return new YearInReviewStepSevenPage();
    }
}
