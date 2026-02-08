package models.mobile.menu.searchPage.searchSpecificSearchResultContentsPage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.generic.genericSpecificSearchResultContentsPage.GenericSpecificSearchResultContentsPage;
import models.mobile.menu.searchPage.searchSpecificSearchResultPage.SearchSpecificSearchResultPage;

import static utils.logger.Log4J.log;

public class SearchSpecificSearchResultContentsPage extends SearchSpecificSearchResultContentsPageLocators {
    public SearchSpecificSearchResultContentsPage() {
        check.isElementDisplayed(scrollerButton, 15);
        log.info("Contents page is displayed.");
    }

    @Step("Tap on specific contents item")
    public SearchSpecificSearchResultPage tapOnSpecificContentsItem(WikiAlphaModel wikiAlphaModel) {
        String expectedContentItem = wikiAlphaModel.getSearchModel().getExpectedContentItem();
        new GenericSpecificSearchResultContentsPage().tapOnSpecificContentItem(expectedContentItem);

        return new SearchSpecificSearchResultPage();
    }
}
