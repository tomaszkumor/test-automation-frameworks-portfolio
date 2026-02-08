package models.mobile.onboardingPage.onboardingStepOnePage.languageSelectionPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LanguageSelectionPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.View[@content-desc = 'Szukaj']/../android.widget.Button")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement findLanguageButton;
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement searchLanguageInput;
}
