package mnassa.stories;

import mnassa.steps.AddGroupSteps;
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
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

/**
 * Created by olsa on 4/28/2016.
 */
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class t2_AddGroupPrivateStory {
    private String browserFilePath = "src/test/resources/browser.properties";
    private String BrowserProfile = PropertyLoader.getProperty(browserFilePath, "BrowserProfile");

    private String propertyFilePath = "src/test/resources/login.properties";
    //private String propertyFilePath = "src/test/resources/login_live.properties";
    private String email = PropertyLoader.getProperty(propertyFilePath, "email");
    private String password = PropertyLoader.getProperty(propertyFilePath, "password");

    private String propertyGroupPath = "src/test/resources/group.properties";
    private String NameGroupPrivateEn = PropertyLoader.getProperty(propertyGroupPath, "NameGroupPrivateEn");
    private String ShortDescriptionEn = PropertyLoader.getProperty(propertyGroupPath, "ShortDescriptionEn");
    private String NameGroupPrivateAr = PropertyLoader.getProperty(propertyGroupPath, "NameGroupPrivateAr");
    private String ShortDescriptionAr = PropertyLoader.getProperty(propertyGroupPath, "ShortDescriptionAr");
    private String wrongNameGroup = PropertyLoader.getProperty(propertyGroupPath, "wrongNameGroup");
    private String maxShortDescription = PropertyLoader.getProperty(propertyGroupPath, "maxShortDescription");
    private String NameGroupPrivateMax = PropertyLoader.getProperty(propertyGroupPath, "NameGroupPrivateMax");
    private String ShortDescriptionNull = PropertyLoader.getProperty(propertyGroupPath, "ShortDescriptionNull");
    private String lblNameGroupError = PropertyLoader.getProperty(propertyGroupPath, "lblNameGroupError");
    private String dscPrivateGroup = PropertyLoader.getProperty(propertyGroupPath, "dscPrivateGroup");
    private String dscPublicGroup = PropertyLoader.getProperty(propertyGroupPath, "dscPublicGroup");
    private String Skill = PropertyLoader.getProperty(propertyGroupPath, "Skill");
    private String Interest = PropertyLoader.getProperty(propertyGroupPath, "Interest");
    private String Skill_new = PropertyLoader.getProperty(propertyGroupPath, "Skill_new");
    private String Interest_new = PropertyLoader.getProperty(propertyGroupPath, "Interest_new");

    private String href_NameGroupEn = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupEn");
    private String href_maxNameGroup = PropertyLoader.getProperty(propertyGroupPath, "href_maxNameGroup");
    private String href_NameGroupAr = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupAr");
    private String href_NameGroupLimitedEn = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupLimitedEn");
    private String href_NameGroupLimitedAr = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupLimitedAr");
    private String href_NameGroupLimitedMax = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupLimitedMax");
    private String href_NameGroupPrivateEn = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupPrivateEn");
    private String href_NameGroupPrivateAr = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupPrivateAr");
    private String href_NameGroupPrivateMax = PropertyLoader.getProperty(propertyGroupPath, "href_NameGroupPrivateMax");

    @Managed
    WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    LoginSteps loginSteps;

    @Steps
    AddGroupSteps addGroupSteps;

    @Steps
    HeaderSteps headerSteps;

    @Steps
    AddItemSteps addItemSteps;

    @Before
    public void setup()throws IOException{
        FirefoxProfile myProfile = new FirefoxProfile(new File(BrowserProfile));
        myProfile.setPreference("network.proxy.socks_port",9999);
        myProfile.setAlwaysLoadNoFocusLib(true);
        myProfile.setEnableNativeEvents(true);
        Serenity.useFirefoxProfile(myProfile);

        loginSteps.openLoginPage();
        loginSteps.PageComplete(driver);

        loginSteps.enterLogin(email);
        loginSteps.enterPassword(password);
        loginSteps.clickEnter(driver);
        loginSteps.PageComplete(driver);
    }

    @After
    public void tearDown() {driver.quit();}
    /*************************************************************/
    @Test
    public void stage1_addGroupPrivateAr(){
        addItemSteps.clickAddButton(driver);
        addGroupSteps.clickAddGroupButton();
        loginSteps.PageComplete(driver);
        addGroupSteps.enterNameGroup(NameGroupPrivateAr, driver);
        addGroupSteps.enterShortDescription(ShortDescriptionAr);
        addGroupSteps.selectTypePrivate(driver, dscPrivateGroup);
        addGroupSteps.enterGroupInterest(Interest, driver);
        addGroupSteps.enterGroupSkill(Skill, driver);
        addGroupSteps.clickCreate(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);
        addGroupSteps.checkGroupInListing(href_NameGroupPrivateAr);
    }

    /*@Test
    public void addGroupLimitedMax(){
        addItemSteps.clickAddButton(driver);
        addGroupSteps.clickAddGroupButton();
        addGroupSteps.enterNameGroup(NameGroupLimitedMax,driver);
        addGroupSteps.enterShortDescription(maxShortDescription);
        addGroupSteps.selectTypeLimited(driver, dscLimitedGroup);
        addGroupSteps.enterGroupInterest(Interest, driver);
        addGroupSteps.enterGroupSkill(Skill, driver);
        addGroupSteps.clickCreate(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyMnassaPage(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.checkGroupInListing(NameGroupLimitedMax);

        headerSteps.openMenuProfile(driver);
        loginSteps.clickLogOut();
        loginSteps.PageComplete(driver);

        loginSteps.openLoginPage();
        //loginSteps.PageComplete(driver);
        loginSteps.enterLogin("obce@yopmail.com");
        loginSteps.enterPassword(password);
        loginSteps.clickEnter(driver);
        loginSteps.PageComplete(driver);
    }*/
    @Test
    public void stage2_addGroupPrivateName() {
        addItemSteps.clickAddButton(driver);
        addGroupSteps.clickAddGroupButton();
        loginSteps.PageComplete(driver);
        addGroupSteps.enterNameGroup(NameGroupPrivateEn, driver);
        addGroupSteps.enterShortDescription(ShortDescriptionNull);
        addGroupSteps.selectTypePrivate(driver, dscPrivateGroup);
        addGroupSteps.clickCreate(driver);

        loginSteps.PageComplete(driver);
        headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.checkGroupInListing(href_NameGroupPrivateEn);
    }

    @Test
    public void stage3_wrongNameGroupPrivate() {
        addItemSteps.clickAddButton(driver);
        addGroupSteps.clickAddGroupButton();
        loginSteps.PageComplete(driver);

        addGroupSteps.enterNameGroup(wrongNameGroup,driver);
        addGroupSteps.selectTypePrivate(driver, dscPrivateGroup);
        addGroupSteps.clickCreateError();
        addGroupSteps.checkNameGroupErrorPresent(driver, lblNameGroupError);
    }
    @Test
    public void stage4_editGroupPrivate() {
        headerSteps.openMyMnassaPage(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.openGroupWall(driver, href_NameGroupPrivateEn);
        //addGroupSteps.clickSettings();
        addGroupSteps.clickbtnSettingsEdit(driver);
        addGroupSteps.enterNameGroup(NameGroupPrivateEn,driver);
        addGroupSteps.enterShortDescription(ShortDescriptionEn);
        addGroupSteps.enterGroupInterest(Interest_new, driver);
        addGroupSteps.enterGroupSkill(Skill_new, driver);
        addGroupSteps.clickCreate(driver);
    }
    @Test
    public void stage5_editPrivateToPublic() {
        headerSteps.openMyMnassaPage(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.openGroupWall(driver, href_NameGroupPrivateEn);
        //addGroupSteps.clickSettings();
        addGroupSteps.clickbtnSettingsEdit(driver);
        addGroupSteps.selectTypePublic(driver, dscPublicGroup);
        addGroupSteps.clickCreate(driver);
    }
    @Test
    public void stage6_editDel_Skill_Interest() {
        headerSteps.openMyMnassaPage(driver);
        //loginSteps.PageComplete(driver);

        headerSteps.openMyGroupsListing(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.openGroupWall(driver, href_NameGroupPrivateEn);
        //addGroupSteps.clickSettings();
        addGroupSteps.clickbtnSettingsEdit(driver);
        loginSteps.PageComplete(driver);

        addGroupSteps.deleteGroupInterest(driver);
        addGroupSteps.deleteGroupSkill(driver);
        addGroupSteps.clickCreate(driver);
    }
}