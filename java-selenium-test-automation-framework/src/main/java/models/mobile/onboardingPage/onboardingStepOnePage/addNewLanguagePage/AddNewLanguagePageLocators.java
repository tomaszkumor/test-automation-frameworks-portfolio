package models.mobile.onboardingPage.onboardingStepOnePage.addNewLanguagePage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class AddNewLanguagePageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/wiki_language_title'])[2]")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement addLanguageButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = 'org.wikipedia.alpha:id/toolbar']/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement backButton;

}
