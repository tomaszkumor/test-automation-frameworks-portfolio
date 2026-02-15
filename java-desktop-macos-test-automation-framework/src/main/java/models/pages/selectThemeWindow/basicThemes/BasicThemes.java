package models.pages.selectThemeWindow.basicThemes;

import io.qameta.allure.Step;
import models.pages.textEditor.TextEditor;

import static utils.logger.Log4J.log;

public class BasicThemes extends BasicThemesLocators {
    public BasicThemes() {
        check.isElementDisplayed(basicThemesHeader, 15);
        check.isAttributeEqualTo(basicThemesHeader, "value", "Podstawowe", 15);
        log.info("Basic themes section has been displayed.");
    }

    @Step("Select empty theme")
    public BasicThemes selectEmptyTheme() {
        click.clickOnVisibleElement(emptyThemeButton, 15);
        log.info("Empty theme button has been clicked.");

        return this;
    }

    @Step("Click on create button")
    public TextEditor clickOnCreateButton() {
        click.clickOnElement(createButton, 15);
        log.info("Create button has been clicked.");

        return new TextEditor();
    }
}
