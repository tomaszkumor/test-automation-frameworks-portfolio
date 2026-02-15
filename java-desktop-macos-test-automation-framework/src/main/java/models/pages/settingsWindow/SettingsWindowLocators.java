package models.pages.settingsWindow;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsWindowLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeDialog[@identifier = '_NS:42']")
    WebElement settingsWindow;
    @FindBy(xpath = "//XCUIElementTypePopUpButton[@identifier = '_NS:57']")
    WebElement fontSizeButton;
}
