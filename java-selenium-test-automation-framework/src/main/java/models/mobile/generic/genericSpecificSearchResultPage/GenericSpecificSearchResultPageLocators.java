package models.mobile.generic.genericSpecificSearchResultPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class GenericSpecificSearchResultPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_contents']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement contentsButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_save']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement saveButton;
}
