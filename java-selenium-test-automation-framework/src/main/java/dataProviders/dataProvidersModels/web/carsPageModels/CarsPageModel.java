package dataProviders.dataProvidersModels.web.carsPageModels;

import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.commonModels.AirportModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
