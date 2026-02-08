package dataProviders.dataProvidersModels.web.flightsPageModels;

import constants.common.Location;
import constants.flightsPage.FlightClass;
import constants.flightsPage.FlightType;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightsPageModel {
    private Location expectedDepartureLocation;
    private Location expectedArrivalLocation;
    private FlightType expectedFlightType;
    private FlightClass expectedFlightClass;
    private DateModel expectedDepartureDate;
    private DateModel expectedReturnDate;
    private TravellerModel expectedTravellers;
}
