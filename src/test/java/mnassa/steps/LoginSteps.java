package mnassa.steps;

import mnassa.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

import javax.mail.MessagingException;
import java.io.IOException;

public class LoginSteps extends ScenarioSteps {

    LoginPage loginPage;

    @Step
    public void openForgotPasswordPage() { loginPage.openForgotPasswordPage();}

    @Step
    public void openLoginPage() { loginPage.open();}

    @Step
    public void pressLoginLink() { loginPage.pressLoginLink();}

    @Step
    public void enterLogin(String email) {loginPage.enterLogin(email);}

    @Step
    public void clearLogin() {loginPage.clearLogin();}

    @Step
    public void enterPassword(String password) {loginPage.enterPassword(password);}

    @Step
    public void clearPassword() {loginPage.clearPassword();}

    @Step
    public void clickEnter(WebDriver driver) {loginPage.clickEnter(driver);}

    @Step
    public void clickEnterError() {loginPage.clickEnterError();}

    @Step
    public void clickLogOut() {loginPage.clickLogOut();}

    @Step
    public void Sleep(int time) {loginPage.Sleep(time);}

    @Step
    public void PageComplete(final WebDriver driver) {loginPage.PageComplete(driver);}

    @Step
    public void PageInteractive(final WebDriver driver) {loginPage.PageInteractive(driver);}

    @Step
    public void pageLoad(String args) throws IOException {loginPage.pageLoad(args);}

    @Step
    public void checkLoginErrorPresent(String text)throws MessagingException, IOException {loginPage.checkLoginErrorPresent(text);}

    @Step
    public void checkPasswordErrorPresent(String text) throws MessagingException {loginPage.checkPasswordErrorPresent(text);}
}
