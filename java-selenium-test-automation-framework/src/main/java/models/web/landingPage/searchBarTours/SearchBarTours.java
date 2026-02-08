package models.web.landingPage.searchBarTours;

import constants.common.Arrow;
import constants.common.Date;
import constants.common.Traveller;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.TravellerModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import driver.WebProperties;
import io.qameta.allure.Step;
import models.web.menu.toursPage.toursSearchPage.ToursSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.format.TextStyle;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarTours extends SearchBarToursLocators {
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
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        closeTravellersWindow();
        checkIfTravellersWindowIsDisplayed(false);
        checkTravellersCountAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public ToursSearchPage clickOnSearchButton() {
        click.clickOnVisibleElement(searchButton, 15);
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

    private void closeTravellersWindow() {
        WebElement element = getWebDriver().getDriver().findElement(By.xpath("//h4"));
        click.clickOnElement(element, 5);
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
        By specificTravellerGroupCountLocator = getActualSpecificTravellerGroupCountLocator(traveller);

        return get.getValueFromElement(specificTravellerGroupCountLocator);
    }

    private By getActualSpecificTravellerGroupCountLocator(Traveller traveller) {
        String specificTravellerGroupClass = switch (traveller) {
            case ADULTS -> "tours_adults";
            case CHILDS -> "tours_child";
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

    private void clickOnTravellersInput() {
        click.clickOnElement(travellersDropDown, 15);
        log.info("Travellers drop down has been clicked.");
    }

    private void checkInitialTravellersCount() {
        WebElement travellersCount = travellersDropDown.findElement(By.xpath(".//span"));
        String actualTravellersCount = get.getTextFromElement(travellersCount);
        String expectedTravellersCount = "1";

        assertThat(actualTravellersCount)
                .as("Travellers count check before change")
                .isEqualTo(expectedTravellersCount);
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
        By locator = By.xpath("//form[@id = 'tours-search']//input[@id = 'date']");
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

    private void checkCityAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedCityValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, citySpan, "after");
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
        By cityLocator = By.xpath("//strong[contains(text(), '" + expectedDestinationCountryName + "')]/ancestor::li[contains(text(), '" + expectedDestinationCityName + "')]/..");
        check.isNumberOfElementsEqualTo(cityLocator, 1, 50, 15);

        WebElement cityButton = getWebDriver().getDriver().findElement(cityLocator);
        click.clickOnVisibleElement(cityButton, 15);

        log.info("{},{} has been selected.", expectedDestinationCityName, expectedDestinationCountryName);
    }

    private void typeCityToInputCity(String expectedDestinationCityName) {
        check.isElementEnabled(cityInput, 15);
        send.sendKeysToWebElementWithNoLeave(cityInput, expectedDestinationCityName);
        log.info("{} has been typed to city input.", expectedDestinationCityName);
    }

    private void checkIfCitiesAreDisplayed() {
        check.isElementDisplayed(citiesContainer, 15);
        By cityLocator = By.xpath("//div[@class = 'most--popular-tours']/div");
        check.isNumberOfElementsGreaterThan(cityLocator, 1, 50, 10);
    }

    private void clickOnCityInput() {
        click.clickOnEnabledElement(citySpan, 15);
        log.info("City input has been clicked.");
    }

    private void checkCityBeforeInput() {
        String browser = WebProperties.getBrowser();
        String expectedPhrase = switch (browser) {
            case "chrome", "firefox" -> "Search By City";
            case "safari" -> "Search by City";
            default -> null;
        };

        compareCityBeforeOrAfterInput(expectedPhrase, citySpan, "before");
    }

    private void compareCityBeforeOrAfterInput(String expectedLocationValue, WebElement element, String inputStage) {
        String actualLocationValue = get.getTextFromElement(element).trim();

        assertThat(actualLocationValue)
                .as("City check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

        log.info("City {} input value meets expected value.", inputStage);
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributeEqualTo(toursTab, "aria-selected", "true", 50, 5))
                .as("Tours tab activity check")
                .isTrue();

        log.info("Tours tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(toursSearchBar, 15);
        log.info("Tours search bar has been displayed");
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getToursPageModel().getExpectedDate();
    }

    private DestinationModel getExpectedDestinationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getToursPageModel().getExpectedDestination();
    }
}
