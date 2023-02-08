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

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signUpPage = new SignUpPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.goToSignupPage();
    }

    @Test
    public void urlValidation() {
        signUpPage.urlContains("/signup");
    }

    @Test
    public void inputTypesValidation() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(signUpPage.getEmailField().getAttribute("type").equals("email"), "Email field does not have type \"email\"");
        softAssert.assertTrue(signUpPage.getPasswordField().getAttribute("type").equals("password"), "Password field does not have type \"password\"");
        softAssert.assertTrue(signUpPage.getConfirmPasswordField().getAttribute("type").equals("password"), "Password confirmation field does not have type \"password\"");
        softAssert.assertAll();
    }

    @Test
    public void existingUserSignUp() {
        SoftAssert softAssert = new SoftAssert();
        signUpPage.signUp("Test Test", "admin@admin.com", "123654", "123654");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div")));
        softAssert.assertTrue(signUpPage.getMessage().contains("E-mail already exists"), "Message is not displayed");
        softAssert.assertTrue(signUpPage.urlContains("/signup"));
        softAssert.assertAll();
    }

    @Test
    public void newUserSignUp() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String passwordConfirmation = password;
        signUpPage.signUp(name, email, password, passwordConfirmation);
        wait.until(ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/home"));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCloseButton()));
        String singUpMessage = homePage.getSignUpMessage().getText();
        homePage.closeMessage();
        System.out.println(singUpMessage);
        Assert.assertTrue(singUpMessage.contains("IMPORTANT: Verify your account"));
    }
}
