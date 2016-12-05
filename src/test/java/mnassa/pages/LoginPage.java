package mnassa.pages;

//import static org.junit.Assert.fail;
//import com.ProjectTestCom.utils.DriverScripts;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

//@DefaultUrl("http://synergybeta.devzone.dp.ua/en/")
@DefaultUrl("http://mnassa.com/en/")
@RunWith(SerenityRunner.class)
public class LoginPage extends PageObject {
    private final By btnForgotPassword = By.xpath("//a[@class='link-forgot-password']");
    private final By fieldEmail = By.id("login_email");
    private final By fieldPassword = By.id("login_password");
    private final By btnEnter = By.id("do_login");
    private final By lblLoginError = By.xpath("//div[@class='error_message']");
    private final By lblPasswordError = By.xpath("//div[@class='error_message']");
    private final By LogOut = By.xpath("//div[@class='header-user']/div[2]/a[3]");

    private final By Button = By.xpath("//button[@class='header-control -auth']");
    private final By LoginLink = By.xpath("//button[@data-target='#auth-tab-login']");

    String parentWindowHandler;

    //Visible elements
    private final By HomeContent = By.xpath("//div[@class='feed container']");
    private final By Counter = By.xpath("//span[@class='wall-post-length']");

    public void openForgotPasswordPage() {
        element(btnForgotPassword).click();
        parentWindowHandler = getDriver().getWindowHandle();
    }

    public void enterLogin(String email) {
        find(Button).click();
        find(LoginLink).click();
        if (find(fieldEmail).isEnabled())
        {
            find(fieldEmail).waitUntilPresent();
        }
        WebDriverWait wt = new WebDriverWait (getDriver(), 200);
        wt.until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));
        find(fieldEmail).sendKeys(email);
    }

    public void enterPassword(String password) {
        clearPassword();
        find(fieldPassword).sendKeys(password);
    }

    public void clickEnter(WebDriver driver){
        element(btnEnter).click();
        WebDriverWait wt = new WebDriverWait (driver, 200);
        wt.until(ExpectedConditions.visibilityOfElementLocated(Counter));
        //wt.until(ExpectedConditions.presenceOfElementLocated(HomeContent));
        //find(Counter).waitUntilVisible();
    }

    public void clickLogOut() {element(LogOut).click();}

    public void clickEnterError() {element(btnEnter).click();}

    public void clearLogin() {
        find(fieldEmail).clear();
    }

    public void clearPassword() {
        find(fieldPassword).clear();
    }

    public void checkPasswordErrorPresent(String text) throws MessagingException{
        try {
            //System.out.println("Validation message now: " + find(lblPasswordError).getText());
            Assert.assertEquals(text, find(lblPasswordError).getText());
        } catch (Error  NameError) {
            NameError :
            System.out.println("Account is going to be Blocked...");
            System.out.println("Validation message now: " + getDriver().findElement(lblPasswordError).getText());
        }
    }

    public void checkLoginErrorPresent(String text) throws MessagingException, IOException{
        //System.out.println("Validation message now: " + find(lblLoginError).getText());
        Assert.assertEquals(text,find(lblLoginError).getText());
    }

    /* Common utils*/

    public void PageComplete(final WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 600);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }

    public void PageInteractive(final WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 200);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState"
                ).equals("interactive");
            }
        });
    }

    public void Sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swithToModalWindow() {
        String modalWindowHandle = "";
        Set<String> handles = getDriver().getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentWindowHandler))
                modalWindowHandle = handle;
        }
        getDriver().switchTo().window(modalWindowHandle);
    }

    public void pageLoad(String args) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        final Long loadEventEnd;
        final Long responseStart;
        final Long responseEnd;
        final Long requestStart;
        final Long indexOfLastResources;
        final Long Request;
        final Long Response;
        final Long fulLoad;

        //http://w3c.github.io/navigation-timing/
        //This attribute must return the time when the load event of the current document is completed
        loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");

        //This attribute must return the time immediately after the user agent receives the first byte of the response from the server
        responseStart = (Long) js.executeScript("return window.performance.timing.responseStart;");

        //This attribute must return the time immediately after the user agent receives the last byte of the current document or immediately before the transport connection is closed, whichever comes first
        responseEnd = (Long) js.executeScript("return window.performance.timing.responseEnd;");

        //This attribute must return the time immediately before the user agent starts requesting the current document from the server
        requestStart = (Long) js.executeScript("return window.performance.timing.requestStart;");

        indexOfLastResources = (Long) js.executeScript("return window.performance.getEntriesByType('resource').length");

        Request = responseStart - requestStart;
        Response = responseEnd - responseStart;
        fulLoad = loadEventEnd - requestStart;

     /*   System.out.println("Load Event End " + loadEventEnd);
        System.out.println("Page Load Time is " + fulLoad + " mseconds.");
        System.out.println("TimeFirstByte " + responseTime + " serverTime " + serverTime);
        System.out.println("Requests " + indexOfLastResources);*/

        /*String TextRequest =  "Request  : " + Request + "| ";
        String TextResponse = "Response : " + Response + "| ";
        String TextfulLoad =  "Full Load: " + fulLoad + "| ";*/

        String TextRequest =  Request + "| ";
        String TextResponse = Response + "| ";
        String TextfulLoad =  fulLoad + "| ";

        String TotalRequests = "Total Requests:" + indexOfLastResources + " ";
        String page = "Page: " + args + "| ";

        String baseUrl = getDriver().getCurrentUrl();
        //System.out.println("URL is " + baseUrl);
        //System.out.println("URL is " + baseUrl.toString());

        //File file = new File("C:/Users/olsa/IdeaProjects/ProjectTestCom/Report.txt");
        File file = new File("D:/Report.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        // FileWriter fr = null;
        try {
            // fr = new FileWriter(file);
            fw.write("\r\n");
            fw.write(baseUrl);
            fw.write("\r\n");
            fw.write(page);
            fw.write(TextRequest);
            fw.write(TextResponse);
            fw.write(TextfulLoad);
            //fw.write(TotalRequests);
            fw.write("\r\n");
            fw.write("\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



