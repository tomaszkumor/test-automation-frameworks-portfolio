package actions;

import com.microsoft.playwright.Locator;
import lombok.SneakyThrows;

import java.nio.file.Paths;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;
import static utils.logger.Log4J.log;

public class SendActions {
    @SneakyThrows
    public void sendKeysToElement(String selector, String text, String selectorName) {
        try {
            getLocator(selector).clear();
            getLocator(selector).fill(text);
            log.debug("'{}' has been typed to {}.", text, selectorName);
            sendKey(selector, "Tab");
        } catch (Exception e) {
            throw new Exception("Unable to send keys to " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void sendKeysToElementWithNoLeave(String selector, String text, String selectorName) {
        try {
            getLocator(selector).clear();
            getLocator(selector).fill(text);
            log.debug("'{}' has been typed to {}.", text, selectorName);
        } catch (Exception e) {
            throw new Exception("Unable to send keys to " + selectorName + ": " + e.getMessage());
        }
    }

    @SneakyThrows
    public void sendKey(String selector, String key) {
        try {
            getPlaywrightInstance().getPage().press(selector, key);
            log.debug("'{}' has been tapped.", key);
        } catch (Exception e) {
            throw new Exception("Unable to press '" + key + "' key: " + e.getMessage());
        }
    }

    @SneakyThrows
    public void uploadFile(String selector, String fileDirectory) {
        try {
            getLocator(selector).setInputFiles(Paths.get(fileDirectory));
            log.debug("File from directory '{}' has been attached to file picker input.", fileDirectory);
        } catch (Exception e) {
            throw new Exception("Unable to upload file from directory: '" + fileDirectory + "': " + e.getMessage());
        }
    }

    private Locator getLocator(String selector) {
        return getPlaywrightInstance().getPage().locator(selector);
    }
}
