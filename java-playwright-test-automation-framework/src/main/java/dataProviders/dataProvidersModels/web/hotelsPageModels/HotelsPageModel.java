package dataProviders.dataProvidersModels.web.hotelsPageModels;

import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelsPageModel {
    private DateModel expectedCheckInDate;
    private DateModel expectedCheckOutDate;
    private DestinationModel expectedDestination;
    private AccommodationModel expectedAccommodation;
    private List<ChildModel> expectedChildren;
    private String expectedNationality;
}
