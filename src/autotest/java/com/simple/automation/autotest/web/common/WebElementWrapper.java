package com.simple.automation.autotest.web.common;

import com.simple.automation.autotest.common.Config;
import com.simple.automation.autotest.common.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple Liang on 9/6/17.
 */
public class WebElementWrapper {
    private static final long DEFAULT_TIMEOUT_SECONDS = Config.getLong("findElementTimeOutSeconds", 1L);

    private WebDriver webDriver;
    private WebElement webElement;
    private WebWaitUtil waitUtil;
    private String name;
    private Select selector;

    public WebElementWrapper(WebDriver webDriver, WebElement element, String name){
        this.webDriver = webDriver;
        this.webElement = element;
        this.name = name;
        this.waitUtil = new WebWaitUtil(webDriver, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebDriver getWebDriver() { return this.webDriver; }

    public WebElement getWebElement(){
        return this.webElement;
    }

    public String getName(){
        return this.name;
    }

    public WebElementWrapper findElement(By locator, String name){
        return new WebElementWrapper(webDriver, waitUtil.findElement(webElement, locator), name);
    }

    public List<WebElementWrapper> findElements(By locator, String name){
        return findElements(locator, false, name);
    }

    public WebElementWrapper findVisibleElement(By locator, String name){
        List<WebElementWrapper> elements = findVisibleElements(locator, name);

        if(elements.size()==0) return null;

        return elements.get(0);
    }

    public List<WebElementWrapper> findVisibleElements(By locator, String name){
        return findElements(locator, true, name);
    }

    private List<WebElementWrapper> findElements(By locator, boolean onlyVisible, String name){
        List<WebElementWrapper> result = new ArrayList<>();

        List<WebElement> elements = onlyVisible ? waitUtil.findVisibleElements(webElement, locator) :
                waitUtil.findElements(webElement, locator);

        for(int i=0; i<elements.size(); i++){
            result.add(new WebElementWrapper(webDriver, elements.get(i), name+i));
        }

        return result;
    }

    public boolean isVisible(By locator){
        return waitUtil.isVisible(webElement, locator);
    }

    public void click(){
        Logger.logAction("Click " + this.name);
        this.webElement.click();
    }

    public void submit(){
        this.webElement.submit();
    }

    public void sendKeys(String text){
        Logger.logAction("Input {0}: {1}", this.name, text);
        this.webElement.sendKeys(text);
    }

    public void clear(){
        Logger.logAction("Clear " + this.name);
        this.webElement.clear();
    }

    public String getTagName(){
        return this.webElement.getTagName();
    }

    public String getAttribute(String attribute){
        return this.webElement.getAttribute(attribute);
    }

    public boolean isSelected(){
        return this.webElement.isSelected();
    }

    public boolean isEnabled(){
        return this.webElement.isEnabled();
    }

    public String getText(){
        return this.webElement.getText();
    }

    public boolean isDisplayed(){
        return this.webElement.isDisplayed();
    }

    public Point getLocation(){
        return this.webElement.getLocation();
    }

    public Dimension getSize(){
        return this.webElement.getSize();
    }

    public Rectangle getRect(){
        return this.webElement.getRect();
    }

    public String getCssValue(String value){
        return this.webElement.getCssValue(value);
    }

    public void selectByValue(String value){
        if(!isDropDown()) return;

        if(this.selector == null) this.selector = new Select(this.webElement);

        Logger.logAction("Select {0} by value: {1}", this.name, value);
        this.selector.selectByValue(value);
    }

    public void selectByText(String text){
        if(!isDropDown()) return;

        if(this.selector == null) this.selector = new Select(this.webElement);

        Logger.logAction("Select {0} by text: {1}", this.name, text);
        this.selector.selectByVisibleText(text);
    }


    public boolean isDropDown(){
        return this.webElement.getTagName().toLowerCase().equals("select");
    }
}
