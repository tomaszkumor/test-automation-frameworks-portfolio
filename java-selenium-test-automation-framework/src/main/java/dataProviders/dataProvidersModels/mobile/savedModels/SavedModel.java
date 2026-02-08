package dataProviders.dataProvidersModels.mobile.savedModels;

import constants.mobile.UpdateType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SavedModel {
    private List<String> expectedSavedResults;
    private String expectedNumberOfArticlesBeforeChange;
    private String expectedNumberOfArticlesAfterChange;
    private UpdateType expectedDiscoverMadeForYouUpdateTypeBeforeChange;
    private UpdateType expectedDiscoverMadeForYouUpdateTypeAfterChange;
}
