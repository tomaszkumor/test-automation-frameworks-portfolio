package models.mobile.menu.discoverPage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.mobile.navigation.Navigation;
import org.openqa.selenium.WebElement;

public class DiscoverPageLocators extends Navigation {
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[@resource-id = 'org.wikipedia.alpha:id/search_container']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchInput;
}
