package com.simple.automation.autotest.web.common;

import com.simple.automation.autotest.common.Config;
import com.simple.automation.autotest.common.Context;
import com.simple.automation.autotest.common.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.text.StrSubstitutor;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Simple Liang on 9/6/17.
 */
public class WebDriverWrapper {
    private static final long DEFAULT_TIMEOUT_SECONDS = Config.getLong("findElementTimeOutSeconds", 1L);
    private WebDriver webDriver;
    private WebWaitUtil webDriverWait;

    public WebDriverWrapper(){

        webDriver = launchWebDriver();
        webDriverWait = new WebWaitUtil(webDriver, DEFAULT_TIMEOUT_SECONDS);
    }

    private static WebDriver launchWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String browser = Config.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
                return launchChromeDriver(capabilities);
            case "firefox":
                return launchFireFoxDriver(capabilities);
            case "safari":
                return launchSafariDriver(capabilities);
            case "ie":
                return launchIeDriver(capabilities);
            case "edge":
                return launchEdgeDriver(capabilities);
            default:
                return null;
        }
    }

    private static DesiredCapabilities getGenericCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_WEB_STORAGE, false);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        return capabilities;
    }

    private static WebDriver launchChromeDriver(DesiredCapabilities capabilities) {
        /*
        // Configure Chrome driver settings
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        */

        // set chrome driver path
        String property = "webdriver.chrome.driver";
        if(System.getProperty(property) == null) {
            System.setProperty(property, getDriverPath());
        }

        // put chrome driver into context
        String key = "chromedriver";
        if(!Context.has(key)) {
            Context.put(key, new ChromeDriver(capabilities));
        }

        // get chrome driver from context
        return (WebDriver)Context.get(key);
    }

    private static String getDriverPath(){
        Map map = new HashMap();
        map.put("browser", Config.getProperty("browser"));
        map.put("driverVersion", Config.getProperty("driverVersion"));

        String suffix = "";
        if(SystemUtils.IS_OS_WINDOWS) {
            suffix = "win32";
        } else if(SystemUtils.IS_OS_MAC) {
            suffix = "mac64";
        } else if(SystemUtils.IS_OS_LINUX) {
            suffix = "linux" + (SystemUtils.OS_ARCH.endsWith("64") ? "64" : "32");
        }
        map.put("suffix", suffix);

        StrSubstitutor substitutor = new StrSubstitutor(map);
        String driverPath = substitutor.replace(Config.getProperty("driverPath"));

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String path = new File(loader.getResource(driverPath).getFile()).getAbsolutePath();
        return path;
    }

    private static WebDriver launchFireFoxDriver(DesiredCapabilities capabilities) {
        // TODO
        return null;
    }

    private static WebDriver launchSafariDriver(DesiredCapabilities capabilities) {
        // TODO
        return null;
    }

    private static WebDriver launchIeDriver(DesiredCapabilities capabilities) {
        // TODO
        return null;
    }

    private static WebDriver launchEdgeDriver(DesiredCapabilities capabilities) {
        // TODO
        return null;
    }

    public WebDriver getWebDriver(){return webDriver;}

    public WebElementWrapper findElement(By locator, String name){
        return new WebElementWrapper(webDriver, webDriverWait.findElement(locator), name);
    }

    public List<WebElementWrapper> findElements(By locator, String name){
        return findElements(locator, false, name);
    }

    public WebElementWrapper findVisibleElement(By locator, String name){
        return new WebElementWrapper(webDriver, webDriverWait.findVisibleElement(locator), name);
    }

    public List<WebElementWrapper> findVisibleElements(By locator, String name){
        return findElements(locator, true, name);
    }

    private List<WebElementWrapper> findElements(By locator, boolean onlyVisible, String name){
        List<WebElementWrapper> result = new ArrayList<>();
        List<WebElement> elements = onlyVisible ? webDriverWait.findVisibleElements(locator) : webDriverWait.findElements(locator);
        for(int i=0; i<elements.size(); i++){
            result.add(new WebElementWrapper(webDriver, elements.get(i), name + i));
        }

        return result;
    }

    public boolean isVisible(By locator){
        return webDriverWait.isVisible(locator);
    }

    public void navigate(String url){
        Logger.logAction("Navigating to " + url);
        webDriver.get(url);
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    public void quit(){
        webDriver.quit();
    }

    public void takeScreenshot(){
        try {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String fileName = MessageFormat.format("log\\{0}.png", new DateTime().toString("yyyyMMddHHmmss"));
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch(IOException e){
            Logger.logError("Screenshot failed.");
        }
    }
}
