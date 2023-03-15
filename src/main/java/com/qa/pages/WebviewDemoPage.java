package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class WebviewDemoPage extends BaseTest {

    private AppiumDriver driver;

    By webpbageInput = By.xpath("//android.widget.EditText[@content-desc=\"urlInput\"]");

    By goButton = By.xpath("//android.view.ViewGroup[@content-desc=\"navigateBtn\"]");

    By emailInputField = By.xpath("//*[@type=\"email\"]");

    By menuButton = By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/a/img");

    By subscribeButton = By.xpath("//*[@class=\"button_button__8r38R\"]");

    public WebviewDemoPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void enterWebPage() {
        selectElementAndSendKey(webpbageInput, "https://appiumpro.com");
    }

    public void clickGoButton() {
        click(goButton);
    }

    public void switchToWebView() {
        switchToWebViewContext();
    }

    public void jsScroll(){
        WebElement element = getDriver().findElement(By.xpath("//*[@class=\"subscribe_subscribe__9ZPV0\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickMenuButton() {
        click(menuButton);
    }

    public void isMenuButtonClickable() {
        isClickable(menuButton);
    }

    String email= "mitkopopov4qa@gmail.com";
    public void enterEmail(){
        selectElementAndSendKey(emailInputField,email);
//        driver.navigate().back();
    }

    public void  clickSubscribeButton(){
            click(subscribeButton);
    }

}
