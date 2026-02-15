package tests;

import baseTest.BaseTest;
import dataProviders.dataProviders.DesktopDP;
import dataProviders.dataProvidersModels.DesktopModel;
import io.qameta.allure.Description;
import listeners.DriverListener;
import models.pages.openFileWindow.OpenFileWindow;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.retryAnalyzer.RetryAnalyzer;

@Listeners(value = {DriverListener.class})
public class PagesTests extends BaseTest {
    @Test(dataProvider = "desktop", dataProviderClass = DesktopDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Open settings and change font size")
    public void openSettingsAndChangeFontSize(DesktopModel desktopModel) {
        new OpenFileWindow()
                .clickOnNewDocumentButton()
                .clickOnBasicThemesButton()
                .selectEmptyTheme()
                .clickOnCreateButton()
                .clickOnPagesButton()
                .clickOnSettingsButton()
                .clickOnFontSizeButton()
                .selectFontSize(desktopModel)
                .checkFontSizeAfterEdit(desktopModel);
    }

    @Test(dataProvider = "desktop", dataProviderClass = DesktopDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Change document name")
    public void changeDocumentName(DesktopModel desktopModel) {
        new OpenFileWindow()
                .deleteFileIfNecessary(desktopModel)
                .clickOnNewDocumentButton()
                .clickOnBasicThemesButton()
                .selectEmptyTheme()
                .clickOnCreateButton()
                .hideDocumentOptions()
                .clickOnDocumentNameLabel()
                .checkDocumentNameBeforeEdit()
                .clickOnFilesButton()
                .clickOnChangeNameButton()
                .changeName(desktopModel)
                .closeChangeNameModal()
                .checkDocumentNameAfterEdit(desktopModel);
    }

    @Test(dataProvider = "desktop", dataProviderClass = DesktopDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Change document scale")
    public void changeDocumentScale(DesktopModel desktopModel) {
        new OpenFileWindow()
                .clickOnNewDocumentButton()
                .clickOnBasicThemesButton()
                .selectEmptyTheme()
                .clickOnCreateButton()
                .hideDocumentOptions()
                .clickOnScaleButton()
                .selectScale(desktopModel)
                .checkScale(desktopModel);
    }
}
