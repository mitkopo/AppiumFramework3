package com.qa.utils;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
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

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    protected  static String dateTime;
    InputStream inputStream;
    TestUtils utils;
    static Logger log = LogManager.getLogger(BaseTest.class.getName());
    public AppiumDriverLocalService service;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        System.out.println("screen recording started");
        ((CanRecordScreen)getDriver()).startRecordingScreen();

    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        String media = ((CanRecordScreen)getDriver()).stopRecordingScreen();

        Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String dir = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
                + File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();

        File videoDir = new File(dir);
        if(!videoDir.exists()){
            videoDir.mkdirs();
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
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

    public void instalApp(String platformName, String platformVersion, String deviceName){


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

    public AppiumDriver getDriver(){
        return driver;
    }
    public void waitForVisibility(WebElement e){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click (WebElement e){
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e, String txt){
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void getAttribute(WebElement e, String attribute){
        waitForVisibility(e);
        e.getAttribute(attribute);
    }

    public WebElement scrollToElement(){
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));"));
    }

    public String getDateTime(){
        return  dateTime;
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}