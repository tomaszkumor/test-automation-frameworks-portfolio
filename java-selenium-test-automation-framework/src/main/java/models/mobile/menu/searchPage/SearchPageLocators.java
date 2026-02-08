package models.mobile.menu.searchPage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.mobile.navigation.Navigation;
import org.openqa.selenium.WebElement;

public class SearchPageLocators extends Navigation {
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[@resource-id = 'org.wikipedia.alpha:id/search_card']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchInput;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'org.wikipedia.alpha:id/main_toolbar']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement toolbarHeader;
}
