package utils.specBuilder;

import config.TestStackProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {
    public static RequestSpecification buildBasicRequestSpecification() {
        String baseUri = TestStackProperties.getApiUrl();

        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public static RequestSpecification buildAuthorizedRequestSpecification() {
        String baseUri = TestStackProperties.getApiUrl();

        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("api_key", "special-key")
                .build();
    }
}
