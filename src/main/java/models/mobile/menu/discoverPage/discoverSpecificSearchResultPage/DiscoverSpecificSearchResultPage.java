package models.mobile.menu.discoverPage.discoverSpecificSearchResultPage;

import io.qameta.allure.Step;
import models.mobile.generic.genericSpecificSearchResultPage.GenericSpecificSearchResultPage;
import utils.tipKiller.TipKiller;

import static utils.logger.Log4J.log;

public class DiscoverSpecificSearchResultPage extends DiscoverSpecificSearchResultPageLocators {
    public DiscoverSpecificSearchResultPage() {
        closeTip();

        check.isElementDisplayed(searchInput, 15);
        log.info("Discover specific search page is displayed.");
    }

    @Step("Tap on save button")
    public DiscoverSpecificSearchResultPage tapOnSaveButton() {
        new GenericSpecificSearchResultPage().tapOnSaveButton();

        return this;
    }

    private void closeTip() {
        TipKiller.closeSpecificSearchResultPageTip(closeTip);
    }
}
