package models.mobile.navigation;

import io.qameta.allure.Step;
import lombok.NoArgsConstructor;
import models.mobile.menu.moreModal.MoreModal;
import models.mobile.menu.savedPage.SavedPage;
import models.mobile.menu.searchPage.SearchPage;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.logger.Log4J.log;

@NoArgsConstructor
public class Navigation extends NavigationLocators {
    @Step("Tap on tab saved")
    public SavedPage tapOnTabSaved() {
        tapOnTab(savedButton, "Saved", true);

        return new SavedPage();
    }

    @Step("Tap on tab search")
    public SearchPage tapOnTabSearch() {
        tapOnTab(searchButton, "Search", true);

        return new SearchPage();
    }

    @Step("Tap on tab more")
    public MoreModal tapOnTabMore() {
        tapOnTab(moreButton, "More", false);

        return new MoreModal();
    }

    private void tapOnTab(WebElement tab, String tabName, boolean checkSelectionAttribute) {
        mobile.tapOnElement(tab, 15);

        if (checkSelectionAttribute) {
            assertThat(check.isAttributeEqualTo(tab, "selected", "true", 15));
        }

        log.info("{} tab button has been clicked.", tabName);
    }
}
