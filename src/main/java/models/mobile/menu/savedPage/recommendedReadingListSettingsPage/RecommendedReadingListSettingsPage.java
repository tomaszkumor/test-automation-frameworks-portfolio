package models.mobile.menu.savedPage.recommendedReadingListSettingsPage;

import constants.mobile.UpdateType;
import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.menu.savedPage.updatesModal.UpdatesModal;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class RecommendedReadingListSettingsPage extends RecommendedReadingListSettingsPageLocators {
    public RecommendedReadingListSettingsPage() {
        checkIfDiscoverReadingListTitleIsPresent();
        log.info("Recommended reading list settings page is displayed.");
    }

    @Step("Change articles number")
    public RecommendedReadingListSettingsPage changeArticlesNumber(WikiAlphaModel wikiAlphaModel) {
        String expectedArticlesNumber = getExpectedArticlesNumberFromDataProvider(wikiAlphaModel);

        checkArticlesNumberBeforeChange(wikiAlphaModel);
        mobile.tapOnElement(articlesNumber, 15);
        typeArticlesNumberToInput(expectedArticlesNumber);
        mobile.hideKeyboard();
        checkArticlesNumberAfterChange(expectedArticlesNumber);

        return this;
    }

    @Step("Check update type before change")
    public RecommendedReadingListSettingsPage checkUpdateTypeBeforeChange(WikiAlphaModel wikiAlphaModel) {
        UpdateType updateTypeBeforeChange = getUpdateTypeBeforeChangeFromDataProvider(wikiAlphaModel);
        checkUpdateType(updateTypeBeforeChange);

        return this;
    }

    @Step("Check update type after change")
    public RecommendedReadingListSettingsPage checkUpdateTypeAfterChange(WikiAlphaModel wikiAlphaModel) {
        UpdateType updateTypeAfterChange = getUpdateTypeAfterChangeFromDataProvider(wikiAlphaModel);
        checkUpdateType(updateTypeAfterChange);

        return this;
    }

    @Step("Tap on updates button")
    public UpdatesModal tapOnUpdatesButton() {
        mobile.tapOnElement(updatesButton, 15);
        log.info("Updates button has been tapped.");

        return new UpdatesModal();
    }

    private void typeArticlesNumberToInput(String expectedArticlesNumber) {
        mobile.tapOnElement(articlesNumber, 15);
        mobile.pressKey(expectedArticlesNumber);
        log.info("{} articles number has ben typed to input.", expectedArticlesNumber);
    }

    private void checkArticlesNumberAfterChange(String expectedArticlesNumber) {
        String actualArticlesNumberAfterChange = get.getTextFromElement(articlesNumberAfterChange);

        assertThat(actualArticlesNumberAfterChange)
                .as("Articles number after change check")
                .isEqualTo(expectedArticlesNumber);

        log.info("Number of articles after change meets expected value.");
    }

    private void checkArticlesNumberBeforeChange(WikiAlphaModel wikiAlphaModel) {
        String actualArticlesNumberBeforeChange = get.getTextFromElement(articlesNumber);
        String expectedArticlesNumberBeforeChange = getExpectedArticlesNumberBeforeChangeFromDataProvider(wikiAlphaModel);

        assertThat(actualArticlesNumberBeforeChange)
                .as("Articles number before change check")
                .isEqualTo(expectedArticlesNumberBeforeChange);

        log.info("Number of articles before change meets expected value.");
    }

    private void checkUpdateType(UpdateType updateType) {
        check.isElementDisplayed(updatesButton, 15);

        String actualUpdateType = get.getValueFromElement(updatesButton.findElement(By.xpath(".//android.widget.TextView")), "text");
        String expectedUpdateType = "Updates ".concat(updateType.getUpdateTypeAdjective());

        assertThat(actualUpdateType)
                .as("Update type before change check")
                .isEqualTo(expectedUpdateType);
    }

    private void checkIfDiscoverReadingListTitleIsPresent() {
        By titleLocator = By.xpath("//android.widget.TextView[@text = 'Discover reading list']");
        check.isElementPresentByLocator(titleLocator, 15);
    }

    private String getExpectedArticlesNumberFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedNumberOfArticlesAfterChange();
    }

    private String getExpectedArticlesNumberBeforeChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedNumberOfArticlesBeforeChange();
    }

    private UpdateType getUpdateTypeBeforeChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedDiscoverMadeForYouUpdateTypeBeforeChange();
    }

    private UpdateType getUpdateTypeAfterChangeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedDiscoverMadeForYouUpdateTypeAfterChange();
    }
}
