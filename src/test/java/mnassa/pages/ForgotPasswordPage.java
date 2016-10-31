package mnassa.pages;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.pages.PageObject;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertTrue;

//@DefaultUrl("http://synergybeta.devzone.dp.ua/en/#!login")
//@DefaultUrl("http://mnassa.com/en/#!login")
@RunWith(SerenityRunner.class)
public class ForgotPasswordPage extends PageObject {
    private final By fieldEmail = By.xpath("//div[@id='forgot-modal']//input[contains(@type, 'email')]");
    private final By btnEnter = By.id("do_request");
    private final By lblEmailError = By.xpath("//div[@class='error_message']");
    private final By SuccessPopup = By.xpath("//div[@id='forgot-modal-success']//div[@class='modal-content']//div[@class='text-center narrow-block-text']");

    public void enterEmail(String email) {
        find(fieldEmail).clear();
        find(fieldEmail).sendKeys(email);
    }

    public void clickConfirm() {
        element(btnEnter).click();
        }

    public void checkIfEmailSucceed() {
        WebDriverWait wt = new WebDriverWait (getDriver(), 200);
        wt.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup));
        wt.until(ExpectedConditions.textToBePresentInElementLocated(SuccessPopup, "The link to enter the site was successfully sent to "));
    }

    public void checkEmailErrorPresent(String text) {
    System.out.println("Validation message now: " + find(lblEmailError).getText());
    assertTrue("Wrong validation message!",find(lblEmailError).containsText(text));
    }
}
