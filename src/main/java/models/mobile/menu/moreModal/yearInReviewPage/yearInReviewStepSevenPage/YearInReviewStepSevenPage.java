package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepSevenPage;

import io.qameta.allure.Step;
import models.mobile.menu.moreModal.yearInReviewPage.improveYearInReviewModal.ImproveYearInReviewModal;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class YearInReviewStepSevenPage extends YearInReviewStepSevenPageLocators {
    public YearInReviewStepSevenPage() {
        check.isElementDisplayed(shareHighlightsButton, 15);
        log.info("Year in review step seven page is displayed.");
    }

    @Step("Check description")
    public YearInReviewStepSevenPage checkDescription() {
        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "YearInReviewStepSixPage";
        assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);

        return this;
    }

    @Step("Tap on forward button")
    public ImproveYearInReviewModal tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new ImproveYearInReviewModal();
    }
}
