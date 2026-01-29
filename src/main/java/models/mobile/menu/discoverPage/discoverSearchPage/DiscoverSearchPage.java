package models.mobile.menu.discoverPage.discoverSearchPage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.generic.genericSearchPage.GenericSearchPage;
import models.mobile.menu.discoverPage.discoverSpecificSearchResultPage.DiscoverSpecificSearchResultPage;

import static utils.logger.Log4J.log;

public class DiscoverSearchPage extends DiscoverSearchPageLocators {
    public DiscoverSearchPage() {
        check.isElementDisplayed(searchInput, 15);
        log.info("Discover search page is displayed.");
    }

    @Step("Type phrase to search bar")
    public DiscoverSearchPage typePhraseToSearchBar(WikiAlphaModel wikiAlphaModel) {
        String expectedPhrase = wikiAlphaModel.getDiscoveryModel().getSearchPhrase();
        new GenericSearchPage().typePhraseToSearchBar(expectedPhrase);

        return this;
    }

    @Step("Tap on specific search result")
    public DiscoverSpecificSearchResultPage tapOnSpecificSearchResult(WikiAlphaModel wikiAlphaModel) {
        String expectedPhrase = wikiAlphaModel.getDiscoveryModel().getSearchPhrase();
        new GenericSearchPage().tapOnSpecificSearchResult(expectedPhrase);

        return new DiscoverSpecificSearchResultPage();
    }
}
