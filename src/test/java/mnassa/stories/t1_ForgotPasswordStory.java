package mnassa.stories;

import mnassa.steps.ForgotPasswordSteps;
import mnassa.steps.LoginSteps;
import mnassa.utils.PropertyLoader;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

@RunWith(SerenityRunner.class)
public class t1_ForgotPasswordStory {
    private String browserFilePath = "src/test/resources/browser.properties";
    private String BrowserProfile = PropertyLoader.getProperty(browserFilePath, "BrowserProfile");

    private String propertyFilePath = "src/test/resources/login.properties";
    //private String propertyFilePath = "src/test/resources/login_live.properties";
    private String email = PropertyLoader.getProperty(propertyFilePath, "email");
    private String wrongEmail = PropertyLoader.getProperty(propertyFilePath, "wrongEmail");
    private String emailOrg = PropertyLoader.getProperty(propertyFilePath, "email_organization");
    private String wrongEmailOrg = PropertyLoader.getProperty(propertyFilePath, "wrongEmailOrg");
    private String lblEmailError = PropertyLoader.getProperty(propertyFilePath, "lblEmailError");
    private String messageErrorEmail = PropertyLoader.getProperty(propertyFilePath, "messageErrorEmail");

    @Managed
    WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    LoginSteps loginSteps;

    @Steps
    ForgotPasswordSteps forgotPasswordSteps;

    @Before
    public void setup() {
        FirefoxProfile myProfile = new FirefoxProfile(new File(BrowserProfile));
        myProfile.setPreference("network.proxy.socks_port",9999);
        myProfile.setAlwaysLoadNoFocusLib(true);
        myProfile.setEnableNativeEvents(true);
        Serenity.useFirefoxProfile(myProfile);

        loginSteps.openLoginPage();
        loginSteps.openForgotPasswordPage();
        loginSteps.PageComplete(driver);
    }

    @After
    public void tearDown() { driver.quit();}

    @Test
    public void ForgotPasswordUser() throws IOException{
        forgotPasswordSteps.enterEmail(email);
        forgotPasswordSteps.clickConfirm();
        forgotPasswordSteps.checkIfEmailSucceed();
    }

    @Test
    public void ForgotPasswordOrganization() {
        forgotPasswordSteps.enterEmail(emailOrg);
        forgotPasswordSteps.clickConfirm();
        forgotPasswordSteps.checkIfEmailSucceed();
    }

    @Test
    public void checkEmailErrors() {
        forgotPasswordSteps.enterEmail(wrongEmail);
        forgotPasswordSteps.clickConfirm();
        forgotPasswordSteps.checkEmailErrorPresent(lblEmailError);

        forgotPasswordSteps.enterEmail(wrongEmailOrg);
        forgotPasswordSteps.clickConfirm();
        forgotPasswordSteps.checkEmailErrorPresent(messageErrorEmail);
    }
}
