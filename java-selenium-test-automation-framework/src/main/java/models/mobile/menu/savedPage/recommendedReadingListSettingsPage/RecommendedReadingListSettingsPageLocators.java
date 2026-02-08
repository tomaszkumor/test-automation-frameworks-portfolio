package models.mobile.menu.savedPage.recommendedReadingListSettingsPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RecommendedReadingListSettingsPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.EditText//android.widget.TextView")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement articlesNumber;
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement articlesNumberAfterChange;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Updates')]/..")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement updatesButton;
}
