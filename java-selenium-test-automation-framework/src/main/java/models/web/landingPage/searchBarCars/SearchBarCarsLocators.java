package models.web.landingPage.searchBarCars;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarCarsLocators extends BasePageFactory {
    @FindBy(xpath = "//div[@class = 'most--popular-cars-origin']")
    WebElement airportsContainer;
    @FindBy(xpath = "//ul[@id = 'select2--results']")
    WebElement citiesContainer;
    @FindBy(xpath = "//ul[@id = 'tab']//button[@data-bs-target = '#tab-cars']")
    WebElement carsTab;
    @FindBy(xpath = "//div[@id = 'tab-cars']")
    WebElement carsSearchBar;
    @FindBy(xpath = "//select[contains(@class, 'cars_flights_origin')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']")
    WebElement departureSpan;
    @FindBy(xpath = "//select[contains(@class, 'cars_city')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']")
    WebElement arrivalSpan;
    @FindBy(xpath = "//input[@class = 'select2-search__field']")
    WebElement airportOrCityInput;
    @FindBy(xpath = "//span[@class = 'guest_cars']/ancestor::div[contains(@class, 'dropdown-contain')]")
    WebElement travellersDropDown;
    @FindBy(xpath = "//form[@id = 'cars-search']//button[@type = 'submit']")
    WebElement searchButton;
    @FindBy(xpath = "//input[@id = 'cars_from_date']")
    WebElement pickUpDateInput;
    @FindBy(xpath = "//input[@id = 'cars_to_date']")
    WebElement dropOffDateInput;
    @FindBy(xpath = "//select[@id = 'cars_from_time']")
    WebElement pickUpTimeSelect;
    @FindBy(xpath = "//select[@id = 'cars_to_time']")
    WebElement dropOffTimeSelect;
}
