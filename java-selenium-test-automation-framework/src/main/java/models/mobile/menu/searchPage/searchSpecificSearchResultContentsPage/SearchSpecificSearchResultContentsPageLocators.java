package models.mobile.menu.searchPage.searchSpecificSearchResultContentsPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SearchSpecificSearchResultContentsPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id = 'org.wikipedia.alpha:id/page_scroller_button']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement scrollerButton;
}
