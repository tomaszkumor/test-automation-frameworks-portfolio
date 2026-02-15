package models.pages.settingsWindow.fontSizeModal;

import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Step;
import models.pages.settingsWindow.SettingsWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getWebDriver;
import static utils.logger.Log4J.log;

public class FontSizeModal extends FontSizeModalLocators {
    public FontSizeModal() {
        check.isElementDisplayed(fontSizeModal, 15);
        log.info("Font size modal has been displayed.");
    }

    @Step("Select font size")
    public SettingsWindow selectFontSize(DesktopModel desktopModel) {
        String fontSize = getExpectedFontSizeFromDataProvider(desktopModel);
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

    private String getExpectedFontSizeFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getFontSize();
    }
}
