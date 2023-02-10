package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {

    private AppiumDriver driver;

    By emailField = By.xpath("//android.widget.EditText[@content-desc=\"Username\"]");
    ;
    By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"Password\"]");
    By logInCTA = By.xpath("//android.view.ViewGroup[@content-desc=\"Log In\"]");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }


    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);

    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);

    }

    public LoginPage pressLoginCTA() {
        driver.findElement(logInCTA).click();
        return this;
    }

}
