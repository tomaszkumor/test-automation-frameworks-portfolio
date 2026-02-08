package actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import lombok.SneakyThrows;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;
import static utils.logger.Log4J.log;

public class ClickActions {
    @SneakyThrows
    public void leftClick(String selector, String selectorName) {
        try {
            getLocator(selector).click();
            log.debug("{} has been clicked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to click on " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void forceLeftClick(String selector, String selectorName) {
        try {
            getLocator(selector).click(new Locator.ClickOptions().setForce(true));
            log.debug("{} has been forced to clicked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to force left click on " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void doubleLeftClick(String selector, String selectorName) {
        try {
            getLocator(selector).dblclick();
            log.debug("{} has been double clicked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to double click on " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void rightClick(String selector, String selectorName) {
        try {
            getLocator(selector).click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
            log.debug("{} has been right clicked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to right click on " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void clickOnSelectOption(String selectionBoxSelector, String optionToSelect, String selectorName) {
        try {
            getLocator(selectionBoxSelector).selectOption(optionToSelect);
            log.debug("{} has been selected in {}.", optionToSelect, selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to select option in " + selectorName + ": " + e.getMessage());
        }
    }

    public void checkCheckbox(String selector, String selectorName) {
        checkCheckboxOrRadioButton(selector, selectorName);
    }

    public void uncheckCheckbox(String selector, String selectorName) {
        uncheckCheckboxOrRadioButton(selector, selectorName);
    }

    public void clickOnRadioButton(String selector, String selectorName) {
        checkCheckboxOrRadioButton(selector, selectorName);
    }

    @SneakyThrows
    private void checkCheckboxOrRadioButton(String selector, String selectorName) {
        try {
            getLocator(selector).check();
            log.debug("{} has been checked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to check " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    private void uncheckCheckboxOrRadioButton(String selector, String selectorName) {
        try {
            getLocator(selector).uncheck();
            log.debug("{} has been unchecked.", selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to uncheck " + selectorName + ": " + e.getMessage());
        }
    }

    private Locator getLocator(String selector) {
        return getPlaywrightInstance().getPage().locator(selector);
    }
}