package models.web.menu.customer.dropDown;

import static utils.logger.Log4J.log;

public class CustomerDropDown extends CustomerDropDownSelectors {
    public CustomerDropDown() {
        check.isVisible(customerDropDownSelector, "Customer drop down");
        log.info("Customer drop down has been displayed.");
    }
}
