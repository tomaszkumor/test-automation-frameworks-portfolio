package models.mobile.menu.discoverPage.discoverSearchPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DiscoverSearchPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.AutoCompleteTextView[@resource-id = 'org.wikipedia.alpha:id/search_src_text']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchInput;
}
