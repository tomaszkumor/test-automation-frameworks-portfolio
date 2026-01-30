package tests.api;

import baseTest.BaseTest;
import dataProviders.dataProviders.api.PetStoreDP;
import dataProviders.dataProvidersModels.api.PetStoreModel;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.api.controllerPet.EndpointPet;
import models.api.controllerStore.EndpointStore;
import models.api.controllerUser.EndpointUser;
import models.api.pojo.POJOApiResponse;
import models.api.pojo.POJOOrder;
import models.api.pojo.POJOPet;
import models.api.pojo.POJOUser;
import org.testng.annotations.Test;
import utils.apiBodyComparer.ApiBodyComparer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PetStoreTest extends BaseTest {
    @Test(dataProvider = "sendGetRequestUserLoginDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send GET request to /user/login endpoint and check status code")
    public void sendGetRequestUserLoginAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointUser().sendGetRequestUserLogin(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendPostRequestUserDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send POST request to /user endpoint and check response")
    public void sendPostRequestUserAndCheckResponse(PetStoreModel petStoreModel) {
        POJOApiResponse actualResponse = new EndpointUser().sendPostRequestUser(petStoreModel).getApiResponsePOJO();
        POJOApiResponse expectedResponse = new POJOApiResponse().deserialize(petStoreModel.getApiResponseBodyPath(), POJOApiResponse.class);
        assertThat(ApiBodyComparer.getPrettyLog(actualResponse)).isEqualTo(ApiBodyComparer.getPrettyLog(expectedResponse));
    }

    @Test(dataProvider = "sendGetRequestUserDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send GET request to /user/{userName} endpoint and check response")
    public void sendGetRequestUserAndCheckResponse(PetStoreModel petStoreModel) {
        POJOUser actualResponse = new EndpointUser().sendGetRequestUser(petStoreModel).getUserPOJO();
        POJOUser expectedResponse = new POJOUser().deserialize(petStoreModel.getApiResponseBodyPath(), POJOUser.class);
        assertThat(ApiBodyComparer.getPrettyLog(actualResponse)).isEqualTo(ApiBodyComparer.getPrettyLog(expectedResponse));
    }

    @Test(dataProvider = "sendPutRequestUserDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send PUT request to /user/{userName} endpoint and check status code")
    public void sendPutRequestUserAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointUser().sendPutRequestUser(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendDeleteRequestUserDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send DELETE request to /user/{userName} endpoint and check status code")
    public void sendDeleteRequestUserAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointUser().sendDeleteRequestUser(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendPostRequestPetDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send POST request to /pet endpoint and check status code")
    public void sendPostRequestPetAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointPet().sendPostRequestPet(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendGetRequestPetFindByStatusDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send GET request to /pet/findByStatus endpoint and check status code")
    public void sendGetRequestPetFindByStatusAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointPet().sendGetRequestPetFindByStatus(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendGetRequestPetFindByIdDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send GET request to /pet/{petId} endpoint and check response")
    public void sendGetRequestPetFindByIdAndCheckResponse(PetStoreModel petStoreModel) {
        POJOPet actualResponse = new EndpointPet().sendGetRequestPetFindById(petStoreModel).getPetPOJO();
        POJOPet expectedResponse = new POJOPet().deserialize(petStoreModel.getApiResponseBodyPath(), POJOPet.class);
        assertThat(ApiBodyComparer.getPrettyLog(actualResponse)).isEqualTo(ApiBodyComparer.getPrettyLog(expectedResponse));
    }

    @Test(dataProvider = "sendPutRequestPetDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send PUT request to /pet endpoint and check status code")
    public void sendPutRequestPetAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointPet().sendPutRequestPet(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendDeleteRequestPetDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send DELETE request to /pet/{petId} endpoint and check status code")
    public void sendDeleteRequestPetAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointPet().sendDeleteRequestPet(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test()
    @Description("API. Send GET request to /store/inventory endpoint and check status code")
    public void sendGetRequestStoreInventoryAndCheckStatusCode() {
        Response actualResponse = new EndpointStore().sendGetRequestStoreInventory().getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendPostRequestStoreOrderDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send POST request to /store/order endpoint and check status code")
    public void sendPostRequestStoreOrderAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointStore().sendPostRequestStoreOrder(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dataProvider = "sendGetRequestStoreOrderDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send GET request to /store/order/{orderId} endpoint and check response")
    public void sendGetRequestStoreOrderAndCheckResponse(PetStoreModel petStoreModel) {
        POJOOrder actualResponse = new EndpointStore().sendGetRequestStoreOrder(petStoreModel).getOrderPOJO();
        POJOOrder expectedResponse = new POJOOrder().deserialize(petStoreModel.getApiResponseBodyPath(), POJOOrder.class).replaceShipDate(actualResponse);
        assertThat(ApiBodyComparer.getPrettyLog(actualResponse)).isEqualTo(ApiBodyComparer.getPrettyLog(expectedResponse));
    }

    @Test(dataProvider = "sendDeleteRequestStoreOrderDP", dataProviderClass = PetStoreDP.class)
    @Description("API. Send DELETE request to /store/order/{orderId} endpoint and check status code")
    public void sendDeleteRequestStoreOrderAndCheckStatusCode(PetStoreModel petStoreModel) {
        Response actualResponse = new EndpointStore().sendDeleteRequestStoreOrder(petStoreModel).getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }

    @Test()
    @Description("API. Send GET request to /user/logout endpoint and check status code")
    public void sendGetRequestUserLogoutAndCheckStatusCode() {
        Response actualResponse = new EndpointUser().sendGetRequestUserLogout().getResponse();
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);
    }
}
