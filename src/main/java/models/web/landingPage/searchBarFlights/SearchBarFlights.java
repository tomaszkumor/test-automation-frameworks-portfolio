package models.web.landingPage.searchBarFlights;

import constants.common.*;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.flightsPage.flightsSearchPage.FlightsSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.format.TextStyle;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarFlights extends SearchBarFlightsLocators {
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
    public SearchBarFlights selectCabinClass(PhpTravelsModel phpTravelsModel) {
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
        click.clickOnVisibleElement(searchButton, 15);
        log.info("Search button has been clicked.");

        return new FlightsSearchPage();
    }

    private void scrollIntoSearchBarIfNecessary() {
        browser.scrollIntoView(h1);
    }

    private void typeCityNameToDepartureSearchInput(PhpTravelsModel phpTravelsModel) {
        String city = getExpectedDepartureLocationFromDataProvider(phpTravelsModel).getAirportCity();
        send.sendKeysToWebElementWithNoLeave(departureLocationInput, city);
        log.info("{} has been typed to departure location search input.", city);
    }

    private void typeCityNameToArrivalSearchInput(PhpTravelsModel phpTravelsModel) {
        String city = getExpectedArrivalLocationFromDataProvider(phpTravelsModel).getAirportCity();
        send.sendKeysToWebElementWithNoLeave(arrivalLocationInput, city);
        log.info("{} has been typed to arrival location search input.", city);
    }

    private void clickOnFlightTypeSelect() {
        click.clickOnElement(flightTypeSelect, 15);
        log.info("Flight type select has been clicked.");
    }

    private void clickOnFlightClassSelect() {
        click.clickOnElement(flightClassSelect, 15);
        log.info("Flight class select has been clicked.");
    }

    private void checkFlightTypeBeforeChange() {
        String expectedFlightTypeValue = "One Way";
        checkSelectValue(flightTypeSelect, "flight type", expectedFlightTypeValue);
    }

    private void checkFlightTypeAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedFlightTypeValue = getExpectedFlightTypeAsStringFromDataProvider(phpTravelsModel);
        checkSelectValue(flightTypeSelect, "flight type", expectedFlightTypeValue);
    }

    private void checkFlightClassBeforeChange() {
        String expectedFlightClassValue = "Economy";
        checkSelectValue(flightClassSelect, "flight class", expectedFlightClassValue);
    }

    private void checkFlightClassAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedFlightClassValue = getExpectedFlightClassFromDataProvider(phpTravelsModel);
        checkSelectValue(flightClassSelect, "flight class", expectedFlightClassValue);
    }

    private void checkSelectValue(WebElement select, String selectName, String expectedVisaTypeValue) {
        check.isTextDisplayed(select, expectedVisaTypeValue, 15);
        log.info("Selected {} value: {}.", selectName, expectedVisaTypeValue);
    }

    private void closeTravellersWindow() {
        click.clickOnElement(passengersDropDown, 15);
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
        WebElement travellersCount = passengersDropDown.findElement(By.xpath("./div/span[@x-text = 'getPassengerText()']"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = "1 Passenger";

        assertThat(actualTravellersCount)
                .as("Travellers count check before change")
                .isEqualTo(expectedTravellersCount);
    }

    private void checkTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement travellersCount = passengersDropDown.findElement(By.xpath("./div/span[@x-text = 'getPassengerText()']"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = getTotalCountOfTravellers(phpTravelsModel).concat(" Passengers");

        assertThat(actualTravellersCount)
                .as("Travellers count check after change")
                .isEqualTo(expectedTravellersCount);
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
        click.clickOnElement(passengersDropDown, 15);
        log.info("Travellers drop down has been clicked.");
    }

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        By specificTravellerGroupCountLocator = getActualSpecificTravellerGroupCountLocator(traveller);

        return get.getTextFromElement(specificTravellerGroupCountLocator);
    }

    private By getActualSpecificTravellerGroupCountLocator(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "adults";
            case CHILDS -> "children";
            case INFANTS -> "infants";
        };

        return By.xpath("//div[contains(@class, 'show')]//span[@x-text = '" + specificTravellerGroupClass + "']");
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

    private By getArrowLocator(Traveller traveller, Arrow arrow) {
        String arrowNumber = switch (arrow) {
            case LEFT -> "1";
            case RIGHT -> "2";
        };

        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "adults";
            case CHILDS -> "children";
            case INFANTS -> "infants";
        };

        return By.xpath("//div[contains(@class, 'show')]//span[@x-text = '" + specificTravellerGroupClass + "']/../button[" + arrowNumber + "]");
    }

    private WebElement getArrowButton(Traveller traveller, Arrow arrow) {
        By arrowLocator = getArrowLocator(traveller, arrow);

        return getWebDriver().getDriver().findElement(arrowLocator);
    }

    private void changeSpecificTravellersGroupCount(PhpTravelsModel phpTravelsModel, Traveller traveller) {
        String actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
        String expectedTravellersCount = getExpectedSpecificTravellerGroupCountAfterChange(phpTravelsModel, traveller);
        int actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);
        int expectedTravellersCountAsNumber = Integer.parseInt(expectedTravellersCount);

        if (actualTravellersCountAsNumber > expectedTravellersCountAsNumber) {
            WebElement arrow = getArrowButton(traveller, Arrow.LEFT);

            do {
                click.clickOnEnabledElement(arrow, 15);
                actualTravellersCount = getActualSpecificTravellerGroupCount(traveller);
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual {} count has been decreased to {}.", traveller.getTraveller(), actualTravellersCount);
            } while (actualTravellersCountAsNumber > expectedTravellersCountAsNumber);
        } else if (actualTravellersCountAsNumber < expectedTravellersCountAsNumber) {
            WebElement arrow = getArrowButton(traveller, Arrow.RIGHT);

            do {
                click.clickOnEnabledElement(arrow, 15);
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
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void selectReturnDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.ARRIVAL);
        checkIfDatePickerWindowIsDisplayed(true, "Arrival");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void selectReturnDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.ARRIVAL);
        checkIfDatePickerWindowIsDisplayed(true, "Arrival");
        selectSpecificDate(phpTravelsModel, LocationType.ARRIVAL, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Arrival");
    }

    private void closeDepartureDatePickerWindowIfNecessary() {
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        click.clickOnElement(body, 15);
        checkIfDatePickerWindowIsDisplayed(false, "Departure");
    }

    private void checkActualDepartureDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.DEPARTURE);
    }

    private void checkActualReturnDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.ARRIVAL);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, LocationType locationType) {
        switch (locationType) {
            case DEPARTURE -> check.isElementEnabled(departureDateInput, 15);
            case ARRIVAL -> check.isElementEnabled(returnDateInput, 15);
        }

        String actualDate = switch (locationType) {
            case DEPARTURE -> get.getValueFromElement(departureDateInput);
            case ARRIVAL -> get.getValueFromElement(returnDateInput);
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
                By locator = By.xpath("//input[@name = 'flights_departure_date']");
                check.isElementPresentByLocator(locator, 10);
                click.clickOnEnabledElement(departureDateInput, 15);
            }
            case ARRIVAL -> {
                By locator = By.xpath("//input[@name = 'flights_arrival_date']");
                check.isElementPresentByLocator(locator, 10);
                click.clickOnEnabledElement(returnDateInput, 15);
            }
        }

        log.info("{} date picker input has been clicked.", locationType.getLocationTypeName());
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed, String destination) {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]");
        checkIfWindowIsDisplayed(datePickerWindowLocator, destination + " date picker", shouldBeDisplayed);
    }

    private void checkIfTravellersWindowIsDisplayed(boolean shouldBeDisplayed) {
        By travellersWindowLocator = By.xpath("//input[@name = 'passengers']/following-sibling::div[contains(@class, 'show')]");
        checkIfWindowIsDisplayed(travellersWindowLocator, "Travellers", shouldBeDisplayed);
    }

    private void checkIfWindowIsDisplayed(By windowLocator, String windowName, boolean shouldBeDisplayed) {
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(windowLocator, 50, 15);

            WebElement window = getWebDriver().getDriver().findElement(windowLocator);
            check.isElementDisplayed(window, 15);
            log.info("{} window has been displayed.", windowName);
        } else {
            check.isNumberOfElementsEqualTo(windowLocator, 0, 50, 15);
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
            By datePickerWindowHeaderLocator = getDatePickerWindowHeaderLocator(i);
            check.isNumberOfElementsEqualTo(datePickerWindowHeaderLocator, 1, 5);
            WebElement datePickerWindowHeader = getWebDriver().getDriver().findElement(datePickerWindowHeaderLocator);
            click.clickOnVisibleElement(datePickerWindowHeader, 15);

            log.info("Date picker header has been clicked.");
        }
    }

    private By getDatePickerWindowHeaderLocator(int i) {
        String datePickerHeader = switch (i) {
            case 0 -> "days";
            case 1 -> "months";
            default -> null;
        };

        return By.xpath("//div[@class = 'datepicker-" + datePickerHeader + "']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-" + datePickerHeader + "']//th[contains(@class, 'switch')]");
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

        By dateLocator = switch (date) {
            case YEAR ->
                    By.xpath("//div[@class = 'datepicker-years']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-years']//span[text() = '" + specificDate + "']");
            case MONTH ->
                    By.xpath("//div[@class = 'datepicker-months']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-months']//span[text() = '" + specificDate + "']");
            case DAY ->
                    By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-days']//div[text() = '" + specificDate + "' and not(contains(@class, 'old'))]");
        };

        WebElement dateButton = getWebDriver().getDriver().findElement(dateLocator);
        browser.scrollIntoView(dateButton);
        click.clickOnVisibleElement(dateButton, 15);
        log.info("{} date: {} {} has been clicked.", locationType.getLocationTypeName(), date.getName(), specificDate);
    }

    private void checkDepartureLocationBeforeInput() {
        compareLocationBeforeOrAfterInput("", departureLocationInput, "Departure", "before");
    }

    private void checkArrivalLocationBeforeInput() {
        compareLocationBeforeOrAfterInput("", arrivalLocationInput, "Arrival", "before");
    }

    private void checkDepartureLocationAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedDepartureLocation = getExpectedLocationValueAfterInput(phpTravelsModel, LocationType.DEPARTURE);
        compareLocationBeforeOrAfterInput(expectedDepartureLocation, departureLocationInput, "Departure", "after");
    }

    private void checkArrivalLocationAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedArrivalLocation = getExpectedLocationValueAfterInput(phpTravelsModel, LocationType.ARRIVAL);
        compareLocationBeforeOrAfterInput(expectedArrivalLocation, arrivalLocationInput, "Arrival", "after");
    }

    private void compareLocationBeforeOrAfterInput(String expectedLocationValue, WebElement element, String destination, String inputStage) {
        String actualLocationValue = get.getValueFromElement(element);

        assertThat(actualLocationValue)
                .as(destination + " location check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

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
        String expectedFlightTypeValue = getExpectedFlightDestinationAsStringFromDataProvider(phpTravelsModel);
        By specificFlightTypeLocator = By.xpath("//span[@x-text = 'type.name' and text() = '" + expectedFlightTypeValue + "']/..");

        WebElement specificSelectButton = getWebDriver().getDriver().findElement(specificFlightTypeLocator);
        click.clickOnElement(specificSelectButton, 15);
    }

    private void selectSpecificFlightClass(PhpTravelsModel phpTravelsModel) {
        String expectedFlightClassValue = getExpectedCabinClassFromDataProvider(phpTravelsModel);
        By specificFlightTypeLocator = By.xpath("//span[@x-text = 'cls.name' and text() = '" + expectedFlightClassValue + "']/..");

        WebElement specificFlightClassButton = getWebDriver().getDriver().findElement(specificFlightTypeLocator);
        click.clickOnElement(specificFlightClassButton, 15);
    }

    private void findAndSelectDepartureLocation(PhpTravelsModel phpTravelsModel) {
        Location expectedDepartureLocation = getExpectedDepartureLocationFromDataProvider(phpTravelsModel);
        WebElement location = getExpectedDepartureLocationInput(expectedDepartureLocation);
        clickOnSpecificLocation(location, expectedDepartureLocation, "departure");
    }

    private void findAndSelectArrivalLocation(PhpTravelsModel phpTravelsModel) {
        Location expectedArrivalLocation = getExpectedArrivalLocationFromDataProvider(phpTravelsModel);
        WebElement location = getExpectedArrivalLocationInput(expectedArrivalLocation);
        clickOnSpecificLocation(location, expectedArrivalLocation, "arrival");
    }

    private void clickOnSpecificLocation(WebElement locationInput, Location location, String destination) {
        String airportName = location.getAirportName();
        String airportCity = location.getAirportCity();
        String airportCountry = location.getAirportCountry();

        click.clickOnElement(locationInput, 15);
        log.info("{}, {}, {} has been set as {} location.", airportName, airportCity, airportCountry, destination);
    }

    private WebElement getExpectedDepartureLocationInput(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();
        By locationLocator = By.xpath("//span[@x-text = 'a.id' and text() = '" + locationCode + "']/../..");

        return getWebDriver().getDriver().findElement(locationLocator);
    }

    private WebElement getExpectedArrivalLocationInput(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();
        By locationLocator = By.xpath("//span[@x-text = 'a.id' and text() = '" + locationCode + "']/../..");

        return getWebDriver().getDriver().findElement(locationLocator);
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributePresent(flightsTab, "class", "text-primary", 50, 5))
                .as("Flights tab activity check")
                .isTrue();

        log.info("Flights tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(flightsTab, 15);
        log.info("Flights tab has been displayed.");
    }

    private void clickOnDepartureLocationInput() {
        clickOnLocationInput(departureLocationInput, "Departure");
    }

    private void clickOnArrivalLocationInput() {
        clickOnLocationInput(arrivalLocationInput, "Arrival");
    }

    private void clickOnLocationInput(WebElement location, String locationDestination) {
        click.clickOnEnabledElement(location, 15);
        log.info(locationDestination + " input has been clicked.");
    }

    private Location getExpectedDepartureLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedDepartureLocation();
    }

    private Location getExpectedArrivalLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedArrivalLocation();
    }

    private String getExpectedFlightDestinationAsStringFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightType().getFlightDestination();
    }

    private String getExpectedCabinClassFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightClass().getCabinClass();
    }

    private TravellerModel getExpectedTravellersFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedTravellers();
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

    private String getExpectedFlightTypeAsStringFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightType().getFlightDestination();
    }
}
