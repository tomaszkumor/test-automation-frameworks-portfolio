package models.web.landingPage.searchBarCars;

import constants.carsPage.DateType;
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
import models.web.menu.toursPage.toursSearchPage.ToursSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.TextStyle;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarCars extends SearchBarCarsLocators {
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
        click.clickOnVisibleElement(searchButton, 15);
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
        compareTimeBeforeOrAfterSelect("00:00 AM", pickUpTimeSelect, "Pick up time", "before");
    }

    private void checkDropOffTimeBeforeInput() {
        compareTimeBeforeOrAfterSelect("00:00 AM", dropOffTimeSelect, "Drop off time", "before");
    }

    private void compareTimeBeforeOrAfterSelect(String expectedTimeValue, WebElement element, String timeType, String inputStage) {
        Select select = new Select(element);
        String actualTimeValue = get.getTextFromElement(select.getFirstSelectedOption());

        assertThat(actualTimeValue)
                .as(timeType + " check " + inputStage + " input")
                .isEqualTo(expectedTimeValue);

        log.info("{} time {} input value meets expected value.", timeType, inputStage);
    }

    private void clickOnPickUpTimeSelect() {
        clickOnTimeSelect(pickUpTimeSelect, "Pick up time");
    }

    private void clickOnDropOffTimeSelect() {
        clickOnTimeSelect(dropOffTimeSelect, "Drop off time");
    }

    private void clickOnTimeSelect(WebElement timeSelect, String timeType) {
        click.clickOnEnabledElement(timeSelect, 15);
        log.info("{} select has been clicked.", timeType);
    }

    private void setPickUpTime(PhpTravelsModel phpTravelsModel) {
        String time = getExpectedTime(phpTravelsModel, DateType.PICK_UP);
        selectTime(pickUpTimeSelect, time, "pick up time");
    }

    private void setDropOffTime(PhpTravelsModel phpTravelsModel) {
        String time = getExpectedTime(phpTravelsModel, DateType.DROP_OFF);
        selectTime(dropOffTimeSelect, time, "drop off time");
    }

    private void selectTime(WebElement timeSelect, String expectedTime, String timeType) {
        Select select = new Select(timeSelect);
        select.selectByContainsVisibleText(expectedTime);

        log.info("{} has been selected for {}", expectedTime, timeType);
    }

    private String getExpectedTime(PhpTravelsModel phpTravelsModel, DateType dateType) {
        TimeModel expectedTime = switch(dateType) {
            case PICK_UP ->  phpTravelsModel.getCarsPageModel().getExpectedPickUpTime();
            case DROP_OFF ->   phpTravelsModel.getCarsPageModel().getExpectedDropOffTime();
        };

        String hours = expectedTime.getHours();
        String minutes = expectedTime.getMinutes();
        String timePeriod = expectedTime.getDayPeriod();

        return hours.concat(":").concat(minutes).concat(" ").concat(timePeriod);
    }

    private void closeTimeSelect() {
        WebElement element = getWebDriver().getDriver().findElement(By.xpath("//h4"));
        click.clickOnElement(element, 5);
    }

    private void checkPickUpTimeAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedTime = getExpectedTime(phpTravelsModel, DateType.PICK_UP);
        compareTimeBeforeOrAfterSelect(expectedTime, pickUpTimeSelect, "Pick up time", "after");
    }

    private void checkDropOffTimeAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedTime = getExpectedTime(phpTravelsModel, DateType.DROP_OFF);
        compareTimeBeforeOrAfterSelect(expectedTime, dropOffTimeSelect, "Drop off time", "after");
    }

    private void checkInitialTravellersCount() {
        WebElement travellersCount = travellersDropDown.findElement(By.xpath(".//span"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = "1";

        assertThat(actualTravellersCount)
                .as("Travellers count check before change")
                .isEqualTo(expectedTravellersCount);
    }

    private void clickOnTravellersInput() {
        click.clickOnElement(travellersDropDown, 15);
        log.info("Travellers drop down has been clicked.");
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

    private WebElement getArrowButton(Traveller traveller, Arrow arrow) {
        By arrowLocator = getArrowLocator(traveller, arrow);

        return getWebDriver().getDriver().findElement(arrowLocator);
    }

    private By getArrowLocator(Traveller traveller, Arrow arrow) {
        String arrowClass = switch (arrow) {
            case LEFT -> "qtyDec";
            case RIGHT -> "qtyInc";
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//label[contains(normalize-space(.), '" + traveller.getTraveller() + "')]/..//div[@class = '" + arrowClass + "']");
    }

    private String getActualSpecificTravellerGroupCount(Traveller traveller) {
        By specificTravellerGroupCountLocator = getActualSpecificTravellerGroupCountLocator(traveller);

        return get.getValueFromElement(specificTravellerGroupCountLocator);
    }

    private By getActualSpecificTravellerGroupCountLocator(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "cars_adults";
            case CHILDS -> "cars_child";
            default -> null;
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//following::input[@id = '" + specificTravellerGroupClass + "']");
    }

    private String getExpectedSpecificTravellerGroupCountBeforeChange(Traveller traveller) {
        return switch (traveller) {
            case ADULTS -> "1";
            case CHILDS -> "0";
            default -> null;
        };
    }

    private void closeTravellersWindow() {
        WebElement element = getWebDriver().getDriver().findElement(By.xpath("//h4"));
        click.clickOnElement(element, 5);
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
        compareCityBeforeOrAfterInput("Select City", departureSpan, "before");
    }

    private void checkArrivalCityBeforeInput() {
        compareCityBeforeOrAfterInput("Select", arrivalSpan, "before");
    }

    private void checkDepartureAirportAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedAirportValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, departureSpan, "after");
    }

    private void checkCityAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedCityValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, arrivalSpan, "after");
    }

    private void compareCityBeforeOrAfterInput(String expectedLocationValue, WebElement element, String inputStage) {
        String actualLocationValue = get.getTextFromElement(element).trim();

        assertThat(actualLocationValue)
                .as("City check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

        log.info("City {} input value meets expected value.", inputStage);
    }

    private void clickOnDepartureAirportInput() {
        clickOnLocationInput(departureSpan, "Departure airport");
    }

    private void clickOnArrivalCityInput() {
        clickOnLocationInput(arrivalSpan, "Arrival city");
    }

    private void clickOnLocationInput(WebElement locationSpan, String locationType) {
        click.clickOnEnabledElement(locationSpan, 15);
        log.info("{} input has been clicked.", locationType);
    }

    private void checkIfDepartureAirportsAreDisplayed() {
        By airportLocator = By.xpath("//div[@class = 'most--popular-cars-origin']/div");
        checkIfLocationIsDisplayed(airportsContainer, airportLocator, 1);
    }

    private void checkIfCitiesAreDisplayed() {
        By cityLocator = By.xpath("//ul[@id = 'select2--results']/li");
        checkIfLocationIsDisplayed(citiesContainer, cityLocator, 0);
    }

    private void checkIfLocationIsDisplayed(WebElement containerLocator, By locator, int expectedElements) {
        check.isElementDisplayed(containerLocator, 15);
        check.isNumberOfElementsGreaterThan(locator, expectedElements, 50, 10);
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
        check.isElementEnabled(airportOrCityInput, 15);
        send.sendKeysToWebElementWithNoLeave(airportOrCityInput, expectedHotelDestinationCityName);
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
        By cityLocator = By.xpath("//strong[contains(text(), '" + expectedHotelDestinationCountryName + "')]/ancestor::li[contains(text(), '" + expectedHotelDestinationCityName + "')]/..");
        check.isNumberOfElementsEqualTo(cityLocator, 1, 50, 15);

        WebElement cityButton = getWebDriver().getDriver().findElement(cityLocator);
        click.clickOnVisibleElement(cityButton, 15);

        log.info("{}, {} has been selected.", expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void clickOnAirportButton(AirportModel expectedAirport) {
        String expectedAirportName = expectedAirport.getAirportName();
        String expectedAirportCode = expectedAirport.getAirportCode();
        String expectedAirportCity = expectedAirport.getAirportCity();
        String expectedAirportCountry = expectedAirport.getAirportCountry();

        By airportLocator = By.xpath("//small[text() = '" + expectedAirportName + "']/ancestor::div/strong[text() = '" + expectedAirportCity + "']/small[text() = '" + expectedAirportCountry + "']/ancestor::li/button[text() = '" + expectedAirportCode + "']");
        check.isNumberOfElementsEqualTo(airportLocator, 1, 50, 15);

        WebElement cityButton = getWebDriver().getDriver().findElement(airportLocator);
        click.clickOnVisibleElement(cityButton, 15);

        log.info("{}, {}, {} has been selected.", expectedAirportName, expectedAirportCity, expectedAirportCountry);
    }

    private void selectPickUpDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectPickUpDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectPickUpDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.PICK_UP);
        checkIfDatePickerWindowIsDisplayed(true, "Pick up");
        selectSpecificDate(phpTravelsModel, DateType.PICK_UP, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Pick up");
    }

    private void selectDropOffDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void selectDropOffDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void selectDropOffDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(DateType.DROP_OFF);
        checkIfDatePickerWindowIsDisplayed(true, "Drop off");
        selectSpecificDate(phpTravelsModel, DateType.DROP_OFF, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Drop off");
    }

    private void checkActualPickUpDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, DateType.PICK_UP);
    }

    private void checkActualDropOffDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, DateType.DROP_OFF);
    }

    private void clickOnDateInput(DateType dateType) {
        By locator = switch (dateType) {
            case PICK_UP -> By.xpath("//input[@id = 'checkin']");
            case DROP_OFF -> By.xpath("//input[@id = 'checkout']");
        };

        check.isElementPresentByLocator(locator, 10);

        switch (dateType) {
            case PICK_UP -> click.clickOnEnabledElement(pickUpDateInput, 15);
            case DROP_OFF -> click.clickOnEnabledElement(dropOffDateInput, 15);
        }

        log.info("{} date picker input has been clicked.", dateType.getDateTypeName());
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

        By dateLocator = switch (date) {
            case YEAR, MONTH ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//span[text() = '" + specificDate + "']");
            case DAY ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//td[@class = 'day ' and text() = '" + specificDate + "']");
        };

        WebElement dateButton = getWebDriver().getDriver().findElement(dateLocator);
        click.clickOnVisibleElement(dateButton, 15);
        log.info("{} date: {} {} has been clicked.", dateType.getDateTypeName(), date.getName(), specificDate);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, DateType dateType) {
        String actualDate = switch (dateType) {
            case PICK_UP -> get.getValueFromElement(pickUpDateInput);
            case DROP_OFF -> get.getValueFromElement(dropOffDateInput);
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
        assertThat(check.isAttributeEqualTo(carsTab, "aria-selected", "true", 50, 5))
                .as("Cars tab activity check")
                .isTrue();

        log.info("Cars tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(carsSearchBar, 15);
        log.info("Cars search bar has been displayed");
    }
}
