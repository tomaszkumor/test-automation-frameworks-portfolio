package models.api.controllerStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.pojo.POJOOrder;
import models.api.pojo.POJOUser;

import static io.restassured.RestAssured.given;
import static utils.logger.Log4J.log;
import static utils.specBuilder.SpecBuilder.buildBasicRequestSpecification;

public class EndpointStore {
    private Response response;
    private POJOOrder pojoOrder;

    @Step("Send GET request to /store/inventory endpoint")
    public EndpointStore sendGetRequestStoreInventory() {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestEndpointUrl = "/store/inventory";

        response = given()
                .spec(requestSpecification)
                .get(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send POST request to /store/order endpoint")
    public EndpointStore sendPostRequestStoreOrder(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "/store/order";

        response = given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /store/order/{orderId} endpoint")
    public EndpointStore sendGetRequestStoreOrder(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        int orderId = getExpectedOrderIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/store/order/{orderId}";

        response = given()
                .spec(requestSpecification)
                .pathParams("orderId", orderId)
                .get(requestEndpointUrl);

        checkRequestStatus(response);
        pojoOrder = response.as(POJOOrder.class);

        return this;
    }

    @Step("Send DELETE request to /store/order/{orderId} endpoint")
    public EndpointStore sendDeleteRequestStoreOrder(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        int orderId = getExpectedOrderIdFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/store/order/{orderId}";

        response = given()
                .spec(requestSpecification)
                .pathParams("orderId", orderId)
                .delete(requestEndpointUrl);

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

    public Response getResponse() {
        return response;
    }

    public POJOOrder getOrderPOJO() {
        return pojoOrder;
    }

    private void checkRequestStatus(Response response) {
        log.info("Status code: " + response.getStatusCode());

        if (response.getStatusCode() != 200) {
            log.info("Response body: " + response.prettyPrint());
        }
    }
}
