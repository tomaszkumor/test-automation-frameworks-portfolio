package models.mobile.onboardingPage.onboardingStepOnePage.languageSelectionPage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.onboardingPage.onboardingStepOnePage.addNewLanguagePage.AddNewLanguagePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class LanguageSelectionPage extends LanguageSelectionPageLocators {
    public LanguageSelectionPage() {
        check.isElementDisplayed(findLanguageButton, 15);
        log.info("Onboarding - step one - select language page is displayed");
    }

    @Step("Tap on find language button")
    public LanguageSelectionPage tapOnFindLanguageButton() {
        mobile.tapOnElement(findLanguageButton, 15);
        log.info("Find language button has been tapped.");

        return this;
    }

    @Step("Search for language")
    public LanguageSelectionPage searchForLanguage(WikiAlphaModel wikiAlphaModel) {
        checkSearchLanguageInputBeforeChange();

        String phrase = getExpectedLanguageToAddFromDataProvider(wikiAlphaModel);
        mobile.sendKeysToWebElement(searchLanguageInput, phrase);

        checkSearchLanguageInputAfterChange(phrase);

        return this;
    }

    @Step("Select language")
    public AddNewLanguagePage selectLanguage(WikiAlphaModel wikiAlphaModel) {
        String language = getExpectedLanguageToAddFromDataProvider(wikiAlphaModel);

        WebElement languageButton = getLanguageButtonElement(language);
        mobile.tapOnElement(languageButton, 15);

        log.info("{} language button has been tapped.", language);

        return new AddNewLanguagePage();
    }

    private WebElement getLanguageButtonElement(String language) {
        By languageButtonLocator = By.xpath("//android.widget.TextView[@text = '" + language + "']");
        return get.getWebElementIfElementIsPresentByLocator(languageButtonLocator, 15);
    }

    private void checkSearchLanguageInputBeforeChange() {
        String actualSearchLanguageInputBeforeInput = get.getTextFromElement(searchLanguageInput);
        String expectedSearchLanguageInputBeforeInput = "";

        assertThat(actualSearchLanguageInputBeforeInput)
                .as("Search language input value check before input")
                .isEqualTo(expectedSearchLanguageInputBeforeInput);

        log.info("Search language input before input meets expected value.");
    }

    private void checkSearchLanguageInputAfterChange(String phrase) {
        String actualSearchLanguageInputAfterInput = get.getTextFromElement(searchLanguageInput);

        assertThat(actualSearchLanguageInputAfterInput)
                .as("Search language input value check before input")
                .isEqualTo(phrase);

        log.info("Search language input after input meets expected value.");
    }

    private String getExpectedLanguageToAddFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getOnboardingModel().getExpectedLanguageToAdd();
    }
}
