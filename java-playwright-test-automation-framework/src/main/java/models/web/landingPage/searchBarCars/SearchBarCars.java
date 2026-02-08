package models.web.landingPage.searchBarCars;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import constants.carsPage.DateType;
import constants.carsPage.StateType;
import constants.common.Arrow;
import constants.common.Date;
import constants.common.Traveller;
import dataProviders.dataProvidersModels.web.carsPageModels.TimeModel;
import dataProviders.dataProvidersModels.web.commonModels.AirportModel;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.carsPage.carsSearchPage.CarsSearchPage;

import java.time.format.TextStyle;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarCars extends SearchBarCarsSelectors {
    public SearchBarCars() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Cars search bar is displayed.");
    }

    @Step("Select departure airport")
    public SearchBarCars selectDepartureAirport(PhpTravelsModel phpTravelsModel) {
        checkDepartureAirportBeforeInput();
        clickOnDepartureAirportInput();
        checkIfDepartureAirportsAreDisplayed();
        findAndSelectAirport(phpTravelsModel);
        checkDepartureAirportAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select arrival city")
    public SearchBarCars selectArrivalCity(PhpTravelsModel phpTravelsModel) {
        checkArrivalCityBeforeInput();
        clickOnArrivalCityInput();
        checkIfCitiesAreDisplayed();
        findAndSelectCity(phpTravelsModel);
        checkCityAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select pick up date")
    public SearchBarCars selectPickUpDate(PhpTravelsModel phpTravelsModel) {
        selectPickUpDateYear(phpTravelsModel);
        selectPickUpDateMonth(phpTravelsModel);
        selectPickUpDateDay(phpTravelsModel);
        checkActualPickUpDate(phpTravelsModel);

        return this;
    }

    @Step("Select drop off date")
    public SearchBarCars selectDropOffDate(PhpTravelsModel phpTravelsModel) {
        selectDropOffDateYear(phpTravelsModel);
        selectDropOffDateMonth(phpTravelsModel);
        selectDropOffDateDay(phpTravelsModel);
        checkActualDropOffDate(phpTravelsModel);

        return this;
    }

    @Step("Select pick up time")
    public SearchBarCars selectPickUpTime(PhpTravelsModel phpTravelsModel) {
        checkPickUpTimeBeforeInput();
        clickOnPickUpTimeSelect();
        setPickUpTime(phpTravelsModel);
        closeTimeSelect();
        checkPickUpTimeAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select drop off time")
    public SearchBarCars selectDropOffTime(PhpTravelsModel phpTravelsModel) {
        checkDropOffTimeBeforeInput();
        clickOnDropOffTimeSelect();
        setDropOffTime(phpTravelsModel);
        closeTimeSelect();
        checkDropOffTimeAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public CarsSearchPage clickOnSearchButton() {
        click.leftClick(searchButtonSelector, "Search button");
        log.info("Search button has been clicked.");

        return new CarsSearchPage();
    }

    @Step("Select travellers")
    public SearchBarCars selectTravellers(PhpTravelsModel phpTravelsModel) {
        checkInitialTravellersCount();
        clickOnTravellersInput();
        checkIfTravellersWindowIsDisplayed(true);
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        closeTravellersWindow();
        checkIfTravellersWindowIsDisplayed(false);
        checkTravellersCountAfterChange(phpTravelsModel);

        return this;
    }

    private void checkPickUpTimeBeforeInput() {
        compareTimeBeforeOrAfterSelect("00:00", pickUpTimeSelectSelector, "Pick up time", "before");
    }

    private void checkDropOffTimeBeforeInput() {
        compareTimeBeforeOrAfterSelect("00:00", dropOffTimeSelectSelector, "Drop off time", "before");
    }

    private void compareTimeBeforeOrAfterSelect(String expectedTimeValue, String elementSelector, String timeType, String inputStage) {
        check.hasValue(elementSelector, expectedTimeValue, timeType);
        log.info("{} time {} input value meets expected value.", timeType, inputStage);
    }

    private void clickOnPickUpTimeSelect() {
        clickOnTimeSelect(pickUpTimeSelectSelector, "Pick up time");
    }

    private void clickOnDropOffTimeSelect() {
        clickOnTimeSelect(dropOffTimeSelectSelector, "Drop off time");
    }

    private void clickOnTimeSelect(String timeSelectSelector, String timeType) {
        click.leftClick(timeSelectSelector, "Time select");
        log.info("{} select has been clicked.", timeType);
    }

    private void setPickUpTime(PhpTravelsModel phpTravelsModel) {
        String time = getExpectedTime(phpTravelsModel, DateType.PICK_UP, StateType.BEFORE);
        selectTime(pickUpTimeSelectSelector, time, "pick up time");
    }

    private void setDropOffTime(PhpTravelsModel phpTravelsModel) {
        String time = getExpectedTime(phpTravelsModel, DateType.DROP_OFF, StateType.BEFORE);
        selectTime(dropOffTimeSelectSelector, time, "drop off time");
    }

    private void selectTime(String timeSelectSelector, String expectedTime, String timeType) {
        click.clickOnSelectOption(timeSelectSelector, expectedTime, timeType);

        log.info("{} has been selected for {}.", expectedTime, timeType);
    }

    private String getExpectedTime(PhpTravelsModel phpTravelsModel, DateType dateType, StateType stateType) {
        TimeModel expectedTime = switch (dateType) {
            case PICK_UP -> phpTravelsModel.getCarsPageModel().getExpectedPickUpTime();
            case DROP_OFF -> phpTravelsModel.getCarsPageModel().getExpectedDropOffTime();
        };

        String hours = expectedTime.getHours();
        String minutes = expectedTime.getMinutes();
        String timePeriod = expectedTime.getDayPeriod();

        return switch(stateType) {
            case AFTER -> hours.concat(":").concat(minutes);
            case BEFORE -> hours.concat(":").concat(minutes).concat(" ").concat(timePeriod);
        };
    }

    private void closeTimeSelect() {
        click.leftClick(h4Selector, "h4");
    }

    private void checkPickUpTimeAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedTime = getExpectedTime(phpTravelsModel, DateType.PICK_UP, StateType.AFTER);
        compareTimeBeforeOrAfterSelect(expectedTime, pickUpTimeSelectSelector, "Pick up time", "after");
    }

    private void checkDropOffTimeAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedTime = getExpectedTime(phpTravelsModel, DateType.DROP_OFF, StateType.AFTER);
        compareTimeBeforeOrAfterSelect(expectedTime, dropOffTimeSelectSelector, "Drop off time", "after");
    }

    private void checkInitialTravellersCount() {
        String travellersCountSelector = travellersDropDownSelector.concat("//span");
        String expectedTravellersCount = "1";
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count");
    }

    private void clickOnTravellersInput() {
        click.leftClick(travellersDropDownSelector, "Travellers drop down");
        log.info("Travellers drop down has been clicked.");
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

    private void compareSpecificTravellerGroupCount(String actualSpecificTravellerGroupCount, String expectedSpecificTravellerGroupCount, Traveller traveller, String stage) {
        assertThat(actualSpecificTravellerGroupCount)
                .as(traveller.getTraveller() + " count check " + stage + " change")
                .isEqualTo(expectedSpecificTravellerGroupCount);
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

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        String specificTravellerGroupCountSelector = getActualSpecificTravellerGroupCountSelector(traveller);

        return get.getInputValue(specificTravellerGroupCountSelector, "Specific traveller group count");
    }

    private String getActualSpecificTravellerGroupCountSelector(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "cars_adults";
            case CHILDS -> "cars_child";
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

    private void closeTravellersWindow() {
        click.leftClick(h4Selector, "h4");
    }

    private void checkTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        String travellersCountSelector = travellersDropDownSelector.concat("//span");
        String expectedTravellersCount = getTotalCountOfTravellers(phpTravelsModel);
        check.hasText(travellersCountSelector, expectedTravellersCount, "Travellers count");
    }

    private String getTotalCountOfTravellers(PhpTravelsModel phpTravelsModel) {
        String expectedAdultsCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.ADULTS);
        String expectedChildrenCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, Traveller.CHILDS);

        int expectedAdultsCountAsNumber = Integer.parseInt(expectedAdultsCount);
        int expectedChildrenCountAsNumber = Integer.parseInt(expectedChildrenCount);

        return String.valueOf(expectedAdultsCountAsNumber + expectedChildrenCountAsNumber);
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
        return phpTravelsModel.getCarsPageModel().getExpectedTravellers();
    }

    private void checkDepartureAirportBeforeInput() {
        compareCityBeforeOrAfterInput("Select City", departureSpanSelector, "before");
    }

    private void checkArrivalCityBeforeInput() {
        compareCityBeforeOrAfterInput("Select", arrivalSpanSelector, "before");
    }

    private void checkDepartureAirportAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedAirportValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, departureSpanSelector, "after");
    }

    private void checkCityAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedCityValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, arrivalSpanSelector, "after");
    }

    private void compareCityBeforeOrAfterInput(String expectedLocationValue, String elementSelector, String inputStage) {
        check.hasText(elementSelector, expectedLocationValue, "Location value");
        log.info("City {} input value meets expected value.", inputStage);
    }

    private void clickOnDepartureAirportInput() {
        clickOnLocationInput(departureSpanSelector, "Departure airport");
    }

    private void clickOnArrivalCityInput() {
        clickOnLocationInput(arrivalSpanSelector, "Arrival city");
    }

    private void clickOnLocationInput(String locationSpanSelector, String locationType) {
        click.leftClick(locationSpanSelector, "Location span");
        log.info("{} input has been clicked.", locationType);
    }

    private void checkIfDepartureAirportsAreDisplayed() {
        checkIfLocationIsDisplayed(airportsContainerSelector, airportSelector, "Airports container", "Airport");
    }

    private void checkIfCitiesAreDisplayed() {
        checkIfLocationIsDisplayed(citiesContainerSelector, citySelector, "Cities container", "City");
    }

    private void checkIfLocationIsDisplayed(String containerSelector, String selector, String containerName, String selectorName) {
        check.isVisible(containerSelector, containerName);
        check.isElementPresent(selector, selectorName);
    }

    private void findAndSelectAirport(PhpTravelsModel phpTravelsModel) {
        AirportModel expectedAirport = getExpectedAirportFromDataProvider(phpTravelsModel);
        String expectedAirportCity = expectedAirport.getAirportCity();

        typeAirportToAirportInput(expectedAirportCity);
        clickOnAirportButton(expectedAirport);
    }

    private void findAndSelectCity(PhpTravelsModel phpTravelsModel) {
        DestinationModel expectedDestination = getExpectedArrivalCityFromDataProvider(phpTravelsModel);
        String expectedDestinationCityName = expectedDestination.getCity();
        String expectedDestinationCountryName = expectedDestination.getCountry();

        typeDestinationToInputCity(expectedDestinationCityName);
        clickOnCityButton(expectedDestinationCityName, expectedDestinationCountryName);
    }

    private void typeDestinationToInputCity(String expectedHotelDestinationCityName) {
        typeLocationToInput(expectedHotelDestinationCityName, "city");
    }

    private void typeAirportToAirportInput(String expectedAirport) {
        typeLocationToInput(expectedAirport, "airport");
    }

    private void typeLocationToInput(String expectedHotelDestinationCityName, String inputType) {
        check.isEnabled(airportOrCityInputSelector, "Airport/City input");
        send.sendKeysToElementWithNoLeave(airportOrCityInputSelector, expectedHotelDestinationCityName, "City/airport input");
        log.info("{} has been typed to {} input.", expectedHotelDestinationCityName, inputType);
    }

    private String getExpectedCityValueAfterInput(PhpTravelsModel phpTravelsModel) {
        DestinationModel hotelDestination = getExpectedArrivalCityFromDataProvider(phpTravelsModel);

        return getArrivalCityName(hotelDestination);
    }

    private String getExpectedAirportValueAfterInput(PhpTravelsModel phpTravelsModel) {
        AirportModel airport = getExpectedAirportFromDataProvider(phpTravelsModel);

        return getDepartureAirportName(airport);
    }

    private String getArrivalCityName(DestinationModel hotelDestination) {
        String city = hotelDestination.getCity();
        String country = hotelDestination.getCountry();

        return city.concat(" ").concat(country);
    }

    private String getDepartureAirportName(AirportModel airport) {
        String city = airport.getAirportCity();
        String code = airport.getAirportCode();

        return city.concat(" ").concat(code);
    }

    private void clickOnCityButton(String expectedHotelDestinationCityName, String expectedHotelDestinationCountryName) {
        String citySelector = "//strong[contains(text(), '" + expectedHotelDestinationCountryName + "')]/ancestor::li[contains(text(), '" + expectedHotelDestinationCityName + "')]/..";
        check.isElementPresent(citySelector, "City");
        click.leftClick(citySelector, "City");

        log.info("{}, {} has been selected.", expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void clickOnAirportButton(AirportModel expectedAirport) {
        String expectedAirportName = expectedAirport.getAirportName();
        String expectedAirportCode = expectedAirport.getAirportCode();
        String expectedAirportCity = expectedAirport.getAirportCity();
        String expectedAirportCountry = expectedAirport.getAirportCountry();

        String airportSelector = "//small[text() = '" + expectedAirportName + "']/ancestor::div/strong[text() = '" + expectedAirportCity + "']/small[text() = '" + expectedAirportCountry + "']/ancestor::li/button[text() = '" + expectedAirportCode + "']";
        check.isElementPresent(airportSelector, "Airport");
        click.leftClick(airportSelector, "City");

        log.info("{}, {}, {} has been selected.", expectedAirportName, expectedAirportCity, expectedAirportCountry);
    }

    private void selectPickUpDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectPickUpDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectPickUpDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        scrollIntoSearchBarIfNecessary();
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectDropOffDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void selectDropOffDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        scrollIntoSearchBarIfNecessary();
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void selectDropOffDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        scrollIntoSearchBarIfNecessary();
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void scrollIntoSearchBarIfNecessary() {
        Locator locator = get.getLocator(carsSearchBarSelector);
        locator.scrollIntoViewIfNeeded();
    }

    private void checkActualPickUpDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, DateType.PICK_UP);
    }

    private void checkActualDropOffDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, DateType.DROP_OFF);
    }

    private void clickOnDateInput(DateType dateType) {
        String dateInputSelector = switch (dateType) {
            case PICK_UP -> checkInDateInputSelector;
            case DROP_OFF -> checkOutDateInputSelector;
        };

        check.isElementPresent(dateInputSelector, "Date input selector");

        switch (dateType) {
            case PICK_UP -> click.leftClick(pickUpDateInputSelector, "Pick up date input");
            case DROP_OFF -> click.leftClick(dropOffDateInputSelector, "Drop off date input");
        }

        log.info("{} date picker input has been clicked.", dateType.getDateTypeName());
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed, String destination) {
        if (shouldBeDisplayed) {
            check.isElementPresent(datePickerWindowSelector, "Date picker window");
            check.isVisible(datePickerWindowSelector, "Date picker window");
            log.info("{} date picker window has been displayed.", destination);
        } else {
            check.isElementNotPresent(datePickerWindowSelector, "Date picker window");
            log.info("{} date picker window has been closed.", destination);
        }
    }

    private void clickOnDatePickerHeader(Date date) {
        int iterator = switch (date) {
            case YEAR -> 2;
            case MONTH -> 1;
            default -> 0;
        };

        for (int i = 0; i < iterator; i++) {
            check.isElementPresent(datePickerWindowHeaderSelector, "Date picker window");
            click.leftClick(datePickerWindowHeaderSelector, "Date picker window");

            log.info("Date picker header has been clicked.");
        }
    }

    private void selectSpecificDate(PhpTravelsModel phpTravelsModel, DateType dateType, Date date) {
        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, dateType);

        String specificDate = switch (date) {
            case YEAR -> expectedDate.getYear();
            case MONTH -> expectedDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            case DAY -> {
                String day = expectedDate.getDay();
                yield (day.length() == 2 && day.startsWith("0")) ? day.substring(1) : day;
            }
        };

        String dateSelector = switch (date) {
            case YEAR, MONTH ->
                    "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//span[text() = '" + specificDate + "']";
            case DAY ->
                    "//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//td[@class = 'day ' and text() = '" + specificDate + "']";
        };

        click.leftClick(dateSelector, "Date");
        log.info("{} date: {} {} has been clicked.", dateType.getDateTypeName(), date.getName(), specificDate);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, DateType dateType) {
        switch (dateType) {
            case PICK_UP -> check.isEnabled(pickUpDateInputSelector, "Departure date input");
            case DROP_OFF -> check.isEnabled(dropOffDateInputSelector, "Return date input");
        }

        String actualDate = switch (dateType) {
            case PICK_UP -> get.getInputValue(pickUpDateInputSelector, "Pick up date input");
            case DROP_OFF -> get.getInputValue(dropOffDateInputSelector, "Drop off date input");
        };

        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, dateType);

        String year = expectedDate.getYear();
        String month = String.format("%02d", expectedDate.getMonth().getValue());
        String day = String.format("%02d", Integer.parseInt(expectedDate.getDay()));
        String expectedDateAsString = day.concat("-").concat(month).concat("-").concat(year);

        assertThat(actualDate)
                .as(dateType.getDateTypeName() + " date check")
                .isEqualTo(expectedDateAsString);
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel, DateType dateType) {
        return switch (dateType) {
            case PICK_UP -> phpTravelsModel.getCarsPageModel().getExpectedPickUpDate();
            case DROP_OFF -> phpTravelsModel.getCarsPageModel().getExpectedDropOffDate();
        };
    }

    private AirportModel getExpectedAirportFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getCarsPageModel().getExpectedDepartureAirport();
    }

    private DestinationModel getExpectedArrivalCityFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getCarsPageModel().getExpectedArrivalCity();
    }

    private void checkIfTabIsActive() {
        check.hasAttribute(carsTabSelector, "aria-selected", "true", "Cars tab");
        log.info("Cars tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isVisible(carsSearchBarSelector, "Cars search bar");
        log.info("Cars search bar has been displayed.");
    }
}
