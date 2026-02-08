package actions;

import com.microsoft.playwright.Locator;
import lombok.SneakyThrows;

import java.util.List;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;

public class GetActions {
    @SneakyThrows
    public String getInnerText(String selector, String selectorName) {
        try {
            return getLocator(selector).innerText();
        } catch (Exception e) {
            throw new Exception("Unable to get inner text from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public List<String> getAllInnerText(String selector, String selectorName) {
        try {
            return getLocator(selector).allInnerTexts();
        } catch (Exception e) {
            throw new Exception("Unable to get all inner texts from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public int getCount(String selector, String selectorName) {
        try {
            return getLocator(selector).count();
        } catch (Exception e) {
            throw new Exception("Unable to get count of selector \"" + selectorName + "\": " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getAttribute(String selector, String attribute, String selectorName) {
        try {
            return getLocator(selector).getAttribute(attribute);
        } catch (Exception e) {
            throw new Exception("Unable to get attribute from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getInnerHtml(String selector, String selectorName) {
        try {
            return getLocator(selector).innerHTML();
        } catch (Exception e) {
            throw new Exception("Unable to get inner html from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getInputValue(String selector, String selectorName) {
        try {
            return getLocator(selector).inputValue();
        } catch (Exception e) {
            throw new Exception("Unable to get input value from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getTextContent(String selector, String selectorName) {
        try {
            return getLocator(selector).textContent();
        } catch (Exception e) {
            throw new Exception("Unable to get text content from " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementChecked(String selector, String selectorName) {
        try {
            return getLocator(selector).isChecked();
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is checked: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementDisabled(String selector, String selectorName) {
        try {
            return getLocator(selector).isDisabled();
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is disabled: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementEnabled(String selector, String selectorName) {
        try {
            return getLocator(selector).isEnabled();

        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is enabled: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementHidden(String selector, String selectorName) {
        try {
            return getLocator(selector).isHidden();
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is hidden: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementVisible(String selector, String selectorName) {
        try {
            return getLocator(selector).isVisible();
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is visible: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementEditable(String selector, String selectorName) {
        try {
            return getLocator(selector).isEditable();
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is editable: " + e.getMessage());
        }
    }

    @SneakyThrows
    public boolean isElementPresent(String selector, String selectorName) {
        try {
            return getLocator(selector).count() > 0;
        } catch (Exception e) {
            throw new Exception("Unable to check whether " + selectorName + " is present: " + e.getMessage());
        }
    }

    @SneakyThrows
    public String getCurrentUrl() {
        try {
            return getPlaywrightInstance().getPage().url();
        } catch (Exception e) {
            throw new Exception("Unable to get website url: " + e.getMessage());
        }
    }

    public Locator getLocator(String selector) {
        return getPlaywrightInstance().getPage().locator(selector);
    }
}