package models.web.navigation;

import constants.header.HeaderDropDown;
import constants.header.HeaderEntrypoint;
import io.qameta.allure.Step;
import models.web.landingPage.LandingPage;
import models.web.menu.agents.dropDown.AgentsDropDown;
import models.web.menu.blogsPage.BlogsPage;
import models.web.menu.carsPage.CarsPage;
import models.web.menu.currencyDropDown.CurrencyDropDown;
import models.web.menu.customer.dropDown.CustomerDropDown;
import models.web.menu.flightsPage.FlightsPage;
import models.web.menu.staysPage.StaysPage;
import models.web.menu.languageDropDown.LanguageDropDown;
import models.web.menu.toursPage.ToursPage;
import models.web.menu.visaPage.VisaPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class NavigationHeaderAndFooter extends NavigationHeaderAndFooterLocators {
    @Step("Click on Landing page button")
    public LandingPage clickOnLandingPageButton() {
        clickOnEntrypoint(HeaderEntrypoint.LANDING_PAGE, landingPageButton);

        return new LandingPage();
    }

    @Step("Click on Flights button")
    public FlightsPage clickOnFlightsPageButton() {
        clickOnEntrypoint(HeaderEntrypoint.FLIGHTS, flightsButton);

        return new FlightsPage();
    }

    @Step("Click on Stays button")
    public StaysPage clickOnStaysButton() {
        clickOnEntrypoint(HeaderEntrypoint.STAYS, staysButton);

        return new StaysPage();
    }

    @Step("Click on Tours button")
    public ToursPage clickOnToursButton() {
        clickOnEntrypoint(HeaderEntrypoint.TOURS, toursButton);

        return new ToursPage();
    }

    @Step("Click on Cars button")
    public CarsPage clickOnCarsButton() {
        clickOnEntrypoint(HeaderEntrypoint.CARS, carsButton);

        return new CarsPage();
    }

    @Step("Click on Visa button")
    public VisaPage clickOnVisaButton() {
        clickOnEntrypoint(HeaderEntrypoint.VISA, visaButton);

        return new VisaPage();
    }

    @Step("Click on Blogs button")
    public BlogsPage clickOnBlogsButton() {
        clickOnEntrypoint(HeaderEntrypoint.BLOGS, blogsButton);

        return new BlogsPage();
    }

    @Step("Click on Searches drop down button")
    public void clickOnSearchesDropDown() {
//        clickOnDropDownButton(HeaderDropDown.SEARCHES, languageButton);

//        return new SearchesDropDown();
    }

    @Step("Click on Languages drop down button")
    public LanguageDropDown clickOnLanguagesDropDown() {
        clickOnDropDownButton(HeaderDropDown.LANGUAGE, languageButton);

        return new LanguageDropDown();
    }

    @Step("Click on Currency drop down button")
    public CurrencyDropDown clickOnCurrencyDropDown() {
        clickOnDropDownButton(HeaderDropDown.CURRENCY, currencyButton);

        return new CurrencyDropDown();
    }

    @Step("Click on Agents drop down button")
    public AgentsDropDown clickOnAgentsDropDown() {
        clickOnDropDownButton(HeaderDropDown.AGENTS, agentsButton);

        return new AgentsDropDown();
    }

    @Step("Click on Customer drop down button")
    public CustomerDropDown clickOnCustomerDropDown() {
        clickOnDropDownButton(HeaderDropDown.CUSTOMER, customerButton);

        return new CustomerDropDown();
    }

    private void clickOnEntrypoint(HeaderEntrypoint entrypoint, WebElement entrypointButton) {
        waitUntilEntrypointIsPresent(entrypoint);
        click.clickOnVisibleElement(entrypointButton, 10);
        logEntrypointButtonClick(entrypoint);
    }

    private void clickOnDropDownButton(HeaderDropDown dropDown, WebElement dropDownButton) {
        waitUntilDropDownButtonIsPresent(dropDown);
        click.clickOnElement(dropDownButton, 10);
        logDropDownButtonClick(dropDown);
    }

    private void waitUntilEntrypointIsPresent(HeaderEntrypoint entrypoint) {
        By entrypointLocator = getEntrypointLocator(entrypoint);
        String entrypointName = getEntrypointName(entrypoint);
        boolean isElementPresent = check.isElementPresentByLocator(entrypointLocator, 10);

        assertThat(isElementPresent)
                .as(entrypointName + " entrypoint presence check.")
                .isTrue();
    }

    private void waitUntilDropDownButtonIsPresent(HeaderDropDown dropDown) {
        By dropDownButtonLocator = getDropDownButtonLocator(dropDown);
        String dropDownButtonName = getDropDownButtonName(dropDown);
        boolean isElementPresent = check.isElementPresentByLocator(dropDownButtonLocator, 10);

        assertThat(isElementPresent)
                .as(dropDownButtonName + " drop down presence check.")
                .isTrue();
    }

    private By getEntrypointLocator(HeaderEntrypoint entrypoint) {
        return switch (entrypoint) {
            case LANDING_PAGE -> By.xpath("//img[contains(@src, 'logo')]/..");
            case FLIGHTS -> By.xpath("//a[normalize-space(text()) = 'Flights']");
            case STAYS -> By.xpath("//a[normalize-space(text()) = 'Stays']");
            case TOURS -> By.xpath("//a[normalize-space(text()) = 'Tours']");
            case CARS -> By.xpath("//a[normalize-space(text()) = 'Cars']");
            case VISA -> By.xpath("//a[normalize-space(text()) = 'Visa']");
            case BLOGS -> By.xpath("//a[normalize-space(text()) = 'Blogs']");
        };
    }

    private By getDropDownButtonLocator(HeaderDropDown dropDown) {
        return switch (dropDown) {
            case LANGUAGE -> By.xpath("//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]");
            case CURRENCY -> By.xpath("//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][2]");
            case AGENTS -> By.xpath("//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][3]");
            case CUSTOMER -> By.xpath("//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][4]");
        };
    }

    private void logEntrypointButtonClick(HeaderEntrypoint entrypoint) {
        String entrypointName = getEntrypointName(entrypoint);
        log.info(entrypointName + " entrypoint has been clicked.");
    }

    private void logDropDownButtonClick(HeaderDropDown dropDown) {
        String dropDownName = getDropDownButtonName(dropDown);
        log.info(dropDownName + " drop down button has been clicked.");
    }

    private String getEntrypointName(HeaderEntrypoint entrypoint) {
        return entrypoint.getName();
    }

    private String getDropDownButtonName(HeaderDropDown dropDown) {
        return dropDown.getName();
    }
}
