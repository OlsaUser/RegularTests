package mnassa.steps;

import mnassa.pages.NewMessagePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

/**
 * Created by olsa on 5/10/2016.
 */
public class NewMessageSteps extends ScenarioSteps {

    NewMessagePage newMessagePage;

    @Step
    public void SelectChatWith() {newMessagePage.SelectChatWith();}

    @Step
    public void EnterMessage(String value) {newMessagePage.EnterMessage(value);}

    @Step
    public void PressSendButton(WebDriver driver ) {newMessagePage.PressSendButton(driver);}

    @Step
    public void checkMessageExists(String message, WebDriver driver) {newMessagePage.checkMessageExists(message, driver);}

    @Step
    public void ViewerPageElements(WebDriver driver) {newMessagePage.ViewerPageElements( driver);}
}