package models.web.menu.toursPage.toursSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class ToursSearchPage extends ToursSearchPageLocators {
    public ToursSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        log.info("Tours search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/tours";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
