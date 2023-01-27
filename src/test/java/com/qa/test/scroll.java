package com.qa.test;

import com.qa.BaseTest;
import com.qa.pages.APIDemos;
import com.qa.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class scroll extends BaseTest {
    APIDemos apiDemos;
    @BeforeMethod
    public void beforeMethod(){
        apiDemos = new APIDemos();
    }


    @Test
    public void test1(){
        apiDemos.clickGraphics();
    }

}
