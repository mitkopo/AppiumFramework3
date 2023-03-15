package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EchoPage extends BaseTest {

    private AppiumDriver driver;


    By inputField = By.xpath("//android.widget.EditText[@content-desc=\"messageInput\"]");

    By saveButton = By.xpath("//android.view.ViewGroup[@content-desc=\"messageSaveBtn\"]/android.widget.TextView");

    By staticText = By.xpath("//android.view.ViewGroup[@content-desc=\"messageSaveBtn\"]/android.widget.TextView");
    String inputText = "test";


    By enteredText = By.xpath("//android.widget.TextView[@content-desc='" + inputText + "']");

    public EchoPage(AppiumDriver driver) {

        this.driver = driver;
    }

    public void clickInputField() {

        driver.findElement(inputField).click();
    }

    public void waitForElementToBeClickable() {
        if (isClickable(inputField)) ;
    }

    public void enterText() {
        sendKeys(inputField, inputText);
    }

    public void clickSave() {
        click(saveButton);
    }

    public boolean staticText() {
        try {
            Assert.assertTrue(isDisplayed(staticText));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean enteredText() {

        try {
            Assert.assertEquals(getText(enteredText), inputText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

