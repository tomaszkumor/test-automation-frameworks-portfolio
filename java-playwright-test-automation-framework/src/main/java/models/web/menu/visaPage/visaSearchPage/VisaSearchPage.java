package models.web.menu.visaPage.visaSearchPage;

import models.web.landingPage.LandingPage;

import static utils.logger.Log4J.log;

public class VisaSearchPage extends VisaSearchPageSelectors {
    public VisaSearchPage() {
        checkUrl();
        log.info("Visa search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/visa";
        check.doesUrlContain(expectedUrl);
    }
}
