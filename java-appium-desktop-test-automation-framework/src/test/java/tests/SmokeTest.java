package tests;

import baseTest.BaseTest;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static driver.BaseDriver.getWebDriver;

public class SmokeTest extends BaseTest {
    @Test
    @SneakyThrows
    public void Test() {
        Thread.sleep(20000);

        System.out.println(getWebDriver().getDriver().getPageSource());
    }

}
