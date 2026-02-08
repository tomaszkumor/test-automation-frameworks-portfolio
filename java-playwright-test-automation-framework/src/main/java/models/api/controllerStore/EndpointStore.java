package models.api.controllerStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import lombok.Getter;
import models.api.pojo.POJOOrder;
import tools.jackson.databind.ObjectMapper;

import static utils.contextOptionsBuilder.ContextOptionsBuilder.buildBasicRequestContextOptions;
import static utils.logger.Log4J.log;

public class EndpointStore {
    private @Getter APIResponse response;
    private APIRequestContext request;
    private @Getter POJOOrder pojoOrder;

    @Step("Send GET request to /store/inventory endpoint")
    public EndpointStore sendGetRequestStoreInventory() {
        request = buildBasicRequestContextOptions();
        String requestEndpointUrl = "store/inventory";

        response = request.get(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send POST request to /store/order endpoint")
    public EndpointStore sendPostRequestStoreOrder(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "store/order";

        response = request.post(requestEndpointUrl, RequestOptions.create()
                .setData(requestBody));

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /store/order/{orderId} endpoint")
    public EndpointStore sendGetRequestStoreOrder(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        int orderId = getExpectedOrderIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "store/order/" + orderId;

        response = request.get(requestEndpointUrl);

        checkRequestStatus(response);

        pojoOrder = new ObjectMapper().readValue(
                response.body(),
                POJOOrder.class
        );

        return this;
    }

    @Step("Send DELETE request to /store/order/{orderId} endpoint")
    public EndpointStore sendDeleteRequestStoreOrder(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        int orderId = getExpectedOrderIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "store/order/" + orderId;

        response = request.delete(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    private String getExpectedRequestBodyFromFile(PetStoreModel petStoreModel) {
        String requestBodyPath = getExpectedApiRequestBodyPathFromDataProvider(petStoreModel);
        POJOOrder pojoPet = new POJOOrder().deserialize(requestBodyPath, POJOOrder.class);

        return serializeBody(pojoPet);
    }

    private String getExpectedApiRequestBodyPathFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getApiRequestBodyPath();
    }

    private int getExpectedOrderIdFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getOrder().getId();
    }

    private String serializeBody(POJOOrder pet) {
        Gson gson = new GsonBuilder().create();

        return gson.toJson(pet);
    }

    private void checkRequestStatus(APIResponse response) {
        log.info("Status code: " + response.status());

        if (response.status() != 200) {
            log.info("Response body: " + response.text());
        }
    }
}
