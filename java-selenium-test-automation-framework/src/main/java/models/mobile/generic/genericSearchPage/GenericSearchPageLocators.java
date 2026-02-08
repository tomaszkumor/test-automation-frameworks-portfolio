package models.mobile.generic.genericSearchPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class GenericSearchPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.AutoCompleteTextView[@resource-id = 'org.wikipedia.alpha:id/search_src_text']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchInput;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'org.wikipedia.alpha:id/search_toolbar']/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement backButton;
}
