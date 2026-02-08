package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewOnboardingPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class YearInReviewOnboardingPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text ='Get started']/../android.widget.Button")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement getStartedButton;
}
