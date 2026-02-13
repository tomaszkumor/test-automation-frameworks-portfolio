package tests;

import baseTest.BaseTest;
import models.openFileWindow.OpenFileWindow;
import org.testng.annotations.Test;


public class SmokeTest extends BaseTest {
    @Test(priority = 1)
    public void test() {
        new OpenFileWindow()
                .clickOnNewDocumentButton()
                .clickOnBasicThemesButton()
                .selectEmptyTheme()
                .clickOnCreateButton()
                .clickOnPagesButton()
                .clickOnSettingsButton()
                .clickOnFontSizeButton()
                .selectFontSize("14")
                .checkFontSizeAfterEdit("14");

        //TODO: wprowadz w DP losowanie liczby z zakresu 11-16
    }

    @Test(priority = 2)
    public void test2() {
        new OpenFileWindow()
                //todo: dodaj usuniecie pliku jesli to konieczne
                .clickOnNewDocumentButton()
                .clickOnBasicThemesButton()
                .selectEmptyTheme()
                .clickOnCreateButton()
                .clickOnDocumentNameLabel()
                .checkDocumentNameBeforeEdit()
                .clickOnFilesButton()
                .clickOnChangeNameButton()
                .changeName("huehue")
                .closeChangeNameModal()
                .checkDocumentNameAfterEdit("huehue");
    }

}
