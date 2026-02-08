package dataProviders.dataProviders.mobile;

import constants.mobile.Theme;
import constants.mobile.UpdateType;
import dataProviders.dataProvidersModels.mobile.discoveryModels.DiscoveryModel;
import dataProviders.dataProvidersModels.mobile.moreModels.MoreModel;
import dataProviders.dataProvidersModels.mobile.onboardingModels.OnboardingModel;
import dataProviders.dataProvidersModels.mobile.savedModels.SavedModel;
import dataProviders.dataProvidersModels.mobile.searchModels.SearchModel;
import dataProviders.dataProvidersModels.mobile.wikiAlphaModel.WikiAlphaModel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;

public class WikiAlphaDP {
    @DataProvider
    Object[][] commonDP(Method method) {
        return new Object[][]{
                {WikiAlphaModel.builder()
                        .onboardingModel(OnboardingModel
                                .builder()
                                .expectedLanguageToAdd("Norsk bokmål")
                                .expectedLanguagesBeforeChange(List.of("Polski"))
                                .expectedLanguagesAfterChange(List.of("Polski", "Norsk bokmål"))
                                .build())
                        .moreModel(MoreModel
                                .builder()
                                .theme(Theme.DARK)
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] discoverySearchDP(Method method) {
        return new Object[][]{
                {WikiAlphaModel.builder()
                        .discoveryModel(DiscoveryModel
                                .builder()
                                .searchPhrase("Robert Lewandowski")
                                .build())
                        .build()
                },
                {WikiAlphaModel.builder()
                        .discoveryModel(DiscoveryModel
                                .builder()
                                .searchPhrase("Łukasz Piszczek")
                                .build())
                        .build()
                },
                {WikiAlphaModel.builder()
                        .discoveryModel(DiscoveryModel
                                .builder()
                                .searchPhrase("Jakub Błaszczykowski")
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] savedDP(Method method) {
        return new Object[][]{
                {WikiAlphaModel.builder()
                        .savedModel(SavedModel
                                .builder()
                                .expectedDiscoverMadeForYouUpdateTypeBeforeChange(UpdateType.WEEK)
                                .expectedDiscoverMadeForYouUpdateTypeAfterChange(UpdateType.DAY)
                                .expectedNumberOfArticlesBeforeChange("5")
                                .expectedNumberOfArticlesAfterChange("7")
                                .expectedSavedResults(List.of("Jakub Błaszczykowski", "Robert Lewandowski", "Łukasz Piszczek"))
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] searchDP(Method method) {
        return new Object[][]{
                {WikiAlphaModel.builder()
                        .searchModel(SearchModel
                                .builder()
                                .searchPhrase("Zbigniew Boniek")
                                .expectedContentItem("Odznaczenia")
                                .build())
                        .build()
                }
        };
    }


}
