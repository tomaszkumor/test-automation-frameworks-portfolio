package dataProviders.dataProvidersModels.web.phpTravelsModel;

import constants.header.HeaderLanguage;
import dataProviders.dataProvidersModels.web.carsPageModels.CarsPageModel;
import dataProviders.dataProvidersModels.web.flightsPageModels.FlightsPageModel;
import dataProviders.dataProvidersModels.web.staysPageModels.StaysPageModel;
import dataProviders.dataProvidersModels.web.toursPageModels.ToursPageModel;
import dataProviders.dataProvidersModels.web.visaPageModels.VisaPageModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PhpTravelsModel {
    private List<HeaderLanguage> languages;
    private FlightsPageModel flightsPageModel;
    private StaysPageModel staysPageModel;
    private ToursPageModel toursPageModel;
    private CarsPageModel carsPageModel;
    private VisaPageModel visaPageModel;
}
