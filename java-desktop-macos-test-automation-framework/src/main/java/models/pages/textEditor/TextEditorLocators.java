package models.pages.textEditor;

import models.pages.systemMenuBar.SystemMenuBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextEditorLocators extends SystemMenuBar {
    @FindBy(xpath = "//XCUIElementTypeToolbar")
    WebElement toolbar;
    @FindBy(xpath = "//XCUIElementTypeWindow/XCUIElementTypeStaticText")
    WebElement documentNameLabel;
    @FindBy(xpath = "//XCUIElementTypeMenuButton[@label = 'Zoom']/XCUIElementTypeMenuButton")
    WebElement scaleButton;
    @FindBy(xpath = "//XCUIElementTypeRadioButton[@label = 'Format']")
    WebElement documentOptionsButton;
}
