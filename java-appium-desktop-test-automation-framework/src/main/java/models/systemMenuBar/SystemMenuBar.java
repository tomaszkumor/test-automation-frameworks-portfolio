package models.systemMenuBar;

import lombok.NoArgsConstructor;
import models.systemMenuBar.filesModal.FilesModal;
import models.systemMenuBar.pagesModal.PagesModal;

import static utils.logger.Log4J.log;

@NoArgsConstructor
public class SystemMenuBar extends SystemMenuBarLocators {
    public PagesModal clickOnPagesButton() {
        click.clickOnVisibleElement(pagesButton, 15);
        log.info("System menu bar Pages button has been clicked.");

        return new PagesModal();
    }

    public FilesModal clickOnFilesButton() {
        click.clickOnVisibleElement(filesButton, 15);
        log.info("System menu bar Files button has been clicked.");

        return new FilesModal();
    }
}
