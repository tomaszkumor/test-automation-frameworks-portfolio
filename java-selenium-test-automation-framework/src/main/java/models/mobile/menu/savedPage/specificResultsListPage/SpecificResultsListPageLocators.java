package models.mobile.menu.savedPage.specificResultsListPage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.mobile.navigation.Navigation;
import org.openqa.selenium.WebElement;

public class SpecificResultsListPageLocators extends Navigation {
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/buttonView']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement allClearButton;
}
