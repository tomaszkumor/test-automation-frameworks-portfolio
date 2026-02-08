package dataProviders.dataProviders.api;

import config.TestStackProperties;
import dataProviders.dataProvidersModels.api.OrderModel;
import dataProviders.dataProvidersModels.api.PetModel;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import dataProviders.dataProvidersModels.api.UserModel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

import static config.TestConfig.CONFIG;

public class PetStoreDP {
    @DataProvider
    Object[][] sendPostRequestUserDP(Method method) {
        String requestBodyPath = getApiRequestBodyPathAccordingToTestName(method);
        String responseBodyPath = getApiResponseBodyPathAccordingToTestName(method);

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiRequestBodyPath(requestBodyPath)
                        .apiResponseBodyPath(responseBodyPath)
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendGetRequestUserLoginDP(Method method) {
        String userName = getUserNameBeforeChange();
        String password = getPassword();

        return new Object[][]{
                {PetStoreModel.builder()
                        .user(UserModel
                                .builder()
                                .userName(userName)
                                .password(password)
                                .build()
                        )
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendGetRequestUserDP(Method method) {
        String responseBodyPath = getApiResponseBodyPathAccordingToTestName(method);
        String userName = getUserNameBeforeChange();

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiResponseBodyPath(responseBodyPath)
                        .user(UserModel
                                .builder()
                                .userName(userName)
                                .build()
                        )
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendPutRequestUserDP(Method method) {
        String requestBodyPath = getApiRequestBodyPathAccordingToTestName(method);
        String userName = getUserNameBeforeChange();

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiRequestBodyPath(requestBodyPath)
                        .user(UserModel
                                .builder()
                                .userName(userName)
                                .build()
                        )
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendDeleteRequestUserDP(Method method) {
        String userName = getUserNameAfterChange();

        return new Object[][]{
                {PetStoreModel.builder()
                        .user(UserModel
                                .builder()
                                .userName(userName)
                                .build()
                        )
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendPostRequestPetDP(Method method) {
        String requestBodyPath = getApiRequestBodyPathAccordingToTestName(method);

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiRequestBodyPath(requestBodyPath)
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendGetRequestPetFindByStatusDP(Method method) {
        return new Object[][]{
                {PetStoreModel.builder()
                        .pet(PetModel
                                .builder()
                                .status("available")
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendGetRequestPetFindByIdDP(Method method) {
        int petId = getPetId();
        String responseBodyPath = getApiResponseBodyPathAccordingToTestName(method);

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiResponseBodyPath(responseBodyPath)
                        .pet(PetModel
                                .builder()
                                .id(petId)
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendPutRequestPetDP(Method method) {
        String requestBodyPath = getApiRequestBodyPathAccordingToTestName(method);

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiRequestBodyPath(requestBodyPath)
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendDeleteRequestPetDP(Method method) {
        int petId = getPetId();

        return new Object[][]{
                {PetStoreModel.builder()
                        .pet(PetModel
                                .builder()
                                .id(petId)
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendPostRequestStoreOrderDP(Method method) {
        String requestBodyPath = getApiRequestBodyPathAccordingToTestName(method);

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiRequestBodyPath(requestBodyPath)
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendGetRequestStoreOrderDP(Method method) {
        String responseBodyPath = getApiResponseBodyPathAccordingToTestName(method);
        int orderId = getOrderId();

        return new Object[][]{
                {PetStoreModel.builder()
                        .apiResponseBodyPath(responseBodyPath)
                        .order(OrderModel
                                .builder()
                                .id(orderId)
                                .build())
                        .build()
                }
        };
    }

    @DataProvider
    Object[][] sendDeleteRequestStoreOrderDP(Method method) {
        int orderId = getOrderId();

        return new Object[][]{
                {PetStoreModel.builder()
                        .order(OrderModel
                                .builder()
                                .id(orderId)
                                .build())
                        .build()
                }
        };
    }

    private String getUserNameBeforeChange() {
        String env = TestStackProperties.getEnvironment().toLowerCase();

        return switch(env) {
            case "env1" -> "JanNowak";
            case "env2" -> "StefanKonieczny";
            default -> null;
        };
    }

    private String getUserNameAfterChange() {
        String env = TestStackProperties.getEnvironment().toLowerCase();

        return switch(env) {
            case "env1" -> "AndrzejModny";
            case "env2" -> "IgnacyBonczek";
            default -> null;
        };
    }

    private String getPassword() {
        String env = TestStackProperties.getEnvironment().toLowerCase();

        return switch(env) {
            case "env1" -> "Haslo123.";
            case "env2" -> "Haslo1234.";
            default -> null;
        };
    }

    private int getPetId() {
        String env = TestStackProperties.getEnvironment().toLowerCase();

        return switch(env) {
            case "env1" -> 909091666;
            case "env2" -> 909091555;
            default -> -1;
        };
    }

    private int getOrderId() {
        String env = TestStackProperties.getEnvironment().toLowerCase();

        return switch(env) {
            case "env1" -> 10;
            case "env2" -> 9;
            default -> -1;
        };
    }

    private String getApiRequestBodyPathAccordingToTestName(Method method) {
        String partialYamlPath = "apiFilesPaths.requestBody";

        return switch (method.getName()) {
            case "sendPostRequestUserAndCheckResponse" ->
                    CONFIG.getProperty(partialYamlPath.concat(".postRequestUser"));
            case "sendPutRequestUserAndCheckStatusCode" ->
                    CONFIG.getProperty(partialYamlPath.concat(".putRequestUser"));
            case "sendPostRequestPetAndCheckStatusCode" ->
                    CONFIG.getProperty(partialYamlPath.concat(".postRequestPet"));
            case "sendPutRequestPetAndCheckStatusCode" -> CONFIG.getProperty(partialYamlPath.concat(".putRequestPet"));
            case "sendPostRequestStoreOrderAndCheckStatusCode" -> CONFIG.getProperty(partialYamlPath.concat(".postRequestStoreOrder"));
            default -> "";
        };
    }

    private String getApiResponseBodyPathAccordingToTestName(Method method) {
        String partialYamlPath = "apiFilesPaths.responseBody";

        return switch (method.getName()) {
            case "sendPostRequestUserAndCheckResponse" ->
                    CONFIG.getProperty(partialYamlPath.concat(".postRequestUser"));
            case "sendGetRequestUserAndCheckResponse" -> CONFIG.getProperty(partialYamlPath.concat(".getRequestUser"));
            case "sendGetRequestPetFindByIdAndCheckResponse" ->
                    CONFIG.getProperty(partialYamlPath.concat(".getRequestPetFindById"));
            case "sendGetRequestStoreOrderAndCheckResponse" ->
                    CONFIG.getProperty(partialYamlPath.concat(".getRequestStoreOrder"));
            default -> "";
        };
    }
}
