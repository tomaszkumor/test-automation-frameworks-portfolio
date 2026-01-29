package models.mobile.onboardingPage.onboardingStepOnePage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.onboardingPage.onboardingStepOnePage.addNewLanguagePage.AddNewLanguagePage;
import models.mobile.onboardingPage.onboardingStepTwoPage.OnboardingStepTwoPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class OnboardingStepOnePage extends OnboardingStepOnePageLocators {
    public OnboardingStepOnePage() {
        check.isElementDisplayed(addLanguageButton, 15);
        log.info("Onboarding - Step one page is displayed.");
    }

    @Step("Tap on forward button")
    public OnboardingStepTwoPage tapOnForwardButton() {
        mobile.tapOnElement(forwardButton, 15);
        log.info("Forward button has been tapped.");

        return new OnboardingStepTwoPage();
    }

    @Step("Swipe to onboarding step two page")
    public OnboardingStepTwoPage swipeToOnboardingStepTwoPage() {
        mobile.swipeRight(600);
        log.info("Swiped to 'Onboarding step two' page.");

        return new OnboardingStepTwoPage();
    }

    @Step("Tap on add language button")
    public AddNewLanguagePage tapOnAddLanguageButton() {
        mobile.tapOnElement(addLanguageButton, 15);
        log.info("Add/edit language button has been tapped.");

        return new AddNewLanguagePage();
    }

    @Step("Check description")
    public OnboardingStepOnePage checkDescription() {
        String actualTitle = get.getValueFromElement(title, "text").replaceAll("\n", "");
        String expectedTitle = "Wolna encyklopedia...w prawie 300 językach";

        String actualDescription = get.getValueFromElement(description, "text");
        String expectedDescription = "Znaleźliśmy następujące na twoim urządzeniu:";

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualTitle).as("Title check").isEqualTo(expectedTitle);
        softAssertions.assertThat(actualDescription).as("Description check").isEqualTo(expectedDescription);
        softAssertions.assertAll();

        return this;
    }

    @Step("Check languages on device before change")
    public OnboardingStepOnePage checkLanguagesOnDeviceBeforeChange(WikiAlphaModel wikiAlphaModel) {
        List<String> expectedLanguages = getExpectedLanguagesBeforeChangeFromDataProvider(wikiAlphaModel);
        checkLanguagesOnDevice(expectedLanguages, "before");

        return this;
    }

    @Step("Check languages on device after change")
    public OnboardingStepOnePage checkLanguagesOnDeviceAfterChange(WikiAlphaModel wikiAlphaModel) {
        List<String> expectedLanguages = getExpectedLanguagesAfterChangeFromDataProvider(wikiAlphaModel);
        checkLanguagesOnDevice(expectedLanguages, "after");

        return this;
    }

    private void checkLanguagesOnDevice(List<String> expectedLanguages, String stage) {
        By languageLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/option_label']");
        check.isNumberOfElementsEqualTo(languageLocator, expectedLanguages.size(), 50, 15);
        log.info("Languages {} change count meets expectation.", stage);

        List<WebElement> actualLanguageElements = mobile.getWebElementsIfElementsArePresentByLocator(languageLocator, 15);
        List<String> actualLanguages = actualLanguageElements.stream().map(e -> e.getText().split("\\.")[1].trim()).toList();
        assertThat(actualLanguages).as("Languages check " + stage + " change").isEqualTo(expectedLanguages);
        log.info("Languages {} change count meets expectation.", stage);
    }

    private List<String> getExpectedLanguagesBeforeChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getOnboardingModel().getExpectedLanguagesBeforeChange();
    }

    private List<String> getExpectedLanguagesAfterChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getOnboardingModel().getExpectedLanguagesAfterChange();
    }
}
