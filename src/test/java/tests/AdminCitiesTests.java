package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CitiesPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminCitiesTests extends BaseTest {
    private HomePage homePage;
    private CitiesPage citiesPage;
    private LoginPage loginPage;
    private String cityName;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
        citiesPage = new CitiesPage(driver, wait, faker);
        cityName = faker.country().capital();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.goToLoginPage();
        loginPage.login("admin@admin.com", "12345");
        homePage.goToCitiesPage();
    }

    @Test(priority = 1)
    public void urlAndLogoutValidation() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/admin/cities"), "URL is not valid");
        softAssert.assertTrue(homePage.getLogoutButton().isDisplayed(), "Logout button is not displayed");
        softAssert.assertAll();
        homePage.logout();
    }

    @Test(priority = 2)
    public void addCityTest() {
        citiesPage.addCity(cityName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        Assert.assertTrue(citiesPage.getAddedCityMessage().contains("Saved successfully"));
        homePage.logout();
    }

    @Test(priority = 3)
    public void editCityTest() {
        citiesPage.editCity(cityName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        Assert.assertTrue((citiesPage.getAddedCityMessage().contains("Saved successfully")));
        homePage.logout();
    }

    @Test(priority = 4)
    public void searchCityTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit")));
        Assert.assertTrue(citiesPage.getCities().contains(cityName));
        homePage.logout();
    }

    @Test(priority = 5)
    public void deleteCityTest() {
        SoftAssert softAssert = new SoftAssert();
        citiesPage.searchCity(cityName);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]"), 1));
        citiesPage.deleteCity();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        softAssert.assertTrue(citiesPage.getMessage().contains("Deleted successfully"), "Message is not valid");
        homePage.logout();
    }
}

