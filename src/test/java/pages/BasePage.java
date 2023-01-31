package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;

    public BasePage() {

    }

    public BasePage(WebDriver driver, WebDriverWait wait, Faker faker) {
        this.driver = driver;
        this.wait = wait;
        this.faker = faker;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[1]")
    protected WebElement homePageButton;

    @FindBy (xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[2]")
    protected WebElement aboutPageButton;

    @FindBy (xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/div[1]/div/div/div/div[1]/div/div[3]")
    protected WebElement changeThemeButton;

    @FindBy (xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button")
    protected WebElement changeLanguageButton;

    public boolean urlContains(String urlPart){
        return driver.getCurrentUrl().contains(urlPart);
    }
}
