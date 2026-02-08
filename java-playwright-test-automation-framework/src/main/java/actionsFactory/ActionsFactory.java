package actionsFactory;

import actions.CheckActions;
import actions.ClickActions;
import actions.GetActions;
import actions.SendActions;

import static playwrightFactory.PlaywrightFactory.getPlaywrightInstance;
import static utils.logger.Log4J.log;

public class ActionsFactory {
    public CheckActions check;
    public ClickActions click;
    public GetActions get;
    public SendActions send;

    public ActionsFactory() {
        check = new CheckActions();
        click = new ClickActions();
        get = new GetActions();
        send = new SendActions();
    }

    public void refreshPage() {
        getPlaywrightInstance().getPage().reload();
        log.info("Page has been refreshed.");
    }
}