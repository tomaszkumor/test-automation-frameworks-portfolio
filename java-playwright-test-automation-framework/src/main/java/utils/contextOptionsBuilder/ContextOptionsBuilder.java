package utils.contextOptionsBuilder;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import config.TestStackProperties;

import java.util.Map;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;

public class ContextOptionsBuilder {
    public static APIRequestContext buildBasicRequestContextOptions() {
        APIRequest.NewContextOptions contextOptions = new APIRequest.NewContextOptions();
        String baseUrl = TestStackProperties.getApiUrl();

        contextOptions
                .setBaseURL(baseUrl)
                .setIgnoreHTTPSErrors(true)
                .setExtraHTTPHeaders(Map.of(
                        "Content-Type", "application/json",
                        "Accept", "application/json")
                );

        return getPlaywrightInstance()
                .getPlaywright()
                .request()
                .newContext(contextOptions);
    }

    public static APIRequestContext buildAuthorizedRequestContextOptions() {
        APIRequest.NewContextOptions contextOptions = new APIRequest.NewContextOptions();
        String baseUrl = TestStackProperties.getApiUrl();

        contextOptions
                .setBaseURL(baseUrl)
                .setIgnoreHTTPSErrors(true)
                .setExtraHTTPHeaders(Map.of(
                        "Content-Type", "application/json",
                        "Accept", "application/json",
                        "api_key", "special-key")
                );

        return getPlaywrightInstance()
                .getPlaywright()
                .request()
                .newContext(contextOptions);
    }
}
