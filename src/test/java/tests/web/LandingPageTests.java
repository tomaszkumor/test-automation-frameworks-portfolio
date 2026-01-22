package tests.web;

import baseTest.BaseTest;
import dataProviders.dataProviders.web.landingPage.LandingPageDP;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import listeners.DriverListener;
import models.web.landingPage.LandingPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = {DriverListener.class})
public class LandingPageTests extends BaseTest {
    @Test(dataProvider = "searchForFlights", dataProviderClass = LandingPageDP.class)
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

    @Test(dataProvider = "searchForHotels", dataProviderClass = LandingPageDP.class)
    public void searchForHotels(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnHotelsTab()
                .selectCity(phpTravelsModel)
                .selectCheckInDate(phpTravelsModel)
                .selectCheckOutDate(phpTravelsModel)
                .selectAccommodation(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForTours", dataProviderClass = LandingPageDP.class)
    public void searchForTours(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnToursTab()
                .selectCity(phpTravelsModel)
                .selectDate(phpTravelsModel)
                .selectTravellers(phpTravelsModel)
                .clickOnSearchButton();
    }

    @Test(dataProvider = "searchForCars", dataProviderClass = LandingPageDP.class)
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

    @Test(dataProvider = "searchForVisa", dataProviderClass = LandingPageDP.class)
    public void searchForVisa(PhpTravelsModel phpTravelsModel) {
        new LandingPage()
                .clickOnVisaTab()
                .selectDepartureCountry(phpTravelsModel)
                .selectArrivalCountry(phpTravelsModel)
                .selectDate(phpTravelsModel)
                .clickOnSearchButton();
    }
}
