package models.settingsWindow.fontSizeModal;

import models.settingsWindow.SettingsWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class FontSizeModal extends FontSizeModalLocators {
    public FontSizeModal() {
        check.isElementDisplayed(fontSizeModal, 15);
        log.info("Font size modal has been displayed.");
    }

    public SettingsWindow selectFontSize(String fontSize) {
        WebElement fontSizeButton = getFontSizeButton(fontSize);
        click.clickOnVisibleElement(fontSizeButton, 15);
        log.info("'{} pkt' font size button has been clicked.", fontSize);

        return new SettingsWindow();
    }

    private WebElement getFontSizeButton(String fontSize) {
        By fontButtonLocator = By.xpath("//XCUIElementTypeMenuItem[contains(@title, '" + fontSize + " pkt')]");
        check.isNumberOfElementsEqualTo(fontButtonLocator, 1, 50, 15);

        return getWebDriver().getDriver().findElement(fontButtonLocator);
    }
}
