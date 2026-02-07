package models.web.menu.carsPage.carsSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class CarsSearchPage extends CarsSearchPageLocators {
    public CarsSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        log.info("Cars search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/cars";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
