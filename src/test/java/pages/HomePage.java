package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(className = "btnAdminCities")
    private WebElement citiesPageButton;

    @FindBy(id = "list-item-124")
    private WebElement usersPageButton;

    @FindBy(className = "btnClose")
    private WebElement closeVerifyMessage;

    public void logout() {
        logoutButton.click();
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getSignUpMessage() {
        return signUpMessage;
    }

    public void goToCitiesPage() {
        adminPageButton.click();
        citiesPageButton.click();
    }

    public void goToMyProfile() {
        myProfilePageButton.click();
    }

    public void closeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btnClose")));
        closeVerifyMessage.click();
    }

    public WebElement getCloseButton() {
        return closeVerifyMessage;
    }
}

