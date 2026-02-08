package utils.tipKiller;

import basePageFactory.BasePageFactory;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static driver.BaseDriver.*;
import static driver.MobileProperties.getMobileSystem;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;
import static utils.screenshot.Screenshot.attachScreenshot;

public class TipKiller extends BasePageFactory {
    public static boolean wasListPageTipAlreadyClosed;
    public static boolean wasSpecificSearchResultTipAlreadyClosed;
    public static boolean wasSearchTipAlreadyClosed;

    @SneakyThrows
    public static void closeSpecificSearchResultPageTip(WebElement element) {
        if (!TipKiller.wasSpecificSearchResultTipAlreadyClosed) {
            Dimension size = getWindowSize();
            int x = size.width / 2;
            int y = size.height / 2;

            Sequence tap = getTapActions(x, y);

            boolean flag;
            int i = 0;

            do {
                performTapAction(tap);

                flag = isElementDisplayed(element, 500, 15);
                i++;
                Thread.sleep(1000);
            } while (!flag || i > 3);

            log.info("Tip has been closed.");

            TipKiller.wasSpecificSearchResultTipAlreadyClosed = true;
        }
    }

    @SneakyThrows
    public static void closeThemesModal(WebElement element) {
        Dimension size = getWindowSize();
        int x = size.width / 2;
        int y = (int) (size.height * 0.2);

        Sequence tap = getTapActions(x, y);

        boolean flag;
        int i = 0;

        do {
            performTapAction(tap);

            log.info("Tapped to escape themes modal window");
            flag = isElementDisplayed(element, 500, 2);
            i++;
            Thread.sleep(1000);
        } while (flag || i > 3);

        log.info("Themes modal window has been closed.");

        By themesHeaderLocator = By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia.alpha:id/textSettingsCategory']");
        assertThat(isNumberOfElementsEqualTo(themesHeaderLocator, 0, 50, 15))
                .as("Themes modal display check")
                .isTrue();
    }

    public static void closeSearchPageTip(WebElement element) {
        if (!TipKiller.wasSearchTipAlreadyClosed) {
            tapOnElement(element);
            TipKiller.wasSearchTipAlreadyClosed = true;
        }
    }

    public static void closeSpecificResultsListPageTip(WebElement element) {
        if (!wasListPageTipAlreadyClosed) {
            tapOnElement(element);
            log.info("All clear button has been tapped.");
            wasListPageTipAlreadyClosed = true;
        }
    }

    private static Sequence getTapActions(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        return tap;
    }

    private static void performTapAction(Sequence tap) {
        String mobileSystem = getMobileSystem();

        switch (mobileSystem) {
            case "android" -> getAndroidDriver().perform(List.of(tap));
            case "ios" -> getIOSDriver().perform(List.of(tap));
        }
    }

    private static void tapOnElement(WebElement element) {
        String mobileSystem = getMobileSystem();

        switch (mobileSystem) {
            case "android" -> {
                try {
                    getAndroidDriver().executeScript("mobile: clickGesture",
                            ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
                } catch (Exception e) {
                    attachScreenshot();
                    throw new RuntimeException("Unable to tap on element: " + e.getMessage());
                }
            }

            case "ios" -> {
                try {
                    Map<String, Object> params = new HashMap<>();
                    params.put("elementId", ((RemoteWebElement) element).getId());
                    params.put("x", 0);
                    params.put("y", 0);
                    getIOSDriver().executeScript("mobile: tap", params);
                } catch (Exception e) {
                    attachScreenshot();
                    throw new RuntimeException("Unable to tap on element: " + e.getMessage());
                }
            }
        }
    }

    private static boolean isElementDisplayed(WebElement element, int interval, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private static boolean isNumberOfElementsEqualTo(By locator, int numberOfElements, int interval, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
            wait.pollingEvery(Duration.ofMillis(interval));
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberOfElements));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private static Dimension getWindowSize() {
        String mobileSystem = getMobileSystem();

        return switch (mobileSystem) {
            case "android" -> getAndroidDriver().manage().window().getSize();
            case "ios" -> getIOSDriver().manage().window().getSize();
            default -> null;
        };
    }
}
