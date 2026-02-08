package models.web.menu.currencyDropDown;

import static utils.logger.Log4J.log;

public class CurrencyDropDown extends CurrencyDropDownLocators {
    public CurrencyDropDown() {
        browser.waitForPageLoaded();
        check.isElementDisplayed(currencyDropDown, 10);
        log.info("Currency drop down has been displayed.");
    }
}
