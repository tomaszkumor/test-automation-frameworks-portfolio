package models.numbers.systemMenuBar.filesModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilesModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenu[@identifier = '_NS:3434']")
    WebElement filesModal; //todo: sprawdz czy tu jest takie samo
    @FindBy(xpath = "//XCUIElementTypeMenuItem[@identifier = 'renameDocument:']")
    WebElement changeNameButton;
}
