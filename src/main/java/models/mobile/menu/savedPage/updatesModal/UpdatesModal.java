package models.mobile.menu.savedPage.updatesModal;

import constants.mobile.UpdateType;
import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import io.qameta.allure.Step;
import models.mobile.menu.savedPage.recommendedReadingListSettingsPage.RecommendedReadingListSettingsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getAndroidDriver;
import static driver.BaseDriver.getIOSDriver;
import static driver.MobileProperties.getMobileSystem;
import static utils.logger.Log4J.log;

public class UpdatesModal extends UpdatesModalLocators {
    public UpdatesModal() {
        check.isElementDisplayed(updatesModal, 15);
        log.info("Updates modal is displayed");
    }

    @Step("Change update type")
    public RecommendedReadingListSettingsPage changeUpdateType(WikiAlphaModel wikiAlphaModel) {
        UpdateType expectedUpdateType = getExpectedUpdateTypeFromDataProvider(wikiAlphaModel);
        String updateTypeName = getUpdateTypeNoun(expectedUpdateType);
        WebElement updateButton = getUpdateButtonElement(updateTypeName);

        mobile.tapOnElement(updateButton, 15);
        log.info("{} update type has been tapped.", updateTypeName);

        return new RecommendedReadingListSettingsPage();
    }

    private UpdateType getExpectedUpdateTypeFromDataProvider(WikiAlphaModel wikiAlphaModel) {
        return wikiAlphaModel.getSavedModel().getExpectedDiscoverMadeForYouUpdateTypeAfterChange();
    }

    private String getUpdateTypeNoun(UpdateType expectedUpdateType) {
        return expectedUpdateType.getUpdateTypeNoun();
    }

    private WebElement getUpdateButtonElement(String updateTypeName) {
        By updateTypeLocator = By.xpath("//android.widget.TextView[@text = 'Every " + updateTypeName + "']");

        String mobileSystem = getMobileSystem();
        return switch (mobileSystem) {
            case "android" -> getAndroidDriver().findElement(updateTypeLocator);
            case "ios" -> getIOSDriver().findElement(updateTypeLocator);
            default -> null;
        };
    }
}
