package models.mobile.menu.moreModal.yearInReviewPage.improveYearInReviewModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ImproveYearInReviewModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id = 'org.wikipedia.alpha:id/optionsContainer']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement improveModalContainer;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/cancelButton']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement cancelButton;
}
