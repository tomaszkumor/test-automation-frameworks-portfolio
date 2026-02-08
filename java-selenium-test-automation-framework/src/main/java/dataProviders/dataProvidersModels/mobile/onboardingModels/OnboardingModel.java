package dataProviders.dataProvidersModels.mobile.onboardingModels;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OnboardingModel {
    private List<String> expectedLanguagesBeforeChange;
    private List<String> expectedLanguagesAfterChange;
    private String expectedLanguageToAdd;
}
