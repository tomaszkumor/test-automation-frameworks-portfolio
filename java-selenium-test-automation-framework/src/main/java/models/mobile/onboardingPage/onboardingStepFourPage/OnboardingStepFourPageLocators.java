package models.mobile.onboardingPage.onboardingStepFourPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class OnboardingStepFourPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/fragment_onboarding_done_button']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement doneButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/primaryTextView']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/secondaryTextView']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement description;
}
