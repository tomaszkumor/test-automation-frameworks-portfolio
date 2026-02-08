package models.mobile.navigation;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class NavigationLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/nav_tab_reading_lists']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement savedButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/nav_tab_search']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/nav_tab_more']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement moreButton;
}
