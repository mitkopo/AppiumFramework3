package com.qa.utils;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    protected static String dateTime;
    InputStream inputStream;
    TestUtils utils;
    static Logger log = LogManager.getLogger(BaseTest.class.getName());
    public AppiumDriverLocalService service;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        System.out.println("screen recording started");
        ((CanRecordScreen) getDriver()).startRecordingScreen();

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

        Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String dir = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
                + File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();

        File videoDir = new File(dir);
        if (!videoDir.exists()) {
            videoDir.mkdirs();
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
        log.info("this is a info message");
        log.error("this is a error message");

        utils = new TestUtils();
        dateTime = utils.getDateTime();
        try {
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\mitpopov\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();
            instalApp(platformName, platformVersion, deviceName);
            DesiredCapabilities caps = new DesiredCapabilities();
//            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
//
//            caps.setCapability("platformName", platformName);
////        capabilities.setCapability(CapabilityType.BROWSER_NAME, BROWSER_NAME);
//            caps.setCapability("platformVersion", platformVersion);
//            caps.setCapability("deviceName", deviceName);
//            caps.setCapability("automationName", props.getProperty("androidAutomationName"));
//            caps.setCapability("app", props.getProperty("androidAppLocation"));

//            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
//     caps.setCapability("appActivity", props.getProperty("androidAppActivity"));

//            URL url = new URL(props.getProperty("appiumURL"));
//            driver = new AndroidDriver(url, caps);
            String sessionId = driver.getSessionId().toString();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.WAIT));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void instalApp(String platformName, String platformVersion, String deviceName) {


        props = new Properties();
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("automationName", props.getProperty("androidAutomationName"));
        caps.setCapability("app", props.getProperty("androidAppLocation"));
//        caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
//        caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
        URL url = null;
        try {
            url = new URL(props.getProperty("appiumURL"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver = new AndroidDriver(url, caps);

    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void waitForVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void click(By element) {
        waitForVisibility(element);
        driver.findElement(element).click();


    }

    public boolean isDisplayed(By element) {
        try {
            waitForVisibility(element);
            driver.findElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean getAttributePassword(By element) {
        try {
            waitForVisibility(element);
            driver.findElement(element).getAttribute("password");
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getText(By element) {
        waitForVisibility(element);
        return driver.findElement(element).getText();
    }

    public void sendKeys(By element, String txt) {
        waitForVisibility(element);
        driver.findElement(element).sendKeys(txt);
    }

    public void selectElementAndSendKey(By element, String txt) {
        waitForVisibility(element);
        driver.findElement(element).click();
        driver.findElement(element).sendKeys(txt);
    }

    public void getAttribute(By element, String attribute) {
        waitForVisibility(element);
        driver.findElement(element).getAttribute(attribute);
    }

    public WebElement scrollToElement(String txt) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"+".scrollIntoView(new UiSelector()"+".textContains(\""+ txt +"\").instance(0))"));
    }

    public static WebElement scrollToTextByAndroidUIAutomator(By text) {

        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));

    }

    public void reCaptchaHandler(){
        By reCaptchaLocator = By.xpath("//iframe[contains(@src, 'recaptcha')]");
        driver.findElement(reCaptchaLocator);


        driver.switchTo().frame(driver.findElement(reCaptchaLocator));


        By reCaptchaCheckboxLocator = By.xpath("//div[@class='recaptcha-checkbox-border']");
        driver.findElement(reCaptchaCheckboxLocator).click();


        driver.switchTo().defaultContent();


        By reCaptchaSuccessLocator = By.xpath("//div[@class='recaptcha-checkbox-checked']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until((ExpectedConditions.invisibilityOfElementLocated(reCaptchaSuccessLocator)));

    }


    public void switchToWebViewContext() {
        Set<String> contextNames = ((AndroidDriver)driver).getContextHandles();
        for (String contextName : contextNames) {
            if (contextName.contains("WEBVIEW")) {
                ((AndroidDriver)driver).context(contextName);
                break;
            }
        }
    }

    public void jsClick(By element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void setClipboardText(String txt) {
        ((HasClipboard) driver).setClipboardText(txt);
    }

    public void pasteClipboardText(By element) {

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

    }



    public boolean isClickable(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getDateTime() {
        return dateTime;
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}