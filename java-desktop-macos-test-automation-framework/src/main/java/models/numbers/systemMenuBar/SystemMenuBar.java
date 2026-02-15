package models.numbers.systemMenuBar;

import io.qameta.allure.Step;
import lombok.NoArgsConstructor;
import models.numbers.systemMenuBar.filesModal.FilesModal;
import models.numbers.systemMenuBar.pagesModal.NumbersModal;

import static utils.logger.Log4J.log;

@NoArgsConstructor
public class SystemMenuBar extends SystemMenuBarLocators {
    @Step("Click on numbers button")
    public NumbersModal clickOnNumbersButton() {
        click.clickOnVisibleElement(numbersButton, 15);
        log.info("System menu bar Numbers button has been clicked.");

        return new NumbersModal();
    }

    @Step("Click on files button")
    public FilesModal clickOnFilesButton() {
        click.clickOnVisibleElement(filesButton, 15);
        log.info("System menu bar Files button has been clicked.");

        return new FilesModal();
    }
}
