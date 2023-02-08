package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {

    //testing if user can go to home page without logging in first
    @Test
    public void homePageVisitWithoutAuthentication() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    //testing if user can go to profile page without logging in first
    @Test
    public void profilePageVisitWithoutAuthentication() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/profile");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    //testing if user can go to cities page without logging in first
    @Test
    public void adminCitiesPageVisitWithoutAuthentication() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/cities");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    //testing if user can go to admin page without logging in first
    @Test
    public void adminUsersPageVisitWithoutAuthentication() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/users");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }
}
