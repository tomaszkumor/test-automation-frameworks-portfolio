package tests.mobile;

import baseTest.BaseTest;
import dataProviders.dataProviders.mobile.WikiAlphaDP;
import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import listeners.DriverListener;
import models.mobile.menu.discoverPage.DiscoverPage;
import models.mobile.menu.moreModal.yearInReviewPage.yearInReviewOnboardingPage.YearInReviewOnboardingPage;
import models.mobile.onboardingPage.onboardingStepOnePage.OnboardingStepOnePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.retryAnalyzer.RetryAnalyzer;

@Listeners(value = {DriverListener.class})
public class WikiAlphaTests extends BaseTest {
    @Test(dataProvider = "commonDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void completeOnboardingProcessWithNewLanguageSelection(WikiAlphaModel wikiAlphaModel) {
        new OnboardingStepOnePage()
                .checkDescription()
                .checkLanguagesOnDeviceBeforeChange(wikiAlphaModel)
                .tapOnAddLanguageButton()
                .checkLanguagesBeforeChange(wikiAlphaModel)
                .tapOnAddLanguageButton()
                .tapOnFindLanguageButton()
                .searchForLanguage(wikiAlphaModel)
                .selectLanguage(wikiAlphaModel)
                .checkLanguagesAfterChange(wikiAlphaModel)
                .tapOnBackButton()
                .checkLanguagesOnDeviceAfterChange(wikiAlphaModel)
                .swipeToOnboardingStepTwoPage()
                .checkDescription()
                .swipeToOnboardingStepThreePage()
                .checkDescription()
                .swipeToOnboardingStepFourPage()
                .checkDescription()
                .tapOnDoneButton();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void completeYearInReviewOnboardingProcess() {
        new YearInReviewOnboardingPage()
                .tapOnGetStartedButton()
                .tapOnContinueWithoutLoggingInButton()
                .checkDescription()
                .swipeToYearInReviewStepTwoPage()
                .checkDescription()
                .swipeToYearInReviewStepThreePage()
                .checkDescription()
                .swipeToYearInReviewStepFourPage()
                .checkDescription()
                .swipeToYearInReviewStepFivePage()
                .checkDescription()
                .swipeToYearInReviewStepSixPage()
                .checkDescription()
                .swipeToYearInReviewStepSevenPage()
                .tapOnForwardButton()
                .tapOnCancelButton();
    }

    @Test(dataProvider = "commonDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void changeThemeToDarkMode(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnTabMore()
                .tapOnSettingsButton()
                .tapOnThemesButton()
                .tapOnFitToSystemThemeSwitch()
                .changeTheme(wikiAlphaModel)
                .closeThemesModal();
    }

    @Test(dataProvider = "discoverySearchDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void searchThroughDiscoveryPageAndAddResultToSaved(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnSearchInput()
                .typePhraseToSearchBar(wikiAlphaModel)
                .tapOnSpecificSearchResult(wikiAlphaModel)
                .tapOnSaveButton();
    }

    @Test(dataProvider = "savedDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void checkIfSpecificResultsWereAddedToListInSavedPage(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnTabSaved()
                .tapOnSpecificSavedResultsList()
                .checkActualSpecificResultsInList(wikiAlphaModel);
    }

    @Test(dataProvider = "searchDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void searchThroughSearchPageAndFindItemInContents(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnTabSearch()
                .tapOnSearchInput()
                .typePhraseToSearchBar(wikiAlphaModel)
                .tapOnSpecificSearchResult(wikiAlphaModel)
                .tapOnContentsButton()
                .tapOnSpecificContentsItem(wikiAlphaModel);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getThroughDiscoverMadeForYou() {
        new DiscoverPage()
                .tapOnTabSaved()
                .tapOnGetStartedButton()
                .tapOnChooseBasedOnSavedArticlesButton()
                .tapOnNextButton();
    }

    @Test(dataProvider = "savedDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    public void changeDiscoverMadeForYouSettings(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnTabSaved()
                .tapOnDiscoverListButton()
                .tapOnSettingsButton()
                .tapOnCustomizeButton()
                .checkUpdateTypeBeforeChange(wikiAlphaModel)
                .changeArticlesNumber(wikiAlphaModel)
                .tapOnUpdatesButton()
                .changeUpdateType(wikiAlphaModel)
                .checkUpdateTypeAfterChange(wikiAlphaModel);
    }
}