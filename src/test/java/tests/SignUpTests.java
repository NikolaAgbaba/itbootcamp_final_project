package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

public class SignUpTests extends BaseTest {
    private SignUpPage signUpPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private String name;
    private String email;
    private String password;
    private String passwordConfirmation;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signUpPage = new SignUpPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        passwordConfirmation = password;
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.goToSignupPage();
    }

    //testing if page url is valid
    @Test
    public void urlValidation() {
        signUpPage.urlContains("/signup");
    }

    //testing if input fields have exact types
    @Test
    public void inputTypesValidation() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(signUpPage.getEmailField().getAttribute("type").equals("email"), "Email field does not have type \"email\"");
        softAssert.assertTrue(signUpPage.getPasswordField().getAttribute("type").equals("password"), "Password field does not have type \"password\"");
        softAssert.assertTrue(signUpPage.getConfirmPasswordField().getAttribute("type").equals("password"), "Password confirmation field does not have type \"password\"");
        softAssert.assertAll();
    }

    //testing if user can sign up using email that already exists
    @Test
    public void existingUserSignUp() {
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp("Test Test", "admin@admin.com", "123654", "123654");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div")));
        softAssert.assertTrue(signUpPage.getMessage().contains("E-mail already exists"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"));
        softAssert.assertAll();
    }

    //verify sign up without providing username in the username field
    @Test
    public void validDataWithoutUsernameSignUp(){
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp("", email, password, passwordConfirmation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("This field is required"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up without providing email in the email field
    @Test
    public void validDataWithoutEmailSignUp(){
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp(name, "", password, passwordConfirmation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("This field is required"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up without providing password in the password field
    @Test
    public void validDataWithoutPasswordSignUp(){
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp(name, email, "", password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("This field is required"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up without providing password confirmation in the password confirmation field
    @Test
    public void validDataWithoutPasswordConfirmationSignUp(){
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp(name, email, password, "");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("This field is required"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up while providing an invalid email format
    @Test
    public void validDataWithInvalidEmail(){
        SoftAssert softAssert = new SoftAssert();
        String invalidEmail = faker.internet().password();
        signUpPage.signUp(name, invalidEmail, password, passwordConfirmation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("Valid email is required"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up while providing too short password
    @Test
    public void validDataWithTooShortPassword(){
        SoftAssert softAssert = new SoftAssert();
        String shortPassword = "1234";
        signUpPage.signUp(name, email, shortPassword, shortPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("This field must have no less than 5 characters"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //verify sign up while providing invalid password confirmation
    @Test
    public void validDataWithInvalidPasswordConfirmation(){
        SoftAssert softAssert = new SoftAssert();
        String confirmedPassword = faker.internet().password();
        signUpPage.signUp(name, email, password, confirmedPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
        softAssert.assertTrue(signUpPage.getRequiredFieldMessage().contains("Passwords must match"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"), "URL is not valid");
        softAssert.assertAll();
    }

    //testing sign up option with valid data
    @Test
    public void newUserSignUp() {
        signUpPage.signUp(name, email, password, passwordConfirmation);
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.getSignUpMessage(), "IMPORTANT: Verify your account"));
        String singUpMessage = homePage.getSignUpMessage().getText();
        homePage.closeMessage();
        Assert.assertTrue(singUpMessage.contains("IMPORTANT: Verify your account"));
    }
}
