package dataProviders.dataProvidersModels.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PetStoreModel {
    private String apiRequestBodyPath;
    private String apiResponseBodyPath;
    private UserModel user;
    private PetModel pet;
    private OrderModel order;
}
