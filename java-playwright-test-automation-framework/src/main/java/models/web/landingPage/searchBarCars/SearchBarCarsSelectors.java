package models.web.landingPage.searchBarCars;

import actionsFactory.ActionsFactory;

public class SearchBarCarsSelectors extends ActionsFactory {
    String airportsContainerSelector = "//div[@class = 'most--popular-cars-origin']";
    String citiesContainerSelector = "//ul[@id = 'select2--results']";
    String carsTabSelector = "//ul[@id = 'tab']//button[@data-bs-target = '#tab-cars']";
    String carsSearchBarSelector = "//div[@id = 'tab-cars']";
    String departureSpanSelector = "//select[contains(@class, 'cars_flights_origin')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']";
    String arrivalSpanSelector = "//select[contains(@class, 'cars_city')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']";
    String airportOrCityInputSelector = "//input[@class = 'select2-search__field']";
    String travellersDropDownSelector = "//span[@class = 'guest_cars']/ancestor::div[contains(@class, 'dropdown-contain')]";
    String searchButtonSelector = "//form[@id = 'cars-search']//button[@type = 'submit']";
    String pickUpDateInputSelector = "//input[@id = 'cars_from_date']";
    String dropOffDateInputSelector = "//input[@id = 'cars_to_date']";
    String pickUpTimeSelectSelector = "//select[@id = 'cars_from_time']";
    String dropOffTimeSelectSelector = "//select[@id = 'cars_to_time']";
    String datePickerWindowHeaderSelector = "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//th[@class = 'switch']";
    String datePickerWindowSelector = "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]";
    String checkInDateInputSelector = "//input[@id = 'checkin']";
    String checkOutDateInputSelector = "//input[@id = 'checkout']";
    String citySelector = "//ul[@id = 'select2--results']/li";
    String airportSelector = "//div[@class = 'most--popular-cars-origin']/div";
    String h4Selector = "(//h4)[1]";
    String travellersWindowSelector = "//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]";

}
