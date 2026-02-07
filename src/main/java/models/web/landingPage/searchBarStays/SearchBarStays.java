package models.web.landingPage.searchBarStays;

import constants.common.Arrow;
import constants.common.Date;
import constants.staysPage.Accommodation;
import constants.staysPage.CheckType;
import dataProviders.dataProvidersModels.web.commonModels.DateModel;
import dataProviders.dataProvidersModels.web.commonModels.DestinationModel;
import dataProviders.dataProvidersModels.web.phpTravelsModel.PhpTravelsModel;
import dataProviders.dataProvidersModels.web.staysPageModels.AccommodationModel;
import dataProviders.dataProvidersModels.web.staysPageModels.ChildModel;
import io.qameta.allure.Step;
import models.web.menu.staysPage.staysSearchPage.StaysSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static driver.BaseDriver.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

public class SearchBarStays extends SearchBarStaysLocators {
    public SearchBarStays() {
        checkIfTabIsActive();
        checkIfSearchBarIsDisplayed();
        log.info("Stays search bar is displayed.");
    }

    @Step("Select Destination or hotel name")
    public SearchBarStays selectDestination(PhpTravelsModel phpTravelsModel) {
        checkDestinationBeforeInput();
        clickOnDestinationInput();
        findAndSelectDestination(phpTravelsModel);
        checkDestinationAfterInput(phpTravelsModel);

        return this;
    }

    @Step("Select check in date")
    public SearchBarStays selectCheckInDate(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.IN);
        selectCheckInDateYear(phpTravelsModel);
        selectCheckInDateMonth(phpTravelsModel);
        selectCheckInDateDay(phpTravelsModel);
        checkCheckInDateAfterChange(phpTravelsModel);
        scrollIntoSearchBarIfNecessary();

        return this;
    }

    @Step("Select check out date")
    public SearchBarStays selectCheckOutDate(PhpTravelsModel phpTravelsModel) {
        clickOnDateInput(CheckType.OUT);
        selectCheckOutDateYear(phpTravelsModel);
        selectCheckOutDateMonth(phpTravelsModel);
        selectCheckOutDateDay(phpTravelsModel);
        checkCheckOutDateAfterChange(phpTravelsModel);
        scrollIntoSearchBarIfNecessary();

        return this;
    }

    @Step("Select accommodation")
    public SearchBarStays selectAccommodation(PhpTravelsModel phpTravelsModel) {
        checkRoomsAndGuestsCountBeforeChange();
        clickOnAccommodationSelect();
        checkIfAccommodationWindowIsDisplayed(true);
        setRoomsNumber(phpTravelsModel);
        setAdultsNumber(phpTravelsModel);
        setChildrenNumber(phpTravelsModel);
        setChildrenAge(phpTravelsModel);
        closeAccommodationsWindow();
        checkIfAccommodationWindowIsDisplayed(false);
        scrollIntoSearchBarIfNecessary();
        checkRoomsAndGuestsCountAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Select nationality")
    public SearchBarStays selectNationality(PhpTravelsModel phpTravelsModel) {
        checkNationalityBeforeChange();
        clickOnNationalitySelect();
        checkIfNationalityWindowIsDisplayed(true);
        typeNationalityInSearchInput(phpTravelsModel);
        setNationality(phpTravelsModel);
        checkIfNationalityWindowIsDisplayed(false);
        checkNationalityAfterChange(phpTravelsModel);

        return this;
    }

    @Step("Click on search button")
    public StaysSearchPage clickOnSearchButton() {
        click.clickOnVisibleElement(searchButton, 15);
        log.info("Search button has been clicked.");

        return new StaysSearchPage();
    }

    private void typeNationalityInSearchInput(PhpTravelsModel phpTravelsModel) {
        String nationality = getExpectedNationalityFromDataProvider(phpTravelsModel);
        send.sendKeysToWebElementWithNoLeave(nationalityInput, nationality);
        log.info("{} has been typed to nationality search input.", nationality);
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

                By childSelectLocator = By.xpath("//span[@x-text = 'childIndex + 1' and text() = '" + expectedChildNo + "']/../following-sibling::select");
                WebElement ageSelect = getWebDriver().getDriver().findElement(childSelectLocator);

                Select select = new Select(ageSelect);
                select.selectByContainsVisibleText(expectedChildAge);

                log.info("Age {} has been selected for child no {}", expectedChildAge, expectedChildNo);
            }
        }
    }

    private void setNationality(PhpTravelsModel phpTravelsModel) {
        String nationality = getExpectedNationalityFromDataProvider(phpTravelsModel);

        By nationalitySelectLocator = By.xpath("//div[contains(@class, 'show')]//span[@x-text = 'country.nicename' and text() = '" + nationality + "']");
        WebElement nationalitySelect = getWebDriver().getDriver().findElement(nationalitySelectLocator);
        click.clickOnElement(nationalitySelect, 15);

        log.info("Nationality {} has been selected", nationality);
    }

    private String getTotalExpectedCountOfGuests(PhpTravelsModel phpTravelsModel) {
        String expectedAdultsCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, Accommodation.ADULTS);
        String expectedChildrenCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, Accommodation.CHILDREN);

        int expectedAdultsCountAsNumber = Integer.parseInt(expectedAdultsCount);
        int expectedChildrenCountAsNumber = Integer.parseInt(expectedChildrenCount);

        return String.valueOf(expectedAdultsCountAsNumber + expectedChildrenCountAsNumber);
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
        checkSpecificAccommodationCountBeforeChange(Accommodation.CHILDREN);
        changeSpecificAccommodationGroupCount(phpTravelsModel, Accommodation.CHILDREN);
        checkSpecificAccommodationCountAfterChange(phpTravelsModel, Accommodation.CHILDREN);
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
        String group = switch (accommodation) {
            case ROOMS -> "roomsData.length";
            case ADULTS -> "room.adults";
            case CHILDREN -> "room.children";
        };

        String arrowDirection = switch (arrow) {
            case LEFT -> "1";
            case RIGHT -> "2";
        };

        return By.xpath("//div[contains(@class, 'show')]//span[@x-text = '" + group + "']/../button[" + arrowDirection + "]");
    }

    private String getExpectedSpecificAccommodationGroupCountAfterChange(PhpTravelsModel phpTravelsModel, Accommodation accommodation) {
        AccommodationModel expectedAccommodation = getExpectedAccommodationFromDataProvider(phpTravelsModel);

        return switch (accommodation) {
            case ROOMS -> expectedAccommodation.getRoomsCount();
            case ADULTS -> expectedAccommodation.getAdultsCount();
            case CHILDREN -> expectedAccommodation.getChildrenCount();
        };
    }

    private By getActualSpecificAccommodationCountLocator(Accommodation accommodation) {
        return switch (accommodation) {
            case ROOMS -> By.xpath("//span[@x-text = 'roomsData.length']");
            case ADULTS -> By.xpath("//span[@x-text = 'room.adults']");
            case CHILDREN -> By.xpath("//span[@x-text = 'room.children']");
        };
    }

    private String getActualSpecificAccommodationGroupCount(Accommodation accommodation) {
        By accommodationLocator = getActualSpecificAccommodationCountLocator(accommodation);

        return get.getTextFromElement(accommodationLocator);
    }

    private String getExpectedSpecificAccommodationGroupCountBeforeChange(Accommodation accommodation) {
        return switch (accommodation) {
            case ROOMS -> "1";
            case ADULTS -> "2";
            case CHILDREN -> "0";
        };
    }

    private void checkIfDatePickerWindowIsDisplayed(boolean shouldBeDisplayed, String destination) {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]");
        checkIfWindowIsDisplayed(datePickerWindowLocator, destination + " date picker", shouldBeDisplayed);
    }

    private void checkIfAccommodationWindowIsDisplayed(boolean shouldBeDisplayed) {
        By accommodationWindowLocator = By.xpath("//div[@x-data = 'guestsRoomsDropdown()']//div[contains(@class, 'show')]");
        checkIfWindowIsDisplayed(accommodationWindowLocator, "Accommodation", shouldBeDisplayed);
    }

    private void checkIfNationalityWindowIsDisplayed(boolean shouldBeDisplayed) {
        By accommodationWindowLocator = By.xpath("//div[@x-data = 'nationalityDropdown()']//div[contains(@class, 'show')]");
        checkIfWindowIsDisplayed(accommodationWindowLocator, "Nationality", shouldBeDisplayed);
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

    private void clickOnAccommodationSelect() {
        click.clickOnElement(accommodationDropDown, 15);
        log.info("Accommodation drop down has been clicked.");
    }

    private void clickOnNationalitySelect() {
        click.clickOnElement(nationalityDropDown, 15);
        log.info("Nationality drop down has been clicked.");
    }

    private void checkRoomsAndGuestsCountBeforeChange() {
        WebElement roomsAndGuestsCount = accommodationDropDown.findElement(By.xpath(".//span[@x-text = 'getGuestText()']"));
        String expectedGuestsCount = "2 Guests, 1 Room";
        checkSelectValueBeforeOrAfterChange(roomsAndGuestsCount, expectedGuestsCount, "Guests and rooms count", "before");
    }

    private void checkNationalityBeforeChange() {
        WebElement roomsAndGuestsCount = nationalityDropDown.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        String expectedGuestsCount = "Select Nationality";
        checkSelectValueBeforeOrAfterChange(roomsAndGuestsCount, expectedGuestsCount, "Nationality", "before");
    }

    private void checkRoomsAndGuestsCountAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement roomsAndGuestsCount = accommodationDropDown.findElement(By.xpath(".//span[@x-text = 'getGuestText()']"));
        String expectedGuestsCount = getTotalExpectedCountOfGuests(phpTravelsModel);
        String expectedRoomsCount = getExpectedSpecificAccommodationGroupCountAfterChange(phpTravelsModel, Accommodation.ROOMS);
        String expectedGuestsAndRoomsCount = expectedGuestsCount.concat(" Guests, ").concat(expectedRoomsCount).concat(" Room");

        checkSelectValueBeforeOrAfterChange(roomsAndGuestsCount, expectedGuestsAndRoomsCount, "Guests and rooms count", "after");
    }

    private void checkNationalityAfterChange(PhpTravelsModel phpTravelsModel) {
        WebElement nationality = nationalityDropDown.findElement(By.xpath(".//span[@x-text = 'getSelectedName()']"));
        String expectedNationality = getExpectedNationalityFromDataProvider(phpTravelsModel);
        checkSelectValueBeforeOrAfterChange(nationality, expectedNationality, "Nationality", "after");
    }

    private void checkSelectValueBeforeOrAfterChange(WebElement element, String expectedValue, String valueName, String stage) {
        String actualGuestsCount = get.getTextFromElement(element);

        assertThat(actualGuestsCount)
                .as(valueName + " check " + stage + " change")
                .isEqualTo(expectedValue);
    }

    private String getExpectedDestinationValueAfterInput(PhpTravelsModel phpTravelsModel) {
        DestinationModel hotelDestination = getExpectedHotelDestinationFromDataProvider(phpTravelsModel);

        return getDestinationName(hotelDestination);
    }

    private void findAndSelectDestination(PhpTravelsModel phpTravelsModel) {
        DestinationModel expectedHotelDestination = getExpectedHotelDestinationFromDataProvider(phpTravelsModel);
        String expectedHotelDestinationCityName = expectedHotelDestination.getCity();
        String expectedHotelDestinationCountryName = expectedHotelDestination.getCountry();

        typeDestinationToDestinationInput(expectedHotelDestinationCityName);
        clickOnDestinationButton(expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void clickOnDestinationButton(String expectedHotelDestinationCityName, String expectedHotelDestinationCountryName) {
        By cityLocator = By.xpath("//span[@x-text = \"d.cityname + ', ' + d.countryname\" and text() = '" + expectedHotelDestinationCityName + ", " + expectedHotelDestinationCountryName + "']");
        check.isNumberOfElementsEqualTo(cityLocator, 1, 50, 15);

        WebElement cityButton = getWebDriver().getDriver().findElement(cityLocator);
        click.clickOnVisibleElement(cityButton, 15);

        log.info("{},{} has been selected.", expectedHotelDestinationCityName, expectedHotelDestinationCountryName);
    }

    private void typeDestinationToDestinationInput(String expectedHotelDestinationCityName) {
        check.isElementEnabled(destinationInput, 15);
        send.sendKeysToWebElementWithNoLeave(destinationInput, expectedHotelDestinationCityName);
        log.info("{} has been typed to destination input.", expectedHotelDestinationCityName);
    }

    private String getDestinationName(DestinationModel hotelDestination) {
        String city = hotelDestination.getCity();
        String country = hotelDestination.getCountry();

        return city.concat(", ").concat(city).concat(", ").concat(country);
    }

    private void checkDestinationBeforeInput() {
        String expectedDestination = "";
        compareDestinationBeforeOrAfterInput(expectedDestination, destinationInput, "before");
    }

    private void checkDestinationAfterInput(PhpTravelsModel phpTravelsModel) {
        String expectedDestination = getExpectedDestinationValueAfterInput(phpTravelsModel);
        compareDestinationBeforeOrAfterInput(expectedDestination, destinationInput, "after");
    }

    private void compareDestinationBeforeOrAfterInput(String expectedLocationValue, WebElement element, String inputStage) {
        String actualLocationValue = get.getValueFromElement(element).trim();

        assertThat(actualLocationValue)
                .as("Destination check " + inputStage + " input")
                .isEqualTo(expectedLocationValue);

        log.info("Destination {} input value meets expected value.", inputStage);
    }

    private void clickOnDestinationInput() {
        click.clickOnEnabledElement(destinationInput, 15);
        log.info("Destination input has been clicked.");
    }

    private void selectCheckInDateYear(PhpTravelsModel phpTravelsModel) {
        checkIfDatePickerWindowIsDisplayed(true, "Check in");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.YEAR);
    }

    private void selectCheckOutDateYear(PhpTravelsModel phpTravelsModel) {
        checkIfDatePickerWindowIsDisplayed(true, "Check out");
        clickOnDatePickerHeader(Date.YEAR);
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.YEAR);
    }

    private void selectCheckInDateMonth(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.MONTH);
    }

    private void selectCheckOutDateMonth(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.MONTH);
    }

    private void selectCheckInDateDay(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, CheckType.IN, Date.DAY);
        closeCheckOutDatePickerWindowIfNecessary();
        checkIfDatePickerWindowIsDisplayed(false, "Check in");
    }

    private void selectCheckOutDateDay(PhpTravelsModel phpTravelsModel) {
        selectSpecificDate(phpTravelsModel, CheckType.OUT, Date.DAY);
        checkIfDatePickerWindowIsDisplayed(false, "Check out");
    }

    private void checkCheckInDateAfterChange(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, CheckType.IN);
    }

    private void checkCheckOutDateAfterChange(PhpTravelsModel phpTravelsModel) {
        checkActualDate(phpTravelsModel, CheckType.OUT);
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

    private void clickOnDateInput(CheckType checkType) {
        By locator = switch (checkType) {
            case IN -> By.xpath("//input[@name = 'checkin_date']");
            case OUT -> By.xpath("//input[@name = 'checkout_date']");
        };

        check.isElementPresentByLocator(locator, 10);

        switch (checkType) {
            case IN -> click.clickOnEnabledElement(checkInDateInput, 15);
            case OUT -> click.clickOnEnabledElement(checkOutDateInput, 15);
        }

        log.info("{} date picker input has been clicked.", checkType.getCheckTypeName());
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
        log.info("{} date: {} {} has been clicked.", checkType.getCheckTypeName(), date.getName(), specificDate);
    }

    private void closeCheckOutDatePickerWindowIfNecessary() {
        By datePickerWindowLocator = By.xpath("//div[@class = 'datepicker-days']/ancestor::div[contains(@class, 'datepicker') and not(contains(@class, 'hidden'))]");
        if (check.isElementPresentByLocator(datePickerWindowLocator, 1)) {
            WebElement element = getWebDriver().getDriver().findElement(By.xpath("//body"));
            click.clickOnElement(element, 5);
        }
    }

    private DateModel getExpectedDateFromDataProvider(PhpTravelsModel phpTravelsModel, CheckType checkType) {
        return switch (checkType) {
            case IN -> phpTravelsModel.getStaysPageModel().getExpectedCheckInDate();
            case OUT -> phpTravelsModel.getStaysPageModel().getExpectedCheckOutDate();
        };
    }

    private void scrollIntoSearchBarIfNecessary() {
        browser.scrollIntoView(h1);
    }

    private AccommodationModel getExpectedAccommodationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getStaysPageModel().getExpectedAccommodation();
    }

    private List<ChildModel> getExpectedChildrenFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getStaysPageModel().getExpectedChildren();
    }

    private DestinationModel getExpectedHotelDestinationFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getStaysPageModel().getExpectedDestination();
    }

    private String getExpectedNationalityFromDataProvider(PhpTravelsModel phpTravelsModel) {
        return phpTravelsModel.getStaysPageModel().getExpectedNationality();
    }

    private void checkIfTabIsActive() {
        assertThat(check.isAttributePresent(staysTab, "class", "text-primary", 50, 5))
                .as("Stays tab activity check")
                .isTrue();

        log.info("Stays tab is an active tab.");
    }

    private void checkIfSearchBarIsDisplayed() {
        check.isElementDisplayed(staysTab, 15);
        log.info("Stays search bar has been displayed.");
    }
}
