package models.numbers.selectThemeWindow;

import models.numbers.systemMenuBar.SystemMenuBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectThemeWindowLocators extends SystemMenuBar {
    @FindBy(xpath = "//XCUIElementTypeScrollView[@ identifier = '_NS:96']")
    WebElement selectThemeWindow;
    @FindBy(xpath = "//XCUIElementTypeStaticText[@identifier = '_NS:11' and @value = 'Podstawowe']/ancestor::XCUIElementTypeTableRow")
    WebElement basicThemesButton;
}
