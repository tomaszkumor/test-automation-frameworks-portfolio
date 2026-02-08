package models.mobile.menu.moreModal.yearInReviewPage.yearInReviewStepSevenPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class YearInReviewStepSevenPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Share highlights']/../android.widget.Button")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement shareHighlightsButton;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc = 'Navigate Right']/../android.widget.Button")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement forwardButton;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView/android.widget.TextView)[1]")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement title;
}
