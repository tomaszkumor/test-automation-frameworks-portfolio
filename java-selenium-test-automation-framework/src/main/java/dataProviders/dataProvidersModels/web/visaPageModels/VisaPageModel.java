package dataProviders.dataProvidersModels.web.visaPageModels;

import constants.visaPage.ProcessingSpeedType;
import constants.visaPage.VisaType;
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
    private VisaType expectedVisaType;
    private ProcessingSpeedType expectedProcessingSpeed;
    private TravellerModel expectedTravellers;
}
