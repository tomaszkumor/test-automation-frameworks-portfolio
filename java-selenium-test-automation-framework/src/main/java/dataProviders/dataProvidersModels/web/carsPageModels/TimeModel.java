package dataProviders.dataProvidersModels.web.carsPageModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeModel {
    private String hours;
    private String minutes;
    private String dayPeriod;
}