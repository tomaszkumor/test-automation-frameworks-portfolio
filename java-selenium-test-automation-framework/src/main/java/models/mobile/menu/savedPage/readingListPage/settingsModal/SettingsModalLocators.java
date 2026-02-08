package models.mobile.menu.savedPage.readingListPage.settingsModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SettingsModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Customize']/ancestor::android.widget.LinearLayout[@resource-id = 'org.wikipedia.alpha:id/content']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement customizeButton;
}
