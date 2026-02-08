package actions;

import driver.MobileProperties;
import io.appium.java_client.android.nativekey.KeyEvent;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static driver.BaseDriver.*;
import static utils.keyMapper.KeyMapper.mapper;
import static utils.logger.Log4J.log;
import static utils.screenshot.Screenshot.attachScreenshot;

public class MobileActions extends BaseActions {
    @SneakyThrows
    public void tapOnElement(WebElement element, int duration) {
        tap(waitForElementToBeClickable(element, duration));
    }

    @SneakyThrows
    public void tap(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            throw new Exception("Unable to click on element: " + e.getMessage());
        }
    }

    @SneakyThrows
    private WebElement waitForElementToBeClickable(WebElement element, int duration) {
        try {
            new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration))
                    .until(ExpectedConditions.elementToBeClickable(element));
            return element;
        } catch (Exception e) {
            attachScreenshot();
            throw new Exception("Unable to click on element: " + e.getMessage());
        }
    }

    public void tapOnElement(int width, int height) {
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), width, height));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            String mobileSystem = getMobileSystem();
            switch (mobileSystem) {
                case "android" -> getAndroidDriver().perform(List.of(tap));
                case "ios" -> getIOSDriver().perform(List.of(tap));
            }

        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to tap on element with coordinates: " + e.getMessage());
        }
    }

    public void pressKey(String key) {
        String mobileSystem = getMobileSystem();

        switch (mobileSystem) {
            case "android" -> {
                try {
                    getAndroidDriver().pressKey(new KeyEvent(mapper(key)));
                } catch (Exception e) {
                    attachScreenshot();
                    throw new RuntimeException("Unable to press key on screen keyboard: " + e.getMessage());
                }
            }

            case "ios" -> {
                //TBD
            }
        }
    }

    public void scrollDown(double startXProportion, double startYProportion, double endYProportion, int scrollTime) {
        try {
            Dimension size = getWindowSize();
            int startX = (int) (size.width * startXProportion);
            int startY = (int) (size.height * startYProportion);
            int endY = (int) (size.height * endYProportion);

            performVerticalScroll(startX, startY, endY, scrollTime);
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to scroll down: " + e.getMessage());
        }
    }

    public void hideKeyboard() {
        try {
            String mobileSystem = getMobileSystem();
            switch (mobileSystem) {
                case "android" -> getAndroidDriver().hideKeyboard();
                case "ios" -> getIOSDriver().hideKeyboard();
            }
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to hide keyboard: " + e.getMessage());
        }

        log.info("Keyboard has been hidden.");
    }

    @SneakyThrows
    public void sendKeysToWebElement(WebElement element, String text) {
        new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));

        try {
            element.sendKeys(text);
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to send keys to element: " + e.getMessage());
        }
    }

    @SneakyThrows
    public WebElement getWebElementIfElementIsPresentByLocator(By locator, int duration) {
        return getWebElementIfElementIsPresentByLocator(locator, 500, duration);
    }

    @SneakyThrows
    public WebElement getWebElementIfElementIsPresentByLocator(By locator, int interval, int duration) {
        implicitlyWaitChangeDuration(0);

        String mobileSystem = getMobileSystem();
        switch (mobileSystem) {
            case "android" -> {
                try {
                    WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
                    wait.pollingEvery(Duration.ofMillis(interval));
                    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                } catch (TimeoutException e) {
                    throw new Exception("Unable to get WebElement located as '" + locator + "': " + e.getMessage());
                } finally {
                    setDefaultImplicitlyWait();
                }
            }
            case "ios" -> {
                //TBD
                return null;
            }
            default -> {
                return null;
            }
        }
    }

    @SneakyThrows
    public List<WebElement> getWebElementsIfElementsArePresentByLocator(By locator, int duration) {
        return getWebElementsIfElementsArePresentByLocator(locator, 500, duration);
    }

    @SneakyThrows
    public List<WebElement> getWebElementsIfElementsArePresentByLocator(By locator, int interval, int duration) {
        implicitlyWaitChangeDuration(0);

        String mobileSystem = getMobileSystem();
        switch (mobileSystem) {
            case "android" -> {
                try {
                    WebDriverWait wait = new WebDriverWait(getWebDriver().getDriver(), Duration.ofSeconds(duration));
                    wait.pollingEvery(Duration.ofMillis(interval));
                    return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                } catch (TimeoutException e) {
                    throw new Exception("Unable to get WebElement located as '" + locator + "': " + e.getMessage());
                } finally {
                    setDefaultImplicitlyWait();
                }
            }
            case "ios" -> {
                //TBD
                return null;
            }
            default -> {
                return null;
            }
        }
    }

    public void swipeRight(int duration) {
        String mobileSystem = getMobileSystem();
        switch (mobileSystem) {
            case "android" -> {
                try {
                    Dimension size = getWindowSize();
                    int endX = (int) (size.width * 0.15);
                    int startX = (int) (size.width * 0.85);
                    int centerY = size.height / 2;

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                    Sequence swipe = new Sequence(finger, 0);

                    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
                    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    swipe.addAction(new Pause(finger, Duration.ofMillis(50)));
                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, centerY));
                    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    getAndroidDriver().perform(List.of(swipe));
                } catch (Exception e) {
                    attachScreenshot();
                    throw new RuntimeException("Unable to swipe: " + e.getMessage());
                }
            }
            case "ios" -> {
                //TBD
            }
        }
    }

    private void performVerticalScroll(int startX, int startY, int endY, int scrollTime) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerMove(Duration.ofMillis(scrollTime), PointerInput.Origin.viewport(), startX, endY));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        String mobileSystem = getMobileSystem();

        try {
            switch (mobileSystem) {
                case "android" -> getAndroidDriver().perform(List.of(tap));
                case "ios" -> getIOSDriver().perform(List.of(tap));
            }
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to swipe: " + e.getMessage());
        }
    }

    private Dimension getWindowSize() {
        String mobileSystem = getMobileSystem();
        try {
            return switch (mobileSystem) {
                case "android" -> getAndroidDriver().manage().window().getSize();
                case "ios" -> getIOSDriver().manage().window().getSize();
                default -> null;
            };
        } catch (Exception e) {
            attachScreenshot();
            throw new RuntimeException("Unable to swipe: " + e.getMessage());
        }
    }

    private String getMobileSystem() {
        return MobileProperties.getMobileSystem().toLowerCase();
    }
}
