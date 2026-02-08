package models.mobile.menu.searchPage.searchSpecificSearchResultPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SearchSpecificSearchResultPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.View[@resource-id = 'pcs']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement closeTip;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_find_in_article']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement findInArticleButton;
}
