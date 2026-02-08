package models.web.navigation;

import basePageFactory.BasePageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationHeaderAndFooterLocators extends BasePageFactory {
    @FindBy(xpath = "//img[contains(@src, 'logo')]/..")
    WebElement landingPageButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Flights']")
    WebElement flightsButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Stays']")
    WebElement staysButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Tours']")
    WebElement toursButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Cars']")
    WebElement carsButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Visa']")
    WebElement visaButton;
    @FindBy(xpath = "//a[normalize-space(text()) = 'Blogs']")
    WebElement blogsButton;
    @FindBy(xpath = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]")
    WebElement languageButton;
    @FindBy(xpath = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][2]")
    WebElement currencyButton;
    @FindBy(xpath = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][3]")
    WebElement agentsButton;
    @FindBy(xpath = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][4]")
    WebElement customerButton;
}
