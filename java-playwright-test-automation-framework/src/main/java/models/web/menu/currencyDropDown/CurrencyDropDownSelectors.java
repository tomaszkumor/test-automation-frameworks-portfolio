package models.web.menu.currencyDropDown;

import models.web.navigation.NavigationHeaderAndFooter;

public class CurrencyDropDownSelectors extends NavigationHeaderAndFooter {
    String currencyDropDownSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][2]//ul[contains(@class, 'dropdown-menu')]";
}
