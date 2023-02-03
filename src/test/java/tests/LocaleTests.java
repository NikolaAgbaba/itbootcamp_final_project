package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Languages;
import pages.LoginPage;

public class LocaleTests extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @Test
    public void englishLanguageTest() {
        homePage.changeLanguage(Languages.EN);
        Assert.assertTrue(homePage.getHeader().equals("Landing"));
    }

    @Test
    public void spanishLanguageTest() {
        homePage.changeLanguage(Languages.ES);
        Assert.assertEquals(homePage.getHeader(), "Página de aterrizaje");
    }

    @Test
    public void frenchLanguageTest() {
        homePage.changeLanguage(Languages.FR);
        Assert.assertEquals(homePage.getHeader(), "Page d'atterrissage");
    }

    @Test
    public void chineseLanguageTest() {
        homePage.changeLanguage(Languages.CN);
        Assert.assertEquals(homePage.getHeader(), "首页");
    }

    @Test
    public void ukrainianLanguageTest() {
        homePage.changeLanguage(Languages.UA);
        Assert.assertEquals(homePage.getHeader(), "Лендінг");
    }
}
