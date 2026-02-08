package models.web.menu.agents.dropDown;

import models.web.navigation.NavigationHeaderAndFooter;

public class AgentsDropDownSelectors extends NavigationHeaderAndFooter {
    String agentsDropDownSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][3]//ul[contains(@class, 'dropdown-menu')]";
}
