package models.pages.systemMenuBar.filesModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilesModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenu[@identifier = '_NS:3563']")
    WebElement filesModal;
    @FindBy(xpath = "//XCUIElementTypeMenuItem[@identifier = 'renameDocument:']")
    WebElement changeNameButton;
}
