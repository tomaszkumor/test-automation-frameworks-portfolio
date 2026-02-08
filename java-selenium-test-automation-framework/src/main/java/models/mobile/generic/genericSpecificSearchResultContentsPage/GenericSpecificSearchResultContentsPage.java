package models.mobile.generic.genericSpecificSearchResultContentsPage;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static driver.BaseDriver.getAndroidDriver;
import static driver.BaseDriver.getIOSDriver;
import static driver.MobileProperties.getMobileSystem;
import static utils.logger.Log4J.log;

public class GenericSpecificSearchResultContentsPage extends GenericSpecificSearchResultContentsPageLocators {
    @SneakyThrows
    public void tapOnSpecificContentItem(String phrase) {
        check.isElementDisplayed(contentsWindow, 15);
        check.didElementStopMoving(contentsWindow, 500, 15);
        log.info("Contents window has been displayed.");

        By specificContentItemLocator = getSpecificContentItemLocator(phrase);
        int maxScrolls = 10;
        int currentScroll = 0;
        String lastVisibleItemText = "";
        boolean isElementPresentInContentsList;

        while (currentScroll < maxScrolls) {
            isElementPresentInContentsList = check.isElementPresentByLocator(specificContentItemLocator, 50, 3);

            if (isElementPresentInContentsList) {
                WebElement contentsItem = getContentItemElement(phrase);
                mobile.tapOnElement(contentsItem, 15);
                log.info("{} has been tapped in content list.", phrase);

                return;
            }

            List<WebElement> contentItemElements = getContentItemElements();
            String newLastVisibleItemText = contentItemElements.get(contentItemElements.size() - 1).getText();

            if (newLastVisibleItemText.equals(lastVisibleItemText)) {
                throw new Exception(phrase + " not found in content items list.");
            }

            lastVisibleItemText = newLastVisibleItemText;
            mobile.scrollDown(0.8, 0.8, 0.3, 1000);
            Thread.sleep(1000);
            currentScroll++;
        }

        throw new TimeoutException(phrase + " not found after " + maxScrolls + " scrolls.");
    }

    private By getSpecificContentItemLocator(String phrase) {
        String mobileSystem = getMobileSystem();

        return switch (mobileSystem) {
            case "android" ->
                    By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_toc_item_text' and @text='" + phrase + "']");
            case "ios" -> By.xpath("TBD");
            default -> null;
        };
    }

    private List<WebElement> getContentItemElements() {
        String mobileSystem = getMobileSystem();

        return switch (mobileSystem) {
            case "android" ->
                    getAndroidDriver().findElements(By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/page_toc_item_text']"));
            case "ios" -> getIOSDriver().findElements(By.xpath("TBD"));
            default -> null;
        };
    }

    private WebElement getContentItemElement(String phrase) {
        By specificContentItemLocator = getSpecificContentItemLocator(phrase);
        String mobileSystem = getMobileSystem();

        return switch (mobileSystem) {
            case "android" -> getAndroidDriver().findElement(specificContentItemLocator);
            case "ios" -> getIOSDriver().findElement(By.xpath("TBD"));
            default -> null;
        };
    }
}
