package dataProviders.dataProvidersModels.mobile.wikiAlphaModel;

import dataProviders.dataProvidersModels.mobile.discoveryModels.DiscoveryModel;
import dataProviders.dataProvidersModels.mobile.moreModels.MoreModel;
import dataProviders.dataProvidersModels.mobile.onboardingModels.OnboardingModel;
import dataProviders.dataProvidersModels.mobile.savedModels.SavedModel;
import dataProviders.dataProvidersModels.mobile.searchModels.SearchModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WikiAlphaModel {
    private OnboardingModel onboardingModel;
    private MoreModel moreModel;
    private SearchModel searchModel;
    private SavedModel savedModel;
    private DiscoveryModel discoveryModel;
}
