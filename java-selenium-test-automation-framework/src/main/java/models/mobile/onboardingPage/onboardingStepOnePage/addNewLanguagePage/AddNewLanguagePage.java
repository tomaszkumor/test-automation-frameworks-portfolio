package models.mobile.onboardingPage.onboardingStepOnePage.addNewLanguagePage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.onboardingPage.onboardingStepOnePage.OnboardingStepOnePage;
import models.mobile.onboardingPage.onboardingStepOnePage.languageSelectionPage.LanguageSelectionPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.logger.Log4J.log;

public class AddNewLanguagePage extends AddNewLanguagePageLocators {
    public AddNewLanguagePage() {
        check.isElementDisplayed(addLanguageButton, 15);
        log.info("Onboarding - step one - add/edit language page is displayed");
    }

    @Step("Tap on back button")
    public OnboardingStepOnePage tapOnBackButton() {
        mobile.tapOnElement(backButton, 15);
        log.info("Back button has been tapped.");

        return new OnboardingStepOnePage();
    }

    @Step("Tap on add language button")
    public LanguageSelectionPage tapOnAddLanguageButton() {
        mobile.tapOnElement(addLanguageButton, 15);
        log.info("Add language button has been tapped.");

        return new LanguageSelectionPage();
    }

    @Step("Check languages before change")
    public AddNewLanguagePage checkLanguagesBeforeChange(WikiAlphaModel wikiAlphaModel) {
        List<String> expectedLanguages = getExpectedLanguagesBeforeChangeFromDataProvider(wikiAlphaModel);
        checkLanguages(expectedLanguages, "before");

        return this;
    }

    @Step("Check languages after change")
    public AddNewLanguagePage checkLanguagesAfterChange(WikiAlphaModel wikiAlphaModel) {
        List<String> expectedLanguages = getExpectedLanguagesAfterChangeFromDataProvider(wikiAlphaModel);
        checkLanguages(expectedLanguages, "after");

        return this;
    }

    private void checkLanguages(List<String> expectedLanguages, String stage) {
        By languageLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/wiki_language_order']/../android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/wiki_language_title']");
        check.isNumberOfElementsEqualTo(languageLocator, expectedLanguages.size(), 50, 15);
        log.info("Languages {} change count meets expectation.", stage);

        List<WebElement> actualLanguageElements = mobile.getWebElementsIfElementsArePresentByLocator(languageLocator, 15);
        List<String> actualLanguages = actualLanguageElements.stream().map(WebElement::getText).toList();
        Assertions.assertThat(actualLanguages).as("Languages check " + stage + " change").isEqualTo(expectedLanguages);
        log.info("Languages {} change count meets expectation.", stage);
    }

    private List<String> getExpectedLanguagesBeforeChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getOnboardingModel().getExpectedLanguagesBeforeChange();
    }

    private List<String> getExpectedLanguagesAfterChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getOnboardingModel().getExpectedLanguagesAfterChange();
    }
}
