package models.api.controllerPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import lombok.Getter;
import models.api.pojo.POJOPet;
import tools.jackson.databind.ObjectMapper;

import static utils.contextOptionsBuilder.ContextOptionsBuilder.buildAuthorizedRequestContextOptions;
import static utils.contextOptionsBuilder.ContextOptionsBuilder.buildBasicRequestContextOptions;
import static utils.logger.Log4J.log;

public class EndpointPet {
    private @Getter APIResponse response;
    private APIRequestContext request;
    private @Getter POJOPet pojoPet;

    @Step("Send POST request to /pet endpoint")
    public EndpointPet sendPostRequestPet(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "pet";

        response = request
                .post(requestEndpointUrl, RequestOptions.create()
                .setData(requestBody));

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /pet/findByStatus endpoint")
    public EndpointPet sendGetRequestPetFindByStatus(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String petStatus = getExpectedPetStatusFromDataProvider(petStoreModel);
        String requestEndpointUrl = "pet/findByStatus";

        response = request
                .get(requestEndpointUrl, RequestOptions.create()
                        .setQueryParam("status", petStatus));

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /pet/{petId} endpoint")
    public EndpointPet sendGetRequestPetFindById(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        int petId = getExpectedPetIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "pet/" + petId;

        response = request.get(requestEndpointUrl);

        checkRequestStatus(response);

        pojoPet = new ObjectMapper().readValue(
                response.body(),
                POJOPet.class
        );

        return this;
    }

    @Step("Send PUT request to /pet endpoint")
    public EndpointPet sendPutRequestPet(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "pet";

        response = request
                .put(requestEndpointUrl, RequestOptions.create()
                        .setData(requestBody));

        checkRequestStatus(response);

        return this;
    }

    @Step("Send DELETE request to /pet/{petId} endpoint")
    public EndpointPet sendDeleteRequestPet(PetStoreModel petStoreModel) {
        request = buildAuthorizedRequestContextOptions();
        int petId = getExpectedPetIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "pet/" + petId;

        response = request.delete(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    private String getExpectedApiRequestBodyPathFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getApiRequestBodyPath();
    }

    private String getExpectedPetStatusFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getPet().getStatus();
    }

    private int getExpectedPetIdFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getPet().getId();
    }

    private String getExpectedRequestBodyFromFile(PetStoreModel petStoreModel) {
        String requestBodyPath = getExpectedApiRequestBodyPathFromDataProvider(petStoreModel);
        POJOPet pojoPet = new POJOPet().deserialize(requestBodyPath, POJOPet.class);

        return serializeBody(pojoPet);
    }

    private String serializeBody(POJOPet pet) {
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
