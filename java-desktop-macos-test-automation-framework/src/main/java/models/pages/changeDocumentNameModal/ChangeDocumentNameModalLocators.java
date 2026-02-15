package models.pages.changeDocumentNameModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangeDocumentNameModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypePopover")
    WebElement changeNameModal;
    @FindBy(xpath = "//XCUIElementTypePopover/XCUIElementTypeTextField[1]")
    WebElement documentNameInput;
}
