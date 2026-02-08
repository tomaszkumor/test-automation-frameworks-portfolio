package models.web.landingPage.searchBarTours;

import actionsFactory.ActionsFactory;

public class SearchBarToursSelectors extends ActionsFactory {
    String toursTabSelector = "//ul[@id = 'tab']//button[@data-bs-target = '#tab-tours']";
    String toursSearchBarSelector = "//div[@id = 'tab-tours']";
    String citySpanSelector = "//select[@id = 'tours_city']/following-sibling::span[@dir = 'ltr']//span[@id = 'select2-tours_city-container']";
    String cityInputSelector = "//input[@class = 'select2-search__field']";
    String dateInputSelector = "//form[@id = 'tours-search']//input[@id = 'date']";
    String travellersDropDownSelector = "//span[@class = 'guest_tours']/ancestor::div[contains(@class, 'dropdown-contain')]";
    String citiesContainerSelector = "//div[@class = 'most--popular-tours']";
    String searchButtonSelector = "//form[@id = 'tours-search']//button[@type = 'submit']";
    String citySelector = "//div[@class = 'most--popular-tours']/div";
    String datePickerWindowHeaderSelector = "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//th[@class = 'switch']";
    String datePickerWindowSelector = "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]";
    String travellersWindowSelector = "//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]";
    String h4Selector = "(//h4)[1]";
}
