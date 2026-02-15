package models.numbers.systemMenuBar;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SystemMenuBarLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenuBar/XCUIElementTypeMenuBarItem[@title = 'Numbers']")
    WebElement numbersButton;
    @FindBy(xpath = "//XCUIElementTypeMenuBar/XCUIElementTypeMenuBarItem[@title = 'Plik']")
    WebElement filesButton;
}
