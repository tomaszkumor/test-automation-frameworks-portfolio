package dataProviders.dataProvidersModels.mobile.searchModels;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchModel {
    private String searchPhrase;
    private String expectedContentItem;
}
