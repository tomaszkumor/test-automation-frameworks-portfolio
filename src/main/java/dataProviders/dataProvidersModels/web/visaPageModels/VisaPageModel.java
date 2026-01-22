package dataProviders.dataProvidersModels.web.visaPageModels;

import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisaPageModel {
    private DateModel expectedDate;
    private DestinationModel expectedDepartureDestination;
    private DestinationModel expectedArrivalDestination;
}
