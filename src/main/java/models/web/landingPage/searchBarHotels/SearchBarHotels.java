package models.web.landingPage.searchBarHotels;

import constants.common.Arrow;
import constants.common.Date;
import constants.hotelsPage.Accommodation;
import constants.hotelsPage.CheckType;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.hotelsPageModels.AccommodationModel;
import dataProviders.dataProvidersModels.web.hotelsPageModels.ChildModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import io.qameta.allure.Step;
import models.web.menu.hotelsPage.hotelsSearchPage.HotelsSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarHotels extends SearchBarHotelsLocators {
    public SearchBarHotels() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Hotels search bar is displayed.");
    }

    @Step("Select city")
    public SearchBarHotels selectCity(PhpTravelsModel phpTravelsModel) {
        checkCityBeforeInput();
        clickOnCityInput();
        checkIfCitiesAreDisplayed();
        findAndSelectCity(phpTravelsModel);
        checkCityAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select check in date")
    public SearchBarHotels selectCheckInDate(PhpTravelsModel phpTravelsModel) {
        selectCheckInDateYear(phpTravelsModel);
        selectCheckInDateMonth(phpTravelsModel);
        selectCheckInDateDay(phpTravelsModel);
        checkActualCheckInDate(phpTravelsModel);

        return this;
    }

    @Step("Select check out date")
    public SearchBarHotels selectCheckOutDate(PhpTravelsModel phpTravelsModel) {
        selectCheckOutDateYear(phpTravelsModel);
        selectCheckOutDateMonth(phpTravelsModel);
        selectCheckOutDateDay(phpTravelsModel);
        checkActualCheckOutDate(phpTravelsModel);

        return this;
    }

    @Step("Select accommodation")
    public SearchBarHotels selectAccommodation(PhpTravelsModel phpTravelsModel) {
        checkInitialGuestsCount();
        checkInitialRoomsCount();
        clickOnAccommodationInput();
        checkIfAccommodationWindowIsDisplayed(true);
        setRoomsNumber(phpTravelsModel);
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        setChildrenAge(phpTravelsModel);
        setNationality(phpTravelsModel);
        closeAccommodationsWindow();
        checkIfAccommodationWindowIsDisplayed(false);
        checkGuestsCountAfterChange(phpTravelsModel);
        checkRoomsCountAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public HotelsSearchPage clickOnSearchButton() {
        click.clickOnVisibleElement(searchButton, 15);
        log.info("Search button has been clicked.");

        return new HotelsSearchPage();
    }

    private void setChildrenAge(PhpTravelsModel phpTravelsModel) {
        String accommodationChildrenCount = getExpectedAccommodationFromDataProvider(phpTravelsModel).getChildrenCount();
        int accommodationChildrenCountAsNumber = Integer.parseInt(accommodationChildrenCount);
        int childrenCountAsNumber = getExpectedChildrenFromDataProvider(phpTravelsModel).size();

        if (accommodationChildrenCountAsNumber != childrenCountAsNumber) {
            assertThat(accommodationChildrenCountAsNumber)
                    .as("Children count in data provider check failure")
                    .isEqualTo(childrenCountAsNumber);
        } else {
            List<ChildModel> children = getExpectedChildrenFromDataProvider(phpTravelsModel);

            for (ChildModel child : children) {
                String expectedChildNo = child.getChildNo();
                String expectedChildAge = child.getChildAge();

                By childSelectLocator = By.xpath("//ol[@id = 'append']/li[" + expectedChildNo + "]//select");
                WebElement ageSelect = getWebDriver().getDriver().findElement(childSelectLocator);

                Select select = new Select(ageSelect);
                select.selectByContainsVisibleText(expectedChildAge);

                log.info("Age {} has been selected for child no {}", expectedChildAge, expectedChildNo);
            }
        }
    }

    private void setNationality(PhpTravelsModel phpTravelsModel) {
        String nationality = getExpectedNationalityFromDataProvider(phpTravelsModel);

        By nationalitySelectLocator = By.xpath("//select[@id = 'nationality']");
        WebElement nationalitySelect = getWebDriver().getDriver().findElement(nationalitySelectLocator);

        Select select = new Select(nationalitySelect);
        select.selectByContainsVisibleText(nationality);

        log.info("Nationality {} has been selected", nationality);
    }

    private void checkGuestsCountAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement guestsCount = accommodationDropDown.findElement(By.xpath(".//span[@class = 'guest_hotels']"));
        String actualGuestsCount = get.getTextFromElement(guestsCount);
        String expectedGuestsCount = getTotalExpectedCountOfGuests(phpTravelsModel);

        assertThat(actualGuestsCount)
                .as("Guests count check after change")
                .isEqualTo(expectedGuestsCount);
    }

    private void checkRoomsCountAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement roomsCount = accommodationDropDown.findElement(By.xpath(".//span[@class = 'roomTotal']"));
        String actualRoomsCount = get.getTextFromElement(roomsCount);
        String expectedRoomsCount = getExpectedRoomsCount(phpTravelsModel);

        assertThat(actualRoomsCount)
                .as("rooms count check after change")
                .isEqualTo(expectedRoomsCount);
    }

    private String getTotalExpectedCountOfGuests(PhpTravelsModel phpTravelsModel) {
        String expectedAdultsCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, Accommodation.ADULTS);
        String expectedChildrenCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, Accommodation.CHILDS);

        int expectedAdultsCountAsNumber = Integer.parseInt(expectedAdultsCount);
        int expectedChildrenCountAsNumber = Integer.parseInt(expectedChildrenCount);

        return String.valueOf(expectedAdultsCountAsNumber + expectedChildrenCountAsNumber);
    }

    private String getExpectedRoomsCount(PhpTravelsModel phpTravelsModel) {
        AccommodationModel expectedAccommodation = getExpectedAccommodationFromDataProvider(phpTravelsModel);

        return expectedAccommodation.getRoomsCount();
    }

    private void closeAccommodationsWindow() {
        WebElement element = getWebDriver().getDriver().findElement(By.xpath("//body"));
        click.clickOnElement(element, 5);
    }

    private void setRoomsNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificAccommodationCountBeforeChange(Accommodation.ROOMS);
        changeSpecificAccommodationGroupCount(phpTravelsModel, Accommodation.ROOMS);
        checkSpecificAccommodationCountAfterChange(phpTravelsModel, Accommodation.ROOMS);
    }

    private void setAdultsNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificAccommodationCountBeforeChange(Accommodation.ADULTS);
        changeSpecificAccommodationGroupCount(phpTravelsModel, Accommodation.ADULTS);
        checkSpecificAccommodationCountAfterChange(phpTravelsModel, Accommodation.ADULTS);
    }

    private void setChildrenNumber(PhpTravelsModel phpTravelsModel) {
        checkSpecificAccommodationCountBeforeChange(Accommodation.CHILDS);
        changeSpecificAccommodationGroupCount(phpTravelsModel, Accommodation.CHILDS);
        checkSpecificAccommodationCountAfterChange(phpTravelsModel, Accommodation.CHILDS);
    }

    private void checkSpecificAccommodationCountBeforeChange(Accommodation accommodation) {
        String actualSpecificAccommodationGroupCount = getActualSpecificAccommodationGroupCount(accommodation);
        String expectedSpecificAccommodationGroupCount = getExpectedSpecificAccommodationGroupCountBeforeChange(accommodation);
        compareSpecificAccommodationGroupCount(actualSpecificAccommodationGroupCount, expectedSpecificAccommodationGroupCount, accommodation, "before");
    }

    private void checkSpecificAccommodationCountAfterChange(PhpTravelsModel phpTravelsModel, Accommodation accommodation) {
        String actualSpecificAccommodationGroupCount = getActualSpecificAccommodationGroupCount(accommodation);
        String expectedSpecificAccommodationGroupCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, accommodation);
        compareSpecificAccommodationGroupCount(actualSpecificAccommodationGroupCount, expectedSpecificAccommodationGroupCount, accommodation, "after");
    }

    private void compareSpecificAccommodationGroupCount(String actualSpecificAccommodationGroupCount, String expectedSpecificAccommodationGroupCount, Accommodation accommodation, String stage) {
        assertThat(actualSpecificAccommodationGroupCount)
                .as(accommodation.getAccommodationName() + " count check " + stage + " change")
                .isEqualTo(expectedSpecificAccommodationGroupCount);
    }

    private void changeSpecificAccommodationGroupCount(PhpTravelsModel phpTravelsModel, Accommodation accommodation) {
        String actualAccommodationCount = getActualSpecificAccommodationGroupCount(accommodation);
        String expectedAccommodationCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, accommodation);
        int actualAccommodationCountAsNumber = Integer.parseInt(actualAccommodationCount);
        int expectedAccommodationCountAsNumber = Integer.parseInt(expectedAccommodationCount);

        if (actualAccommodationCountAsNumber > expectedAccommodationCountAsNumber) {
            WebElement arrow = getArrowButton(accommodation, Arrow.LEFT);

            do {
                click.clickOnEnabledElement(arrow, 15);
                actualAccommodationCount = getActualSpecificAccommodationGroupCount(accommodation);
                actualAccommodationCountAsNumber = Integer.parseInt(actualAccommodationCount);

                log.info("Actual {} count has been decreased to {}.", accommodation.getAccommodationName(), actualAccommodationCount);
            } while (actualAccommodationCountAsNumber > expectedAccommodationCountAsNumber);
        } else if (actualAccommodationCountAsNumber < expectedAccommodationCountAsNumber) {
            WebElement arrow = getArrowButton(accommodation, Arrow.RIGHT);

            do {
                click.clickOnEnabledElement(arrow, 15);
                actualAccommodationCount = getActualSpecificAccommodationGroupCount(accommodation);
                actualAccommodationCountAsNumber = Integer.parseInt(actualAccommodationCount);

                log.info("Actual {} count has been increased to {}.", accommodation.getAccommodationName(), actualAccommodationCount);
            } while (actualAccommodationCountAsNumber < expectedAccommodationCountAsNumber);
        }
    }

    private WebElement getArrowButton(Accommodation accommodation, Arrow arrow) {
        By arrowLocator = getArrowLocator(accommodation, arrow);

        return getWebDriver().getDriver().findElement(arrowLocator);
    }

    private By getArrowLocator(Accommodation accommodation, Arrow arrow) {
        String arrowClass = switch (accommodation) {
            case ROOMS -> switch (arrow) {
                case LEFT -> "roomDec";
                case RIGHT -> "roomInc";
            };
            case ADULTS, CHILDS -> switch (arrow) {
                case LEFT -> "qtyDec";
                case RIGHT -> "qtyInc";
            };
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//label[contains(normalize-space(.), '" + accommodation.getAccommodationName() + "')]/..//div[@class = '" + arrowClass + "']");
    }

    private String getExpectedSpecificAccommodationGroupCountAfterChange(PhpTravelsModel phpTravelsModel, Accommodation accommodation) {
        AccommodationModel expectedAccommodation = getExpectedAccommodationFromDataProvider(phpTravelsModel);

        return switch (accommodation) {
            case ROOMS -> expectedAccommodation.getRoomsCount();
            case ADULTS -> expectedAccommodation.getAdultsCount();
            case CHILDS -> expectedAccommodation.getChildrenCount();
        };
    }

    private By getActualSpecificAccommodationCountLocator(Accommodation accommodation) {
        String specificAccommodationGroupClass = switch (accommodation) {
            case ROOMS -> "hotels_rooms";
            case ADULTS -> "hotels_adults";
            case CHILDS -> "hotels_childs";
        };

        return By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]//following::input[@id = '" + specificAccommodationGroupClass + "']");
    }

    private String getActualSpecificAccommodationGroupCount(Accommodation accommodation) {
        By accommodationLocator = getActualSpecificAccommodationCountLocator(accommodation);

        return get.getValueFromElement(accommodationLocator);
    }

    private String getExpectedSpecificAccommodationGroupCountBeforeChange(Accommodation accommodation) {
        return switch (accommodation) {
            case ROOMS -> "1";
            case ADULTS -> "2";
            case CHILDS -> "0";
        };
    }

    private void checkIfAccommodationWindowIsDisplayed(boolean shouldBeDisplayed) {
        By accommodationWindowLocator = By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@style, 'block')]");
        if (shouldBeDisplayed) {
            check.isElementPresentByLocator(accommodationWindowLocator, 50, 15);

            WebElement accommodationWindow = getWebDriver().getDriver().findElement(accommodationWindowLocator);
            check.isElementDisplayed(accommodationWindow, 15);
            log.info("Accommodation window has been displayed.");
        } else {
            check.isNumberOfElementsEqualTo(accommodationWindowLocator, 0, 50, 15);
            log.info("Accommodation window has been closed.");
        }
    }

    private void clickOnAccommodationInput() {
        click.clickOnElement(accommodationDropDown, 15);
        log.info("Accommodation drop down has been clicked.");
    }

    private void checkInitialGuestsCount() {
        WebElement guestsCount = accommodationDropDown.findElement(By.xpath(".//span[@class = 'guest_hotels']"));
        String actualGuestsCount = get.getTextFromElement(guestsCount);
        String expectedGuestsCount = "2";

        assertThat(actualGuestsCount)
                .as("Guests count check before change")
                .isEqualTo(expectedGuestsCount);
    }

    private void checkInitialRoomsCount() {
        WebElement roomsCount = accommodationDropDown.findElement(By.xpath(".//span[@class = 'roomTotal']"));
        String actualRoomsCount = get.getTextFromElement(roomsCount);
        String expectedRoomsCount = "1";

        assertThat(actualRoomsCount)
                .as("Rooms count check before change")
                .isEqualTo(expectedRoomsCount);
    }

    private void checkCityAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedCity = getExpectedCityValueAfterInput(phpTravelsModel);
        compareCityBeforeOrAfterInput(expectedCity, citySpan, "after");
    }

    private String getExpectedCityValueAfterInput(PhpTravelsModel phpTravelsModel) {
        DestinationModel hotelDestination = getExpectedHotelDestinationFromDataProvider(phpTravelsModel);

        return getHotelDestinationName(hotelDestination);
    }

    private void findAndSelectCity(PhpTravelsModel phpTravelsModel) {
        DestinationModel expectedHotelDestination = getExpectedHotelDestinationFromDataProvider(phpTravelsModel);
        String expectedHotelDestinationCityName = expectedHotelDestination.getCity();
        String expectedHotelDestinationCountryName = expectedHotelDestination.getCountry();

        typeCityToInputCity(expectedHotelDestinationCityName);
        clickOnCityButton(expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void clickOnCityButton(String expectedHotelDestinationCityName, String expectedHotelDestinationCountryName) {
        By cityLocator = By.xpath("//strong[contains(text(), '" + expectedHotelDestinationCountryName + "')]/ancestor::li[contains(text(), '" + expectedHotelDestinationCityName + "')]/..");
        check.isNumberOfElementsEqualTo(cityLocator, 1, 50, 15);

        WebElement cityButton = getWebDriver().getDriver().findElement(cityLocator);
        click.clickOnVisibleElement(cityButton, 15);

        log.info("{},{} has been selected.", expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void typeCityToInputCity(String expectedHotelDestinationCityName) {
        check.isElementEnabled(cityInput, 15);
        send.sendKeysToWebElementWithNoLeave(cityInput, expectedHotelDestinationCityName);
        log.info("{} has been typed to city input.", expectedHotelDestinationCityName);
    }

    private String getHotelDestinationName(DestinationModel hotelDestination) {
        String city = hotelDestination.getCity();
        String country = hotelDestination.getCountry();

        return city.concat(" ").concat(country);
    }

    private void checkIfCitiesAreDisplayed() {
        check.isElementDisplayed(citiesContainer, 15);
        By cityLocator = By.xpath("//div[@class = 'most--popular-hotels']/div");
        check.isNumberOfElementsGreaterThan(cityLocator, 1, 50, 10);
    }

    private void checkCityBeforeInput() {
        compareCityBeforeOrAfterInput("Search By City", citySpan, "before");
    }

    private void compareCityBeforeOrAfterInput(String expectedLocationValue, WebElement element, String inputStage) {
        String actualLocationValue = get.getTextFromElement(element);

        assertThat(actualLocationValue)
                .as("City check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

        log.info("City {} input value meets expected value.", inputStage);
    }

    private void clickOnCityInput() {
        click.clickOnEnabledElement(citySpan, 15);
        log.info("City input has been clicked.");
    }

    private void selectCheckInDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.IN);
        checkIfDatePickerWindowIsDisplayed(true, "Check in");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.YEAR);
        closeCheckOutDatePickerWindowIfNecessary();
        checkIfDatePickerWindowIsDisplayed(false, "Check in");
    }

    private void selectCheckInDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.IN);
        checkIfDatePickerWindowIsDisplayed(true, "Check in");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.MONTH);
        closeCheckOutDatePickerWindowIfNecessary();
        checkIfDatePickerWindowIsDisplayed(false, "Check in");
    }

    private void selectCheckInDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.IN);
        checkIfDatePickerWindowIsDisplayed(true, "Check in");
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.DAY);
        closeCheckOutDatePickerWindowIfNecessary();
        checkIfDatePickerWindowIsDisplayed(false, "Check in");
    }

    private void selectCheckOutDateYear(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.OUT);
        checkIfDatePickerWindowIsDisplayed(true, "Check out");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.YEAR);
        checkIfDatePickerWindowIsDisplayed(false, "Check out");
    }

    private void selectCheckOutDateMonth(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.OUT);
        checkIfDatePickerWindowIsDisplayed(true, "Check out");
        clickOnDatePickerHeader(Date.MONTH);
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.MONTH);
        checkIfDatePickerWindowIsDisplayed(false, "Check out");
    }

    private void selectCheckOutDateDay(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.OUT);
        checkIfDatePickerWindowIsDisplayed(true, "Check out");
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Check out");
    }

    private void checkActualCheckInDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, CheckType.IN);
    }

    private void checkActualCheckOutDate(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, CheckType.OUT);
    }

    private void clickOnDateInput(CheckType checkType) {
        By locator = switch (checkType) {
            case IN -> By.xpath("//input[@id = 'checkin']");
            case OUT -> By.xpath("//input[@id = 'checkout']");
        };

        check.isElementPresentByLocator(locator, 10);

        switch(checkType) {
            case IN -> click.clickOnEnabledElement(checkInDateInput, 15);
            case OUT -> click.clickOnEnabledElement(checkOutDateInput, 15);
        }

        log.info("{} date picker input has been clicked.", checkType.getCheckTypeName());
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

    private void selectSpecificDate(PhpTravelsModel phpTravelsModel, CheckType checkType, Date date) {
        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, checkType);

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
        log.info("{} date: {} {} has been clicked.", checkType.getCheckTypeName(), date.getName(), specificDate);
    }

    private void closeCheckOutDatePickerWindowIfNecessary() {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker dropdown-menu' and contains(@style, 'block')]");
        if (check.isElementPresentByLocator(datePickerWindowLocator, 1)) {
            WebElement element = getWebDriver().getDriver().findElement(By.xpath("//body"));
            click.clickOnElement(element, 5);
        }
    }

    private void checkActualDate(PhpTravelsModel phpTravelsModel, CheckType checkType) {
        String actualDate = switch (checkType) {
            case IN -> get.getValueFromElement(checkInDateInput);
            case OUT -> get.getValueFromElement(checkOutDateInput);
        };

        DateModel expectedDate = getExpectedDateFromDataProvider(phpTravelsModel, checkType);

        String year = expectedDate.getYear();
        String month = String.format("%02d", expectedDate.getMonth().getValue());
        String day = String.format("%02d", Integer.parseInt(expectedDate.getDay()));
        String expectedDateAsString = day.concat("-").concat(month).concat("-").concat(year);

        assertThat(actualDate)
                .as(checkType.getCheckTypeName() + " date check")
                .isEqualTo(expectedDateAsString);
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel, CheckType checkType) {
        return switch (checkType) {
            case IN -> phpTravelsModel.getHotelsPageModel().getExpectedCheckInDate();
            case OUT -> phpTravelsModel.getHotelsPageModel().getExpectedCheckOutDate();
        };
    }

    private AccommodationModel getExpectedAccommodationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getHotelsPageModel().getExpectedAccommodation();
    }

    private List<ChildModel> getExpectedChildrenFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getHotelsPageModel().getExpectedChildren();
    }

    private DestinationModel getExpectedHotelDestinationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getHotelsPageModel().getExpectedDestination();
    }

    private String getExpectedNationalityFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getHotelsPageModel().getExpectedNationality();
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributeEqualTo(hotelsTab, "aria-selected", "true", 50, 5))
                .as("Hotels tab activity check")
                .isTrue();

        log.info("Hotels tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(hotelsSearchBar, 15);
        log.info("Hotels search bar has been displayed");
    }
}
