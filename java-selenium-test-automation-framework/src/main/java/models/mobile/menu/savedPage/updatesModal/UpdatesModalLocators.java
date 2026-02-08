package models.mobile.menu.savedPage.updatesModal;

import basePageFactory.BasePageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class UpdatesModalLocators extends BasePageFactory {
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View")
    @iOSXCUITFindBy(xpath = "TBD")
    WebElement updatesModal;
}
