package models.mobile.menu.savedPage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.mobile.navigation.Navigation;
import org.openqa.selenium.WebElement;

public class SavedPageLocators extends Navigation {
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/menu_search_lists']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement filtersButton;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id = 'org.wikipedia.alpha:id/recycler_view']/android.view.ViewGroup")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement specificList;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/positiveButton']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement getStartedButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Made for you']/..")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement discoverListButton;
}
