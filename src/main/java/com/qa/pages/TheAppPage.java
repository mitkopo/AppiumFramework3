package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TheAppPage extends BaseTest {


    private AppiumDriver driver;


    By firstScreen = By.className("android.widget.TextView");

    By echoBox = By.xpath("//android.view.ViewGroup[@content-desc=\"Echo Box\"]");

    By loginScreen = By.xpath("//android.view.ViewGroup[@content-desc=\"Login Screen\"]");

    By clipBoardDemo = By.xpath("//android.view.ViewGroup[@content-desc=\"Clipboard Demo\"]");

    By webViewDemo = By.xpath("//android.view.ViewGroup[@content-desc=\"Webview Demo\"]");

    By listDemo = By.xpath("//android.view.ViewGroup[@content-desc=\"List Demo\"]");

    By photoDemo = By.xpath("//android.view.ViewGroup[@content-desc=\"Photo Demo\"]");

    By geolocationDemo = By.xpath("//android.view.ViewGroup[@content-desc=\"Geolocation Demo\"]");

    By verifyPhoneNumber = By.xpath("//android.view.ViewGroup[@content-desc=\"Verify Phone Number\"]");

    By backButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageButton");



    public TheAppPage(AppiumDriver driver) {
        this.driver = driver;

    }

    public void test() {
        driver.findElements(firstScreen);

        System.out.println(driver.findElements(firstScreen).size());



    }

    public List<String> getListItemsText() {

        List<WebElement> list1 = driver.findElements(firstScreen);
        List<String>list1text = new ArrayList<>();
        for(WebElement element: list1){
            list1text.add(element.getText());
        }

        return list1text;
    }

    public boolean compareText(){
        List<WebElement> listItems = driver.findElements(firstScreen);
        List<String> list2text = new ArrayList<>();
        for (WebElement listItem : listItems) {
            list2text.add(listItem.getText());
    }
        return list2text.equals(getListItemsText());
}

    public void clickEchoBox(){

        click(echoBox);
    }

    public void goBack() {
        driver.findElement(backButton).click();
    }

    public void clickLoginScreen(){
        click(loginScreen);
    }

    public void clickClipboardDemo(){
        click(clipBoardDemo);
    }

}