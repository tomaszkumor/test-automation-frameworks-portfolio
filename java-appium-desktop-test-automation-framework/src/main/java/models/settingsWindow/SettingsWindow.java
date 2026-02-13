package models.settingsWindow;

import models.settingsWindow.fontSizeModal.FontSizeModal;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SettingsWindow extends SettingsWindowLocators {
    public SettingsWindow() {
        check.isElementDisplayed(settingsWindow, 15);
        log.info("Settings window is displayed.");
    }

    public FontSizeModal clickOnFontSizeButton() {
        click.clickOnVisibleElement(fontSizeButton, 15);
        log.info("Font size button has been clicked.");

        return new FontSizeModal();
    }

    public SettingsWindow checkFontSizeAfterEdit(String expectedFontSize) {
        String actualFontSize = get.getValueFromElement(fontSizeButton);

        assertThat(actualFontSize)
                .as("Font size check after edit")
                .isEqualTo(expectedFontSize.concat(" pkt"));

        log.info("Font size after edit meets expected value.");

        return this;
    }
}
