package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPageApp extends BaseTest {

    private AppiumDriver driver;


    By username = By.xpath("//android.widget.EditText[@content-desc=\"username\"]");

    By password = By.xpath("//android.widget.EditText[@content-desc=\"password\"]");

    By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"loginBtn\"]/android.widget.TextView");

    By popUpAlert = By.xpath("/hierarchy/android.widget.FrameLayout");

    By okButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button");



    public LoginPageApp(AppiumDriver driver){
        this.driver = driver;
    }

    public void enterUsername(){

        String usernameText = "dummyUsername";

        selectElementAndSendKey(username, usernameText);

    }

    public void enterPassword(){
        String passwordText = "123456";
        selectElementAndSendKey(password, passwordText);
    }

    public boolean isAPasswordField(){

        try {
            Assert.assertTrue(getAttributePassword(password));
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public void clickLoginButton(){
        click(loginButton);
    }

    public boolean isPopAlertDisplayed(){
        try {
            Assert.assertTrue(isDisplayed(popUpAlert));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void closePopUp(){
        click(okButton);
    }


}
