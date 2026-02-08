package models.mobile.menu.moreModal.yearInReviewPage.introModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class IntroModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "(//android.widget.FrameLayout[@resource-id = 'android:id/content']//android.widget.Button)[2]")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement continueWithoutLoggingInButton;
}
