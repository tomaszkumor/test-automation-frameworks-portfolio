package models.web.landingPage;

import models.web.navigation.NavigationHeaderAndFooter;

public class LandingPageLocators extends NavigationHeaderAndFooter {
    String tabHotelsSelector = "//span[contains(text(), 'Hotels')]/ancestor::button[@role = 'tab']";
    String tabFlightsSelector = "//span[contains(text(), 'Flights')]/ancestor::button[@role = 'tab']";
    String tabToursSelector = "//span[contains(text(), 'Tours')]/ancestor::button[@role = 'tab']";
    String tabCarsSelector = "//span[contains(text(), 'Cars')]/ancestor::button[@role = 'tab']";
    String tabVisaSelector = "//span[contains(text(), 'Visa')]/ancestor::button[@role = 'tab']";
}
