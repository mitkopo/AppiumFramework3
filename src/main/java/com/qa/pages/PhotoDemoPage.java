package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PhotoDemoPage extends BaseTest {

    private AppiumDriver driver;

    By imageText = By.id("android:id/message");


//    By imageOne = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView");

//    By imageTwo = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ImageView");

//    By imageThree = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ImageView");

//    By imageFour = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.ImageView");

//    By imageFive = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.widget.ImageView");

//    By imageSix = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ImageView");

    By images = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup");

    By okButton = By.id("android:id/button1");

    public PhotoDemoPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public List<String> imagesDescriptons() {
        List<String> imagesDescripton = new ArrayList<>();
        imagesDescripton.add("This is a picture of: The Vancouver skyline at sunrise");
        imagesDescripton.add("This is a picture of: Imposing mountains and West Vancouver");
        imagesDescripton.add("This is a picture of: 2 tanker ships with snowy mountains in the background");
        imagesDescripton.add("This is a picture of: A long thin cloud below mountain level");
        imagesDescripton.add("This is a picture of: English bay with snowy mountains");
        imagesDescripton.add("This is a picture of: Wispy clouds in a blue sky");
        return imagesDescripton;
    }


    public boolean test() throws InterruptedException {

        Boolean b = null;
        List<WebElement> imagess = driver.findElements(images);


        for (WebElement image : imagess) {
            Thread.sleep(1000);
            clickWebElement(image);
            String text = getText(imageText);
            click(okButton);
            for (String imageD : imagesDescriptons()) {
                if (text.equals(imageD)) {
                    System.out.println("lajna");

                    b = true;
                    break;
                }
            }
        }
        return b;
    }
}







