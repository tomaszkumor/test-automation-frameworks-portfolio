package models.api.controllerUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.pojo.POJOApiResponse;
import models.api.pojo.POJOUser;

import static io.restassured.RestAssured.given;
import static utils.logger.Log4J.log;
import static utils.specBuilder.SpecBuilder.*;

public class EndpointUser {
    private Response response;
    private POJOApiResponse pojoApiResponse;
    private POJOUser pojoUser;

    @Step("Send POST request to /user endpoint")
    public EndpointUser sendPostRequestUser(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "/user";

        response = given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(requestEndpointUrl);

        checkRequestStatus(response);
        pojoApiResponse = response.as(POJOApiResponse.class);

        return this;
    }

    @Step("Send GET request to /user/login endpoint")
    public EndpointUser sendGetRequestUserLogin(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String password = getExpectedPasswordFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/user/login";

        response = given()
                .spec(requestSpecification)
                .param("username", userName)
                .param("password", password)
                .get(requestEndpointUrl);

        checkRequestStatus(response);
        pojoApiResponse = response.as(POJOApiResponse.class);

        return this;
    }

    @Step("Send GET request to /user/logout endpoint")
    public EndpointUser sendGetRequestUserLogout() {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestEndpointUrl = "/user/logout";

        response = given()
                .spec(requestSpecification)
                .get(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /user/{userName} endpoint")
    public EndpointUser sendGetRequestUser(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);

        String requestEndpointUrl = "/user/{userName}";

        response = given()
                .spec(requestSpecification)
                .pathParam("userName", userName)
                .get(requestEndpointUrl);

        checkRequestStatus(response);
        pojoUser = response.as(POJOUser.class);

        return this;
    }

    @Step("Send PUT request to /user/{userName} endpoint")
    public EndpointUser sendPutRequestUser(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildBasicRequestSpecification();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/user/{userName}";

        response = given()
                .spec(requestSpecification)
                .pathParam("userName", userName)
                .body(requestBody)
                .put(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send DELETE request to /user/{userName} endpoint")
    public EndpointUser sendDeleteRequestUser(PetStoreModel petStoreModel) {
        RequestSpecification requestSpecification = buildAuthorizedRequestSpecification();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String requestEndpointUrl = "/user/{userName}";

        response = given()
                .spec(requestSpecification)
                .pathParam("userName", userName)
                .delete(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    private void checkRequestStatus(Response response) {
        log.info("Status code: " + response.getStatusCode());

        if (response.getStatusCode() != 200) {
            log.info("Response body: " + response.prettyPrint());
        }
    }

    private String getExpectedRequestBodyFromFile(PetStoreModel petStoreModel) {
        String requestBodyPath = getExpectedApiRequestBodyPathFromDataProvider(petStoreModel);
        POJOUser pojoUser = new POJOUser().deserialize(requestBodyPath, POJOUser.class);

        return serializeBody(pojoUser);
    }

    private String getExpectedApiRequestBodyPathFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getApiRequestBodyPath();
    }

    private String getExpectedUserNameFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getUser().getUserName();
    }

    private String getExpectedPasswordFromDataProvider(PetStoreModel petStoreModel) {
        return petStoreModel.getUser().getPassword();
    }

    private String serializeBody(POJOUser user) {
        Gson gson = new GsonBuilder().create();

        return gson.toJson(user);
    }

    public Response getResponse() {
        return response;
    }

    public POJOApiResponse getApiResponsePOJO() {
        return pojoApiResponse;
    }

    public POJOUser getUserPOJO() {
        return pojoUser;
    }
}
