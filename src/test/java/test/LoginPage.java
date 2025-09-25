package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By userIdInput = By.id("formEmail");    // adjust locator if different
    private By passwordInput = By.id("formPassword");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By passwordToggle = By.xpath("//img[@alt='Password Not Visible']");
    private By errorMsg = By.xpath("//p[contains(.,'Error')]"); // adjust locator
    //private By pageTitle = By.tagName("title");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUserId(String user) {
        driver.findElement(userIdInput).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordInput).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }

    public String getPasswordType() {
        return driver.findElement(passwordInput).getAttribute("type"); // "password" or "text"
    }

    public void togglePasswordVisibility() {
        driver.findElement(passwordToggle).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    public boolean isPageElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    // --- Test Methods for requirements ---
    public boolean testLoginButtonDisabledWhenFieldAreEmpty() {
        return !isLoginButtonEnabled();
    }

    public boolean testPasswordMaskedbutton() {
        String type = getPasswordType();
        return type.equals("password");
    }

    public String testInvalidLoginShowErrorMsg(String user, String pass) {
        enterUserId(user);
        enterPassword(pass);
        clickLogin();
        return getErrorMessage();
    }
}

