package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")
    private WebElement signUpButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div")
    private WebElement message;

    @FindBy(className = "btnClose")
    private WebElement closeMessageButton;

    @FindBy(className = "v-messages__message")
    private WebElement requiredFieldMessage;

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void signUp(String name, String email, String password, String passwordConfirmation) {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(passwordConfirmation);
        signUpButton.click();
    }

    public String getMessage() {
        return message.getText();
    }

    public void closeMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btnClose")));
        closeMessageButton.click();
    }

    public String getRequiredFieldMessage(){
        return requiredFieldMessage.getText();
    }
}
