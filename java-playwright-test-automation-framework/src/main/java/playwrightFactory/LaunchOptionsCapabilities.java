package playwrightFactory;

import java.util.List;

import static com.microsoft.playwright.BrowserType.LaunchOptions;
import static playwrightFactory.WebProperties.getEngine;
import static playwrightFactory.WebProperties.isHeadlessMode;

public class LaunchOptionsCapabilities {
    public LaunchOptions getLaunchOptionsCapabilities() {
        boolean isHeadlessMode = isHeadlessMode();
        String engine = getEngine();

        return switch (engine) {
            case "chromium" -> new LaunchOptions()
                    .setHeadless(isHeadlessMode)
                    .setArgs(List.of(
                            "--disable-features=TranslateUI",
                            "--disable-infobars",
                            "--disable-infobars",
                            "--disable-extensions",
                            "--disable-notifications",
                            "--disable-dev-shm-usage",
                            "--no-sandbox",
                            "--ignore-certificate-errors",
                            "--remote-allow-origins=*",
                            "--start-maximized")
                    );

            case "firefox" -> new LaunchOptions()
                    .setHeadless(isHeadlessMode)
                    .setSlowMo(100)
                    .setArgs(List.of("--start-maximized")
                    );

            case "webkit" -> new LaunchOptions()
                    .setHeadless(isHeadlessMode)
                    .setSlowMo(100);

            default -> throw new IllegalArgumentException("Unsupported engine for LaunchOptions");
        };
    }
}
