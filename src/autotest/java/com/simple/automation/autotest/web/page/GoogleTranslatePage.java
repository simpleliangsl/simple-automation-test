package com.simple.automation.autotest.web.page;

import com.simple.automation.autotest.common.Config;
import com.simple.automation.autotest.web.common.WebDriverWrapper;
import com.simple.automation.autotest.web.common.WebElementWrapper;
import org.openqa.selenium.By;

/**
 * Created by Simple Liang on 9/5/17.
 */
public class GoogleTranslatePage extends WebPage {

    // Constructor
    public GoogleTranslatePage(WebDriverWrapper browser) {
        super(browser);
    }

    // Go to web page
    public void go() {
        String url = Config.getProperty("googleTranslateUrl");
        browser.navigate(url);
    }

    /* A Serial of Methods to Find Web Elements Below */

    public WebElementWrapper findInputBox() {
        final By LOCATOR = By.id("source");
        return browser.findVisibleElement(LOCATOR, "Source Input Box");
    }

    public WebElementWrapper findDetectLanguage() {
        final By LOCATOR = By.xpath("//div[@id='gt-sl-sugg']/div[@value='auto']");
        return browser.findVisibleElement(LOCATOR, "Detect language button");
    }

    public WebElementWrapper findLangDropdown() {
        final By LOCATOR = By.xpath("//*[@id='gt-tl-gms']//*[contains(@class, 'goog-flat-menu-button-dropdown')]");
        return browser.findElement(LOCATOR, "Target Language Dropdown");
    }

    /* A Serial of Methods to Perform Actions on Page Below */

    public void inputText(String text) {
        WebElementWrapper inputBox = findInputBox();
        inputBox.clear();
        inputBox.sendKeys(text);
    }

    public void detectLanguage() {
        findDetectLanguage().click();
    }

    public void chooseTargetChinese() {
        final By LANGUAGE_MENU = By.id("gt-tl-gms-menu");

        // show language menu
        if(!browser.isVisible(LANGUAGE_MENU)) {
            findLangDropdown().click();
        }

        // choose Chinese in target language menu
        WebElementWrapper langMenu = browser.findVisibleElement(LANGUAGE_MENU, "Target Language Menu");
        WebElementWrapper langItem = langMenu.findElement(By.id(":5t"), "Chinese item");
        langItem.click();
    }

    public String getResult() {
        final By LOCATOR = By.id("result_box");
        WebElementWrapper resultBox = browser.findElement(LOCATOR, "Result Box");
        return resultBox.getText();
    }
}
