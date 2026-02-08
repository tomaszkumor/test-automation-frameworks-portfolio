package models.web.menu.flightsPage.flightsSearchPage;

import models.web.landingPage.LandingPage;

import static utils.logger.Log4J.log;

public class FlightsSearchPage extends FlightsSearchPageSelectors {
    public FlightsSearchPage() {
        checkUrl();
        log.info("Flights search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/flights";
        check.doesUrlContain(expectedUrl);
    }
}
