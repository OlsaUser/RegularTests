package mnassa.pages;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@RunWith(SerenityRunner.class)
public class FacebookPage  extends PageObject {

    public void facebookLogin(WebDriver driver, String Email, String Password) {
        driver.get("https://www.facebook.com/");
        /*WebDriverWait wt = new WebDriverWait (driver, 200);
        wt.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));*/

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(Email);
        WebElement pass = driver.findElement(By.name("pass"));
        pass.sendKeys(Password);
        driver.findElement(By.id("u_0_l")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//span[text()='News Feed']"));
        } catch (WebDriverException e) {
            Assert.fail();
        }
    }

    public void confirmFbReg(WebDriver driver, String Email, String Password) {
        for(String winHandle : driver.getWindowHandles())
        {
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);
        }
        WebElement continueAs = driver.findElement(By.xpath("//button[@name='__CONFIRM__']"));
        continueAs.click();
        System.out.println(driver.getCurrentUrl());
    }
}
