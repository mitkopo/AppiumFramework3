package com.qa.test;

import com.qa.pages.ClipboardDemo;
import com.qa.pages.EchoPage;
import com.qa.pages.LoginPageApp;
import com.qa.pages.TheAppPage;

import com.qa.utils.BaseTest;
import lombok.extern.java.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.sound.sampled.Clip;
import java.util.List;

public class TheAppTests extends BaseTest {

    TheAppPage TheAppPage;
    EchoPage EchoPage;
    LoginPageApp LoginPageApp;

    ClipboardDemo ClipboardDemo;

    @BeforeMethod
    public void beforeMethod() {
        TheAppPage = new TheAppPage(driver);
        EchoPage = new EchoPage(driver);
        LoginPageApp = new LoginPageApp(driver);
        ClipboardDemo = new ClipboardDemo(driver);
    }

    @Test
    public void test2() {
        TheAppPage.test();


    }

    @Test
    public void test3() {
        //Boolean b = null;

        Assert.assertTrue(TheAppPage.compareText());
    }

    @Test
    public void checkEchoBox(){

        TheAppPage.clickEchoBox();
       EchoPage.waitForElementToBeClickable();
        EchoPage.clickInputField();
        EchoPage.enterText();
        EchoPage.clickSave();
        Assert.assertTrue(EchoPage.staticText());
        Assert.assertTrue(EchoPage.enteredText());
        TheAppPage.goBack();


    }

    @Test
    public void loginScreen(){
        TheAppPage.clickLoginScreen();
        Assert.assertTrue(LoginPageApp.isAPasswordField());
        LoginPageApp.enterUsername();
        LoginPageApp.enterPassword();
        LoginPageApp.clickLoginButton();
        Assert.assertTrue(LoginPageApp.isPopAlertDisplayed());
        LoginPageApp.closePopUp();
        TheAppPage.goBack();
    }

    @Test
    public void clipboardDemo(){
        TheAppPage.clickClipboardDemo();
        ClipboardDemo.setClipboardText();
        ClipboardDemo.pasteClipboardText();
        Assert.assertTrue(ClipboardDemo.isStaticTextDisplayed());
        Assert.assertTrue(ClipboardDemo.isClipboardTextDisplayed());

    }


}