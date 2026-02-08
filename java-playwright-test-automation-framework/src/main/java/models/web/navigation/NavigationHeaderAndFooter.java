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
import models.web.menu.hotelsPage.HotelsPage;
import models.web.menu.languageDropDown.LanguageDropDown;
import models.web.menu.toursPage.ToursPage;
import models.web.menu.visaPage.VisaPage;
import static utils.logger.Log4J.log;

public class NavigationHeaderAndFooter extends NavigationHeaderAndFooterSelectors {
    @Step("Click on Landing page button")
    public LandingPage clickOnLandingPageButton() {
        clickOnEntrypoint(HeaderEntrypoint.LANDING_PAGE, landingPageButtonSelector);

        return new LandingPage();
    }

    @Step("Click on Flights button")
    public FlightsPage clickOnFlightsPageButton() {
        clickOnEntrypoint(HeaderEntrypoint.FLIGHTS, flightsButtonSelector);

        return new FlightsPage();
    }

    @Step("Click on Hotels button")
    public HotelsPage clickOnHotelsButton() {
        clickOnEntrypoint(HeaderEntrypoint.HOTELS, hotelsButtonSelector);

        return new HotelsPage();
    }

    @Step("Click on Tours button")
    public ToursPage clickOnToursButton() {
        clickOnEntrypoint(HeaderEntrypoint.TOURS, toursButtonSelector);

        return new ToursPage();
    }

    @Step("Click on Cars button")
    public CarsPage clickOnCarsButton() {
        clickOnEntrypoint(HeaderEntrypoint.CARS, carsButtonSelector);

        return new CarsPage();
    }

    @Step("Click on Visa button")
    public VisaPage clickOnVisaButton() {
        clickOnEntrypoint(HeaderEntrypoint.VISA, visaButtonSelector);

        return new VisaPage();
    }

    @Step("Click on Blogs button")
    public BlogsPage clickOnBlogsButton() {
        clickOnEntrypoint(HeaderEntrypoint.BLOGS, blogsButtonSelector);

        return new BlogsPage();
    }

    @Step("Click on Languages drop down button")
    public LanguageDropDown clickOnLanguagesDropDown() {
        clickOnDropDownButton(HeaderDropDown.LANGUAGE, languageButtonSelector);

        return new LanguageDropDown();
    }

    @Step("Click on Currency drop down button")
    public CurrencyDropDown clickOnCurrencyDropDown() {
        clickOnDropDownButton(HeaderDropDown.CURRENCY, currencyButtonSelector);

        return new CurrencyDropDown();
    }

    @Step("Click on Agents drop down button")
    public AgentsDropDown clickOnAgentsDropDown() {
        clickOnDropDownButton(HeaderDropDown.AGENTS, agentsButtonSelector);

        return new AgentsDropDown();
    }

    @Step("Click on Customer drop down button")
    public CustomerDropDown clickOnCustomerDropDown() {
        clickOnDropDownButton(HeaderDropDown.CUSTOMER, customerButtonSelector);

        return new CustomerDropDown();
    }

    private void clickOnEntrypoint(HeaderEntrypoint entrypoint, String entrypointButtonSelector) {
        waitUntilEntrypointIsPresent(entrypoint);
        click.leftClick(entrypointButtonSelector, "Entrypoint button");
        logEntrypointButtonClick(entrypoint);
    }

    private void clickOnDropDownButton(HeaderDropDown dropDown, String dropDownButtonSelector) {
        waitUntilDropDownButtonIsPresent(dropDown);
        click.leftClick(dropDownButtonSelector, "Drop down button");
        logDropDownButtonClick(dropDown);
    }

    private void waitUntilEntrypointIsPresent(HeaderEntrypoint entrypoint) {
        String entrypointSelector = getEntrypointSelector(entrypoint);
        String entrypointName = getEntrypointName(entrypoint);
        check.isElementPresent(entrypointSelector, entrypointName);
    }

    private void waitUntilDropDownButtonIsPresent(HeaderDropDown dropDown) {
        String dropDownButtonSelector = getDropDownButtonSelector(dropDown);
        String dropDownButtonName = getDropDownButtonName(dropDown);
        check.isElementPresent(dropDownButtonSelector, dropDownButtonName);
    }

    private String getEntrypointSelector(HeaderEntrypoint entrypoint) {
        return switch (entrypoint) {
            case LANDING_PAGE -> "//img[contains(@src, 'logo')]/..";
            case FLIGHTS -> "//a[normalize-space(text()) = 'Flights']";
            case HOTELS -> "//a[normalize-space(text()) = 'Hotels']";
            case TOURS -> "//a[normalize-space(text()) = 'Tours']";
            case CARS -> "//a[normalize-space(text()) = 'Cars']";
            case VISA -> "//a[normalize-space(text()) = 'Visa']";
            case BLOGS -> "//a[normalize-space(text()) = 'Blogs']";
        };
    }

    private String getDropDownButtonSelector(HeaderDropDown dropDown) {
        return switch (dropDown) {
            case LANGUAGE -> "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]";
            case CURRENCY -> "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][2]";
            case AGENTS -> "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][3]";
            case CUSTOMER -> "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][4]";
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
