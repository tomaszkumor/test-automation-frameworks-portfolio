package models.web.landingPage.searchBarStays;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBarStaysLocators extends BasePageFactory {
    @FindBy(xpath = "(//h1)[1]")
    WebElement h1;
    @FindBy(xpath = "//span[contains(text(), 'Stays')]/ancestor::button[@role = 'tab']")
    WebElement staysTab;
    @FindBy(xpath = "//div[@x-data = 'hotelSearchData()']//input[@x-ref = 'destinationInput']")
    WebElement destinationInput;
    @FindBy(xpath = "//input[@name = 'checkin_date']")
    WebElement checkInDateInput;
    @FindBy(xpath = "//input[@name = 'checkout_date']")
    WebElement checkOutDateInput;
    @FindBy(xpath = "//div[@x-data = 'guestsRoomsDropdown()']")
    WebElement accommodationDropDown;
    @FindBy(xpath = "//div[@x-data = 'nationalityDropdown()']")
    WebElement nationalityDropDown;
    @FindBy(xpath = "//input[@x-ref = 'nationalitySearch']")
    WebElement nationalityInput;
    @FindBy(xpath = "//span[text() = 'Search Hotels']/ancestor::button")
    WebElement searchButton;
}
