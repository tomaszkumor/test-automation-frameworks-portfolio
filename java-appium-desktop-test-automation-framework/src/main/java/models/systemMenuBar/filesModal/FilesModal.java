package models.systemMenuBar.filesModal;

import models.changeDocumentNameModal.ChangeDocumentNameModal;

import static utils.logger.Log4J.log;

public class FilesModal extends FilesModalLocators {
    public FilesModal() {
        check.isElementDisplayed(filesModal, 15);
        log.info("Files modal is displayed.");
    }

    public ChangeDocumentNameModal clickOnChangeNameButton() {
        click.clickOnVisibleElement(changeNameButton, 15);
        log.info("Change name button has been clicked.");

        return new ChangeDocumentNameModal();
    }
}
