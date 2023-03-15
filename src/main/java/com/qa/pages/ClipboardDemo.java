package com.qa.pages;

import com.qa.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ClipboardDemo extends BaseTest {

    private AppiumDriver driver;

    By messageInputField = By.xpath("//android.widget.EditText[@content-desc=\"messageInput\"]");

    By setClipboardText = By.xpath("//android.view.ViewGroup[@content-desc=\"setClipboardText\"]");

    By refreshClipboardText = By.xpath("//android.view.ViewGroup[@content-desc=\"refreshClipboardText\"]");

    By staticText = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView[1]");

    String enterClipboardText = "New entered clipboard text";

    By enteredClipboardText = By.xpath("//android.widget.TextView[@content-desc='" + enterClipboardText + "']");

    By test = By.xpath("//android.widget.TextView[@content-desc=\"entered clipboard text\"]");


    public ClipboardDemo(AppiumDriver driver) {
        this.driver = driver;
    }


    public void enterText() {
        selectElementAndSendKey(messageInputField, enterClipboardText);
    }

    public void setClipboardText() {
       setClipboardText(enterClipboardText);
    }

    public void pasteClipboardText(){
        click(messageInputField);
        pasteClipboardText(messageInputField);
    }

    public void clickRefreshClipboardText() {
        click(refreshClipboardText);
    }

    public boolean isStaticTextDisplayed() {
        try {
            Assert.assertTrue(isDisplayed(staticText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isClipboardTextDisplayed() {
        try {
            Assert.assertEquals(getText(enteredClipboardText), enterClipboardText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
