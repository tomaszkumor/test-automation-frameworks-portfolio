package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class WebCapabilitiesManager {
    public FirefoxOptions setFirefoxOptionsRemote() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setCapability(CapabilityType.PLATFORM_NAME, "ANY");
        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.disable_open_during_load", true);
        profile.setPreference("signon.rememberSignons", false);
        options.setProfile(profile);

        if (isHeadless()) {
            options.addArguments("-headless");
            options.addArguments("--width=1200");
            options.addArguments("--height=1024");
        }

        return options;
    }

    public FirefoxOptions setFirefoxOptions() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setAcceptInsecureCerts(true);

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.disable_open_during_load", true);
        profile.setPreference("signon.rememberSignons", false);
        profile.setPreference("network.cookie.cookieBehavior", 0);
        options.setProfile(profile);

        if (isHeadless()) {
            options.addArguments("-headless");
            options.addArguments("--width=1200");
            options.addArguments("--height=1024");
        }

        return options;
    }

    public SafariOptions setSafariOptionsRemote() {
        SafariOptions options = new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "safari");
        options.setCapability(CapabilityType.PLATFORM_NAME, "MAC");

        return options;
    }

    public SafariOptions setSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.PLATFORM_NAME, "MAC");

        return options;
    }

    public ChromeOptions setChromeOptionsRemote() {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.PERFORMANCE, Level.WARNING);

        ChromeOptions options = new ChromeOptions()
                .addArguments("--start-maximized")
                .addArguments("--disable-infobars")
                .addArguments("--disable-extensions")
                .addArguments("--disable-notifications")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--no-sandbox")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--remote-allow-origins=*");

        if (isHeadless()) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1200,1024");
        }

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setCapability("goog:loggingPrefs", loggingPreferences);
        options.setCapability(CapabilityType.PLATFORM_NAME, "ANY");
        options.setCapability(ChromeOptions.CAPABILITY, options);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.merge(options);

        return options;
    }

    public ChromeOptions setChromeOptions() {
        WebDriverManager.chromedriver().setup();

        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.PERFORMANCE, Level.WARNING);

        ChromeOptions options = (ChromeOptions) new ChromeOptions()
                .addArguments("--start-maximized")
                .addArguments("--disable-infobars")
                .addArguments("--disable-extensions")
                .addArguments("--disable-notifications")
                .addArguments("--disable-modal-animations")
                .addArguments("--disable-web-security")
                .addArguments("--disable-popup-blocking")
                .addArguments("--no-sandbox")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--remote-allow-origins=*")
                .setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking", "enable-automation"))
                .setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (isHeadless()) {
            options.addArguments(
                    "--headless",
                    "--disable-gpu",
                    "--window-size=1200,1024",
                    "--ignore-certificate-errors",
                    "--disable-extensions",
                    "--no-sandbox",
                    "--disable-dev-shm-usage");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("credentials_enable_service", false);

        options.setExperimentalOption("prefs", prefs);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        options.setCapability("goog:loggingPrefs", loggingPreferences);

        return options;
    }

    private boolean isHeadless() {
        return WebProperties.isHeadlessMode();
    }
}
