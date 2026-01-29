package models.api.controllerPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.pojo.POJOPet;

import static io.restassured.RestAssured.given;
import static utils.logger.Log4J.log;
import static utils.specBuilder.SpecBuilder.buildAuthorizedRequestSpecification;
import static utils.specBuilder.SpecBuilder.buildBasicRequestSpecification;

public class EndpointPet {
    private Response response;
    private POJOPet pojoPet;

    @Step("Send POST request to /pet endpoint")
    public EndpointPet sendPostRequestPet(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "/pet";

        response = given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /pet/findByStatus endpoint")
    public EndpointPet sendGetRequestPetFindByStatus(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String petStatus = getExpectedPetStatusFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/pet/findByStatus";

        response = given()
                .spec(requestSpecification)
                .param("status", petStatus)
                .get(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /pet/{petId} endpoint")
    public EndpointPet sendGetRequestPetFindById(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        int petId = getExpectedPetIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/pet/{petId}";

        response = given()
                .spec(requestSpecification)
                .pathParam("petId", petId)
                .get(requestEndpointUrl);

        checkRequestStatus(response);
        pojoPet = response.as(POJOPet.class);

        return this;
    }

    @Step("Send PUT request to /pet endpoint")
    public EndpointPet sendPutRequestPet(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "/pet";

        response = given()
                .spec(requestSpecification)
                .body(requestBody)
                .put(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send DELETE request to /pet/{petId} endpoint")
    public EndpointPet sendDeleteRequestPet(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildAuthorizedRequestSpecification();
        int petId = getExpectedPetIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/pet/{petId}";

        response = given()
                .spec(requestSpecification)
                .pathParam("petId", petId)
                .delete(requestEndpointUrl);

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

    public Response getResponse() {
        return response;
    }

    public POJOPet getPetPOJO() {
        return pojoPet;
    }

    private void checkRequestStatus(Response response) {
        log.info("Status code: " + response.getStatusCode());

        if (response.getStatusCode() != 200) {
            log.info("Response body: " + response.prettyPrint());
        }
    }
}
