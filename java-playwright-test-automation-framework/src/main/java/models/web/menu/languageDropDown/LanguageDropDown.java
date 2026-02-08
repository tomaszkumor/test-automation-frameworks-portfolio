package models.web.menu.languageDropDown;

import constants.header.HeaderLanguage;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.landingPage.LandingPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;


public class LanguageDropDown extends LanguageDropDownSelectors {
    public LanguageDropDown() {
        check.isVisible(languageDropDownSelector, "Language drop down");
        log.info("Language drop down has been displayed.");
    }

    @Step("Check available languages")
    public LanguageDropDown checkAvailableLanguages(PhpTravelsModel phpTravelsModel) {
        List<String> actualLanguages = getActualLanguages();
        List<String> expectedLanguages = getExpectedLanguages(phpTravelsModel);
        compareLanguages(actualLanguages, expectedLanguages);

        return this;
    }

    @Step("Click on english language")
    public LandingPage selectEnglish() {
        selectLanguage(HeaderLanguage.EN);

        return new LandingPage();
    }

    @Step("Click on arabic language")
    public LandingPage selectArabic() {
        selectLanguage(HeaderLanguage.AR);

        return new LandingPage();
    }

    @Step("Click on turkish language")
    public LandingPage selectTurkish() {
        selectLanguage(HeaderLanguage.TR);

        return new LandingPage();
    }

    @Step("Click on russian language")
    public LandingPage selectRussian() {
        selectLanguage(HeaderLanguage.RU);

        return new LandingPage();
    }

    @Step("Click on french language")
    public LandingPage selectFrench() {
        selectLanguage(HeaderLanguage.FR);

        return new LandingPage();
    }

    @Step("Click on chinese language")
    public LandingPage selectChinese() {
        selectLanguage(HeaderLanguage.ZH);

        return new LandingPage();
    }

    @Step("Click on german language")
    public LandingPage selectGerman() {
        selectLanguage(HeaderLanguage.DE);

        return new LandingPage();
    }

    private void compareLanguages(List<String> actualLanguages, List<String> expectedLanguages) {
        assertThat(actualLanguages)
                .as("Languages check")
                .containsExactlyInAnyOrderElementsOf(expectedLanguages)
                .doesNotHaveDuplicates();
    }

    private List<String> getActualLanguages() {
        String languageNameSelector = "//a[contains(@href, 'language')]/span";
        check.isElementPresent(languageNameSelector, "Language name");

        return get.getAllInnerText(languageNameSelector, "Languages");
    }

    private List<String> getExpectedLanguages(PhpTravelsModel phpTravelsModel) {
        List<HeaderLanguage> expectedLanguagesA = getExpectedLanguagesFromDataProvider(phpTravelsModel);

        return expectedLanguagesA.stream().map(HeaderLanguage::getLanguage).toList();
    }

    private void selectLanguage(HeaderLanguage language) {
        String languageButtonSelector = getLanguageButtonSelector(language);
        String languageName = language.getLanguage();
        click.leftClick(languageButtonSelector, languageName + " language button");
    }

    private String getLanguageButtonSelector(HeaderLanguage language) {
        return switch (language) {
            case EN -> englishLanguageButtonSelector;
            case AR -> arabicLanguageButtonSelector;
            case TR -> turkishLanguageButtonSelector;
            case RU -> russianLanguageButtonSelector;
            case FR -> frenchLanguageButtonSelector;
            case ZH -> chineseLanguageButtonSelector;
            case DE -> germanLanguageButtonSelector;
        };
    }

    private List<HeaderLanguage> getExpectedLanguagesFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getLanguages();
    }
}
