package models.web.menu.currencyDropDown;

import static utils.logger.Log4J.log;

public class CurrencyDropDown extends CurrencyDropDownSelectors {
    public CurrencyDropDown() {
        check.isVisible(currencyDropDownSelector, "Currency drop down");
        log.info("Currency drop down has been displayed.");
    }
}
