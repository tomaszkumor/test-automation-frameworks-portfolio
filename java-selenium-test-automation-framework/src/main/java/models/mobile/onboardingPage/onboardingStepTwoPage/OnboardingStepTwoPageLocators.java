package models.mobile.onboardingPage.onboardingStepTwoPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class OnboardingStepTwoPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/fragment_onboarding_skip_button']/following-sibling::android.widget.HorizontalScrollView")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement pageIndicator;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/fragment_onboarding_forward_button']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement forwardButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/action_bar_root']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement background;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/primaryTextView']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/secondaryTextView']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement description;
}
