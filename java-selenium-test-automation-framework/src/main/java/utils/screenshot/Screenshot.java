package utils.screenshot;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static driver.BaseDriver.getWebDriver;

public class Screenshot {
    public static void attachScreenshot() {
        String name = "screenshot";
        InputStream content = new ByteArrayInputStream(((TakesScreenshot)getWebDriver().getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment(name, content);
    }
}
