package models.mobile.menu.moreModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MoreModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/design_bottom_sheet']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement moreModalContainer;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id = 'org.wikipedia.alpha:id/main_drawer_settings_container']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement settingsButton;
}
