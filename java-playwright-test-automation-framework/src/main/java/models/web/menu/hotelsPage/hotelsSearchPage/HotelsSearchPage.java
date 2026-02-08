package models.web.menu.hotelsPage.hotelsSearchPage;

import models.web.landingPage.LandingPage;

import static utils.logger.Log4J.log;

public class HotelsSearchPage extends HotelsSearchPageSelectors {
    public HotelsSearchPage() {
        checkUrl();
        log.info("Hotels search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/hotels";
        check.doesUrlContain(expectedUrl);
    }
}
