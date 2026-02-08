package dataProviders.dataProvidersModels.web.commonModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DestinationModel {
    private String city;
    private String country;
}
