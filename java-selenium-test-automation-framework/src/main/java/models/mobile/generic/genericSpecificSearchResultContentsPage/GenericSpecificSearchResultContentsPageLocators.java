package models.mobile.generic.genericSpecificSearchResultContentsPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class GenericSpecificSearchResultContentsPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id = 'org.wikipedia.alpha:id/toc_list']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement contentsWindow;
}
