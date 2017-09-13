package com.simple.automation.autotest.web.common;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple Liang on 9/6/17.
 */
public class WebWaitUtil extends WebDriverWait {
    private WebDriver driver;
    private long timeOutSeconds;

    public WebWaitUtil(WebDriver driver, long timeOutSeconds) {
        super(driver, timeOutSeconds);
        this.driver = driver;
        this.timeOutSeconds = timeOutSeconds;
    }

    public WebDriver getWebDriver(){ return this.driver; }

    public long getTimeOutSeconds(){ return this.timeOutSeconds; }

    public WebElement findElement(By locator) {
        return this.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElement(WebElement element, By subLocator){
        return this.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, subLocator));
    }

    public List<WebElement> findElements(By locator) {
        return this.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> findElements(WebElement element, By subLocator){
        findElement(element, subLocator); // check if a element is found.

        return element.findElements(subLocator);
    }

    public WebElement findVisibleElement(final By locator){
        return findVisibleElement(driver, locator);
    }

    public WebElement findVisibleElement(SearchContext context, By subLocator){
        return findVisibleElements(context, subLocator).get(0);
    }

    public boolean isVisible(By locator){
        return isVisible(driver, locator);
    }

    public boolean isVisible(SearchContext context, final By subLocator){
        try{
            return findVisibleElements(context, subLocator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> findVisibleElements(final By locator) {
        return findVisibleElements(driver, locator);
    }

    public List<WebElement> findVisibleElements(final SearchContext context, final By subLocator) {
        return this.until(new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = new ArrayList();
                for(WebElement e : context.findElements(subLocator)){
                    if (e.isDisplayed()) elements.add(e);
                }
                return elements.size() > 0? elements : null;
            }

            public String toString() {
                return "Any visible elements located by " + context + subLocator;
            }
        });
    }
}
