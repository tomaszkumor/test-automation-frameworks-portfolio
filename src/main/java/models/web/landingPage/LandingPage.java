package models.web.landingPage;

import io.qameta.allure.Step;
import models.web.landingPage.searchBarCars.SearchBarCars;
import models.web.landingPage.searchBarFlights.SearchBarFlights;
import models.web.landingPage.searchBarHotels.SearchBarHotels;
import models.web.landingPage.searchBarTours.SearchBarTours;
import models.web.landingPage.searchBarVisa.SearchBarVisa;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class LandingPage extends LandingPageLocators {
    public static ThreadLocal<Boolean> IS_AD_CLOSED = ThreadLocal.withInitial(() -> false);

    public LandingPage() {
        browser.waitForPageLoaded(30);
        checkUrl();
        hideAd();
        log.info("Landing page has been displayed.");
    }

    @Step("Click on Flights tab")
    public SearchBarFlights clickOnFlightsTab() {
        click.clickOnElement(tabFlights, 15);
        log.info("Flights tab has been clicked.");

        return new SearchBarFlights();
    }

    @Step("Click on Hotels tab")
    public SearchBarHotels clickOnHotelsTab() {
        click.clickOnElement(tabHotels, 15);
        log.info("Hotels tab has been clicked.");

        return new SearchBarHotels();
    }

    @Step("Click on Tours tab")
    public SearchBarTours clickOnToursTab() {
        click.clickOnElement(tabTours, 15);
        log.info("Tours tab has been clicked.");

        return new SearchBarTours();
    }

    @Step("Click on Cars tab")
    public SearchBarCars clickOnCarsTab() {
        click.clickOnElement(tabCars, 15);
        log.info("Cars tab has been clicked.");

        return new SearchBarCars();
    }

    @Step("Click on Visa tab")
    public SearchBarVisa clickOnVisaTab() {
        click.clickOnElement(tabVisa, 15);
        log.info("Visa tab has been clicked.");

        return new SearchBarVisa();
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .isEqualTo(expectedUrl);
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
