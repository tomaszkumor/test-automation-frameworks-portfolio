package models.pages.systemMenuBar;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SystemMenuBarLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenuBar/XCUIElementTypeMenuBarItem[@title = 'Pages']")
    WebElement pagesButton;
    @FindBy(xpath = "//XCUIElementTypeMenuBar/XCUIElementTypeMenuBarItem[@title = 'Plik']")
    WebElement filesButton;
}
