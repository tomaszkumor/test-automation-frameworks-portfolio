package models.web.landingPage;

import io.qameta.allure.Step;
import models.web.landingPage.searchBarCars.SearchBarCars;
import models.web.landingPage.searchBarFlights.SearchBarFlights;
import models.web.landingPage.searchBarHotels.SearchBarHotels;
import models.web.landingPage.searchBarTours.SearchBarTours;
import models.web.landingPage.searchBarVisa.SearchBarVisa;

import static utils.logger.Log4J.log;

public class LandingPage extends LandingPageLocators {
    public LandingPage() {
        checkUrl();
        log.info("Landing page has been displayed.");
    }

    @Step("Click on Flights tab")
    public SearchBarFlights clickOnFlightsTab() {
        click.leftClick(tabFlightsSelector, "lights tab");
        log.info("Flights tab has been clicked.");

        return new SearchBarFlights();
    }

    @Step("Click on Hotels tab")
    public SearchBarHotels clickOnHotelsTab() {
        click.leftClick(tabHotelsSelector, "Hotels tab");
        log.info("Hotels tab has been clicked.");

        return new SearchBarHotels();
    }

    @Step("Click on Tours tab")
    public SearchBarTours clickOnToursTab() {
        click.leftClick(tabToursSelector, "Tours tab");
        log.info("Tours tab has been clicked.");

        return new SearchBarTours();
    }

    @Step("Click on Cars tab")
    public SearchBarCars clickOnCarsTab() {
        click.leftClick(tabCarsSelector, "Cars tab");
        log.info("Cars tab has been clicked.");

        return new SearchBarCars();
    }

    @Step("Click on Visa tab")
    public SearchBarVisa clickOnVisaTab() {
        click.leftClick(tabVisaSelector, "Visa tab");
        log.info("Visa tab has been clicked.");

        return new SearchBarVisa();
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/";
        check.doesUrlContain(expectedUrl);
    }
}
