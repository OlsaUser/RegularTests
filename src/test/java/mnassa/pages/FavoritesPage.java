package mnassa.pages;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.pages.PageObject;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

//@DefaultUrl("http://synergy.devzone.dp.ua/en/#!login")
//@DefaultUrl("http://mnassa.com/en/#!login")

@RunWith(SerenityRunner.class)
public class FavoritesPage extends PageObject {
    private final By menu = By.xpath("//div[@class='header-user']");
    private final By menuFavorites = By.xpath("//div[@class='header-user']/div[2]/a[1]");
    private final By iconFavorite = By.xpath("//button[@can-click='handle_favorite']");
    private final By emptyPlace = By.xpath("//div[@class='filter-results-count -has-results-list']");
    private final By FirstItemInFavorite = By.xpath("//div[@class='row']/div[1]/div[1]/a");
    private int count=0;

    public int openFavoritesPageAndCheck(WebDriver driver) {
        element(menuFavorites).click();
        WebDriverWait wt = new WebDriverWait (driver, 99);
        wt.until(ExpectedConditions.visibilityOfElementLocated(FirstItemInFavorite));
        count++;
        String lCount = String.valueOf(count);
        System.out.println("Count of items on Favorites: " + lCount);
        return count;
    }

    public void openMenu() {element(menu).click();}

    public void pressIcon_addToFavorite(WebDriver driver) {
        List<WebElement> starFavorites = getDriver().findElements(iconFavorite);

        for (WebElement star : starFavorites) {
           // if (star.getAttribute("class").equals("card-info-favorite "))
            if (!star.getAttribute("class").equals("card-info-favorite is-active"))
            {
                pressFavoriteIconOnItem(star, driver);
                break;
            }
        }
    }
    public void pressFavoriteIconOnItem(WebElement star, WebDriver driver) {
        star.click();
        find(emptyPlace).click();
        checkFavoriteIconWasPressed(star);
        openMenu();
        openFavoritesPageAndCheck(driver);
        System.out.println("Success!");
    }

    public void checkFavoriteIconWasPressed(WebElement star) {
        assertTrue("Favorite icon NOT changed status to 'in favorite'", star.getAttribute("class").equals("card-info-favorite is-active"));
    }
}


