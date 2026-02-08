package actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;
import static utils.logger.Log4J.log;

public class CheckActions {
    public void containsText(String selector, String expectedText, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).containsText(expectedText);
    }

    public void hasAttribute(String selector, String attributeName, String expectedAttributeValue, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasAttribute(attributeName, expectedAttributeValue);
    }

    public void containsAttribute(String selector, String attributeName, String expectedAttributeValue, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasAttribute(attributeName, Pattern.compile(expectedAttributeValue));
    }

    public void hasCount(String selector, int count, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasCount(count);
        log.debug("Number of {}: {}.", selectorName, count);
    }

    public void hasNotCount(String selector, int count) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).not().hasCount(count);
        log.debug("Number of elements: {}.", count);
    }

    public void hasText(String selector, String expectedText, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasText(expectedText);
        log.debug("{} has text: {}.", selectorName, expectedText);
    }

    public void hasText(String selector, LocatorAssertions.HasTextOptions options, String expectedText, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasText(expectedText, options);
        log.debug("{} has text: {}.", selectorName, expectedText);
    }

    public void hasValue(String selector, String expectedValue, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).hasValue(expectedValue);
        log.debug("{} has value: {}.", selectorName, expectedValue);
    }

    public void isChecked(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isChecked();
        log.debug("{} is checked.", selectorName);
    }

    public void isNotChecked(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).not().isChecked();
        log.debug("{} is unchecked.", selectorName);
    }

    public void isDisabled(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isDisabled();
        log.debug("{} is disabled.", selectorName);
    }

    public void isEditable(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isEditable();
        log.debug("{} is editable.", selectorName);
    }

    public void isEmpty(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isEmpty();
        log.debug("{} is empty.", selectorName);
    }

    public void isEnabled(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isEnabled();
        log.debug("{} is enabled.", selectorName);
    }

    public void isNotEnabled(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).not().isEnabled();
        log.debug("{} is disabled.", selectorName);
    }

    public void isFocused(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isFocused();
        log.debug("Focus is on {}.", selectorName);
    }

    public void isHidden(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isHidden();
        log.debug("{} is hidden.", selectorName);
    }

    public void isInViewport(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isInViewport();
        log.debug("{} is in viewport.", selectorName);
    }

    public void isVisible(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).isVisible();
        log.debug("{} is visible.", selectorName);
    }

    public void isNotVisible(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).not().isVisible();
        log.debug("{} is not visible.", selectorName);
    }

    public void isElementPresent(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator.first()).isAttached();
        log.debug("{} is present in DOM.", selectorName);
    }

    public void isElementNotPresent(String selector, String selectorName) {
        Locator locator = getLocator(selector);
        PlaywrightAssertions.assertThat(locator).not().isAttached();
        log.debug("{} is not present in DOM.", selectorName);
    }

    public void doesUrlContain(String expectedUrl) {
        Page page = getPlaywrightInstance().getPage();
        PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile(".*" + expectedUrl + ".*"));
        log.debug("URL contains expected value.");
    }

    public void isUrlEqualTo(String expectedUrl) {
        Page page = getPlaywrightInstance().getPage();
        PlaywrightAssertions.assertThat(page).hasURL(expectedUrl);
        log.debug("URL contains expected value.");
    }

    private Locator getLocator(String selector) {
        return getPlaywrightInstance().getPage().locator(selector);
    }
}