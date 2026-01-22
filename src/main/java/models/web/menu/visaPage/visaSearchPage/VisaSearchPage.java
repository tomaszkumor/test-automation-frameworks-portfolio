package models.web.menu.visaPage.visaSearchPage;

import models.web.landingPage.LandingPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class VisaSearchPage extends VisaSearchPageLocators {
    public VisaSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        hideAd();
        log.info("Visa search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/visa";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }

    private void hideAd() {
        if (!LandingPage.IS_AD_CLOSED.get()) {
            if (check.isElementDisplayed(hideAdButton, 10)) {
                click.clickOnVisibleElement(hideAdButton, 10);
                log.info("Hide ad button has been clicked.");

                LandingPage.IS_AD_CLOSED.set(true);
            }
        }
    }
}
