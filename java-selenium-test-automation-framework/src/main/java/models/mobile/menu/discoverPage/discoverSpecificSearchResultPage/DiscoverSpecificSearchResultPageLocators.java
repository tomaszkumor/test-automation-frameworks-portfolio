package models.mobile.menu.discoverPage.discoverSpecificSearchResultPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DiscoverSpecificSearchResultPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_toolbar_button_search']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchInput;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'pcs']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement closeTip;
}
