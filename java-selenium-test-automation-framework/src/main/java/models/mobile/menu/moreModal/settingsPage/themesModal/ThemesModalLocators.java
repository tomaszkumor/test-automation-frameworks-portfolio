package models.mobile.menu.moreModal.settingsPage.themesModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ThemesModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/textSettingsCategory']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement themesHeader;
    @AndroidFindBy(xpath = "//android.widget.Switch[@resource-id = 'org.wikipedia.alpha:id/theme_chooser_match_system_theme_switch']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement fitToSystemThemeSwitch;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/button_theme_sepia']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement changeThemeLightButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/button_theme_sepia']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement changeThemeSepiaButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/button_theme_dark']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement changeThemeDarkButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/button_theme_black']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement changeThemeBlackButton;
}
