package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[3]")
    private WebElement myProfilePageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]")
    private WebElement adminPageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div")
    private WebElement signUpMessage;

    public void logout(){
        logoutButton.click();
    }

    public WebElement getLogoutButton(){
        return logoutButton;
    }

    public WebElement getSignUpMessage() {
        return signUpMessage;
    }
}
