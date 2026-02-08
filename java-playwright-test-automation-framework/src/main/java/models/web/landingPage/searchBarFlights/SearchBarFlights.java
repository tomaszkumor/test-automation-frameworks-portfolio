package models.web.landingPage.searchBarFlights;

import com.microsoft.playwright.Locator;
import constants.common.*;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.flightsPage.flightsSearchPage.FlightsSearchPage;

import java.time.format.TextStyle;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarFlights extends SearchBarFlightsSelectors {
    public SearchBarFlights() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Flights search bar is displayed.");
    }

    @Step("Select departure location")
    public SearchBarFlights selectDepartureLocation(PhpTravelsModel phpTravelsModel) {
        checkDepartureLocationBeforeInput();
        clickOnDepartureLocationInput();
        typeCityNameToDepartureSearchInput(phpTravelsModel);
        findAndSelectDepartureLocation(phpTravelsModel);
        checkDepartureLocationAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select arrival location")
    public SearchBarFlights selectArrivalLocation(PhpTravelsModel phpTravelsModel) {
        checkArrivalLocationBeforeInput();
        clickOnArrivalLocationInput();
        typeCityNameToArrivalSearchInput(phpTravelsModel);
        findAndSelectArrivalLocation(phpTravelsModel);
        checkArrivalLocationAfterInput(phpTravelsModel);
        closeDepartureDatePickerWindowIfNecessary();

        return this;
    }

    @Step("Select flight destination")
    public SearchBarFlights selectFlightType(PhpTravelsModel phpTravelsModel) {
        checkFlightTypeBeforeChange();
        clickOnFlightTypeSelect();
        selectSpecificFlightType(phpTravelsModel);
        checkFlightTypeAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Select cabin class")
    public SearchBarFlights selectFlightClass(PhpTravelsModel phpTravelsModel) {
        checkFlightClassBeforeChange();
        clickOnFlightClassSelect();
        selectSpecificFlightClass(phpTravelsModel);
        checkFlightClassAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Select departure date")
    public SearchBarFlights selectDepartureDate(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.DEPARTURE);
        selectDepartureDateYear(phpTravelsModel);
        selectDepartureDateMonth(phpTravelsModel);
        selectDepartureDateDay(phpTravelsModel);
        checkActualDepartureDate(phpTravelsModel);
        scrollIntoSearchBarIfNecessary();

        return this;
    }

    @Step("Select return date")
    public SearchBarFlights selectReturnDate(PhpTravelsModel phpTravelsModel) {
        selectReturnDateYear(phpTravelsModel);
        selectReturnDateMonth(phpTravelsModel);
        selectReturnDateDay(phpTravelsModel);
        checkActualReturnDate(phpTravelsModel);

        return this;
    }

    @Step("Select travellers")
    public SearchBarFlights selectTravellers(PhpTravelsModel phpTravelsModel) {
        checkTravellersCountBeforeChange();
        clickOnTravellersInput();
        checkIfTravellersWindowIsDisplayed(true);
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        setInfantsNumber(phpTravelsModel);
        closeTravellersWindow();
        checkIfTravellersWindowIsDisplayed(false);
        checkTravellersCountAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public FlightsSearchPage clickOnSearchButton() {
        click.leftClick(searchButtonSelector, "Search button");
        log.info("Search button has been clicked.");

        return new FlightsSearchPage();
    }

    private void typeCityNameToDepartureSearchInput(PhpTravelsModel phpTravelsModel) {
        String city = getExpectedDepartureLocationFromDataProvider(phpTravelsModel).getAirportCity();
        send.sendKeysToElementWithNoLeave(departureLocationInputSelector, city, "Departure location search input");
        log.info("{} has been typed to departure location search input.", city);
    }

    private void typeCityNameToArrivalSearchInput(PhpTravelsModel phpTravelsModel) {
        String city = getExpectedArrivalLocationFromDataProvider(phpTravelsModel).getAirportCity();
        send.sendKeysToElementWithNoLeave(arrivalLocationInputSelector, city, "Arrival location search input");
        log.info("{} has been typed to arrival location search input.", city);
    }

    private void clickOnFlightTypeSelect() {
        click.leftClick(flightTypeSelectSelector, "Flight Type");
        log.info("Flight type select has been clicked.");
    }

    private void clickOnFlightClassSelect() {
        click.leftClick(flightClassSelectSelector, "Flight Class");
        log.info("Flight class select has been clicked.");
    }

    private void checkFlightTypeBeforeChange() {
        String expectedFlightTypeValue = "One Way";
        checkSelectValue(flightTypeSelectSelector, "flight type", expectedFlightTypeValue);
    }

    private void checkFlightTypeAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedFlightTypeValue = getExpectedFlightTypeAsStringFromDataProvider(phpTravelsModel);
        checkSelectValue(flightTypeSelectSelector, "flight type", expectedFlightTypeValue);
    }

    private void checkFlightClassBeforeChange() {
        String expectedFlightClassValue = "Economy";
        checkSelectValue(flightClassSelectSelector, "flight class", expectedFlightClassValue);
    }

    private void checkFlightClassAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedFlightClassValue = getExpectedFlightClassFromDataProvider(phpTravelsModel);
        checkSelectValue(flightClassSelectSelector, "flight class", expectedFlightClassValue);
    }

    private void checkSelectValue(String selectSelector, String selectName, String expectedVisaTypeValue) {
        check.hasText(selectSelector, expectedVisaTypeValue, selectName);
        log.info("Selected {} value: {}.", selectName, expectedVisaTypeValue);
    }

    private void closeTravellersWindow() {
        click.leftClick(h1Selector, "body");
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

    private void setInfantsNumber(PhpTravelsModel phpTravelsModel) {

        checkSpecificTravellersCountBeforeChange(Traveller.INFANTS);
        changeSpecificTravellersGroupCount(phpTravelsModel, Traveller.INFANTS);
        checkSpecificTravellersCountAfterChange(phpTravelsModel, Traveller.INFANTS);
    }

    private void checkTravellersCountBeforeChange() {
        String travellersCountSelector = passengersDropDownSelector.concat("/div/span[@x-text = 'getPassengerText()']");
        String expectedTravellersCount = "1 Passenger";
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count check before change");
    }

    private void checkTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        String travellersCountSelector = passengersDropDownSelector.concat("/div/span[@x-text = 'getPassengerText()']");
        String expectedTravellersCount = getTotalCountOfTravellers(phpTravelsModel).concat(" Passengers");
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count check after change");
    }

    private String getTotalCountOfTravellers(PhpTravelsModel phpTravelsModel) {
        String expectedAdultsCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.ADULTS);
        String expectedChildrenCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.CHILDS);
        String expectedInfantCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.INFANTS);

        int expectedAdultsCountAsNumber = Integer.parseInt(expectedAdultsCount);
        int expectedChildrenCountAsNumber = Integer.parseInt(expectedChildrenCount);
        int expectedInfantCountAsNumber = Integer.parseInt(expectedInfantCount);

        return String.valueOf(expectedAdultsCountAsNumber + expectedChildrenCountAsNumber + expectedInfantCountAsNumber);
    }

    private void clickOnTravellersInput() {
        click.leftClick(passengersDropDownSelector, "Travellers drop down");
        log.info("Travellers drop down has been clicked.");
    }

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        String specificTravellerGroupCountLocator = getActualSpecificTravellerGroupCountSelector(traveller);

        return get.getInnerText(specificTravellerGroupCountLocator, "Specific traveller group count");
    }

    private String getActualSpecificTravellerGroupCountSelector(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "adults";
            case CHILDS -> "children";
            case INFANTS -> "infants";
        };

        return "//div[contains(@class, 'show')]//span[@x-text = '" + specificTravellerGroupClass + "']";
    }

    private void compareSpecificTravellerGroupCount(String actualSpecificTravellerGroupCount, String expectedSpecificTravellerGroupCount, Traveller traveller, String stage) {
        assertThat(actualSpecificTravellerGroupCount)
                .as(traveller.getTraveller() + " count check " + stage + " change")
                .isEqualTo(expectedSpecificTravellerGroupCount);
    }

    private String getExpectedSpecificTravellerGroupCountBeforeChange(Traveller traveller) {
        return switch (traveller) {
            case ADULTS -> "1";
            case CHILDS, INFANTS -> "0";
        };
    }

    private String getExpectedSpecificTravellerGroupCountAfterChange(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        TravellerModel expectedTravellers = getExpectedTravellersFromDataProvider(phpTravelsModel);

        return switch (traveller) {
            case ADULTS -> expectedTravellers.getAdultsCount();
            case CHILDS -> expectedTravellers.getChildrenCount();
            case INFANTS -> expectedTravellers.getInfantsCount();
        };
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

    private String getArrowButtonSelector(Traveller traveller, Arrow arrow) {
        String arrowNumber = switch (arrow) {
            case LEFT -> "1";
            case RIGHT -> "2";
        };

        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "adults";
            case CHILDS -> "children";
            case INFANTS -> "infants";
        };

        return "//div[contains(@class, 'show')]//span[@x-text = '" + specificTravellerGroupClass + "']/../button[" + arrowNumber + "]";
    }

    private void changeSpecificTravellersGroupCount(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        String actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
        String expectedTravellersCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, traveller);
        int actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);
        int expectedTravellersCountAsNumber = Integer.parseInt(expectedTravellersCount);

        if (actualTravellersCountAsNumber > expectedTravellersCountAsNumber) {
            String arrowSelector = getArrowButtonSelector(traveller, Arrow.LEFT);

            do {
                check.isEnabled(arrowSelector, "Arrow");
                click.leftClick(arrowSelector, "Arrow");
                actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual {} count has been decreased to {}.", traveller.getTraveller(), actualTravellersCount);
            } while (actualTravellersCountAsNumber > expectedTravellersCountAsNumber);
        } else if (actualTravellersCountAsNumber < expectedTravellersCountAsNumber) {
            String arrowSelector = getArrowButtonSelector(traveller, Arrow.RIGHT);

            do {
                check.isEnabled(arrowSelector, "Arrow");
                click.leftClick(arrowSelector, "Arrow");
                actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual {} count has been increased to {}.", traveller.getTraveller(), actualTravellersCount);
            } while (actualTravellersCountAsNumber < expectedTravellersCountAsNumber);
        }
    }

    private void selectDepartureDateYear(PhpTravelsModel phpTravelsModel) {
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.YEAR);
    }

    private void selectDepartureDateMonth(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.MONTH);
    }

    private void selectDepartureDateDay(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Departure");
    }

    private void selectReturnDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.ARRIVAL);
        checkIfDatePickerWindowIsDisplayed(true, "Arrival");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void selectReturnDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.ARRIVAL);
        checkIfDatePickerWindowIsDisplayed(true, "Arrival");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void selectReturnDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.ARRIVAL);
        checkIfDatePickerWindowIsDisplayed(true, "Arrival");
        scrollIntoSearchBarIfNecessary();
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void closeDepartureDatePickerWindowIfNecessary() {
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        click.leftClick(bodySelector, "body");
        checkIfDatePickerWindowIsDisplayed(false, "Departure");
    }

    private void scrollIntoSearchBarIfNecessary() {
        Locator locator = get.getLocator(flightsSearchBarSelector);
        locator.scrollIntoViewIfNeeded();
    }

    private void checkActualDepartureDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.DEPARTURE);
    }

    private void checkActualReturnDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.ARRIVAL);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, LocationType locationType) {
        switch (locationType) {
            case DEPARTURE -> check.isEnabled(departureDateInputSelector, "Departure date input");
            case ARRIVAL -> check.isEnabled(returnDateInputSelector, "Return date input");
        }

        String actualDate = switch (locationType) {
            case DEPARTURE -> get.getInputValue(departureDateInputSelector, "Departure date input");
            case ARRIVAL -> get.getInputValue(returnDateInputSelector, "Return date input");
        };

        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, locationType);

        String year = expectedDate.getYear();
        String month = String.format("%02d", expectedDate.getMonth().getValue());
        String day = String.format("%02d", Integer.parseInt(expectedDate.getDay()));
        String expectedDateAsString = day.concat("-").concat(month).concat("-").concat(year);

        assertThat(actualDate)
                .as(locationType.getLocationTypeName() + " date check")
                .isEqualTo(expectedDateAsString);
    }

    private void clickOnDateInput(LocationType locationType) {
        switch (locationType) {
            case DEPARTURE -> {
                check.isElementPresent(departureDateInputSelector, "Selector");
                click.leftClick(departureDateInputSelector, "Departure date input");
            }
            case ARRIVAL -> {
                check.isElementPresent(returnDateInputSelector, "Selector");
                click.leftClick(returnDateInputSelector, "Return date input");
            }
        }

        log.info("{} date picker input has been clicked.", locationType.getLocationTypeName());
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed, String destination) {
        checkIfWindowIsDisplayed(datePickerWindowSelector, destination + " date picker window",  shouldBeDisplayed);
    }

    private void checkIfTravellersWindowIsDisplayed(boolean shouldBeDisplayed) {
        checkIfWindowIsDisplayed(travellersWindowSelector, "Travellers window",  shouldBeDisplayed);
    }

    private void checkIfWindowIsDisplayed(String windowSelector, String windowName, boolean shouldBeDisplayed) {
        if (shouldBeDisplayed) {
            check.isElementPresent(windowSelector, windowName);
            check.isVisible(windowSelector, windowName);
            log.info("{} window has been displayed.", windowName);
        } else {
            check.isNotVisible(windowSelector, windowName);
            log.info("{} window has been closed.", windowName);
        }
    }

    private void clickOnDatePickerHeader(Date date) {
        int iterator = switch (date) {
            case YEAR -> 2;
            case MONTH -> 1;
            default -> 0;
        };

        for (int i = 0; i < iterator; i++) {
            String datePickerWindowHeaderSelector = getDatePickerWindowHeaderSelector(i);

            check.isElementPresent(datePickerWindowHeaderSelector, "Date picker window header");
            click.leftClick(datePickerWindowHeaderSelector, "Date picker window header");

            log.info("Date picker header has been clicked.");
        }
    }

    private String getDatePickerWindowHeaderSelector(int i) {
        String selectorPart = switch (i) {
            case 0 -> "days";
            case 1 -> "months";
            default -> null;
        };

        return "//div[@class = 'datepicker-" + selectorPart + "']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-" + selectorPart + "']//th[contains(@class, 'switch')]";
    }

    private void selectSpecificDate(PhpTravelsModel phpTravelsModel, LocationType locationType, Date date) {
        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, locationType);

        String specificDate = switch (date) {
            case YEAR -> expectedDate.getYear();
            case MONTH -> expectedDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            case DAY -> {
                String day = expectedDate.getDay();
                yield (day.length() == 2 && day.startsWith("0")) ? day.substring(1) : day;
            }
        };

        String dateButtonSelector = switch (date) {
            case YEAR ->
                    "//div[@class = 'datepicker-years']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-years']//span[text() = '" + specificDate + "']";
            case MONTH ->
                    "//div[@class = 'datepicker-months']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-months']//span[text() = '" + specificDate + "']";
            case DAY ->
                    "//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-days']//div[text() = '" + specificDate + "' and not(contains(@class, 'old'))]";
        };

        Locator locator = get.getLocator(flightsSearchBarSelector);
        locator.scrollIntoViewIfNeeded();
        check.isInViewport(dateButtonSelector, "Date picker window");
        check.isElementPresent(dateButtonSelector, "Date picker window");


        click.leftClick(dateButtonSelector, "Date button");
        log.info("{} date: {} {} has been clicked.", locationType.getLocationTypeName(), date.getName(), specificDate);
    }

    private void checkDepartureLocationBeforeInput() {
        compareLocationBeforeOrAfterInput("", departureLocationInputSelector, "Departure", "before");
    }

    private void checkArrivalLocationBeforeInput() {
        compareLocationBeforeOrAfterInput("", arrivalLocationInputSelector, "Arrival", "before");
    }

    private void checkDepartureLocationAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedDepartureLocation = getExpectedLocationValueAfterInput(phpTravelsModel, LocationType.DEPARTURE);
        compareLocationBeforeOrAfterInput(expectedDepartureLocation, departureLocationInputSelector, "Departure", "after");
    }

    private void checkArrivalLocationAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedArrivalLocation = getExpectedLocationValueAfterInput(phpTravelsModel, LocationType.ARRIVAL);
        compareLocationBeforeOrAfterInput(expectedArrivalLocation, arrivalLocationInputSelector, "Arrival", "after");
    }

    private void compareLocationBeforeOrAfterInput(String expectedLocationValue, String elementSelector, String destination, String inputStage) {
        check.hasValue(elementSelector, expectedLocationValue, "Location value");
        log.info("{} location {} input value meets expected value.", destination, inputStage);
    }

    private String getExpectedLocationValueAfterInput(PhpTravelsModel phpTravelsModel, LocationType locationType) {
        String expectedLocationCode;
        String expectedLocationName;

        switch (locationType) {
            case DEPARTURE -> {
                expectedLocationCode = getExpectedDepartureLocationFromDataProvider(phpTravelsModel).getAirportCode();
                expectedLocationName = getExpectedDepartureLocationFromDataProvider(phpTravelsModel).getAirportName();

                return expectedLocationCode.concat(" - ").concat(expectedLocationName);
            }

            case ARRIVAL -> {
                expectedLocationCode = getExpectedArrivalLocationFromDataProvider(phpTravelsModel).getAirportCode();
                expectedLocationName = getExpectedArrivalLocationFromDataProvider(phpTravelsModel).getAirportName();

                return expectedLocationCode.concat(" - ").concat(expectedLocationName);
            }

            default -> {
                return null;
            }
        }
    }

    private void selectSpecificFlightType(PhpTravelsModel phpTravelsModel) {
        String expectedFlightTypeValue = getExpectedFlightTypeAsStringFromDataProvider(phpTravelsModel);
        String specificFlightTypeSelector = "//span[@x-text = 'type.name' and text() = '" + expectedFlightTypeValue + "']/..";
        click.leftClick(specificFlightTypeSelector, expectedFlightTypeValue + " flight type select");
    }

    private void selectSpecificFlightClass(PhpTravelsModel phpTravelsModel) {
        String expectedFlightClassValue = getExpectedFlightClassFromDataProvider(phpTravelsModel);
        String specificFlightClassSelector = "//span[@x-text = 'cls.name' and text() = '" + expectedFlightClassValue + "']/..";
        click.leftClick(specificFlightClassSelector, expectedFlightClassValue + " flight type select");
    }

    private void findAndSelectDepartureLocation(PhpTravelsModel phpTravelsModel) {
        Location expectedDepartureLocation = getExpectedDepartureLocationFromDataProvider(phpTravelsModel);
        String locationSelector = getExpectedDepartureLocationInputSelector(expectedDepartureLocation);
        clickOnSpecificLocation(locationSelector, expectedDepartureLocation, "departure");
    }

    private void findAndSelectArrivalLocation(PhpTravelsModel phpTravelsModel) {
        Location expectedArrivalLocation = getExpectedArrivalLocationFromDataProvider(phpTravelsModel);
        String locationSelector = getExpectedArrivalLocationInputSelector(expectedArrivalLocation);
        clickOnSpecificLocation(locationSelector, expectedArrivalLocation, "arrival");
    }

    private void clickOnSpecificLocation(String locationInputSelector, Location location, String destination) {
        String airportName = location.getAirportName();
        String airportCity = location.getAirportCity();
        String airportCountry = location.getAirportCountry();

        click.leftClick(locationInputSelector, "Location input");
        log.info("{}, {}, {} has been set as {} location.", airportName, airportCity, airportCountry, destination);
    }

    private String getExpectedDepartureLocationInputSelector(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();

        return "//span[@x-text = 'a.id' and text() = '" + locationCode + "']/../..";
    }

    private String getExpectedArrivalLocationInputSelector(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();

        return "//span[@x-text = 'a.id' and text() = '" + locationCode + "']/../..";
    }

    private void checkIfTabIsActive() {
        check.containsAttribute(flightsTabSelector, "class", "text-primary", "Flights tab activity");
        log.info("Flights tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isVisible(flightsSearchBarSelector, "Flights search bar");
        log.info("Flights search bar has been displayed.");
    }

    private void clickOnDepartureLocationInput() {
        clickOnLocationInput(departureLocationInputSelector, "Departure");
    }

    private void clickOnArrivalLocationInput() {
        clickOnLocationInput(arrivalLocationInputSelector, "Arrival");
    }

    private void clickOnLocationInput(String cabinClassLocator, String locationDestination) {
        click.leftClick(cabinClassLocator, "Location");
        log.info("{} input has been clicked.", locationDestination);
    }

    private Location getExpectedDepartureLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedDepartureLocation();
    }

    private Location getExpectedArrivalLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedArrivalLocation();
    }

    private String getExpectedFlightTypeAsStringFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightType().getFlightDestination();
    }

    private String getExpectedFlightClassFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightClass().getCabinClass();
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel, LocationType locationType) {
        return switch (locationType) {
            case DEPARTURE -> phpTravelsModel.getFlightsPageModel().getExpectedDepartureDate();
            case ARRIVAL -> phpTravelsModel.getFlightsPageModel().getExpectedReturnDate();
        };
    }

    private TravellerModel getExpectedTravellersFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedTravellers();
    }
}
