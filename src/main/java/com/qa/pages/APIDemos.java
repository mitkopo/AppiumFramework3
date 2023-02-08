package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class APIDemos extends BaseTest {

   By graphics = By.xpath("//android.widget.TextView[@content-desc=\"Grsaphics\"]");

    By alphaBitmap = By.xpath("//android.widget.TextView[@content-desc=\"AlphaBitmap\"]");

    By pathEfects= By.xpath ("//android.widget.TextView[@content-desc=\"PathEffects\"]");



    public APIDemos clickGraphics(){
        driver.findElement(graphics).click();
        return this;


    }



}
