package models.numbers.systemMenuBar.pagesModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NumbersModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenu[@identifier = '_NS:3409']")
    WebElement numbersModal;
    @FindBy(xpath = "//XCUIElementTypeMenuItem[@identifier = '_NS:1990']")
    WebElement settingsButton;
}
