package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class AmazonPage {
    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;

    @FindBy(id = "sp-cc-accept")
    public WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div[contains(@class,'s-result-item') and @data-component-type='s-search-result']")
    public List<WebElement> searchResults;

    // All Add to Cart buttons on the page
    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    public List<WebElement> addToCartButtons;

    // The Buy Now button (to help locate the correct Add to Cart button)
    @FindBy(xpath = "//input[@id='buy-now-button']")
    public WebElement buyNowBtn;

    public AmazonPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }
}
