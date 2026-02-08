package models.mobile.onboardingPage.onboardingStepFourPage;

import io.qameta.allure.Step;
import models.mobile.menu.discoverPage.DiscoverPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import static utils.logger.Log4J.log;

public class OnboardingStepFourPage extends OnboardingStepFourPageLocators {
    public OnboardingStepFourPage() {
        check.isElementDisplayed(doneButton, 15);
        log.info("Onboarding - Step four page is displayed.");
    }

    @Step("Check description")
    public OnboardingStepFourPage checkDescription() {
        By titleLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/primaryTextView']");
        check.isNumberOfElementsEqualTo(titleLocator, 1, 50, 15);

        String actualTitle = get.getValueFromElement(title, "text");
        String expectedTitle = "Dane i prywatność";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "Uważamy, że do udziału w inicjatywie wolnej wiedzy nie powinno się wymagać podawania danych osobowych. Wszystkie dane dotyczące użytkowania zbierane dla tej aplikacji są anonimowe. Dowiedz się więcej o naszej polityce prywatności oraz warunkach użytkowania.";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Tap on done button")
    public DiscoverPage tapOnDoneButton() {
        mobile.tapOnElement(doneButton, 15);
        log.info("Done button has been tapped.");

        return new DiscoverPage();
    }
}
