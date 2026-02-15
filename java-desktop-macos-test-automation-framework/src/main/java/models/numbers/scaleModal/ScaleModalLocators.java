package models.numbers.scaleModal;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScaleModalLocators extends BasePageFactory {
    @FindBy(xpath = "//XCUIElementTypeMenuButton[@label = 'Zoom']/XCUIElementTypeMenu")
    WebElement scaleModal;
}
