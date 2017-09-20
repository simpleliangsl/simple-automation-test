# simple-automation-test
There is a complete example GoogleTranslatePageTest to tell how to write automation test. Following this example, you could try finishing exercise BingTranslatePageTest.

**[Code Structure]**

**Class Diagram** describes the relationships in **System Classes**, **Selenium Classes** and **Simple Automation Test Classes**. Selenium supplies 1) **WebDriver** to manage web browers, 2) **WebElement** to find web elements on page, 3) **WebDriverWait** to find web elements until time out. Simple Automation Test has 1) **Config** to get test data from configurations, 2) **Web Page Models** to perform actions on pages, 3) **Assertions** to verify actual result == expected value
![simple-automation-test-class-diagram](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-class-diagram.png "simple-automation-test-class-diagram")

Automation web test root "**autotest/web**" includes 3 packages: 1) **common** for commont tools, 2) **page** for web page models, 3) **test** for all test cases. **A test class** has name **WebPageName + "Test"**. _`As we said before in Chapter -1, a good test case should be Small, Straightforward and Stable. A liable test case fails only when there are product defects, or it should be always green. Don't let it be The Boy Who Cried Wolf. Automation test is not silver bullet, so don't make things complicated, keep them simple.`_ **A test case** could be simplified as 3 parts: 1) **precondition**: set up test data from configurations, 2) **perform** actions by web page models, 3) **postcondition**: verify acutal result == expected value by Assertions
![simple-automation-test-code-structure](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-code-structure.png "simple-automation-test-code-structure")

Below is an ugly version of googleTranslateTest() in class ZzzSampleTest, which directly uses Selenium without information encapsulation. Actually it works. Comparing with the refined version above, it exposes so much implement details that it is pretty hard to read, understand, reuse and maintain.
![simple-automation-rough-test-case](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-rough-test-case.png "simple-automation-rough-test-case")

A **web page model**: 1) defines a serial of methods to **find web elments**. Usually speaking, there are 3 ways to find a web element: **By.id**, **By.className** and **By.xpath**. Please firstly consider By.id or By.className for high accuracy and convenience even though By.xpath is more powerful. You may see more ways to find elements such as By.linkText, By.partialLinkText, By.name, By.tagName, By.cssSelector in official Selenium docs. 2) defines **go()** method. 3) defines a serial of methods to **perform actions** on page.
![simple-automation-test-web-page-model](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-web-page-model.png "simple-automation-test-web-page-model")

Test data in **configurations** ("key=value" pairs)
![simple-automation-test-configurations](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-configurations.png "simple-automation-test-configurations")
 
**[Run Automation Test]**
1. **Run in IDE** (Intellij IEDA, Eclipse...): right click the test case name (marked as "**@Test**"), or the class name, then choose "**Run...**"
![simple-automation-test-run-in-IDE](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-run-in-IDE.png "simple-automation-test-run-in-IDE")

You also can edit **VM options** in Run/Debug Configitions: **-DautoConfig=qa**  Notice: **1)** "**-D**" parameters are JVM system properties catching by System.getProperty(), which means you can pass any parameter into system using "-D" (e.g. "-Dabc=hahaha") **2)** "**autoConfig**" is for reading test configurations (autoConfig=dev, qa7 or prod). **3)** If "autoConfig" is not set, then it will be default value "dev".
![simple-automation-test-vm-options-in-IDE](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-vm-options-in-IDE.png "simple-automation-test-vm-options-in-IDE")

Test Case on Google Translate page: https://translate.google.cn
![simple-automation-test-screenshot](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-screenshot.png "simple-automation-test-screenshot")

2. **Run by command line** under project root directory: **./gradlew autoTest -DautoConfig=qa**
![simple-automation-test-run-in-command-line](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-run-in-command-line.png "simple-automation-test-run-in-command-line")
 
Both IDE and command line can generate test reports under project **root/build/reports**:
![simple-automation-test-result-dir](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-result-dir.png "simple-automation-test-result-dir")
![simple-automation-test-report](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-report.png "simple-automation-test-report")

3. **Pipeline**: Image there is CI/CD pipeline, Each code check-in will trigger piline: build→ run unit test → deploy to test lab → run integration test (automation: ./gradlew autoTest -DautoConfig=qa) → release to prod
![basic-test-concept-pipeline](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/basic-test-concept-pipeline.png "basic-test-concept-pipeline")
