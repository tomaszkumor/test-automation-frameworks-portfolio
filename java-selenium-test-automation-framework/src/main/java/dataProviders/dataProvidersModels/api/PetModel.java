package dataProviders.dataProvidersModels.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetModel {
    private int id;
    private String name;
    private String status;
}
