package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {

    @AndroidFindBy (xpath= "//android.widget.EditText[@content-desc=\"Username\"]") private WebElement emailField;
    @AndroidFindBy (xpath ="//android.widget.EditText[@content-desc=\"Password\"]" ) private WebElement passwordField;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"Log In\"]") private WebElement logInCTA;


    public LoginPage enterEmail(String  email){
        sendKeys(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password){
        sendKeys(passwordField, password);
        return this;
    }

    public LoginPage pressLoginCTA(){
        click(logInCTA);
        return this;
    }

}
