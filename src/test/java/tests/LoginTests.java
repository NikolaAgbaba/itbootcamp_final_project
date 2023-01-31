package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private final String VALIDEMAIL = " admin@admin.com";
    private final String VALIDPASSWORD = "12345";

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void urlVerify() {
        Assert.assertTrue(loginPage.urlContains("/login"));
    }

    @Test
    public void inputTypesVerify() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getEmailField().getAttribute("type").equals("email"), "Email field does not have type \"email\"");
        softAssert.assertTrue(loginPage.getPasswordField().getAttribute("type").equals("password"), "Password field does not have type \"password\"");
        softAssert.assertAll();
    }

    @Test
    public void invalidEmailAndPasswordLogin() {
        SoftAssert softAssert = new SoftAssert();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        loginPage.login(email, password);
        softAssert.assertTrue(loginPage.getMessage().contains("User does not exists"), "Message for invalid user is not displayed");
        //softAssert.assertTrue(driver.getCurrentUrl().contains("/login"), "URL is not valid");
        softAssert.assertTrue(loginPage.urlContains("/login"), "URL is not valid");
        softAssert.assertAll();
    }

    @Test
    public void validEmailWrongPasswordLogin() {
        SoftAssert softAssert = new SoftAssert();
        String password = faker.internet().password();
        wait.until((ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/login")));
        loginPage.login(VALIDEMAIL, password);
        softAssert.assertTrue(loginPage.getMessage().contains("Wrong password"), "Message for wrong password is not displayed");
        softAssert.assertTrue(loginPage.urlContains("/login"), "URL is not valid");
        softAssert.assertAll();
    }

    @Test
    public void validDataLogin() {
        loginPage.login(VALIDEMAIL, VALIDPASSWORD);
        wait.until((ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/home")));
        Assert.assertTrue(homePage.urlContains("/home"));
        homePage.logout();
    }
}
