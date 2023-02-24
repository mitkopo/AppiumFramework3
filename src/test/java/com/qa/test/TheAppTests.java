package com.qa.test;

import com.qa.pages.*;

import com.qa.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TheAppTests extends BaseTest {

    TheAppPage TheAppPage;
    EchoPage EchoPage;
    LoginPageApp LoginPageApp;
    ClipboardDemo ClipboardDemo;
    WebviewDemoPage WebviewDemoPage;
    com.qa.pages.PhotoDemoPage PhotoDemoPage;

    @BeforeMethod
    public void beforeMethod() {
        TheAppPage = new TheAppPage(driver);
        EchoPage = new EchoPage(driver);
        LoginPageApp = new LoginPageApp(driver);
        ClipboardDemo = new ClipboardDemo(driver);
        WebviewDemoPage = new WebviewDemoPage(driver);
        PhotoDemoPage = new PhotoDemoPage(driver);
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

    @Test
    public void webviewDemo() {
        TheAppPage.clickWebviewDemo();
        WebviewDemoPage.enterWebPage();
        WebviewDemoPage.clickGoButton();
        driver.navigate().back();
        WebviewDemoPage.switchToWebViewContext();
        WebviewDemoPage.jsScroll();
        WebviewDemoPage.isMenuButtonClickable();
        WebviewDemoPage.enterEmail();
        WebviewDemoPage.clickSubscribeButton();
        reCaptchaHandler();
        //Add time to manually handle reCaptcha 
    }

    @Test
    public void photoDemo() throws InterruptedException {
        TheAppPage.clickphotoDemo();
        PhotoDemoPage.test();
    }


}