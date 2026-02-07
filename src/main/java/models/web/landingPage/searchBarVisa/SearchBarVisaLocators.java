package models.web.landingPage.searchBarVisa;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarVisaLocators extends BasePageFactory {
    @FindBy(xpath = "(//h1)[1]")
    WebElement h1;
    @FindBy(xpath = "//span[contains(text(), 'Visa')]/ancestor::button[@role = 'tab']")
    WebElement visaTab;
    @FindBy(xpath = "//span[@x-text = 'travelers']")
    WebElement travellersCount;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'From Country']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']")
    WebElement departureSpan;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'From Country']/ancestor::div[@class = 'form-control']//input[@x-ref = 'searchInput']")
    WebElement departureCountryInput;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'To Country']/ancestor::div[@class = 'form-control']/div[@class = 'input-dropdown']")
    WebElement arrivalSpan;
    @FindBy(xpath = "//label/text()[normalize-space(.) = 'To Country']/ancestor::div[@class = 'form-control']//input[@x-ref = 'searchInput']")
    WebElement arrivalCountryInput;
    @FindBy(xpath = "//input[@name = 'travel_date']")
    WebElement dateInput;
    @FindBy(xpath = "//span[normalize-space(text()) = 'Check Visa']/ancestor::button")
    WebElement searchButton;
    @FindBy(xpath = "//input[@name = 'travelers']/following-sibling::div[@class = 'input-dropdown-content show']")
    WebElement travellersWindow;
    @FindBy(xpath = "//input[@name = 'visa_type']/following-sibling::div[@class = 'input-dropdown-content show']")
    WebElement visaTypeWindow;
    @FindBy(xpath = "//input[@name = 'processing_speed']/following-sibling::div[@class = 'input-dropdown-content show']")
    WebElement processingSpeedWindow;
    @FindBy(xpath = "//input[@name = 'visa_type']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text]")
    WebElement visaTypeValue;
    @FindBy(xpath = "//input[@name = 'processing_speed']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text]")
    WebElement processingSpeedValue;
    @FindBy(xpath = "(//input[@name = 'travelers']/following-sibling::div[not(@*[name() = 'x-bind:class'])]/span[@x-text])[1]")
    WebElement travellersValue;
}
