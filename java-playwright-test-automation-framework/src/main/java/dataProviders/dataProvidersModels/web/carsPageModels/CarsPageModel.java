package dataProviders.dataProvidersModels.web.carsPageModels;

import dataProviders.dataProvidersModels.web.commonModels.AirportModel;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarsPageModel {
    private AirportModel expectedDepartureAirport;
    private DestinationModel expectedArrivalCity;
    private DateModel expectedPickUpDate;
    private TimeModel expectedPickUpTime;
    private DateModel expectedDropOffDate;
    private TimeModel expectedDropOffTime;
    private TravellerModel expectedTravellers;
}
