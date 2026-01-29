package models.mobile.onboardingPage.onboardingStepThreePage;

import io.qameta.allure.Step;
import models.mobile.onboardingPage.onboardingStepFourPage.OnboardingStepFourPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.logger.Log4J.log;

public class OnboardingStepThreePage extends OnboardingStepThreePageLocators {
    public OnboardingStepThreePage() {
        checkIfIndicatorIsCorrect();
        log.info("Onboarding - Step three page is displayed.");
    }

    @Step("Check description")
    public OnboardingStepThreePage checkDescription() {
        By titleLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/primaryTextView']");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Synchronizacja list do przeczytania";

        String actualDescription = get.getValueFromElement(description, "text").replaceAll("\n", "");
        String expectedDescription = "Możesz tworzyć listy do czytania z artykułów, które chcesz przeczytać później, nawet gdy jesteś offline. Zaloguj się do swojego konta w Wikipedii, aby zsynchronizować listy czytelnicze. Dołącz do Wikipedii ";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on forward button")
    public OnboardingStepFourPage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new OnboardingStepFourPage();
    }

    @Step("Swipe to onboarding step four page")
    public OnboardingStepFourPage swipeToOnboardingStepFourPage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Onboarding - step four' page.");

        return new OnboardingStepFourPage();
    }

    private void checkIfIndicatorIsCorrect() {
        check.isElementDisplayed(pageIndicator, 15);
        String indicatorValue = get.getValueFromElement(pageIndicator, "content-desc");

        Pattern p = Pattern.compile("(\\d+)\\D+(\\d+)");
        Matcher m = p.matcher(indicatorValue);

        if (m.find()) {
            int actualCurrent = Integer.parseInt(m.group(1));
            int actualTotal = Integer.parseInt(m.group(2));

            int expectedCurrent = 3;
            int expectedTotal = 4;

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualCurrent).as("Current page indicator check").isEqualTo(expectedCurrent);
            softAssertions.assertThat(actualTotal).as("Total page indicator check").isEqualTo(expectedTotal);
            softAssertions.assertAll();
        }
    }
}
