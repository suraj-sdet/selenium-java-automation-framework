package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Example test. Update credentials and assertions for your app.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Verify login with invalid credentials shows error")
    public void invalidLoginShowsError() {
        LoginPage loginPage = new LoginPage(driver);

        // update these to values appropriate for your app or pass as system properties
        String user = System.getProperty("test.user", "invaliduser");
        String pass = System.getProperty("test.pass", "invalidpass");

        loginPage.login(user, pass);

        // example assertion - update to match your app behavior
        String err = loginPage.getErrorMessage();
        Assert.assertTrue(err != null && !err.isEmpty(), "Expected an error message on invalid login");
    }
}
