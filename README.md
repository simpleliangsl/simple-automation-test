# simple-automation-test
The source code includes a complete example GoogleTranslatePageTest to tell how to write automation test case. Following this example, you could try finishing exercise BingTranslatePageTest.

**[Code Structure]**

**Class Diagram** describes the relationships in **System Classes**, **Selenium Classes** and **Simple Automation Test Classes**. Selenium supplies 1) **WebDriver** to manage web browers, 2) **WebElement** to find web elements on page, 3) **WebDriverWait** to find web elements until time out. Simple Automation Test has 1) **Config** to get test data from configurations, 2) **Web Page Models** to perform actions on pages, 3) **Assertions** to verify actual result == expected value
![simple-automation-test-class-diagram](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-class-diagram.png "simple-automation-test-class-diagram")

Automation web test root "**autotest/web**" includes 3 packages: 1) **common** for commont tools, 2) **page** for web page models, 3) **test** for all test cases. **A test class** has name **WebPageName + "Test"**. _`As we said before in Chapter -1, a good test case should be Small, Straightforward and Stable. A liable test case fails only when there are product defects, or it should be always green. Don't let it be The Boy Who Cried Wolf. Automation test is not silver bullet, so don't make things complicated, keep them simple.`_ **A test case** could be simplified as 3 parts: 1) **precondition**: set up test data from configurations, 2) **perform** actions by web page models, 3) **postcondition**: verify acutal result == expected value by Assertions
![simple-automation-test-code-structure](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-test-code-structure.png "simple-automation-test-code-structure")

Below is an ugly version of googleTranslateTest() in class ZzzSampleTest, which directly uses Selenium without information encapsulation. Actually it works. Comparing with the refined version above, it exposes so much implement details that it is pretty hard to read, understand, reuse and maintain.
![simple-automation-rough-test-case](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/simple-automation-rough-test-case.png "simple-automation-rough-test-case")

A **web page model**: 1) defines a serial of methods to **find web elments**. Usually speaking, there are 3 ways to find a web element: **By.id**, **By.className** and **By.xpath**. _`Please firstly consider By.id or By.className for high accuracy and convenience even though By.xpath is more powerful. You may see more ways to find elements such as By.linkText, By.partialLinkText, By.name, By.tagName, By.cssSelector in official Selenium docs.`_ 2) defines **go()** method. 3) defines a serial of methods to **perform actions** on page.
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


# Useful Basic Test Concept

**[What Is Test]**

**Test** is to **Verify** **Actual Result** meets **Expected Result** when doing **Actions** in conditions (described as **Test Data**). The model of test case could be simplied as **`Action(Data) → Actual = Expected`**: 1) **`Precondition`**: set up test data  2) **`Perform`** actions 3) **`Postcondition`**: verify acutal result = expected result. Also we could format the words like **`When... Do... Then...`**

**Test Case**: **when** the light is off, **switch** it on, **then** it will be on.  
&emsp;&emsp;**`Precondition`**: light is off  
&emsp;&emsp;**`Actions`**: switch on it  
&emsp;&emsp;**`Postcondition`**: it will be on  

**Test Case**: **when** the weather is rainy, **open** the umbrella, **then** I should not get wet.  
&emsp;&emsp;**`Precondition`**: weather is rainy  
&emsp;&emsp;**`Actions`**: open umbrella  
&emsp;&emsp;**`Postcondition`**: I will not get wet  

**Test Case**: **when** Google Translate is accessible, **input** "Hello Dolores, Welcome to the World" and **select** Chinese as target language, then result will be "多洛雷斯，欢迎来到世界".  
&emsp;&emsp;**`Precondition`**: Google Translate is accessible  
&emsp;&emsp;**`Actions`**:  
&emsp;&emsp;&emsp;&emsp;**Step 1**: go to web page: https://translate.google.cn  
&emsp;&emsp;&emsp;&emsp;**Step 2**: input "Hello Dolores, Welcome to the World"  
&emsp;&emsp;&emsp;&emsp;**Step 3**: select Chinese 中文 in target langue menu  
&emsp;&emsp;&emsp;&emsp;**Step 4**: wait 1 or 2 seconds  
&emsp;&emsp;**`Postcondition`**: the result box will show "多洛雷斯，欢迎来到世界"  
 
**[What Is a Good Test Case]**

A good test case could be Stable (liable), Small and Straightforward.

**Stable**: A liable test case fails only when there are product defects, or it should be always green. Don't let it be The Boy Who Cried Wolf who nobody trusts. Most of time test data is the main problem. If test data issue makes a test case impossible to be stable, please skip that case.

**Small**: Don't put too much test points into a test case, follow The Single Responsibility Principle. One case, One thing. 10 test points into 1 case, the case may always fail because of multiple causes, then we waste more time to find it out. 10 test points into 10 cases, the read cases clearly tell us which parts are bad and the green cases directly tell us which parts are healthy, but we have more cases to maintain. If we just put relative test points into a test case, for example, 10 test points into 3 cases, that would be better. How many test cases needed, the balance is not easy.

10 Test Points in Test Cases | Failed Test Cases | Pass Rate
--- | --- | ---
All in One | `X` | 0% (What is wrong?)
One per One | `X`	V	V	V	V	V	V	V	V	V | 90% (The wrong parts are clear, but there are so many test cases to maintained)
Grouped Relatively | `X`	V	V | 67% (Relieve the pain by grouping test points into relative test cases)

**Straightforward**: Idealy there is no if-else branch in a test case. If it has, please divide it into 2 test cases. The shape of a test case is straight line (I), not if-else tree (Y), not loop circle (O). We can see a serial of steps clearly: step 1, step 2, step 3, ... **There should be nothing ambiguous**. See examples below:

**Test Case**: when Google Translate is accessible, input "Hello Dolores, Welcome to the World" and select Chinese as target language, then result will be "多洛雷斯，欢迎来到世界".  
&emsp;&emsp;**Precondition**: Google Translate is accessible  
&emsp;&emsp;**Actions**:  
&emsp;&emsp;&emsp;&emsp;**Step 1**: go to web page: https://translate.google.cn  
&emsp;&emsp;&emsp;&emsp;**Step 2**: input "Hello Dolores, Welcome to the World"  
&emsp;&emsp;&emsp;&emsp;**Step 3**: select Chinese 中文 in target langue menu  
&emsp;&emsp;&emsp;&emsp;**Step 4**: wait 1 or 2 seconds  
&emsp;&emsp;**Postcondition**: the result box will show "多洛雷斯，欢迎来到世界"

**`Not` Test Case**: when Google Translate is accessible, input any text in English and choose any other target language, then result will be as expected  
&emsp;&emsp;**Precondition**: Google Translate is accessible  
&emsp;&emsp;**Actions**:  
&emsp;&emsp;&emsp;&emsp;**Step 1**: go to `Google Translate page`  
&emsp;&emsp;&emsp;&emsp;**Step 2**: input `any text` in English  
&emsp;&emsp;&emsp;&emsp;**Step 3**: choose `any other` target language  
&emsp;&emsp;&emsp;&emsp;**Step 4**: wait `a moment`  
&emsp;&emsp;**Postcondition**: the result box will be `as expected`  

**`Not` Test Case**: when Google Translate is accessible, input random text in English and choose French as target language, then result will be as expected  
&emsp;&emsp;**Precondition**: Google Translate is accessible  
&emsp;&emsp;**Actions**:  
&emsp;&emsp;&emsp;&emsp;**Step 1**: go to https://translate.google.com `if` it is accessible, `else` try https://translate.google.cn  
&emsp;&emsp;&emsp;&emsp;**Step 2**: input `random text` ("Hello Dolores", "Hi Arnold", "Everything happens for a reason")  
&emsp;&emsp;&emsp;&emsp;**Step 3**: choose French in target langue menu  
&emsp;&emsp;&emsp;&emsp;**Step 4**: wait `until` the result is changed  
&emsp;&emsp;**Postcondition**: the result box will be `as expected` ("Bonjour Dolores", "Bonjour Arnold", or "Tout arrive pour une raison")  

**[Test Types]**

**Unit Testing** vs **Integration Testing** (Manual Testing, Automation Testing)

Comparing to atom of ordinary matter, **method (function)** is the small constituent **unit of software system**, so unit testing about methods. **Unit testing** is **white-box testing** where we can see the implement details of how it works: varibles, operations, branch flow and loop flow. Usually the quality of unit testing is mearused by **code line coverage** and **branch coverage**, but actually how many **assertions** based on funcitons are more important. Theorially Unit tests can go through all lines of code (code line coverage 100%) and all branches (branch coverage 100%), but have no any assertions (test points 0%). All those tests are always green, they are rubbish.

Unlike unit testing, **integration testing** is **black-box testing** where the individual modules are combined as a **integrated system**. We treat the whole system as a **input-output box** in high level. It could be **manual** or **automated**.

Considering **Cost-Benift Principle**, unit testing is more and more important since it is very easy to implement technically (e.g. test data stubbing, mocking, sppying...) while integration testing is very complicated to achieve the same effect. Unit testing is Stable, Small and Straightforward as it has no test data issue. Integration testing is usually blocked by test data problem. System Complexity and Data Issue makes integration testing fragile. In some software organizations, they let unit testing take more responsibilities instead of integration testing. They call it **Test Pyramid**, which really helps to build **CI/CD Pipeline** for Agile Development.
![basic-test-concept-H2O](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/basic-test-concept-H2O.png "basic-test-concept-H2O")
![basic-test-concept-test-type](https://raw.githubusercontent.com/simpleliangsl/simple-automation-test/master/readme/basic-test-concept-test-type.png "basic-test-concept-test-type")

Wikipedia: https://en.wikipedia.org/wiki/Functional_testing  
* [Smoke testing](https://en.wikipedia.org/wiki/Smoke_testing_(software))  
* [Sanity testing](https://en.wikipedia.org/wiki/Sanity_testing)  
* [Acceptance testing](https://en.wikipedia.org/wiki/Acceptance_testing)  
* [Integration testing](https://en.wikipedia.org/wiki/Integration_testing)  
* [Unit testing](https://en.wikipedia.org/wiki/Unit_testing)  
* [Regression testing](https://en.wikipedia.org/wiki/Regression_testing)  
