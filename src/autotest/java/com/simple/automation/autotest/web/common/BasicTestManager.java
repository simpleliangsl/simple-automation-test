package com.simple.automation.autotest.web.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Simple Liang on 9/12/17.
 */
public class BasicTestManager {
    protected WebDriverWrapper browser;

    @BeforeMethod
    public void setUp() {
        browser = new WebDriverWrapper(); // start browser
    }

    @AfterMethod
    public void tearDown() {
        browser.quit(); // stop browser
    }
}
