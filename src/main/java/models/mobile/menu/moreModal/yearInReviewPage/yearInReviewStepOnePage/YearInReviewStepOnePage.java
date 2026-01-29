package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepOnePage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepTwoPage.YearInReviewStepTwoPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class YearInReviewStepOnePage extends YearInReviewStepOnePageLocators {
    public YearInReviewStepOnePage() {
        checkPresenceOfDonateButton();
        log.info("Year in review step one page is displayed.");
    }

    @Step("Tap on forward button")
    public YearInReviewStepTwoPage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new YearInReviewStepTwoPage();
    }

    @Step("Swipe to year in review step two page")
    public YearInReviewStepTwoPage swipeToYearInReviewStepTwoPage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Year in review step two' page.");

        return new YearInReviewStepTwoPage();
    }

    @Step("Check description")
    public YearInReviewStepOnePage checkDescription() {
        By titleLocator = By.xpath("(//android.widget.ScrollView/android.widget.TextView)[1]");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Wikipedia was available in more than 300 languages";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "Wikipedia had 65 667 790 articles across over 300 active languages this year. You joined millions in expanding knowledge and exploring diverse topics.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    private void checkPresenceOfDonateButton() {
        By donateButtonLocator = By.xpath("//android.view.View[@content-desc = 'Heart Icon']/..");
        check.isNumberOfElementsEqualTo(donateButtonLocator, 1, 50, 15);
    }
}
