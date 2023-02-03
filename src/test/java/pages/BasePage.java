package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[2]")
    protected WebElement aboutPageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/div[1]/div/div/div/div[1]/div/div[3]")
    protected WebElement changeThemeButton;

    @FindBy(className = "btnLocaleActivation")
    protected WebElement changeLanguageButton;

    @FindBy(className = "btnEN")
    protected WebElement englishLanguage;

    @FindBy(className = "btnES")
    protected WebElement spanishLanguage;

    @FindBy(className = "btnFR")
    protected WebElement frenchLanguage;

    @FindBy(className = "btnCN")
    protected WebElement chineseLanguage;

    @FindBy(className = "btnUA")
    protected WebElement ukrainianLanguage;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div[1]/div[1]/h1")
    protected WebElement header;

    public boolean urlContains(String urlPart) {
        return driver.getCurrentUrl().contains(urlPart);
    }

    public void changeLanguage(Languages languages) {
        changeLanguageButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("menuable__content__active")));
        switch (languages) {
            case EN:
                englishLanguage.click();
                break;
            case ES:
                spanishLanguage.click();
                break;
            case FR:
                frenchLanguage.click();
                break;
            case CN:
                chineseLanguage.click();
                break;
            case UA:
                ukrainianLanguage.click();
                break;
        }
    }

    public String getHeader() {
        return header.getText();
    }
}
