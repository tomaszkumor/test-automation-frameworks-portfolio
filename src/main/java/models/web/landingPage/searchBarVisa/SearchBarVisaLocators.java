package models.web.landingPage.searchBarVisa;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarVisaLocators extends BasePageFactory {
    @FindBy(xpath = "//ul[@id = 'tab']//button[@data-bs-target = '#tab-visa']")
    WebElement visaTab;
    @FindBy(xpath = "//div[@id = 'tab-visa']")
    WebElement visaSearchBar;
    @FindBy(xpath = "//ul[@id = 'select2--results']")
    WebElement countriesContainer;
    @FindBy(xpath = "//select[contains(@class, 'visa_from')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']")
    WebElement departureSpan;
    @FindBy(xpath = "//select[contains(@class, 'visa_to')]/following-sibling::span[@dir = 'ltr']//span[@id = 'select2--container']")
    WebElement arrivalSpan;
    @FindBy(xpath = "//input[@class = 'select2-search__field']")
    WebElement countryInput;
    @FindBy(xpath = "//form[@id = 'visa-submit']//input[@id = 'date']")
    WebElement dateInput;
    @FindBy(xpath = "//form[@id = 'visa-submit']//button[@type = 'submit']")
    WebElement searchButton;
}
