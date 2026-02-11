package utils.customExpectedCondition;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedCondition {
    public static ExpectedCondition<Boolean> elementStopsMoving(WebElement element) {
        return new ExpectedCondition<>() {
            private Point lastPoint = null;

            @Override
            public Boolean apply(WebDriver driver) {
                Point current = element.getLocation();

                if (lastPoint != null && lastPoint.equals(current)) {
                    return true;
                }

                lastPoint = current;

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return false;
            }

            @Override
            public String toString() {
                return "Element to stop moving: " + element;
            }
        };
    }
}
