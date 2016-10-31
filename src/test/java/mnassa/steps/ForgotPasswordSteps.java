package mnassa.steps;

import mnassa.pages.ForgotPasswordPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ForgotPasswordSteps extends ScenarioSteps {

    ForgotPasswordPage forgotPasswordPage;

    @Step
    public void enterEmail(String email) {
        forgotPasswordPage.enterEmail(email);
    }

    @Step
    public void clickConfirm() {
        forgotPasswordPage.clickConfirm();
    }

    @Step
    public void checkIfEmailSucceed() {
        forgotPasswordPage.checkIfEmailSucceed();
    }

    @Step
    public void checkEmailErrorPresent(String text) {forgotPasswordPage.checkEmailErrorPresent(text);}
}
