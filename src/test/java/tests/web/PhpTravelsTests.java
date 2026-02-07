package tests.web;

import baseTest.BaseTest;
import dataProviders.dataProviders.web.landingPage.LandingPageDP;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Description;
import listeners.DriverListener;
import models.web.landingPage.LandingPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.retryAnalyzer.RetryAnalyzer;

@Listeners(value = {DriverListener.class})
public class PhpTravelsTests extends BaseTest {
    @Test(dataProvider = "searchForFlights", dataProviderClass = LandingPageDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Web. Search for flights via landing page search bar")
    public void searchForFlights(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnFlightsTab()
                .selectFlightDestination(phpTravelsModel)
                .selectCabinClass(phpTravelsModel)
                .selectDepartureLocation(phpTravelsModel)
                .selectArrivalLocation(phpTravelsModel)
                .selectDepartureDate(phpTravelsModel)
                .selectReturnDate(phpTravelsModel)
                .selectTravellers(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForHotels", dataProviderClass = LandingPageDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Web. Search for hotels via landing page search bar")
    public void searchForHotels(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnHotelsTab()
                .selectCity(phpTravelsModel)
                .selectCheckInDate(phpTravelsModel)
                .selectCheckOutDate(phpTravelsModel)
                .selectAccommodation(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForTours", dataProviderClass = LandingPageDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Web. Search for tours via landing page search bar")
    public void searchForTours(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnToursTab()
                .selectCity(phpTravelsModel)
                .selectDate(phpTravelsModel)
                .selectTravellers(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForCars", dataProviderClass = LandingPageDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Web. Search for cars via landing page search bar")
    public void searchForCars(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnCarsTab()
                .selectDepartureAirport(phpTravelsModel)
                .selectArrivalCity(phpTravelsModel)
                .selectPickUpDate(phpTravelsModel)
                .selectPickUpTime(phpTravelsModel)
                .selectDropOffDate(phpTravelsModel)
                .selectDropOffTime(phpTravelsModel)
                .selectTravellers(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForVisa", dataProviderClass = LandingPageDP.class, retryAnalyzer = RetryAnalyzer.class)
    @Description("Web. Search for visa via landing page search bar")
    public void searchForVisa(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnVisaTab()
                .selectDepartureCountry(phpTravelsModel)
                .selectArrivalCountry(phpTravelsModel)
                .selectDate(phpTravelsModel)
                .selectVisaType(phpTravelsModel)
                .selectProcessingSpeed(phpTravelsModel)
                .selectTravellers(phpTravelsModel)
                .clickOnSearchButton();
    }
}
