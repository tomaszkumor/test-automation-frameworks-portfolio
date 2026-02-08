package models.mobile.menu.savedPage.readingListPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ReadingListPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id = 'org.wikipedia.alpha:id/item_notification_button']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement bellButton;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id = 'org.wikipedia.alpha:id/item_overflow_menu']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement settingsButton;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'pcs']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement closeTip;
}
