package dataProviders.dataProviders.web.landingPage;

import constants.flightsPage.CabinClass;
import constants.flightsPage.FlightDestination;
import constants.common.Location;
import constants.header.HeaderLanguage;
import dataProviders.dataProvidersModels.web.carsPageModels.CarsPageModel;
import dataProviders.dataProvidersModels.web.carsPageModels.TimeModel;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.AirportModel;
import dataProviders.dataProvidersModels.web.flightsPageModels.FlightsPageModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.hotelsPageModels.AccommodationModel;
import dataProviders.dataProvidersModels.web.hotelsPageModels.ChildModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.hotelsPageModels.HotelsPageModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import dataProviders.dataProvidersModels.web.toursPageModels.ToursPageModel;
import dataProviders.dataProvidersModels.web.visaPageModels.VisaPageModel;
import org.testng.annotations.DataProvider;

import java.time.Month;
import java.util.List;

public class LandingPageDP {
    @DataProvider
    Object[][] searchForFlights() {
        List<AirportModel> expectedDepartureLocations = getExpectedDepartureLocations();
        List<AirportModel> expectedArrivalLocations = getExpectedArrivalLocations();
        List<HeaderLanguage> expectedLanguages = getExpectedLanguages();
        DateModel expectedDepartureDate = getExpectedDepartureDate();
        DateModel expectedReturnDate = getExpectedReturnDate();
        TravellerModel expectedTravellers = getExpectedTravellers();

        return new Object[][]{
                {PhpTravelsModel.builder()
                        .languages(expectedLanguages)
                        .flightsPageModel(FlightsPageModel
                                .builder()
                                .expectedDepartureLocation(Location.NEW_YORK_CITY_AA)
                                .expectedArrivalLocation(Location.BERLIN)
                                .expectedCabinClass(CabinClass.FIRST)
                                .expectedFlightDestination(FlightDestination.RETURN)
                                .expectedDepartureDate(expectedDepartureDate)
                                .expectedReturnDate(expectedReturnDate)
                                .expectedTravellers(expectedTravellers)
                                .expectedDepartureLocations(expectedDepartureLocations)
                                .expectedArrivalLocations(expectedArrivalLocations)
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] searchForHotels() {
        return new Object[][]{
                {PhpTravelsModel.builder()
                        .hotelsPageModel(HotelsPageModel
                                .builder()
                                .expectedCheckInDate(new DateModel("2026", Month.MARCH, "27"))
                                .expectedCheckOutDate(new DateModel("2026", Month.NOVEMBER, "11"))
                                .expectedAccommodation(new AccommodationModel("3", "4", "3"))
                                .expectedDestination(new DestinationModel("Phuket", "Thailand"))
                                .expectedChildren(List.of(
                                        new ChildModel("1", "12"),
                                        new ChildModel("2", "6"),
                                        new ChildModel("3", "10"))
                                )
                                .expectedNationality("Germany")
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] searchForTours() {
        return new Object[][]{
                {PhpTravelsModel.builder()
                        .toursPageModel(ToursPageModel
                                .builder()
                                .expectedDestination(new DestinationModel("Warsaw", "Poland"))
                                .expectedDate(new DateModel("2026", Month.JULY, "27"))
                                .expectedTravellers(new TravellerModel("3", "3", "0"))
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] searchForCars() {
        return new Object[][]{
                {PhpTravelsModel.builder()
                        .carsPageModel(CarsPageModel
                                .builder()
                                .expectedDepartureAirport(new AirportModel("Warsaw Chopin Airport", "Warsaw", "Poland", "WAW"))
                                .expectedArrivalCity(new DestinationModel("Prague", "Czech Republic"))
                                .expectedPickUpDate(new DateModel("2026", Month.JUNE, "17"))
                                .expectedPickUpTime(new TimeModel("10","30", "AM"))
                                .expectedDropOffDate(new DateModel("2026", Month.JUNE, "30"))
                                .expectedDropOffTime(new TimeModel("14","00", "PM"))
                                .expectedTravellers(new TravellerModel("2", "1", "0"))
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] searchForVisa() {
        return new Object[][]{
                {PhpTravelsModel.builder()
                        .visaPageModel(VisaPageModel
                                .builder()
                                .expectedDate(new DateModel("2026", Month.JUNE, "19"))
                                .expectedDepartureDestination(DestinationModel.builder().country("Poland").build())
                                .expectedArrivalDestination(DestinationModel.builder().country("Spain").build())
                                .build())
                        .build()
                }
        };
    }

    private List<AirportModel> getExpectedDepartureLocations() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> List.of(new AirportModel(Location.NEW_YORK_CITY_AA),
                    new AirportModel(Location.LONDON),
                    new AirportModel(Location.BERLIN),
                    new AirportModel(Location.SINGAPORE),
                    new AirportModel(Location.MOSCOW),
                    new AirportModel(Location.MANILA),
                    new AirportModel(Location.JEDDAH)
            );
            default -> null;
        };
    }

    private List<AirportModel> getExpectedArrivalLocations() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> List.of(new AirportModel(Location.DUBAI),
                    new AirportModel(Location.ISTANBUL),
                    new AirportModel(Location.BERLIN),
                    new AirportModel(Location.DELHI),
                    new AirportModel(Location.NEW_YORK_CITY_JFK),
                    new AirportModel(Location.KUALA_LUMPUR),
                    new AirportModel(Location.PHUKET)
            );
            default -> null;
        };
    }

    private List<HeaderLanguage> getExpectedLanguages() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> List.of(HeaderLanguage.EN,
                    HeaderLanguage.AR,
                    HeaderLanguage.TR,
                    HeaderLanguage.RU,
                    HeaderLanguage.FR,
                    HeaderLanguage.ZH,
                    HeaderLanguage.DE
            );
            default -> null;
        };
    }

    private DateModel getExpectedDepartureDate() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> new DateModel("2026", Month.JULY, "27");
            default -> null;
        };
    }

    private DateModel getExpectedReturnDate() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> new DateModel("2026", Month.NOVEMBER, "07");
            default -> null;
        };
    }

    private TravellerModel getExpectedTravellers() {
        String caller = getCaller();

        return switch (caller) {
            case "searchForFlights" -> new TravellerModel("7", "3", "5");
            default -> null;
        };
    }

    private String getCaller() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
}
