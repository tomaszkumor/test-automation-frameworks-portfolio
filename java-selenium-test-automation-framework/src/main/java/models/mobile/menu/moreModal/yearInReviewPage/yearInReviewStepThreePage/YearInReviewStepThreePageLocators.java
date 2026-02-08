package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepThreePage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class YearInReviewStepThreePageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.View[@content-desc = 'Heart Icon']/..")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement donateButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id = 'org.wikipedia.alpha:id/fragment_onboarding_forward_button']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement forwardButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/action_bar_root']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement background;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView/android.widget.TextView)[1]")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement title;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView/android.widget.TextView)[2]")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement description;
}
