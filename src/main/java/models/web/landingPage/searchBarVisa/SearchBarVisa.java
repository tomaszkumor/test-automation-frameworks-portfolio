package models.web.landingPage.searchBarVisa;

import constants.common.Date;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.visaPage.visaSearchPage.VisaSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.format.TextStyle;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarVisa extends SearchBarVisaLocators {
    public SearchBarVisa() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Visa search bar is displayed.");
    }

    @Step("Select departure country")
    public SearchBarVisa selectDepartureCountry(PhpTravelsModel phpTravelsModel) {
        clickOnDepartureCountryInput();
        checkIfCountriesAreDisplayed();
        findAndSelectDepartureCountry(phpTravelsModel);
        checkDepartureCountryAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select arrival country")
    public SearchBarVisa selectArrivalCountry(PhpTravelsModel phpTravelsModel) {
        checkArrivalCountryBeforeInput();
        clickOnArrivalCountryInput();
        checkIfCountriesAreDisplayed();
        findAndSelectArrivalCountry(phpTravelsModel);
        checkArrivalCountryAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select date")
    public SearchBarVisa selectDate(PhpTravelsModel phpTravelsModel) {
        selectCheckInDateYear(phpTravelsModel);
        selectCheckInDateMonth(phpTravelsModel);
        selectCheckInDateDay(phpTravelsModel);
        checkActualCheckInDate(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public VisaSearchPage clickOnSearchButton() {
        click.clickOnVisibleElement(searchButton, 15);
        log.info("Search button has been clicked.");

        return new VisaSearchPage();
    }

    private void selectCheckInDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void selectCheckInDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void selectCheckInDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        checkIfDatePickerWindowIsDisplayed(true);
        selectSpecificDate(phpTravelsModel, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void clickOnDateInput() {
        By locator = By.xpath("//form[@id = 'visa-submit']//input[@id = 'date']");
        check.isElementPresentByLocator(locator, 10);
        click.clickOnEnabledElement(dateInput, 15);

        log.info("Date picker input has been clicked.");
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed) {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]");
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(datePickerWindowLocator, 50, 15);

            WebElement datePickerWindow = getWebDriver().getDriver().findElement(datePickerWindowLocator);
            check.isElementDisplayed(datePickerWindow, 15);
            log.info("Date picker window has been displayed.");
        } else {
            check.isNumberOfElementsEqualTo(datePickerWindowLocator, 0, 50, 15);
            log.info("Date picker window has been closed.");
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

        By dateLocator = switch (date) {
            case YEAR, MONTH ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//span[text() = '" + specificDate + "']");
            case DAY ->
                    By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]/div[contains(@style, 'block')]//tbody//td[@class = 'day ' and text() = '" + specificDate + "']");
        };

        WebElement dateButton = getWebDriver().getDriver().findElement(dateLocator);
        click.clickOnVisibleElement(dateButton, 15);
        log.info("Date: {} {} has been clicked.", date.getName(), specificDate);
    }

    private void checkActualCheckInDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel);
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel) {
        String actualDate = get.getValueFromElement(dateInput);

        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel);

        String year = expectedDate.getYear();
        String month = String.format("%02d", expectedDate.getMonth().getValue());
        String day = String.format("%02d", Integer.parseInt(expectedDate.getDay()));
        String expectedDateAsString = day.concat("-").concat(month).concat("-").concat(year);

        assertThat(actualDate)
                .as("Date check")
                .isEqualTo(expectedDateAsString);
    }

    private void checkArrivalCountryBeforeInput() {
        compareCountryBeforeOrAfterInput("Select Country", arrivalSpan, "Arrival", "before");
    }

    private void checkDepartureCountryAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCountry = getExpectedDepartureCountryFromDataProvider(phpTravelsModel).getCountry();
        compareCountryBeforeOrAfterInput(expectedCountry, departureSpan, "Departure", "after");
    }

    private void checkArrivalCountryAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedArrivalCountryFromDataProvider(phpTravelsModel).getCountry();
        compareCountryBeforeOrAfterInput(expectedCity, arrivalSpan, "Arrival", "after");
    }

    private void compareCountryBeforeOrAfterInput(String expectedLocationValue, WebElement element, String destination, String inputStage) {
        String actualCountryValue = get.getTextFromElement(element);

        assertThat(actualCountryValue)
                .as(destination + " country check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

        log.info("{} country {} input value meets expected value.", destination, inputStage);
    }

    private void clickOnDepartureCountryInput() {
        clickOnLocationInput(departureSpan, "Departure country");
    }

    private void clickOnArrivalCountryInput() {
        clickOnLocationInput(arrivalSpan, "Arrival country");
    }

    private void clickOnLocationInput(WebElement locationSpan, String locationType) {
        click.clickOnEnabledElement(locationSpan, 15);
        log.info("{} input has been clicked.", locationType);
    }

    private void checkIfCountriesAreDisplayed() {
        By countryLocator = By.xpath("//ul[@id = 'select2--results']/li");
        checkIfCountryIsDisplayed(countriesContainer, countryLocator, 1);
    }

    private void checkIfCountryIsDisplayed(WebElement containerLocator, By locator, int expectedElements) {
        check.isElementDisplayed(containerLocator, 15);
        check.isNumberOfElementsGreaterThan(locator, expectedElements, 50, 10);
    }

    private void findAndSelectDepartureCountry(PhpTravelsModel phpTravelsModel) {
        String expectedDestinationCountryName = getExpectedDepartureCountryFromDataProvider(phpTravelsModel).getCountry();

        typeCountryToCountryInput(expectedDestinationCountryName, "Departure country");
        clickOnCityButton(expectedDestinationCountryName);
    }

    private void findAndSelectArrivalCountry(PhpTravelsModel phpTravelsModel) {
        String expectedDestinationCountryName = getExpectedArrivalCountryFromDataProvider(phpTravelsModel).getCountry();

        typeCountryToCountryInput(expectedDestinationCountryName, "Arrival country");
        clickOnCityButton(expectedDestinationCountryName);
    }

    private void typeCountryToCountryInput(String expectedCountry, String inputType) {
        check.isElementEnabled(countryInput, 15);
        send.sendKeysToWebElementWithNoLeave(countryInput, expectedCountry);
        log.info("{} has been typed to {} input.", expectedCountry, inputType);
    }

    private void clickOnCityButton(String expectedCountry) {
        By cityLocator = By.xpath("//li[text() = '" + expectedCountry + "']/..");
        check.isNumberOfElementsEqualTo(cityLocator, 1, 50, 15);

        WebElement countryButton = getWebDriver().getDriver().findElement(cityLocator);
        click.clickOnVisibleElement(countryButton, 15);

        log.info("{} has been selected.", expectedCountry);
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributeEqualTo(visaTab, "aria-selected", "true", 50, 5))
                .as("Visa tab activity check")
                .isTrue();

        log.info("Visa tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(visaSearchBar, 15);
        log.info("Visa search bar has been displayed");
    }

    private DestinationModel getExpectedDepartureCountryFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedDepartureDestination();
    }

    private DestinationModel getExpectedArrivalCountryFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedArrivalDestination();
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedDate();
    }
}
