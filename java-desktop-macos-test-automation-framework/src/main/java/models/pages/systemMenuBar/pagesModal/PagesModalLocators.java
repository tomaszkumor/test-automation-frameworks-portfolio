package models.pages.systemMenuBar.pagesModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PagesModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenu[@identifier = '_NS:3538']")
    WebElement pagesModal;
    @FindBy(xpath = "//XCUIElementTypeMenuItem[@identifier = '_NS:2240']")
    WebElement settingsButton;
}
