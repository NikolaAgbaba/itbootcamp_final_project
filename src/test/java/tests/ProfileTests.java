package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.SignUpPage;

public class ProfileTests extends BaseTest{
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private HomePage homePage;
    private MyProfilePage myProfilePage;
    private String name;
    private String email;
    private String password;
    private String passwordConfirmation;
    private String editedName;
    private String editedNumber;
    private String editedCountry;
    private String editedTwitter;
    private String editedGitHub;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        loginPage = new LoginPage(driver, wait, faker);
        myProfilePage = new MyProfilePage(driver, wait, faker);
        signUpPage = new SignUpPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        passwordConfirmation = password;
        editedName = faker.name().fullName();
        editedNumber = faker.phoneNumber().phoneNumber();
        editedCountry = faker.country().name();
        editedTwitter = "https://" + faker.internet().url();
        editedGitHub = "https://" + faker.internet().url();
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.goToSignupPage();
        signUpPage.signUp(name, email, password, passwordConfirmation);
        signUpPage.closeMessage();
        homePage.goToMyProfile();
    }

    @Test
    public void editProfileTest(){
        SoftAssert softAssert = new SoftAssert();
        wait.until(ExpectedConditions.attributeToBe(myProfilePage.getNameField(), "value", name));
        myProfilePage.editUser1(editedName, editedNumber, editedCountry, editedTwitter, editedGitHub);
        softAssert.assertEquals(myProfilePage.getPhoneValue(), editedNumber, "Numbers are not the same");
        softAssert.assertEquals(myProfilePage.getCountryValue(), editedCountry, "Countries are not the same");
        softAssert.assertEquals(myProfilePage.getTwitterValue(), editedTwitter, "Twitter URL is not the same");
        softAssert.assertEquals(myProfilePage.getGitHubValue(), editedGitHub, "GitHub URL is not the same");
        softAssert.assertEquals(myProfilePage.getNameValue(), editedName, "Names are not the same");
        softAssert.assertAll();
    }
}
