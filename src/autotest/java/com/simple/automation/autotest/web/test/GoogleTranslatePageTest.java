package com.simple.automation.autotest.web.test;

import com.simple.automation.autotest.common.SysUtil;
import com.simple.automation.autotest.web.common.BasicTestManager;
import com.simple.automation.autotest.web.page.GoogleTranslatePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Simple Liang on 9/5/17.
 */
public class GoogleTranslatePageTest extends BasicTestManager {

    @Test
    public void googleTranslateTest(){

        /* Set up Test Data Here (read from configurations) */

        /* Perform Actions Here (a serial of steps) */

        // step 1: go to google translate page
        GoogleTranslatePage page = new GoogleTranslatePage(browser);
        page.go();

        // step 2: input text "Hello Dolores, Welcome to the World"
        page.inputText("Hello Dolores, Welcome to the World");

        // step 3: auto detect source language
        page.detectLanguage();

        // step 4: choose target language Chinese
        page.chooseTargetChinese();

        /* Verify Result Here (assert actual result == expected value) */

        // finally verify translated result
        SysUtil.waitTime(2); // wait 2 seconds
        Assert.assertEquals(page.getResult(), "多洛雷斯，欢迎来到世界", "Translation is incorrect");
    }
}
