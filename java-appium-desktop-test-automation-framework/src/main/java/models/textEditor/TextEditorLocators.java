package models.textEditor;

import models.systemMenuBar.SystemMenuBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextEditorLocators extends SystemMenuBar {
    @FindBy(xpath = "//XCUIElementTypeToolbar")
    WebElement toolbar;
    @FindBy(xpath = "//XCUIElementTypeWindow/XCUIElementTypeStaticText")
    WebElement documentNameLabel;
    //todo: allure-results do gitignore
}
