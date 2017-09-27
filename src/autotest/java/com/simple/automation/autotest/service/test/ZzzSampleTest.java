package com.simple.automation.autotest.service.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by sliang on 9/27/17.
 */
public class ZzzSampleTest {

    @DataProvider(name="default")
    public static Object[][] provideData() {
        return new Object[][]{{"test data 1", "doris"},{"test data 2", "simple"}};
    }

    @Test(dataProvider = "default")
    public void test(String data, String name) {
        System.out.println(data+" with "+name);
    }
}