package models.web.landingPage;

import models.web.navigation.NavigationHeaderAndFooter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPageLocators extends NavigationHeaderAndFooter {
    @FindBy(xpath = "//span[contains(text(), 'Flights')]/ancestor::button[@role = 'tab']")
    WebElement tabFlights;
    @FindBy(xpath = "//span[contains(text(), 'Hotels')]/ancestor::button[@role = 'tab']")
    WebElement tabHotels;
    @FindBy(xpath = "//span[contains(text(), 'Tours')]/ancestor::button[@role = 'tab']")
    WebElement tabTours;
    @FindBy(xpath = "//span[contains(text(), 'Cars')]/ancestor::button[@role = 'tab']")
    WebElement tabCars;
    @FindBy(xpath = "//span[contains(text(), 'Visa')]/ancestor::button[@role = 'tab']")
    WebElement tabVisa;
}
