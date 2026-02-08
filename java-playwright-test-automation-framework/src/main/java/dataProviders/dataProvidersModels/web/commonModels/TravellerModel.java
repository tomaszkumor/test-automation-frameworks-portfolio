package dataProviders.dataProvidersModels.web.commonModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TravellerModel {
    private String adultsCount;
    private String childrenCount;
    private String infantsCount;
}
