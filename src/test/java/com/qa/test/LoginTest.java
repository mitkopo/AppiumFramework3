package com.qa.test;

import com.qa.utils.BaseTest;
import com.qa.pages.LoginPage;
import io.appium.java_client.AppiumDriver;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    InputStream datais;
    JSONObject loginUsers;

        @BeforeClass
        public void beforeClass() throws IOException {

            try {
                String dataFileNam = "data/loginUsers.json";
                datais = getClass().getClassLoader().getResourceAsStream(dataFileNam);
                JSONTokener tokener = new JSONTokener(datais);
                loginUsers = new JSONObject(tokener);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(datais != null){
                    datais.close();
                }
            }

        }

    @AfterClass
    public void afterClass(){

    }

    @BeforeMethod
    public void beforeMethod(){
            loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void afterMethod(){

    }
    @Test
    public void logIn(){
        loginPage.enterEmail(loginUsers.getJSONObject("logIn").getString("email"));
        loginPage.enterPassword(loginUsers.getJSONObject("logIn").getString("password"));
        loginPage.pressLoginCTA();
    }

    @Test
    public void logInFailed(){
        loginPage.enterEmail("krokodilovj@gmail.com");
        loginPage.enterPassword("Bogdanci1@");
        loginPage.pressLoginCTA();
    }
}
