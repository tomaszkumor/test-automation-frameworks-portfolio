package models.web.menu.toursPage.toursSearchPage;

import models.web.landingPage.LandingPage;

import static utils.logger.Log4J.log;

public class ToursSearchPage extends ToursSearchPageSelectors {
    public ToursSearchPage() {
        checkUrl();
        log.info("Tours search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/tours";
        check.doesUrlContain(expectedUrl);
    }
}
