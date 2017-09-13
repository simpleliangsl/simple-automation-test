package com.simple.automation.autotest.web.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Simple Liang on 9/12/17.
 */
public class ZzzSampleTest {

    WebDriver browser;

    @BeforeClass
    public void beforeClass() {
        Assert.assertNotNull(System.getProperty("webdriver.chrome.driver"),
                "Please set chrome driver path by \"-Dwebdriver.chrome.driver\"");
    }

    @BeforeMethod
    public void setUp() {
        browser = new ChromeDriver(); // start browser
    }

    @AfterMethod
    public void tearDown() {
        browser.quit(); // stop browser
    }

    @Test
    public void googleTranslateTest() {

        // 1. go to google translate page
        browser.get("https://translate.google.cn/");

        // 2. input text "Hello Dolores!" in 2 seconds
        WebDriverWait waitUtil = new WebDriverWait(browser, 2);
        WebElement sourceInput = waitUtil.until(ExpectedConditions.visibilityOfElementLocated(By.id("source")));
        sourceInput.sendKeys("Hello Dolores!");

        // 3. auto detect source language
        WebElement sourceDetect = browser.findElement(By.xpath("//div[@id='gt-sl-sugg']/div[@value='auto']"));
        sourceDetect.click();

        // 4. choose target language
        WebElement targetLangGroup = browser.findElement(By.id("gt-tl-gms"));
        WebElement langDropdown = targetLangGroup.findElement(By.className("goog-flat-menu-button-dropdown"));
        langDropdown.click(); // open menu to choose language

        WebElement langMenu = waitUtil.until(ExpectedConditions.visibilityOfElementLocated(By.id("gt-tl-gms-menu")));
        WebElement chinese = langMenu.findElement(By.id(":5t"));
        chinese.click(); // choose Chinese

        // finally verify translated result
        try {Thread.sleep(2000);} catch(InterruptedException e){} // wait 2 seconds
        WebElement resultBox = browser.findElement(By.id("result_box"));
        Assert.assertEquals(resultBox.getText(), "你好多洛雷斯", "Translation is incorrect");
    }
}
