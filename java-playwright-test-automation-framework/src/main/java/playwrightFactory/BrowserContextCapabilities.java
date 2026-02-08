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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        return new NewContextOptions()
                .setViewportSize((int) screenSize.getWidth(), (int) screenSize.getHeight())
                .setIgnoreHTTPSErrors(true)
                .setLocale("pl-PL")
                .setTimezoneId("Europe/Warsaw")
                .setPermissions(List.of("geolocation", "notifications"));
    }


    public NewContextOptions getWebkitContextCapabilities() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        return new NewContextOptions()
                .setViewportSize((int) screenSize.getWidth(), (int) screenSize.getHeight())
                .setIgnoreHTTPSErrors(true)
                .setLocale("pl-PL")
                .setTimezoneId("Europe/Warsaw")
                .setPermissions(List.of("geolocation", "notifications"));
    }
}

