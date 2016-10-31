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
public class t4_DeleteEventStory {
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
        FirefoxProfile myProfile = new FirefoxProfile(new File(BrowserProfile));
        myProfile.setPreference("network.proxy.socks_port",9999);
        myProfile.setAlwaysLoadNoFocusLib(true);
        myProfile.setEnableNativeEvents(true);
        Serenity.useFirefoxProfile(myProfile);

        loginSteps.openLoginPage();
        loginSteps.enterLogin(email);
        loginSteps.enterPassword(password);
        loginSteps.clickEnter(driver);
        loginSteps.PageComplete(driver);

       /* headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyEventListing(driver);
        addItemSteps.openAddItemPageByClickingOnButtonInListing();
        addItemSteps.selectDemand();
        addItemSteps.enterBasicInfo(Title, DescriptionNull);
        addItemSteps.selectCategory1();
        addItemSteps.collapseBasicInfo();


        addItemSteps.collapseMainDetails();
        addItemSteps.VideoContent1(VideoContent1);
        addItemSteps.collapseContent();
        // loginSteps.Sleep(500);
        addItemSteps.clickSave(driver);
        //loginSteps.Sleep(100);
        addItemSteps.checkItemSaved(driver);
        loginSteps.PageComplete(driver);
        loginSteps.Sleep(20);*/
    }
    @After
    public void tearDown() {driver.quit();}
    /*************************************************************/

    /*************************************************************/
    @Test
    public void DeleteItemEvent()  {
        headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyEventListing(driver);
        loginSteps.PageComplete(driver);
        addItemSteps.openItemSettingsMenu();
        addItemSteps.selectDeleteMenu();
        //loginSteps.Sleep(100);
        addItemSteps.clickDeleteButton(driver);
        // loginSteps.Sleep(10);
        addItemSteps.checkItemDeleted(driver);
        loginSteps.PageComplete(driver);
    }
}
