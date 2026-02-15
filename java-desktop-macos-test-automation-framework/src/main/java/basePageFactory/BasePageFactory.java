package basePageFactory;

import actions.*;
import org.openqa.selenium.support.PageFactory;

import static driver.BaseDriver.getWebDriver;

public class BasePageFactory {
    public CheckActions check;
    public ClickActions click;
    public GetActions get;
    public SendActions send;
    public BrowserActions browser;

    protected BasePageFactory() {
        this.check = new CheckActions();
        this.click = new ClickActions();
        this.get = new GetActions();
        this.send = new SendActions();
        this.browser = new BrowserActions();
        initElements();
    }

    private void initElements() {
        PageFactory.initElements(getWebDriver().getDriver(), this);
    }
}
