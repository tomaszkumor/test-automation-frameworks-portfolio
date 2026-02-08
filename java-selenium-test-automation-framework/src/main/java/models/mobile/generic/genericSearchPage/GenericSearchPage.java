package models.mobile.generic.genericSearchPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.BaseDriver.getAndroidDriver;
import static driver.BaseDriver.getIOSDriver;
import static driver.MobileProperties.getMobileSystem;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class GenericSearchPage extends GenericSearchPageLocators {
    public void typePhraseToSearchBar(String phrase) {
        mobile.sendKeysToWebElement(searchInput, phrase);

        String actualValue = get.getTextFromElement(searchInput);
        assertThat(actualValue).as("Search bar value check after input").isEqualTo(phrase);
    }

    public void tapOnSpecificSearchResult(String phrase) {
        WebElement specificResult = getSpecificResultElement(phrase);
        mobile.tapOnElement(specificResult, 15);
        log.info("{} search result  has been tapped.", phrase);
    }

    private WebElement getSpecificResultElement(String phrase) {
        By specificResultLocator = By.xpath("//android.widget.FrameLayout[@resource-id = 'org.wikipedia.alpha:id/fragment_search_results']//android.widget.TextView[@text = '" + phrase + "']");
        String mobileSystem = getMobileSystem();

        return switch (mobileSystem) {
            case "android" -> getAndroidDriver().findElement(specificResultLocator);
            case "ios" -> getIOSDriver().findElement(specificResultLocator);
            default -> null;
        };
    }
}
