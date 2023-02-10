package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TheAppPage {


    private AppiumDriver driver;


    By firstScreen = By.className("android.widget.TextView");


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
}