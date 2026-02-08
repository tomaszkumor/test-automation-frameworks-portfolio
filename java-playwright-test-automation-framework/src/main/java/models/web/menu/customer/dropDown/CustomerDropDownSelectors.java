package models.web.menu.customer.dropDown;

import models.web.navigation.NavigationHeaderAndFooter;

public class CustomerDropDownSelectors extends NavigationHeaderAndFooter {
    String customerDropDownSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][4]//ul[contains(@class, 'dropdown-menu')]";
}
