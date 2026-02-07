package models.web.landingPage.searchBarFlights;

import constants.common.Arrow;
import constants.common.Date;
import constants.common.Traveller;
import constants.flightsPage.FlightType;
import constants.common.Location;
import constants.common.LocationType;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.commonModels.AirportModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.flightsPage.flightsSearchPage.FlightsSearchPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
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
        checkIfDepartureAirportsAreDisplayed();
        checkDepartureLocationsAvailability(phpTravelsModel);
        findAndSelectDepartureLocation(phpTravelsModel);
        checkDepartureLocationAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select arrival location")
    public SearchBarFlights selectArrivalLocation(PhpTravelsModel phpTravelsModel) {
        checkArrivalLocationBeforeInput();
        clickOnArrivalLocationInput();
        checkIfArrivalAirportsAreDisplayed();
        checkArrivalLocationsAvailability(phpTravelsModel);
        findAndSelectArrivalLocation(phpTravelsModel);
        checkArrivalLocationAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select flight destination")
    public SearchBarFlights selectFlightDestination(PhpTravelsModel phpTravelsModel) {
        selectSpecificFlightDestination(phpTravelsModel);
        compareFlightDestinies(phpTravelsModel);

        return this;
    }

    @Step("Select cabin class")
    public SearchBarFlights selectCabinClass(PhpTravelsModel phpTravelsModel) {
        selectSpecificCabinClass(phpTravelsModel);
        compareCabinClasses(phpTravelsModel);

        return this;
    }

    @Step("Swap departure and arrival locations")
    public SearchBarFlights swapDepartureAndArrivalLocations() {
        swapLocations();
        checkLocationsAfterSwap();

        return this;
    }

    @Step("Select departure date")
    public SearchBarFlights selectDepartureDate(PhpTravelsModel phpTravelsModel) {
        selectDepartureDateYear(phpTravelsModel);
        selectDepartureDateMonth(phpTravelsModel);
        selectDepartureDateDay(phpTravelsModel);
        checkActualDepartureDate(phpTravelsModel);

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
        checkInitialTravellersCount();
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

    private void swapLocations() {
        click.clickOnEnabledElement(swapDirectionsButton, 15);

        log.info("Swap locations button has been clicked.");
    }

    private void checkLocationsAfterSwap() {
        String departureLocationBeforeSwap = get.getValueFromElement(departureLocationInput);
        String arrivalLocationBeforeSwap = get.getValueFromElement(arrivalLocationInput);
        String departureLocationAfterSwap = get.getValueFromElement(departureLocationInput);
        String arrivalLocationAfterSwap = get.getValueFromElement(arrivalLocationInput);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(departureLocationAfterSwap).isEqualTo(arrivalLocationBeforeSwap);
        softAssertions.assertThat(arrivalLocationAfterSwap).isEqualTo(departureLocationBeforeSwap);
        softAssertions.assertAll();

        log.info("Locations has been swapped correctly.");
    }

    private void closeTravellersWindow() {
        WebElement element = getWebDriver().getDriver().findElement(By.xpath("//body"));
        click.clickOnElement(element, 5);
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

    private void checkInitialTravellersCount() {
        WebElement travellersCount = travellersDropDown.findElement(By.xpath(".//span"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = "1";

        assertThat(actualTravellersCount)
                .as("Travellers count check before change")
                .isEqualTo(expectedTravellersCount);
    }

    private void checkTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement travellersCount = travellersDropDown.findElement(By.xpath(".//span"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = getTotalCountOfTravellers(phpTravelsModel);

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
        click.clickOnElement(travellersDropDown, 15);
        log.info("Travellers drop down has been clicked.");
    }

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        By specificTravellerGroupCountLocator = getActualSpecificTravellerGroupCountLocator(traveller);

        return get.getValueFromElement(specificTravellerGroupCountLocator);
    }

    private By getActualSpecificTravellerGroupCountLocator(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "fadults";
            case CHILDS -> "fchilds";
            case INFANTS -> "finfant";
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//following::input[@id = '" + specificTravellerGroupClass + "']");
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
        String arrowClass = switch (arrow) {
            case LEFT -> "qtyDec";
            case RIGHT -> "qtyInc";
        };

        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "adult_qty";
            case CHILDS -> "child_qty";
            case INFANTS -> "infant_qty";
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//div[contains(@class, '" + specificTravellerGroupClass + "')]//div[@class = '" + arrowClass + "']");
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
        clickOnDateInput(LocationType.DEPARTURE);
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.YEAR);
        closeReturnDatePickerWindowIfNecessary(phpTravelsModel);
        checkIfDatePickerWindowIsDisplayed(false, "Departure");
    }

    private void selectDepartureDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.DEPARTURE);
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.MONTH);
        closeReturnDatePickerWindowIfNecessary(phpTravelsModel);
        checkIfDatePickerWindowIsDisplayed(false, "Departure");
    }

    private void selectDepartureDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(LocationType.DEPARTURE);
        checkIfDatePickerWindowIsDisplayed(true, "Departure");
        selectSpecificDate(phpTravelsModel, LocationType.DEPARTURE, Date.DAY);
        closeReturnDatePickerWindowIfNecessary(phpTravelsModel);
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

    private void closeReturnDatePickerWindowIfNecessary(PhpTravelsModel phpTravelsModel) {
        FlightType flightDestination = getExpectedFlightDestinationFromDataProvider(phpTravelsModel);

        if (flightDestination == FlightType.RETURN) {
            checkIfDatePickerWindowIsDisplayed(true, "Arrival");
            WebElement element = getWebDriver().getDriver().findElement(By.xpath("//body"));
            click.clickOnElement(element, 5);
            checkIfDatePickerWindowIsDisplayed(false, "Arrival");
        }
    }

    private void checkActualDepartureDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.DEPARTURE);
    }

    private void checkActualReturnDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, LocationType.ARRIVAL);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, LocationType locationType) {
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
                By locator = By.xpath("//input[@id = 'departure']");
                check.isElementPresentByLocator(locator, 10);
                click.clickOnEnabledElement(departureDateInput, 15);
            }
            case ARRIVAL -> {
                By locator = By.xpath("//input[@id = 'return_date']");
                check.isElementPresentByLocator(locator, 10);
                click.clickOnEnabledElement(returnDateInput, 15);
            }
        }

        log.info("{} date picker input has been clicked.", locationType.getLocationTypeName());
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed, String destination) {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]");
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(datePickerWindowLocator, 50, 15);

            WebElement datePickerWindow = getWebDriver().getDriver().findElement(datePickerWindowLocator);
            check.isElementDisplayed(datePickerWindow, 15);
            log.info("{} date picker window has been displayed.", destination);
        } else {
            check.isNumberOfElementsEqualTo(datePickerWindowLocator, 0, 50, 15);
            log.info("{} date picker window has been closed.", destination);
        }
    }

    private void checkIfTravellersWindowIsDisplayed(boolean shouldBeDisplayed) {
        By travellersWindowLocator = By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]");
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(travellersWindowLocator, 50, 15);

            WebElement travellersWindow = getWebDriver().getDriver().findElement(travellersWindowLocator);
            check.isElementDisplayed(travellersWindow, 15);
            log.info("Travellers window has been displayed.");
        } else {
            check.isNumberOfElementsEqualTo(travellersWindowLocator, 0, 50, 15);
            log.info("Traveller window has been closed.");
        }
    }

    private void clickOnDatePickerHeader(Date date) {
        int iterator = switch (date) {
            case YEAR -> 2;
            case MONTH -> 1;
            default -> 0;
        };

        By datePickerWindowHeaderLocator = By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//th[@class = 'switch']");
        for (int i = 0; i < iterator; i++) {
            check.isNumberOfElementsEqualTo(datePickerWindowHeaderLocator, 1, 5);
            WebElement datePickerWindowHeader = getWebDriver().getDriver().findElement(datePickerWindowHeaderLocator);
            click.clickOnVisibleElement(datePickerWindowHeader, 15);

            log.info("Date picker header has been clicked.");
        }
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
            case YEAR, MONTH ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//span[text() = '" + specificDate + "']");
            case DAY ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//td[@class = 'day ' and text() = '" + specificDate + "']");
        };

        WebElement dateButton = getWebDriver().getDriver().findElement(dateLocator);
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

    private void selectSpecificFlightDestination(PhpTravelsModel phpTravelsModel) {
        String expectedFlightDestinationValue = getExpectedFlightDestinationAsStringFromDataProvider(phpTravelsModel);
        Select flightDestinationSelect = new Select(this.flightDestinationSelect);
        flightDestinationSelect.selectByContainsVisibleText(expectedFlightDestinationValue);
    }

    private void selectSpecificCabinClass(PhpTravelsModel phpTravelsModel) {
        String expectedCabinClassValue = getExpectedCabinClassFromDataProvider(phpTravelsModel);
        Select cabinClassSelect = new Select(this.cabinClassSelect);
        cabinClassSelect.selectByContainsVisibleText(expectedCabinClassValue);
    }

    private void compareFlightDestinies(PhpTravelsModel phpTravelsModel) {
        String expectedFlightDestinationValue = getExpectedFlightDestinationAsStringFromDataProvider(phpTravelsModel);

        Select flightDestinationSelect = new Select(this.flightDestinationSelect);
        String actualFlightDestinationValue = get.getTextFromElement(flightDestinationSelect.getFirstSelectedOption()).trim();
        assertThat(actualFlightDestinationValue)
                .as("Flight destination value check")
                .isEqualTo(expectedFlightDestinationValue);

        log.info("Selected flight destination value: " + actualFlightDestinationValue);
    }

    private void compareCabinClasses(PhpTravelsModel phpTravelsModel) {
        String expectedCabinClassValue = getExpectedCabinClassFromDataProvider(phpTravelsModel);

        Select cabinClassSelect = new Select(this.cabinClassSelect);
        String actualCabinClassValue = get.getTextFromElement(cabinClassSelect.getFirstSelectedOption()).trim();
        assertThat(actualCabinClassValue)
                .as("Cabin class value check")
                .isEqualTo(expectedCabinClassValue);

        log.info("Selected cabin class value: " + actualCabinClassValue);
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

    private void checkDepartureLocationsAvailability(PhpTravelsModel phpTravelsModel) {
        List<AirportModel> actualDepartureLocations = getActualDepartureLocations();
//        List<AirportModel> expectedDepartureLocations = getExpectedDepartureLocationsFromDataProvider(phpTravelsModel);

//        compareLocations(actualDepartureLocations, expectedDepartureLocations);
    }

    private void checkArrivalLocationsAvailability(PhpTravelsModel phpTravelsModel) {
        List<AirportModel> actualArrivalLocations = getActualArrivalLocations();
//        List<AirportModel> expectedArrivalLocations = getExpectedArrivalLocationsFromDataProvider(phpTravelsModel);

//        compareLocations(actualArrivalLocations, expectedArrivalLocations);
    }

    private void compareLocations(List<AirportModel> actualLocations, List<AirportModel> expectedLocations) {
        assertThat(actualLocations)
                .as("Locations check")
                .doesNotHaveDuplicates()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedLocations);
    }

    private WebElement getExpectedDepartureLocationInput(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();
        By locationLocator = By.xpath("//div[contains(@class, 'results-container-from')]/div[@data-code = '" + locationCode + "']");

        return getWebDriver().getDriver().findElement(locationLocator);
    }

    private WebElement getExpectedArrivalLocationInput(Location expectedDepartureLocation) {
        String locationCode = expectedDepartureLocation.getAirportCode();
        By locationLocator = By.xpath("//div[contains(@class, 'results-container-to')]/div[@data-code = '" + locationCode + "']");

        return getWebDriver().getDriver().findElement(locationLocator);
    }

    private List<AirportModel> getActualDepartureLocations() {
        List<AirportModel> actualDepartureLocations = new ArrayList<>();

        for (WebElement departureLocation : departureLocations) {
            String airportName = getActualAirportName(departureLocation);
            String airportCity = getActualAirportCityName(departureLocation);
            String airportCountry = getActualAirportCountryName(departureLocation);
            String airportCode = getActualAirportCodeName(departureLocation);

            actualDepartureLocations.add(new AirportModel(airportName, airportCity, airportCountry, airportCode));
        }

        return actualDepartureLocations;
    }

    private List<AirportModel> getActualArrivalLocations() {
        List<AirportModel> actualArrivalLocations = new ArrayList<>();

        for (WebElement arrivalLocation : arrivalLocations) {
            String airportName = getActualAirportName(arrivalLocation);
            String airportCity = getActualAirportCityName(arrivalLocation);
            String airportCountry = getActualAirportCountryName(arrivalLocation);
            String airportCode = getActualAirportCodeName(arrivalLocation);

            actualArrivalLocations.add(new AirportModel(airportName, airportCity, airportCountry, airportCode));
        }

        return actualArrivalLocations;
    }

    private String getActualAirportName(WebElement location) {
        return get.getTextFromElement(location.findElement(By.xpath(".//small[contains(@class, 'airport--name')]")));
    }

    private String getActualAirportCityName(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver().getDriver();
        return (String) js.executeScript(
                """
                            var node = arguments[0].firstChild;
                            return (node && node.nodeType === 3) ? node.textContent.trim() : '';
                        """,
                element.findElement(By.xpath(".//strong"))
        );
    }

    private String getActualAirportCountryName(WebElement location) {
        return get.getTextFromElement(location.findElement(By.xpath(".//strong/small")));
    }

    private String getActualAirportCodeName(WebElement location) {
        return get.getTextFromElement(location.findElement(By.xpath("./button")));
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributeEqualTo(flightsTab, "aria-selected", "true", 50, 5))
                .as("Flights tab activity check")
                .isTrue();

        log.info("Flights tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(flightsSearchBar, 15);
        log.info("Flights search bar has been displayed");
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

    private void checkIfDepartureAirportsAreDisplayed() {
        checkIfAirportsAreDisplayed(departureLocationsContainer, "from");
    }

    private void checkIfArrivalAirportsAreDisplayed() {
        checkIfAirportsAreDisplayed(arrivalLocationsContainer, "to");
    }

    private void checkIfAirportsAreDisplayed(WebElement airportsContainer, String airportLocator) {
        check.isElementDisplayed(airportsContainer, 15);
        By departureAirportLocator = By.xpath("//div[contains(@class, 'results-container-" + airportLocator + "')]/div");
        check.isNumberOfElementsGreaterThan(departureAirportLocator, 0, 50, 10);
    }

    private Location getExpectedDepartureLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedDepartureLocation();
    }

    private Location getExpectedArrivalLocationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedArrivalLocation();
    }

//    private List<AirportModel> getExpectedDepartureLocationsFromDataProvider(PhpTravelsModel phpTravelsModel) {
//        return phpTravelsModel.getFlightsPageModel().getExpectedDepartureLocations();
//    }

//    private List<AirportModel> getExpectedArrivalLocationsFromDataProvider(PhpTravelsModel phpTravelsModel) {
//        return phpTravelsModel.getFlightsPageModel().getExpectedArrivalLocations();
//    }

    private String getExpectedFlightDestinationAsStringFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightType().getFlightDestination();
    }

    private FlightType getExpectedFlightDestinationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getFlightsPageModel().getExpectedFlightType();
    }

    private String getExpectedCabinClassFromDataProvider(PhpTravelsModel phpTravelsModel) {
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
