package models.web.menu.agents.dropDown;

import static utils.logger.Log4J.log;

public class AgentsDropDown extends AgentsDropDownLocators {
    public AgentsDropDown() {
        browser.waitForPageLoaded();
        check.isElementDisplayed(agentsDropDown, 10);
        log.info("Agents drop down has been displayed.");
    }
}
