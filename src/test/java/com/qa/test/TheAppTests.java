package com.qa.test;

import com.qa.pages.TheAppPage;

import com.qa.utils.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TheAppTests extends BaseTest {

    TheAppPage TheAppPage;

    @BeforeMethod
    public void beforeMethod() {
        TheAppPage = new TheAppPage(driver);
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


}