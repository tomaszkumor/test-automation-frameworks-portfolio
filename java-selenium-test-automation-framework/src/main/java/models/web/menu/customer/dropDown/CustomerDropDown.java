package models.web.menu.customer.dropDown;

import static utils.logger.Log4J.log;

public class CustomerDropDown extends CustomerDropDownLocators {
    public CustomerDropDown() {
        browser.waitForPageLoaded();
        check.isElementDisplayed(customerDropDown, 10);
        log.info("Customer drop down has been displayed.");
    }
}
