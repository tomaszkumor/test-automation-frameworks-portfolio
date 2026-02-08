package models.web.menu.carsPage.carsSearchPage;

import models.web.landingPage.LandingPage;

import static utils.logger.Log4J.log;

public class CarsSearchPage extends CarsSearchPageSelectors {
    public CarsSearchPage() {
        checkUrl();
        log.info("Cars search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/cars";
        check.doesUrlContain(expectedUrl);
    }
}
