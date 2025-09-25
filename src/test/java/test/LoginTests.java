package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginButtonDisabledWhenFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.testLoginButtonDisabledWhenFieldAreEmpty(),
                "Login button should be disabled when fields are empty.");
    }

    @Test
    public void verifyPasswordMaskedAndUnmasked() {
        LoginPage loginPage = new LoginPage(driver);

        // Initially masked
        Assert.assertTrue(loginPage.testPasswordMaskedbutton(),
                "Password should be masked initially");

        // Toggle visibility
        loginPage.enterPassword("dummyPass");
        loginPage.togglePasswordVisibility();
        Assert.assertEquals(loginPage.getPasswordType(), "text",
                "Password should be visible after toggle");
    }

    @Test
    public void verifyInvalidLoginShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);

        String error = loginPage.testInvalidLoginShowErrorMsg("wrongUser", "wrongPass");
        System.out.println("Error Message: " + error);

        Assert.assertTrue(error.length() > 0, "Error message should be displayed for invalid login");
    }

    @Test
    public void verifyPresenceOfPageElements() {
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isPageElementDisplayed(By.id("formEmail")));
        Assert.assertTrue(loginPage.isPageElementDisplayed(By.id("formPassword")));
        Assert.assertTrue(loginPage.isPageElementDisplayed(By.xpath("//button[@type='submit']")));
    }
}

