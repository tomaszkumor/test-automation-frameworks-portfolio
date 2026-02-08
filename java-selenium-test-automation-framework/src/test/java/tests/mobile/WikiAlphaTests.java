package tests.mobile;

import baseTest.BaseTest;
import dataProviders.dataProviders.mobile.WikiAlphaDP;
import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Description;
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
    @Description("Mobile. Complete onboarding process with new language selection")
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
    @Description("Mobile. Complete year in review onboarding process")
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
    @Description("Mobile. Change theme to dark mode")
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
    @Description("Mobile. Search through discovery page and add result to saved")
    public void searchThroughDiscoveryPageAndAddResultToSaved(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnSearchInput()
                .typePhraseToSearchBar(wikiAlphaModel)
                .tapOnSpecificSearchResult(wikiAlphaModel)
                .tapOnSaveButton();
    }

    @Test(dataProvider = "savedDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Mobile. Check if specific results were added to list in saved page")
    public void checkIfSpecificResultsWereAddedToListInSavedPage(WikiAlphaModel wikiAlphaModel) {
        new DiscoverPage()
                .tapOnTabSaved()
                .tapOnSpecificSavedResultsList()
                .checkActualSpecificResultsInList(wikiAlphaModel);
    }

    @Test(dataProvider = "searchDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Mobile. Search through search page and find item in contents")
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
    @Description("Mobile. Get through discover made for you")
    public void getThroughDiscoverMadeForYou() {
        new DiscoverPage()
                .tapOnTabSaved()
                .tapOnGetStartedButton()
                .tapOnChooseBasedOnSavedArticlesButton()
                .tapOnNextButton();
    }

    @Test(dataProvider = "savedDP", dataProviderClass = WikiAlphaDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Mobile. Change discover made for you settings")
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