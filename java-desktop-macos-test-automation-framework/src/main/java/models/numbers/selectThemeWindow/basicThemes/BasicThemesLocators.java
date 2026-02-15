package models.numbers.selectThemeWindow.basicThemes;

import models.numbers.systemMenuBar.SystemMenuBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicThemesLocators extends SystemMenuBar {
    @FindBy(xpath = "//XCUIElementTypeCollectionView/following-sibling::XCUIElementTypeStaticText")
    WebElement basicThemesHeader;
    @FindBy(xpath = "//XCUIElementTypeStaticText[@value = '\u200E\u2068Pusty\u2069']/ancestor::XCUIElementTypeButton")
    WebElement emptyThemeButton;
    @FindBy(xpath = "//XCUIElementTypeButton[@identifier = '_NS:33']")
    WebElement createButton;
}
