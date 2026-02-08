package basePageFactory;

import actions.*;
import config.TestStackProperties;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static driver.BaseDriver.getWebDriver;

public class BasePageFactory {
    public CheckActions check;
    public ClickActions click;
    public GetActions get;
    public SendActions send;
    public BrowserActions browser;
    public MobileActions mobile;

    protected BasePageFactory() {
        this.check = new CheckActions();
        this.click = new ClickActions();
        this.get = new GetActions();
        this.send = new SendActions();
        this.browser = new BrowserActions();
        this.mobile = new MobileActions();
        initElements();
    }

    private void initElements() {
        String platform = TestStackProperties.getPlatform();
        switch (platform) {
            case "web", "api" -> initElementsForDesktopTest();
            case "mobile" -> initElementsForMobileTest();
        }
    }

    private void initElementsForMobileTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getWebDriver().getDriver()), this);
    }

    private void initElementsForDesktopTest() {
        PageFactory.initElements(getWebDriver().getDriver(), this);
    }
}
