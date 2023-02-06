package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyProfilePage extends BasePage{

    public MyProfilePage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy (id = "name")
    private WebElement nameField;

    @FindBy (id = "phone")
    private WebElement phoneField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(className = "v-select-list")
    private WebElement citiesList;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "urlTwitter")
    private WebElement twitterField;

    @FindBy(id = "urlGitHub")
    private WebElement gitHubField;

    @FindBy(className = "btnSave")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")
    private WebElement message;

//    public void editUser(){
//        nameField.clear();
//        nameField.sendKeys(faker.name().fullName());
//        phoneField.click();
//        phoneField.sendKeys(faker.phoneNumber().phoneNumber());
//        cityField.click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-select-list")));
//        citiesList.click();
//        countryField.click();
//        countryField.sendKeys(faker.country().name());
//        twitterField.sendKeys("https://" + faker.internet().url());
//        gitHubField.click();
//        gitHubField.sendKeys("https://" + faker.internet().url());
//        saveButton.click();
//    }

    public void editUser1(String name, String phone, String country, String twitter, String gitHub){
        nameField.sendKeys(Keys.CONTROL + "a");
        nameField.sendKeys(Keys.DELETE);
        nameField.sendKeys(name);
        phoneField.click();
        phoneField.sendKeys(phone);
        cityField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-select-list")));
        cityField.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        countryField.sendKeys(country);
        twitterField.sendKeys(twitter);
        gitHubField.sendKeys(gitHub);
        saveButton.click();
    }

    public String getMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")));
        return message.getText();
    }

    public String getNameValue(){
        return nameField.getAttribute("value");
    }

    public String getPhoneValue(){
        return phoneField.getAttribute("value");
    }

    public String getCityValue(){
        return cityField.getAttribute("value");
    }

    public String getCountryValue(){
        return countryField.getAttribute("value");
    }

    public String getTwitterValue(){
        return twitterField.getAttribute("value");
    }

    public String getGitHubValue(){
        return gitHubField.getAttribute("value");
    }

    public WebElement getNameField(){
        return nameField;
    }
}
