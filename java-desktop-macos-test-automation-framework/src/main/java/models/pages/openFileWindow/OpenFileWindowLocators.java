package models.pages.openFileWindow;

import models.pages.systemMenuBar.SystemMenuBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenFileWindowLocators extends SystemMenuBar {
    @FindBy(xpath = "//XCUIElementTypeWindow/XCUIElementTypeSplitGroup")
    WebElement openFileWindow;
    @FindBy(xpath = "//XCUIElementTypeWindow/XCUIElementTypeSplitGroup/XCUIElementTypeButton[@identifier = 'NewDocumentButton']")
    WebElement newDocumentButton;
    @FindBy(xpath = "//XCUIElementTypeMenu/XCUIElementTypeMenuItem[@identifier = 'cmdMoveToTrash:']")
    WebElement removeButton;
}
