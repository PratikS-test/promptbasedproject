package com.selenium.test.tests;

import com.selenium.test.data.TestData;
import com.selenium.test.pages.TestAutomationPracticePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAutomationPracticePageTest {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        System.setProperty(TestData.CHROMEDRIVER_KEY, TestData.CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=2560,1600");
        options.addArguments("--headless=new"); // Always run in headless mode
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        new TestAutomationPracticePage(driver);
        driver.get(TestData.TEST_AUTOMATION_PRACTICE_URL);
    }

    @Test
    public void testFillAllTextBoxes() {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(TestData.WAIT_TIME_SECONDS));
        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor)d).executeScript("return document.readyState").equals("complete"));

        java.util.List<WebElement> textInputs = driver.findElements(org.openqa.selenium.By.cssSelector("input[type='text']"));
        java.util.List<WebElement> textAreas = driver.findElements(org.openqa.selenium.By.tagName("textarea"));

        int count = 1;
        for (WebElement input : textInputs) {
            try {
                input.clear();
                input.sendKeys("SampleText" + count);
                count++;
            } catch (Exception e) {
                System.out.println("Could not fill input: " + input.getAttribute("name") + " - " + e.getMessage());
            }
        }
        for (WebElement area : textAreas) {
            try {
                area.clear();
                area.sendKeys("SampleTextArea" + count);
                count++;
            } catch (Exception e) {
                System.out.println("Could not fill textarea: " + area.getAttribute("name") + " - " + e.getMessage());
            }
        }
        try {
            Thread.sleep(TestData.MANUAL_INSPECTION_WAIT_MS * 3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
