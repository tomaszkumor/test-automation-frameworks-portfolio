package dataProviders.dataProvidersModels.web.hotelsPageModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccommodationModel {
    private String roomsCount;
    private String adultsCount;
    private String childrenCount;
}
