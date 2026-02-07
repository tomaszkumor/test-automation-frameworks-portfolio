package models.web.menu.hotelsPage.hotelsSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class HotelsSearchPage extends HotelsSearchPageLocators {
    public HotelsSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        log.info("Hotels search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/hotels";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
