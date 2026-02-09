package playwrightFactory;

import java.awt.*;
import java.util.List;

import static com.microsoft.playwright.Browser.NewContextOptions;

public class BrowserContextCapabilities {
    public NewContextOptions getChromiumContextCapabilities() {
        return new NewContextOptions()
                .setViewportSize(null) // dla chromium
                .setIgnoreHTTPSErrors(true)
                .setLocale("pl-PL")
                .setTimezoneId("Europe/Warsaw")
                .setPermissions(List.of("geolocation", "notifications"));
    }


    public NewContextOptions getFirefoxContextCapabilities() {
        NewContextOptions newContextOptions = new NewContextOptions();

        newContextOptions
                .setIgnoreHTTPSErrors(true)
                .setLocale("pl-PL")
                .setTimezoneId("Europe/Warsaw")
                .setPermissions(List.of("geolocation", "notifications"));

        if (!WebProperties.isHeadlessMode()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            newContextOptions
                    .setViewportSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        }

        return newContextOptions;
    }


    public NewContextOptions getWebkitContextCapabilities() {
        NewContextOptions newContextOptions = new NewContextOptions();

        newContextOptions
                .setIgnoreHTTPSErrors(true)
                .setLocale("pl-PL")
                .setTimezoneId("Europe/Warsaw")
                .setPermissions(List.of("geolocation", "notifications"));

        if (!WebProperties.isHeadlessMode()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            newContextOptions
                    .setViewportSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        }

        return newContextOptions;
    }
}

