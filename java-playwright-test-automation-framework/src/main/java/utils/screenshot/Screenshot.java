package utils.screenshot;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;

public class Screenshot {
    public static void attachScreenshot() {
        String name = "screenshot";
        byte[] bytes = getPlaywrightInstance().getPage().screenshot();
        Allure.addAttachment(name, new ByteArrayInputStream(bytes));
    }
}
