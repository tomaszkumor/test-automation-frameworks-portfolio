package models.mobile.generic.genericSpecificSearchResultPage;

import static utils.logger.Log4J.log;

public class GenericSpecificSearchResultPage extends GenericSpecificSearchResultPageLocators {
    public void tapOnSaveButton() {
        mobile.tapOnElement(saveButton, 15);
        log.info("Save button has been tapped.");
    }

    public void tapOnContentsButton() {
        mobile.tapOnElement(contentsButton, 15);
        log.info("Contents button has been tapped.");
    }
}
