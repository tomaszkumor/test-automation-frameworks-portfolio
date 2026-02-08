package models.web.landingPage.searchBarVisa;

import actionsFactory.ActionsFactory;

public class SearchBarVisaSelectors extends ActionsFactory {
    String h1Selector = "(//h1)[1]";
    String visaTabSelector = "//span[contains(text(), 'Visa')]/ancestor::button[@role = 'tab']";
    String countriesContainerSelector = "//div[@class = 'input-dropdown-content show']";
    String departureSpanSelector = "//label/text()[normalize-space(.) = 'From Country']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']";
    String arrivalSpanSelector = "//label/text()[normalize-space(.) = 'To Country']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']";
    String arrivalCountryInputSelector = "//label/text()[normalize-space(.) = 'To Country']/ancestor::div[@class = 'form-control']//input[@x-ref = 'searchInput']";
    String departureCountryInputSelector = "//label/text()[normalize-space(.) = 'From Country']/ancestor::div[@class = 'form-control']//input[@x-ref = 'searchInput']";
    String dateInputSelector = "//input[@name = 'travel_date']";
    String searchButtonSelector = "//span[normalize-space(text()) = 'Check Visa']/ancestor::button";
    String countrySelector = "//div[@class = 'input-dropdown-content show']/div";
    String datePickerWindowSelector = "//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]";
    String visaTypeWindowSelector = "//input[@name = 'visa_type']/following-sibling::div[@class = 'input-dropdown-content show']";
    String processingSpeedWindowSelector = "//input[@name = 'processing_speed']/following-sibling::div[@class = 'input-dropdown-content show']";
    String travellersWindowSelector = "//input[@name = 'travelers']/following-sibling::div[@class = 'input-dropdown-content show']";
    String visaTypeValueSelector = "//input[@name = 'visa_type']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text]";
    String processingSpeedValueSelector = "//input[@name = 'processing_speed']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text]";
    String travellersValueSelector = "(//input[@name = 'travelers']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text])[1]";
    String specificTravellerGroupCountLocator = "//span[@x-text = 'travelers']";
}
