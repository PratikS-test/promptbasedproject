package com.selenium.test.tests;

import com.selenium.test.pages.AmazonPage;
import com.selenium.test.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AmazonAddToCartTest {
    private WebDriver driver;
    private AmazonPage amazonPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty(TestData.CHROMEDRIVER_KEY, TestData.CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=2560,1600");
        options.addArguments("--headless=new"); // Run in headed mode
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        amazonPage = new AmazonPage(driver);
    }

    @Test
    public void testAddRedmiNote20ProToCart() throws Exception {
        driver.get(TestData.AMAZON_URL);
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(TestData.WAIT_TIME_SECONDS));
        // Accept cookies if present
        try {
            wait.until(d -> amazonPage.acceptCookiesBtn.isDisplayed());
            amazonPage.acceptCookiesBtn.click();
        } catch (Exception ignored) {}

        // Search for product
        wait.until(ExpectedConditions.visibilityOf(amazonPage.searchBox));
        amazonPage.searchBox.clear();
        amazonPage.searchBox.sendKeys(TestData.AMAZON_SEARCH_CRITERIA);
        amazonPage.searchButton.click();

        // Wait for search results
        wait.until(d -> amazonPage.searchResults.size() > 0);
        org.openqa.selenium.WebElement lowestResult = null;
        int lowestPrice = Integer.MAX_VALUE;
        for (org.openqa.selenium.WebElement result : amazonPage.searchResults) {
            // Check title
            java.util.List<org.openqa.selenium.WebElement> titleElements = result.findElements(org.openqa.selenium.By.xpath(".//span[contains(text(),'Redmi') and contains(text(),'14 Pro')]"));
            // Check price
            java.util.List<org.openqa.selenium.WebElement> priceElements = result.findElements(org.openqa.selenium.By.xpath(".//span[contains(@class,'a-price-whole')]"));
            if (!titleElements.isEmpty() && !priceElements.isEmpty()) {
                String priceText = priceElements.get(0).getText().replace(",", "").replace(".", "");
                try {
                    int price = Integer.parseInt(priceText);
                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestResult = result;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        if (lowestResult == null) throw new RuntimeException("No Redmi note 14 Pro found in results");
        // Click the product link for the lowest price
        java.util.List<org.openqa.selenium.WebElement> links = lowestResult.findElements(org.openqa.selenium.By.xpath(".//a[@class='a-link-normal s-no-outline']"));
        if (!links.isEmpty()) {
            links.get(0).click();
        } else {
            throw new RuntimeException("No product link found for lowest price result");
        }

        // Switch to new tab if opened
        java.util.ArrayList<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
        }

        // Debug: Print current URL and title, take screenshot
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File("amazon_add_to_cart_debug.png").toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Find the Add to Cart button just above the Buy Now button
        org.openqa.selenium.WebElement buyNowBtn = amazonPage.buyNowBtn;
        // Find all Add to Cart buttons on the page
        java.util.List<org.openqa.selenium.WebElement> addToCartBtns = amazonPage.getAddToCartButtons();
        org.openqa.selenium.WebElement correctAddToCartBtn = null;
        // Find the Add to Cart button that is immediately above the Buy Now button
        for (org.openqa.selenium.WebElement btn : addToCartBtns) {
            if (btn.getLocation().getY() < buyNowBtn.getLocation().getY()) {
                if (correctAddToCartBtn == null || btn.getLocation().getY() > correctAddToCartBtn.getLocation().getY()) {
                    correctAddToCartBtn = btn;
                }
            }
        }
        if (correctAddToCartBtn == null) throw new RuntimeException("Could not find Add to Cart button above Buy Now");
        // Scroll Add to Cart button (just above Buy Now) into view and click it
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", correctAddToCartBtn);
        wait.until(ExpectedConditions.elementToBeClickable(correctAddToCartBtn));
        try {
            correctAddToCartBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Add to Cart button was not interactable, trying JS click...");
            ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].click();", correctAddToCartBtn);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
