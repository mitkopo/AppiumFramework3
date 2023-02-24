package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class GeolocationDemoPage extends BaseTest {

    private AppiumDriver driver;
    By WhileUsingTheApp = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");


    public GeolocationDemoPage(AppiumDriver driver){
        this.driver= driver;
    }
    public void testLocationPermissons(){


    }


}
