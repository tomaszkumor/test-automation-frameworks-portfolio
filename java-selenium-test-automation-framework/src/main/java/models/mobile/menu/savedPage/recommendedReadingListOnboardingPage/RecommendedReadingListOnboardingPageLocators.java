package models.mobile.menu.savedPage.recommendedReadingListOnboardingPage;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RecommendedReadingListOnboardingPageLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.View[@content-desc = 'Choose for me based on my saved articles']/..")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement chooseBasedOnSavedArticlesButton;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc = 'Dalej']")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement nextButton;
}
