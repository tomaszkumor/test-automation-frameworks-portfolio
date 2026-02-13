package models.selectThemeWindow;

import models.selectThemeWindow.basicThemes.BasicThemes;

import static utils.logger.Log4J.log;

public class SelectThemeWindow extends SelectThemeWindowLocators {
    public SelectThemeWindow() {
        check.isElementDisplayed(selectThemeWindow, 15);
        log.info("Select theme window has been displayed.");
    }

    public BasicThemes clickOnBasicThemesButton() {
        click.clickOnVisibleElement(basicThemesButton, 15);
        log.info("Basic themes button has been clicked.");

        return new BasicThemes();
    }
}
