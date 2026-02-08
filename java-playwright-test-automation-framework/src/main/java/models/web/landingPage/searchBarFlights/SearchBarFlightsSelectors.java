package models.web.landingPage.searchBarFlights;

import actionsFactory.ActionsFactory;

public class SearchBarFlightsSelectors extends ActionsFactory {
    String flightsTabSelector = "//span[text() = 'flight_takeoff']/ancestor::button";
    String h1Selector = "(//h1)[1]";
    String bodySelector = "//body";
    String flightsSearchBarSelector = "//div[@x-data = 'flightSearchData()']/..";
    String flightTypeSelectSelector = "//label/text()[normalize-space(.) = 'Flight Type']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']//span[@x-text = 'getSelectedName()']";
    String flightClassSelectSelector = "//label/text()[normalize-space(.) = 'Flight Class']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']//span[@x-text = 'getSelectedName()']";
    String passengersDropDownSelector = "//label/text()[normalize-space(.) = 'Passengers']/ancestor::div[contains(@class, 'form-control')]/div[@class = 'input-dropdown']";
    String departureLocationInputSelector = "//input[@x-ref = 'fromInput']";
    String arrivalLocationInputSelector = "//input[@x-ref = 'toInput']";
    String departureDateInputSelector = "//input[@name = 'flights_departure_date']";
    String returnDateInputSelector = "//input[@name = 'flights_arrival_date']";
    String searchButtonSelector = "//span[text() = 'Search Flights']/..";
    String datePickerWindowSelector = "//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]";
    String travellersWindowSelector = "//input[@name = 'passengers']/following-sibling::div[contains(@class, 'input-dropdown-content')]";
}
