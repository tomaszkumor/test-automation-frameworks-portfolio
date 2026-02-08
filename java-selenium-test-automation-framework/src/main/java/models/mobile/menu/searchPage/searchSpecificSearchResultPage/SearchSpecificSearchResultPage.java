package models.mobile.menu.searchPage.searchSpecificSearchResultPage;

import io.qameta.allure.Step;
import models.mobile.generic.genericSpecificSearchResultPage.GenericSpecificSearchResultPage;
import utils.tipKiller.TipKiller;
import models.mobile.menu.searchPage.searchSpecificSearchResultContentsPage.SearchSpecificSearchResultContentsPage;

import static utils.logger.Log4J.log;

public class SearchSpecificSearchResultPage extends SearchSpecificSearchResultPageLocators {
    public SearchSpecificSearchResultPage() {
        closeTip();
        check.isElementDisplayed(findInArticleButton, 15);
        log.info("Search specific search page is displayed.");
    }

    @Step("Tap on contents button")
    public SearchSpecificSearchResultContentsPage tapOnContentsButton() {
        new GenericSpecificSearchResultPage().tapOnContentsButton();

        return new SearchSpecificSearchResultContentsPage();
    }

    private void closeTip() {
        TipKiller.closeSpecificSearchResultPageTip(closeTip);
    }
}
