package models.web.landingPage.searchBarFlights;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarFlightsLocators extends BasePageFactory {
    @FindBy(xpath = "//span[text() = 'flight_takeoff']/ancestor::button")
    WebElement flightsTab;
    @FindBy(xpath = "(//h1)[1]")
    WebElement h1;
    @FindBy(xpath = "//body")
    WebElement body;
    @FindBy(xpath = "//input[@x-ref = 'fromInput']")
    WebElement departureLocationInput;
    @FindBy(xpath = "//input[@x-ref = 'toInput']")
    WebElement arrivalLocationInput;
    @FindBy(xpath = "//input[@name = 'flights_departure_date']")
    WebElement departureDateInput;
    @FindBy(xpath = "//input[@name = 'flights_arrival_date']")
    WebElement returnDateInput;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'Passengers']/ancestor::div[contains(@class, 'form-control')]/div[@class = 'input-dropdown']")
    WebElement passengersDropDown;
    @FindBy(xpath = "//span[text() = 'Search Flights']/..")
    WebElement searchButton;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'Flight Class']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']//span[@x-text = 'getSelectedName()']")
    WebElement flightClassSelect;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'Flight Type']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']//span[@x-text = 'getSelectedName()']")
    WebElement flightTypeSelect;
}
