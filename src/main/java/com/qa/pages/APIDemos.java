package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class APIDemos extends BaseTest {

    @AndroidFindBy(xpath ="//android.widget.TextView[@content-desc=\"Grsaphics\"]") private WebElement graphics;

    @AndroidFindBy(xpath ="//android.widget.TextView[@content-desc=\"AlphaBitmap\"]") private WebElement alphaBitmap;

    @AndroidFindBy(xpath ="//android.widget.TextView[@content-desc=\"PathEffects\"]") private WebElement pathEfects;



    public APIDemos clickGraphics(){
        click(graphics);
        return this;


    }



}
