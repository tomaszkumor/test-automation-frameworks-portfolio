package models.web.menu.visaPage.visaSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class VisaSearchPage extends VisaSearchPageLocators {
    public VisaSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        log.info("Visa search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/visa";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
