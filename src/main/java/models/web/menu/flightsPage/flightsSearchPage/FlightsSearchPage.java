package models.web.menu.flightsPage.flightsSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class FlightsSearchPage extends FlightsSearchPageLocators {
    public FlightsSearchPage() {
        browser.waitForPageLoaded(10);
        checkUrl();
        log.info("Flights search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/flights";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
