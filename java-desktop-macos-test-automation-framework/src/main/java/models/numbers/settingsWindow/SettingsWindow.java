package models.numbers.settingsWindow;

import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Step;
import models.numbers.settingsWindow.fontSizeModal.FontSizeModal;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SettingsWindow extends SettingsWindowLocators {
    public SettingsWindow() {
        check.isElementDisplayed(settingsWindow, 15);
        log.info("Settings window is displayed.");
    }

    @Step("Click on font size button")
    public FontSizeModal clickOnFontSizeButton() {
        click.clickOnVisibleElement(fontSizeButton, 15);
        log.info("Font size button has been clicked.");

        return new FontSizeModal();
    }

    @Step("Check font size after edit")
    public SettingsWindow checkFontSizeAfterEdit(DesktopModel desktopModel) {
        String expectedFontSize = getExpectedFontSizeFromDataProvider(desktopModel);
        String actualFontSize = get.getValueFromElement(fontSizeButton);

        assertThat(actualFontSize)
                .as("Font size check after edit")
                .isEqualTo(expectedFontSize.concat(" pkt"));

        log.info("Font size after edit meets expected value.");

        return this;
    }

    private String getExpectedFontSizeFromDataProvider(DesktopModel desktopModel) {
        return desktopModel.getFontSize();
    }
}
