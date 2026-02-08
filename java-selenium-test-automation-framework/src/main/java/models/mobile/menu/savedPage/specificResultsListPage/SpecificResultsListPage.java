package models.mobile.menu.savedPage.specificResultsListPage;

import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import utils.tipKiller.TipKiller;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.logger.Log4J.log;

public class SpecificResultsListPage extends SpecificResultsListPageLocators {
    public SpecificResultsListPage() {
        closeTip();
        checkPresenceOfShareButton();
        log.info("Specific results page is displayed.");
    }

    @Step("Check actual specific results in list")
    public SpecificResultsListPage checkActualSpecificResultsInList(WikiAlphaModel wikiAlphaModel) {
        List<String> expectedSpecificResults = getExpectedSpecificResultsFromDataProvider(wikiAlphaModel);

        By specificResultLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_list_item_title']");
        check.isNumberOfElementsGreaterThan(specificResultLocator, 0, 50, 15);
        log.info("Specific results count meets expectation.");

        compareSpecificResultsInList(expectedSpecificResults, specificResultLocator);

        return this;
    }

    private void compareSpecificResultsInList(List<String> expectedSpecificResults, By specificResultLocator) {
        List<WebElement> actualSpecificResultElements = mobile.getWebElementsIfElementsArePresentByLocator(specificResultLocator, 15);
        List<String> actualSpecificResults = actualSpecificResultElements.stream().map(WebElement::getText).toList();
        Assertions.assertThat(actualSpecificResults)
                .as("Specific results in list check")
                .containsExactlyInAnyOrderElementsOf(expectedSpecificResults)
                .doesNotHaveDuplicates();

        log.info("Specific results in list meets expectation.");
    }

    private void closeTip() {
        TipKiller.closeSpecificResultsListPageTip(allClearButton);
    }

    private void checkPresenceOfShareButton() {
        By shareButtonLocator = By.xpath("//android.widget.ImageView[@resource-id = 'org.wikipedia.alpha:id/item_share_button']");
        check.isNumberOfElementsGreaterThan(shareButtonLocator, 0, 50, 15);
    }

    private List<String> getExpectedSpecificResultsFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedSavedResults();
    }
}
