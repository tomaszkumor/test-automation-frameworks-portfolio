package models.web.landingPage.searchBarVisa;

import constants.common.Arrow;
import constants.common.Date;
import constants.visaPage.ProcessingSpeedType;
import constants.visaPage.VisaType;
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
        checkDepartureCountryBeforeInput();
        clickOnDepartureCountryInput();
        findAndSelectDepartureCountry(phpTravelsModel);
        checkDepartureCountryAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select arrival country")
    public SearchBarVisa selectArrivalCountry(PhpTravelsModel phpTravelsModel) {
        checkArrivalCountryBeforeInput();
        clickOnArrivalCountryInput();
        findAndSelectArrivalCountry(phpTravelsModel);
        checkArrivalCountryAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select date")
    public SearchBarVisa selectDate(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput();
        selectCheckInDateYear(phpTravelsModel);
        selectCheckInDateMonth(phpTravelsModel);
        selectCheckInDateDay(phpTravelsModel);
        checkActualCheckInDate(phpTravelsModel);

        return this;
    }

    @Step("Select visa type")
    public SearchBarVisa selectVisaType(PhpTravelsModel phpTravelsModel) {
        checkVisaTypeValueBeforeChange();
        clickOnVisaTypeSelect();
        checkIfVisaTypeWindowIsDisplayed(true);
        selectSpecificVisaType(phpTravelsModel);
        checkIfVisaTypeWindowIsDisplayed(false);
        checkVisaTypeValueAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Select processing speed")
    public SearchBarVisa selectProcessingSpeed(PhpTravelsModel phpTravelsModel) {
        checkProcessingSpeedValueBeforeChange();
        clickOnProcessingSpeedSelect();
        checkIfProcessingSpeedWindowIsDisplayed(true);
        selectSpecificProcessingSpeed(phpTravelsModel);
        checkIfProcessingSpeedWindowIsDisplayed(false);
        checkProcessingSpeedValueAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Select travellers")
    public SearchBarVisa selectTravellers(PhpTravelsModel phpTravelsModel) {
        checkTravellersValueBeforeChange();
        clickOnTravellersSelect();
        checkIfTravellersWindowIsDisplayed(true);
        setTravellersNumber(phpTravelsModel);
        closeTravellersWindow();
        checkIfTravellersWindowIsDisplayed(false);
        checkTravellersValueAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public VisaSearchPage clickOnSearchButton() {
        click.clickOnVisibleElement(searchButton, 15);
        log.info("Search button has been clicked.");

        return new VisaSearchPage();
    }

    private void setTravellersNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificTravellersCountBeforeChange();
        changeSpecificTravellersGroupCount(phpTravelsModel);
        checkSpecificTravellersCountAfterChange(phpTravelsModel);
    }

    private void checkSpecificTravellersCountBeforeChange() {
        String actualSpecificTravellerGroupCount = getActualSpecificTravellerGroupCount();
        String expectedSpecificTravellerGroupCount = getExpectedSpecificTravellerGroupCountBeforeChange();
        compareSpecificTravellerGroupCount(actualSpecificTravellerGroupCount, expectedSpecificTravellerGroupCount, "before");
    }

    private void checkSpecificTravellersCountAfterChange(PhpTravelsModel phpTravelsModel) {
        String actualSpecificTravellerGroupCount = getActualSpecificTravellerGroupCount();
        String expectedSpecificTravellerGroupCount = getExpectedTravellersFromDataProvider(phpTravelsModel);
        compareSpecificTravellerGroupCount(actualSpecificTravellerGroupCount, expectedSpecificTravellerGroupCount, "after");
    }

    private void compareSpecificTravellerGroupCount(String actualSpecificTravellerGroupCount, String expectedSpecificTravellerGroupCount, String stage) {
        assertThat(actualSpecificTravellerGroupCount)
                .as("Travellers count check " + stage + " change")
                .isEqualTo(expectedSpecificTravellerGroupCount);
    }

    private String getExpectedSpecificTravellerGroupCountBeforeChange() {
        return "1";
    }

    private String getActualSpecificTravellerGroupCount() {
        return get.getTextFromElement(travellersCount);
    }

    private void changeSpecificTravellersGroupCount(PhpTravelsModel phpTravelsModel) {
        String actualTravellersCount = getActualSpecificTravellerGroupCount();
        String expectedTravellersCount = getExpectedTravellersFromDataProvider(phpTravelsModel);
        int actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);
        int expectedTravellersCountAsNumber = Integer.parseInt(expectedTravellersCount);

        if (actualTravellersCountAsNumber > expectedTravellersCountAsNumber) {
            WebElement arrowButton = getArrowButton(Arrow.LEFT);

            do {
                check.isElementEnabled(arrowButton, 15);
                click.clickOnElement(arrowButton, 15);
                actualTravellersCount = getActualSpecificTravellerGroupCount();
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual count has been decreased to {}.", actualTravellersCount);
            } while (actualTravellersCountAsNumber > expectedTravellersCountAsNumber);
        } else if (actualTravellersCountAsNumber < expectedTravellersCountAsNumber) {
            WebElement arrowButton = getArrowButton(Arrow.RIGHT);

            do {
                check.isElementEnabled(arrowButton, 15);
                click.clickOnElement(arrowButton, 15);
                actualTravellersCount = getActualSpecificTravellerGroupCount();
                actualTravellersCountAsNumber = Integer.parseInt(actualTravellersCount);

                log.info("Actual count has been increased to {}.", actualTravellersCount);
            } while (actualTravellersCountAsNumber < expectedTravellersCountAsNumber);
        }
    }

    private WebElement getArrowButton(Arrow arrow) {
        String arrowDirection = switch (arrow) {
            case LEFT -> "remove";
            case RIGHT -> "add";
        };

        return travellersWindow.findElement(By.xpath(".//span[text() = '" + arrowDirection + "']/.."));
    }

    private void closeTravellersWindow() {
        click.clickOnElement(h1, 15);
    }

    private void clickOnVisaTypeSelect() {
        WebElement visaTypeSelect = visaTypeValue.findElement(By.xpath("./.."));
        click.clickOnElement(visaTypeSelect, 15);
        log.info("Visa type select has been clicked.");
    }

    private void clickOnProcessingSpeedSelect() {
        WebElement processingSpeedSelect = processingSpeedValue.findElement(By.xpath("./.."));
        click.clickOnElement(processingSpeedSelect, 15);
        log.info("Processing speed select has been clicked.");
    }

    private void clickOnTravellersSelect() {
        WebElement travellersSelect = travellersValue.findElement(By.xpath("./.."));
        click.clickOnElement(travellersSelect, 15);
        log.info("Travellers select has been clicked.");
    }

    private void selectSpecificVisaType(PhpTravelsModel phpTravelsModel) {
        String expectedVisaTypeValue = getExpectedVisaTypeFromDataProvider(phpTravelsModel);
        WebElement specificVisaType = visaTypeWindow.findElement(By.xpath(".//span[text() = '" + expectedVisaTypeValue + "']/.."));
        click.clickOnElement(specificVisaType, 15);
        log.info("{} visa type has been clicked.", expectedVisaTypeValue);
    }

    private void selectSpecificProcessingSpeed(PhpTravelsModel phpTravelsModel) {
        String expectedProcessingSpeedValue = getExpectedProcessingSpeedFromDataProvider(phpTravelsModel);
        WebElement specificVisaType = processingSpeedWindow.findElement(By.xpath(".//div[text() = '" + expectedProcessingSpeedValue + "']/ancestor::div[@class = 'input-dropdown-item']"));
        click.clickOnElement(specificVisaType, 15);
        log.info("{} processing speed has been clicked.", expectedProcessingSpeedValue);
    }

    private void selectCheckInDateYear(PhpTravelsModel phpTravelsModel) {
        checkIfDatePickerWindowIsDisplayed(true);
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, Date.YEAR);
    }

    private void selectCheckInDateMonth(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, Date.MONTH);
    }

    private void selectCheckInDateDay(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false);
    }

    private void clickOnDateInput() {
        By locator = By.xpath("//input[@name = 'travel_date']");
        check.isElementPresentByLocator(locator, 10);
        click.clickOnEnabledElement(dateInput, 15);

        log.info("Date picker input has been clicked.");
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed) {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]");
        checkIfWindowIsDisplayed(datePickerWindowLocator, "Date picker", shouldBeDisplayed);
    }

    private void checkIfVisaTypeWindowIsDisplayed(boolean shouldBeDisplayed) {
        By visaTypeWindowLocator = By.xpath("//input[@name = 'visa_type']/following-sibling::div[@class = 'input-dropdown-content show']");
        checkIfWindowIsDisplayed(visaTypeWindowLocator, "Date picker", shouldBeDisplayed);
    }

    private void checkIfProcessingSpeedWindowIsDisplayed(boolean shouldBeDisplayed) {
        By processingSpeedWindowLocator = By.xpath("//input[@name = 'processing_speed']/following-sibling::div[@class = 'input-dropdown-content show']");
        checkIfWindowIsDisplayed(processingSpeedWindowLocator, "Date picker", shouldBeDisplayed);
    }

    private void checkIfTravellersWindowIsDisplayed(boolean shouldBeDisplayed) {
        By travellersWindowLocator = By.xpath("//input[@name = 'travelers']/following-sibling::div[@class = 'input-dropdown-content show']");
        checkIfWindowIsDisplayed(travellersWindowLocator, "Date picker", shouldBeDisplayed);
    }

    private void checkIfWindowIsDisplayed(By windowLocator, String windowName, boolean shouldBeDisplayed) {
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(windowLocator, 50, 15);

            WebElement window = getWebDriver().getDriver().findElement(windowLocator);
            check.isElementDisplayed(window, 15);
            log.info("{} window has been displayed.", windowName);
        } else {
            check.isNumberOfElementsEqualTo(windowLocator, 0, 50, 15);
            log.info("{}} window has been closed.", windowName);
        }
    }

    private By getDatePickerWindowHeaderLocator(int i) {
        String datePickerHeaderType = switch (i) {
            case 0 -> "days";
            case 1 -> "months";
            default -> null;
        };

        return By.xpath("//div[@class = 'datepicker-" + datePickerHeaderType + "']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-" + datePickerHeaderType + "']//th[contains(@class, 'switch')]");
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
            case YEAR ->
                    By.xpath("//div[@class = 'datepicker-years']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-years']//span[text() = '" + specificDate + "']");
            case MONTH ->
                    By.xpath("//div[@class = 'datepicker-months']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-months']//span[text() = '" + specificDate + "']");
            case DAY ->
                    By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]/div[@class = 'datepicker-days']//div[text() = '" + specificDate + "' and not(contains(@class, 'old'))]");
        };

        WebElement dateButton = getWebDriver().getDriver().findElement(dateLocator);
        click.clickOnVisibleElement(dateButton, 15);
        log.info("Date: {} {} has been clicked.", date.getName(), specificDate);
    }

    private void checkVisaTypeValueBeforeChange() {
        String expectedVisaTypeValue = VisaType.TOURIST.getVisaType();
        checkSelectValue(visaTypeValue, "visa type", expectedVisaTypeValue);
    }

    private void checkProcessingSpeedValueBeforeChange() {
        String expectedProcessingSpeedValue = ProcessingSpeedType.STANDARD.getProcessingSpeedType();
        checkSelectValue(processingSpeedValue, "processing speed", expectedProcessingSpeedValue);
    }

    private void checkTravellersValueBeforeChange() {
        String expectedTravellersValue = "1 Traveler";
        checkSelectValue(travellersValue, "travelers", expectedTravellersValue);
    }

    private void checkVisaTypeValueAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedVisaTypeValue = getExpectedVisaTypeFromDataProvider(phpTravelsModel);
        checkSelectValue(visaTypeValue, "visa type", expectedVisaTypeValue);
    }

    private void checkProcessingSpeedValueAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedProcessingSpeedValue = getExpectedProcessingSpeedFromDataProvider(phpTravelsModel);
        checkSelectValue(processingSpeedValue, "processing speed", expectedProcessingSpeedValue);
    }

    private void checkTravellersValueAfterChange(PhpTravelsModel phpTravelsModel) {
        String expectedTravellersValue = getExpectedTravellersFromDataProvider(phpTravelsModel);
        checkSelectValue(travellersValue, "travellers", expectedTravellersValue.concat(" Travelers"));
    }

    private void checkSelectValue(WebElement elementValue, String elementName, String expectedValue) {
        check.isTextDisplayed(elementValue, expectedValue, 15);
        log.info("Selected {} value: {}.", elementName, expectedValue);
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

    private void checkDepartureCountryBeforeInput() {
        WebElement country = departureSpan.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        compareCountryBeforeOrAfterInput("Select Country", country, "Departure", "before");
    }

    private void checkArrivalCountryBeforeInput() {
        WebElement country = arrivalSpan.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        compareCountryBeforeOrAfterInput("Select Country", country, "Arrival", "before");
    }

    private void checkDepartureCountryAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCountry = getExpectedDepartureCountryFromDataProvider(phpTravelsModel).getCountry();
        WebElement country = departureSpan.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        compareCountryBeforeOrAfterInput(expectedCountry, country, "Departure", "after");
    }

    private void checkArrivalCountryAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedArrivalCountryFromDataProvider(phpTravelsModel).getCountry();
        WebElement country = arrivalSpan.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        compareCountryBeforeOrAfterInput(expectedCity, country, "Arrival", "after");
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

    private void findAndSelectDepartureCountry(PhpTravelsModel phpTravelsModel) {
        String expectedDestinationCountryName = getExpectedDepartureCountryFromDataProvider(phpTravelsModel).getCountry();

        typeCountryToCountryInput(departureCountryInput, expectedDestinationCountryName, "Departure country");

        By countryButtonLocator = By.xpath("//label/text()[normalize-space(.) = 'From Country']/ancestor::div[@class = 'form-control']//div[@class = 'input-dropdown-content show']//span[text() = '" + expectedDestinationCountryName + "']");
        clickOnCountryButton(countryButtonLocator, expectedDestinationCountryName);
    }

    private void findAndSelectArrivalCountry(PhpTravelsModel phpTravelsModel) {
        String expectedDestinationCountryName = getExpectedArrivalCountryFromDataProvider(phpTravelsModel).getCountry();

        typeCountryToCountryInput(arrivalCountryInput, expectedDestinationCountryName, "Arrival country");

        By countryButtonLocator = By.xpath("//label/text()[normalize-space(.) = 'To Country']/ancestor::div[@class = 'form-control']//div[@class = 'input-dropdown-content show']//span[text() = '" + expectedDestinationCountryName + "']");
        clickOnCountryButton(countryButtonLocator, expectedDestinationCountryName);
    }

    private void typeCountryToCountryInput(WebElement countryInput, String expectedCountry, String inputType) {
        check.isElementDisplayed(countryInput, 15);
        send.sendKeysToWebElementWithNoLeave(countryInput, expectedCountry);
        log.info("{} has been typed to {} input.", expectedCountry, inputType);
    }

    private void clickOnCountryButton(By countryLocator, String expectedCountry) {
        check.isNumberOfElementsEqualTo(countryLocator, 1, 50, 15);

        WebElement countryButton = getWebDriver().getDriver().findElement(countryLocator);
        click.clickOnVisibleElement(countryButton, 15);

        log.info("{} has been selected.", expectedCountry);
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributePresent(visaTab, "class", "text-primary", 50, 5))
                .as("Visa tab activity check")
                .isTrue();

        log.info("Visa tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(visaTab, 15);
        log.info("Visa search bar has been displayed.");
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

    private String getExpectedVisaTypeFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedVisaType().getVisaType();
    }

    private String getExpectedProcessingSpeedFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedProcessingSpeed().getProcessingSpeedType();
    }

    private String getExpectedTravellersFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getVisaPageModel().getExpectedTravellers().getAdultsCount();
    }
}
