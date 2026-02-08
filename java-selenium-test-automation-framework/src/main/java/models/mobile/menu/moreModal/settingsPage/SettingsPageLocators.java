package models.mobile.menu.moreModal.settingsPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'org.wikipedia.alpha:id/toolbar']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement settingsToolbar;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Skórka aplikacji']/..")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement themesButton;
}
