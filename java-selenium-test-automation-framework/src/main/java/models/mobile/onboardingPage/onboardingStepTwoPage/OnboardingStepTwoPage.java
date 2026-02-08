package models.mobile.onboardingPage.onboardingStepTwoPage;

import io.qameta.allure.Step;
import models.mobile.onboardingPage.onboardingStepThreePage.OnboardingStepThreePage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.logger.Log4J.log;

public class OnboardingStepTwoPage extends OnboardingStepTwoPageLocators {
    public OnboardingStepTwoPage() {
        checkIfIndicatorIsCorrect();
        log.info("Onboarding - Step two page is displayed.");
    }

    @Step("Check description")
    public OnboardingStepTwoPage checkDescription() {
        By titleLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/primaryTextView']");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Nowe metody eksploracji";

        String actualDescription = get.getValueFromElement(description, "text").replaceAll("\n", " ");
        String expectedDescription = "Zanurkuj w króliczej norze Wikipedii, ze stale aktualizowanym kanałem Eksploruj. Dostosuj źródła do swoich zainteresowań - niezależnie od tego, czy chodzi o wydarzenia historyczne Tego dnia, czy rzut kostką za pomocą Losowego artykułu.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public OnboardingStepThreePage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new OnboardingStepThreePage();
    }

    @Step("Swipe to onboarding step three page")
    public OnboardingStepThreePage swipeToOnboardingStepThreePage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Onboarding step three' page.");

        return new OnboardingStepThreePage();
    }

    private void checkIfIndicatorIsCorrect() {
        check.isElementDisplayed(pageIndicator, 15);
        String indicatorValue = get.getValueFromElement(pageIndicator, "content-desc");

        Pattern p = Pattern.compile("(\\d+)\\D+(\\d+)");
        Matcher m = p.matcher(indicatorValue);

        if (m.find()) {
            int actualCurrent = Integer.parseInt(m.group(1));
            int actualTotal = Integer.parseInt(m.group(2));

            int expectedCurrent = 2;
            int expectedTotal = 4;

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualCurrent).as("Current page indicator check").isEqualTo(expectedCurrent);
            softAssertions.assertThat(actualTotal).as("Total page indicator check").isEqualTo(expectedTotal);
            softAssertions.assertAll();
        }
    }
}
