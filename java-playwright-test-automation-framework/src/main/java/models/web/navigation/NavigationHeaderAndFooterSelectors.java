package models.web.navigation;

import actionsFactory.ActionsFactory;

public class NavigationHeaderAndFooterSelectors extends ActionsFactory {
    String landingPageButtonSelector = "//img[contains(@src, 'logo')]/..";
    String flightsButtonSelector = "//a[normalize-space(text()) = 'Flights']";
    String hotelsButtonSelector = "//a[normalize-space(text()) = 'Hotels']";
    String toursButtonSelector = "//a[normalize-space(text()) = 'Tours']";
    String carsButtonSelector = "//a[normalize-space(text()) = 'Cars']";
    String visaButtonSelector = "//a[normalize-space(text()) = 'Visa']";
    String blogsButtonSelector = "//a[normalize-space(text()) = 'Blogs']";
    String languageButtonSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]";
    String currencyButtonSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][2]";
    String agentsButtonSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][3]";
    String customerButtonSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][4]";
}
