package models.web.landingPage.searchBarTours;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarToursLocators extends BasePageFactory {
    @FindBy(xpath = "//ul[@id = 'tab']//button[@data-bs-target = '#tab-tours']")
    WebElement toursTab;
    @FindBy(xpath = "//div[@id = 'tab-tours']")
    WebElement toursSearchBar;
    @FindBy(xpath = "//select[@id = 'tours_city']/following-sibling::span[@dir = 'ltr']//span[@id = 'select2-tours_city-container']")
    WebElement citySpan;
    @FindBy(xpath = "//input[@class = 'select2-search__field']")
    WebElement cityInput;
    @FindBy(xpath = "//form[@id = 'tours-search']//input[@id = 'date']")
    WebElement dateInput;
    @FindBy(xpath = "//span[@class = 'guest_tours']/ancestor::div[contains(@class, 'dropdown-contain')]")
    WebElement travellersDropDown;
    @FindBy(xpath = "//div[@class = 'most--popular-tours']")
    WebElement citiesContainer;
    @FindBy(xpath = "//form[@id = 'tours-search']//button[@type = 'submit']")
    WebElement searchButton;
}
