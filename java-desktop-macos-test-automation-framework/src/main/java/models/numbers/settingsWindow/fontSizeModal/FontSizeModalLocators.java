package models.numbers.settingsWindow.fontSizeModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FontSizeModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypePopUpButton/XCUIElementTypeMenu")
    WebElement fontSizeModal;


}
