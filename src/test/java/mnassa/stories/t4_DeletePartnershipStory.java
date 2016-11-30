package mnassa.stories;

import mnassa.steps.AddItemSteps;
import mnassa.steps.HeaderSteps;
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
public class t4_DeletePartnershipStory {
    private String browserFilePath = "src/test/resources/browser.properties";
    private String BrowserProfile = PropertyLoader.getProperty(browserFilePath, "BrowserProfile");

    private String propertyFilePath = "src/test/resources/login.properties";
    //private String propertyFilePath = "src/test/resources/login_live.properties";
    private String email = PropertyLoader.getProperty(propertyFilePath, "email");
    private String password = PropertyLoader.getProperty(propertyFilePath, "password");

    private String propertyItemPath = "src/test/resources/item.properties";
    private String Title = PropertyLoader.getProperty(propertyItemPath, "Title");
    private String DescriptionNull = PropertyLoader.getProperty(propertyItemPath, "DescriptionNull");
    private String VideoContent1 = PropertyLoader.getProperty(propertyItemPath, "VideoContent1");

    @Managed
    WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    LoginSteps loginSteps;

    @Steps
    AddItemSteps addItemSteps;

    @Steps
    HeaderSteps headerSteps;

    @Before
    public void setup() throws IOException {
        driver.manage().window().maximize();

        loginSteps.openLoginPage();
        loginSteps.enterLogin(email);
        loginSteps.enterPassword(password);
        loginSteps.clickEnter(driver);
    }

    @After
    public void tearDown() {driver.quit();
    }

    @Test
    public void DeleteItemPartnership()  throws NoSuchFieldException{
        headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyPartnershipListing(driver);
        //loginSteps.PageComplete(driver);

        addItemSteps.openItemSettingsMenu();
        addItemSteps.selectDeleteMenu();
        addItemSteps.clickDeleteButton(driver);
        addItemSteps.checkItemDeleted(driver);
        loginSteps.PageComplete(driver);
    }
}
