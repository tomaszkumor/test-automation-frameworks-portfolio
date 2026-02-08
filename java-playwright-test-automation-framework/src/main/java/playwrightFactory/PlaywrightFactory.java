package playwrightFactory;

import com.microsoft.playwright.*;
import config.TestStackProperties;

import static com.microsoft.playwright.Browser.NewContextOptions;
import static com.microsoft.playwright.BrowserType.LaunchOptions;
import static playwrightFactory.WebProperties.isRemoteMode;

public class PlaywrightFactory {
    private static PlaywrightFactory playwrightInstance;
    private static Playwright PLAYWRIGHT_INSTANCE;
    private static LaunchOptions LAUNCH_OPTIONS_INSTANCE;
    private static Browser BROWSER_INSTANCE;
    private static ThreadLocal<BrowserContext> BROWSER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();
    private static ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public static PlaywrightFactory getPlaywrightInstance() {
        if (playwrightInstance == null) {
            playwrightInstance = new PlaywrightFactory();
        }

        return playwrightInstance;
    }

    public Playwright getPlaywright() {
        return PLAYWRIGHT_INSTANCE;
    }

    public Browser getBrowser() {
        return BROWSER_INSTANCE;
    }

    public LaunchOptions getLaunchOptions() {
        return LAUNCH_OPTIONS_INSTANCE;
    }

    public BrowserContext getBrowserContext() {
        return BROWSER_CONTEXT_THREAD_LOCAL.get();
    }

    public Page getPage() {
        return PAGE_THREAD_LOCAL.get();
    }

    public void setPlaywright() {
        PLAYWRIGHT_INSTANCE = Playwright.create();
    }

    public void setBrowser() {
        if (isRemoteMode()) {
            getPlaywrightInstance().setRemoteBrowser();
        } else {
            getPlaywrightInstance().setLaunchOptions();
            getPlaywrightInstance().setLocalBrowser();
        }
    }

    public void setLaunchOptions() {
        LAUNCH_OPTIONS_INSTANCE = new LaunchOptionsCapabilities().getLaunchOptionsCapabilities();
    }

    public void setLocalBrowser() {
        String engine = WebProperties.getEngine();
        BrowserType browserType = switch (engine) {
            case "chromium" -> getPlaywright().chromium();
            case "firefox" -> getPlaywright().firefox();
            case "webkit" -> getPlaywright().webkit();
            default -> throw new IllegalArgumentException("Unsupported engine");
        };

        LaunchOptions launchOptions = getLaunchOptions();
        BROWSER_INSTANCE = browserType.launch(launchOptions);
    }

    public void setRemoteBrowser() {
        String engine = WebProperties.getEngine();
        BrowserType browserType = switch (engine) {
            case "chromium" -> getPlaywright().chromium();
            case "firefox" -> getPlaywright().firefox();
            case "webkit" -> getPlaywright().webkit();
            default -> throw new IllegalArgumentException("Unsupported engine");
        };

        String remoteBrowserWsUrl = TestStackProperties.getRemoteBrowserWsUrl();
        Browser browser = browserType.connect(remoteBrowserWsUrl);

        BROWSER_INSTANCE = browser;
    }

    public void setBrowserContext() {
        String engine = WebProperties.getEngine();
        NewContextOptions contextOptions = switch (engine) {
            case "chromium" -> new BrowserContextCapabilities().getChromiumContextCapabilities();
            case "firefox" -> new BrowserContextCapabilities().getFirefoxContextCapabilities();
            case "webkit" -> new BrowserContextCapabilities().getWebkitContextCapabilities();
            default -> throw new IllegalArgumentException("Unsupported engine");
        };

        Browser browser = getBrowser();
        BROWSER_CONTEXT_THREAD_LOCAL.set(browser.newContext(contextOptions));
    }

    public void setPage() {
        Page page = getBrowserContext().newPage();
        PAGE_THREAD_LOCAL.set(page);
    }

    public void goUrl() {
        String url = TestStackProperties.getWebUrl();
        getPage().navigate(url);
    }

    public void unloadPlaywright() {
        PLAYWRIGHT_INSTANCE = null;
    }

    public void unloadLaunchOptions() {
        LAUNCH_OPTIONS_INSTANCE = null;
    }

    public void unloadBrowser() {
        BROWSER_INSTANCE = null;
    }

    public void unloadBrowserContext() {
        BROWSER_CONTEXT_THREAD_LOCAL.remove();
    }

    public void unloadPage() {
        PAGE_THREAD_LOCAL.remove();
    }
}
