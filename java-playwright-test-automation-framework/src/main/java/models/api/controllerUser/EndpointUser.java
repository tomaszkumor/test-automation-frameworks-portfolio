package models.api.controllerUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Step;
import lombok.Getter;
import models.api.pojo.POJOApiResponse;
import models.api.pojo.POJOUser;
import tools.jackson.databind.ObjectMapper;

import static utils.contextOptionsBuilder.ContextOptionsBuilder.buildAuthorizedRequestContextOptions;
import static utils.contextOptionsBuilder.ContextOptionsBuilder.buildBasicRequestContextOptions;
import static utils.logger.Log4J.log;

public class EndpointUser {
    private APIRequestContext request;
    @Getter private APIResponse response;
    @Getter private POJOApiResponse pojoApiResponse;
    @Getter private POJOUser pojoUser;

    @Step("Send POST request to /user endpoint")
    public EndpointUser sendPostRequestUser(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String requestEndpointUrl = "user";

        response = request
                .post(requestEndpointUrl, RequestOptions.create()
                        .setData(requestBody));

        checkRequestStatus(response);

        pojoApiResponse = new ObjectMapper().readValue(
                response.body(),
                POJOApiResponse.class
        );

        return this;
    }

    @Step("Send GET request to /user/login endpoint")
    public EndpointUser sendGetRequestUserLogin(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String password = getExpectedPasswordFromDataProvider(petStoreModel);
        String requestEndpointUrl = "user/login";

        response = request
                .get(requestEndpointUrl, RequestOptions.create()
                        .setQueryParam("username", userName)
                        .setQueryParam("password", password));

        checkRequestStatus(response);

        pojoApiResponse = new ObjectMapper().readValue(
                response.body(),
                POJOApiResponse.class
        );

        return this;
    }

    @Step("Send GET request to /user/logout endpoint")
    public EndpointUser sendGetRequestUserLogout() {
        request = buildBasicRequestContextOptions();
        String requestEndpointUrl = "user/logout";

        response = request.get(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    @Step("Send GET request to /user/{userName} endpoint")
    public EndpointUser sendGetRequestUser(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String requestEndpointUrl = "user/" + userName;

        response = request.get(requestEndpointUrl);

        checkRequestStatus(response);

        pojoUser = new ObjectMapper().readValue(
                response.body(),
                POJOUser.class
        );

        return this;
    }

    @Step("Send PUT request to /user/{userName} endpoint")
    public EndpointUser sendPutRequestUser(PetStoreModel petStoreModel) {
        request = buildBasicRequestContextOptions();
        String requestBody = getExpectedRequestBodyFromFile(petStoreModel);
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String requestEndpointUrl = "user/" + userName;

        response = request.put(requestEndpointUrl, RequestOptions.create()
                .setData(requestBody));

        checkRequestStatus(response);

        return this;
    }

    @Step("Send DELETE request to /user/{userName} endpoint")
    public EndpointUser sendDeleteRequestUser(PetStoreModel petStoreModel) {
        request = buildAuthorizedRequestContextOptions();
        String userName = getExpectedUserNameFromDataProvider(petStoreModel);
        String requestEndpointUrl = "user/" + userName;

        response = request.delete(requestEndpointUrl);

        checkRequestStatus(response);

        return this;
    }

    private void checkRequestStatus(APIResponse response) {
        log.info("Status code: " + response.status());

        if (response.status() != 200) {
            log.info("Response body: " + response.text());
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
}
