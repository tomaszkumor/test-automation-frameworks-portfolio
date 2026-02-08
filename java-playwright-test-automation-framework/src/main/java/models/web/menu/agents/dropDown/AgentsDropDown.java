package models.web.menu.agents.dropDown;

import static utils.logger.Log4J.log;

public class AgentsDropDown extends AgentsDropDownSelectors {
    public AgentsDropDown() {
        check.isVisible(agentsDropDownSelector, "Agents drop down");
        log.info("Agents drop down has been displayed.");
    }
}
