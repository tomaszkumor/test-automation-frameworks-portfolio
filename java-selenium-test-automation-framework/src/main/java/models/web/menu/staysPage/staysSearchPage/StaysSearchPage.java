package models.web.menu.staysPage.staysSearchPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.logger.Log4J.log;

public class StaysSearchPage extends StaysSearchPageLocators {
    public StaysSearchPage() {
        browser.waitForPageLoaded(15);
        checkUrl();
        log.info("Stays search page has been displayed.");
    }

    private void checkUrl() {
        String expectedUrl = "https://phptravels.net/stays";
        String actualUrl = get.getCurrentUrl();

        assertThat(actualUrl)
                .as("URL check")
                .contains(expectedUrl);
    }
}
