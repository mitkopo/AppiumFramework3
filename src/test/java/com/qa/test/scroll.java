package com.qa.test;

import com.qa.utils.BaseTest;
import com.qa.pages.APIDemos;
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
