package dataProviders.dataProvidersModels.web.toursPageModels;

import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToursPageModel {
    private DateModel expectedDate;
    private DestinationModel expectedDestination;
    private TravellerModel expectedTravellers;
}
