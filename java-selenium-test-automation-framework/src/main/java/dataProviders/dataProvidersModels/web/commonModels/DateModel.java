package dataProviders.dataProvidersModels.web.commonModels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;

@Data
@AllArgsConstructor
public class DateModel {
    private String year;
    private Month month;
    private String day;
}