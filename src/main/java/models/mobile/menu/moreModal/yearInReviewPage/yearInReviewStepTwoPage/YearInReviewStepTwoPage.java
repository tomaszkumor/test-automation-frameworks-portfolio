package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepTwoPage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepThreePage.YearInReviewStepThreePage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class YearInReviewStepTwoPage extends YearInReviewStepTwoPageLocators {
    public YearInReviewStepTwoPage() {
        check.isElementDisplayed(donateButton, 15);
        log.info("Year in review step two page is displayed.");
    }

    @Step("Check description")
    public YearInReviewStepTwoPage checkDescription() {
        By titleLocator = By.xpath("(//android.widget.ScrollView/android.widget.TextView)[1]");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "App users viewed Wikipedia articles 14 085 467 928 times";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "For people around the world, Wikipedia is the first stop when answering a question, looking up information for school or work, or learning a new fact.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public YearInReviewStepThreePage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new YearInReviewStepThreePage();
    }

    @Step("Swipe to year in review step three page")
    public YearInReviewStepThreePage swipeToYearInReviewStepThreePage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Year in review step three' page.");

        return new YearInReviewStepThreePage();
    }
}
