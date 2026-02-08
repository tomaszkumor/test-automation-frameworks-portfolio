package models.web.landingPage.searchBarTours;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import constants.common.Arrow;
import constants.common.Date;
import constants.common.Traveller;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.toursPage.toursSearchPage.ToursSearchPage;

import java.time.format.TextStyle;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarTours extends SearchBarToursSelectors {
    public SearchBarTours() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Tours search bar is displayed.");
    }

    @Step("Select city")
    public SearchBarTours selectCity(PhpTravelsModel phpTravelsModel) {
        checkCityBeforeInput();
        clickOnCityInput();
        checkIfCitiesAreDisplayed();
        findAndSelectCity(phpTravelsModel);
        checkCityAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select date")
    public SearchBarTours selectDate(PhpTravelsModel phpTravelsModel) {
        selectCheckInDateYear(phpTravelsModel);
        selectCheckInDateMonth(phpTravelsModel);
        selectCheckInDateDay(phpTravelsModel);
        checkActualCheckInDate(phpTravelsModel);

        return this;
    }

    @Step("Select travellers")
    public SearchBarTours selectTravellers(PhpTravelsModel phpTravelsModel) {
        checkInitialTravellersCount();
        clickOnTravellersInput();
        checkIfTravellersWindowIsDisplayed(true);
        scrollIntoSearchBarIfNecessary();
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        closeTravellersWindow();
        checkIfTravellersWindowIsDisplayed(false);
        checkTravellersCountAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public ToursSearchPage clickOnSearchButton() {
        click.leftClick(searchButtonSelector, "Search button");
        log.info("Search button has been clicked.");

        return new ToursSearchPage();
    }

    private void setAdultsNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificTravellersCountBeforeChange(Traveller.ADULTS);
        changeSpecificTravellersGroupCount(phpTravelsModel, Traveller.ADULTS);
        checkSpecificTravellersCountAfterChange(phpTravelsModel, Traveller.ADULTS);
    }

    private void setChildrenNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificTravellersCountBeforeChange(Traveller.CHILDS);
        changeSpecificTravellersGroupCount(phpTravelsModel, Traveller.CHILDS);
        checkSpecificTravellersCountAfterChange(phpTravelsModel, Traveller.CHILDS);
    }

    private void checkTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        String travellersCountSelector = travellersDropDownSelector.concat("//span");
        String expectedTravellersCount = getTotalCountOfTravellers(phpTravelsModel);
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count check after change");
    }

    private String getTotalCountOfTravellers(PhpTravelsModel phpTravelsModel) {
        String expectedAdultsCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.ADULTS);
        String expectedChildrenCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.CHILDS);

        int expectedAdultsCountAsNumber = Integer.parseInt(expectedAdultsCount);
        int expectedChildrenCountAsNumber = Integer.parseInt(expectedChildrenCount);

        return String.valueOf(expectedAdultsCountAsNumber + expectedChildrenCountAsNumber);
    }

    private void closeTravellersWindow() {
        click.leftClick(h4Selector, "h4");
    }

    private void checkSpecificTravellersCountBeforeChange(Traveller traveller) {
        String actualSpecificTravellerGroupCount = getActualSpecificTravellerGroupCount(traveller);
        String expectedSpecificTravellerGroupCount = getExpectedSpecificTravellerGroupCountBeforeChange(traveller);
        compareSpecificTravellerGroupCount(actualSpecificTravellerGroupCount, expectedSpecificTravellerGroupCount, traveller, "before");
    }

    private void checkSpecificTravellersCountAfterChange(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        String actualSpecificTravellerGroupCount = getActualSpecificTravellerGroupCount(traveller);
        String expectedSpecificTravellerGroupCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, traveller);
        compareSpecificTravellerGroupCount(actualSpecificTravellerGroupCount, expectedSpecificTravellerGroupCount, traveller, "after");
    }

    private String getExpectedSpecificTravellerGroupCountAfterChange(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        TravellerModel expectedTravellers = getExpectedTravellersFromDataProvider(phpTravelsModel);

        return switch (traveller) {
            case ADULTS -> expectedTravellers.getAdultsCount();
            case CHILDS -> expectedTravellers.getChildrenCount();
            default -> null;
        };
    }

    private TravellerModel getExpectedTravellersFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getToursPageModel().getExpectedTravellers();
    }

    private void compareSpecificTravellerGroupCount(String actualSpecificTravellerGroupCount, String expectedSpecificTravellerGroupCount, Traveller traveller, String stage) {
        assertThat(actualSpecificTravellerGroupCount)
                .as(traveller.getTraveller() + " count check " + stage + " change")
                .isEqualTo(expectedSpecificTravellerGroupCount);
    }

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        String specificTravellerGroupCountSelector = getActualSpecificTravellerGroupCountSelector(traveller);

        return get.getInputValue(specificTravellerGroupCountSelector, "Specific traveller group count");
    }

    private String getActualSpecificTravellerGroupCountSelector(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "tours_adults";
            case CHILDS -> "tours_child";
            default -> null;
        };

        return "//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//following::input[@id = '" + specificTravellerGroupClass + "']";
    }

    private String getExpectedSpecificTravellerGroupCountBeforeChange(Traveller traveller) {
        return switch (traveller) {
            case ADULTS -> "1";
            case CHILDS -> "0";
            default -> null;
        };
    }

    private void changeSpecificTravellersGroupCount(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        String actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
        String expectedTravellersCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, traveller);
        int actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);
        int expectedTravellersCountAsNumber = Integer.parseInt(expectedTravellersCount);

        if (actualTravellersCountAsNumber > expectedTravellersCountAsNumber) {
            String arrowSelector = getArrowButtonSelector(traveller, Arrow.LEFT);

            do {
                click.leftClick(arrowSelector, "Arrow");
                actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual {} count has been decreased to {}.", traveller.getTraveller(), actualTravellersCount);
            } while (actualTravellersCountAsNumber > expectedTravellersCountAsNumber);
        } else if (actualTravellersCountAsNumber < expectedTravellersCountAsNumber) {
            String arrowSelector = getArrowButtonSelector(traveller, Arrow.RIGHT);

            do {
                click.leftClick(arrowSelector, "Arrow");
                actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual {} count has been increased to {}.", traveller.getTraveller(), actualTravellersCount);
            } while (actualTravellersCountAsNumber < expectedTravellersCountAsNumber);
        }
    }

    private String getArrowButtonSelector(Traveller traveller, Arrow arrow) {
        return getArrowSelector(traveller, arrow);
    }

    private String getArrowSelector(Traveller traveller, Arrow arrow) {
        String arrowClass = switch (arrow) {
            case LEFT -> "qtyDec";
            case RIGHT -> "qtyInc";
        };

        return "//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//label[contains(normalize-space(.), '" + traveller.getTraveller() + "')]/..//div[@class = '" + arrowClass + "']";
    }

    private void checkIfTravellersWindowIsDisplayed(boolean shouldBeDisplayed) {
        if (shouldBeDisplayed) {
            check.isElementPresent(travellersWindowSelector, "Travellers window");
            check.isVisible(travellersWindowSelector, "Travellers window");
            log.info("Travellers window has been displayed.");
        } else {
            check.isElementNotPresent(travellersWindowSelector, "Travellers window");
            log.info("Traveller window has been closed.");
        }
    }

    private void clickOnTravellersInput() {
        click.leftClick(travellersDropDownSelector, "Travellers drop down");
        log.info("Travellers drop down has been clicked.");
    }

    private void checkInitialTravellersCount() {
        String travellersCountSelector = travellersDropDownSelector.concat("//span");
        String expectedTravellersCount = "1";
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count check before change");
    }

    private void selectCheckInDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void selectCheckInDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void selectCheckInDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        scrollIntoSearchBarIfNecessary();
        selectSpecificDate(phpTravelsModel, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void scrollIntoSearchBarIfNecessary() {
        Locator locator = get.getLocator(toursSearchBarSelector);
        locator.scrollIntoViewIfNeeded();
    }

    private void clickOnDateInput() {
        check.isElementPresent(dateInputSelector, "Date input");
        click.leftClick(dateInputSelector, "Date input");

        log.info("Date picker input has been clicked.");
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed) {
        if (shouldBeDisplayed) {
            check.isElementPresent(datePickerWindowSelector, "Date picker window");
            check.isVisible(datePickerWindowSelector, "Date picker window");
            log.info("Date picker window has been displayed.");
        } else {
            check.isElementNotPresent(datePickerWindowSelector, "Date picker window");
            log.info("Date picker window has been closed.");
        }
    }

    private void clickOnDatePickerHeader(Date date) {
        int iterator = switch (date) {
            case YEAR -> 2;
            case MONTH -> 1;
            default -> 0;
        };

        for (int i = 0; i < iterator; i++) {
            check.isElementPresent(datePickerWindowHeaderSelector, "Date picker window header");
            click.leftClick(datePickerWindowHeaderSelector, "Date picker window header");
            log.info("Date picker header has been clicked.");
        }
    }

    private void selectSpecificDate(PhpTravelsModel phpTravelsModel, Date date) {
        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel);

        String specificDate = switch (date) {
            case YEAR -> expectedDate.getYear();
            case MONTH -> expectedDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            case DAY -> {
                String day = expectedDate.getDay();
                yield (day.length() == 2 && day.startsWith("0")) ? day.substring(1) : day;
            }
        };

        String dateButtonSelector = switch (date) {
            case YEAR, MONTH ->
                    "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//span[text() = '" + specificDate + "']";
            case DAY ->
                    "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//td[@class = 'day ' and text() = '" + specificDate + "']";
        };

        click.leftClick(dateButtonSelector, "Date button");
        log.info("Date: {} {} has been clicked.", date.getName(), specificDate);
    }

    private void checkActualCheckInDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel) {
        String actualDate = get.getInputValue(dateInputSelector, "Date input");

        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel);

        String year = expectedDate.getYear();
        String month = String.format("%02d", expectedDate.getMonth().getValue());
        String day = String.format("%02d", Integer.parseInt(expectedDate.getDay()));
        String expectedDateAsString = day.concat("-").concat(month).concat("-").concat(year);

        assertThat(actualDate)
                .as("Date check")
                .isEqualTo(expectedDateAsString);
    }

    private void checkCityAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedCityValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, citySpanSelector, "after");
    }

    private String getExpectedCityValueAfterInput(PhpTravelsModel phpTravelsModel) {
        DestinationModel destination = getExpectedDestinationFromDataProvider(phpTravelsModel);

        return getDestinationName(destination);
    }

    private String getDestinationName(DestinationModel destination) {
        String city = destination.getCity();
        String country = destination.getCountry();

        return city.concat(" ").concat(country);
    }

    private void findAndSelectCity(PhpTravelsModel phpTravelsModel) {
        DestinationModel expectedDestination = getExpectedDestinationFromDataProvider(phpTravelsModel);
        String expectedDestinationCityName = expectedDestination.getCity();
        String expectedDestinationCountryName = expectedDestination.getCountry();

        typeCityToInputCity(expectedDestinationCityName);
        clickOnCityButton(expectedDestinationCityName, expectedDestinationCountryName);
    }

    private void clickOnCityButton(String expectedDestinationCityName, String expectedDestinationCountryName) {
        String cityButtonSelector = "//strong[contains(text(), '" + expectedDestinationCountryName + "')]/ancestor::li[contains(text(), '" + expectedDestinationCityName + "')]/..";
        check.isElementPresent(cityButtonSelector, "City button");
        click.leftClick(cityButtonSelector, "City button");

        log.info("{},{} has been selected.", expectedDestinationCityName, expectedDestinationCountryName);
    }

    private void typeCityToInputCity(String expectedDestinationCityName) {
        send.sendKeysToElementWithNoLeave(cityInputSelector, expectedDestinationCityName, "City input");
        log.info("{} has been typed to city input.", expectedDestinationCityName);
    }

    private void checkIfCitiesAreDisplayed() {
        check.isVisible(citiesContainerSelector, "Cities container");
        check.isElementPresent(citySelector, "City");
    }

    private void clickOnCityInput() {
        check.isEnabled(citySpanSelector,  "City input");
        click.leftClick(citySpanSelector, "City input");
        log.info("City input has been clicked.");
    }

    private void checkCityBeforeInput() {
        compareCityBeforeOrAfterInput("Search By City", citySpanSelector, "before");
    }

    private void compareCityBeforeOrAfterInput(String expectedLocationValue, String elementSelector, String inputStage) {
        LocatorAssertions.HasTextOptions options = new LocatorAssertions.HasTextOptions().setUseInnerText(true);
        check.hasText(elementSelector, options, expectedLocationValue, "City check " + inputStage + " input");
        log.info("City {} input value meets expected value.", inputStage);
    }

    private void checkIfTabIsActive() {
        check.hasAttribute(toursTabSelector, "aria-selected", "true", "Tours tab activity");
        log.info("Tours tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isVisible(toursSearchBarSelector, "Tours search bar");
        log.info("Tours search bar has been displayed");
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getToursPageModel().getExpectedDate();
    }

    private DestinationModel getExpectedDestinationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getToursPageModel().getExpectedDestination();
    }
}
