package com.selenium.test.utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Utility class for all test verifications/assertions.
 * Used by: HideTextboxTest, PracticePage-related tests, and any test needing UI state checks.
 */
public class TestVerifications {
    /**
     * Verifies if a radio button is selected or not.
     * Used in: TestAutomationPracticePageTest, HideTextboxTest
     * @param radio The radio button WebElement
     * @param expected Expected selection state
     */
    public static void verifyRadioSelected(WebElement radio, boolean expected) {
        Assert.assertEquals(radio.isSelected(), expected, "Radio button selection mismatch");
    }

    /**
     * Verifies if a checkbox is selected or not.
     * Used in: TestAutomationPracticePageTest
     * @param checkbox The checkbox WebElement
     * @param expected Expected selection state
     */
    public static void verifyCheckboxSelected(WebElement checkbox, boolean expected) {
        Assert.assertEquals(checkbox.isSelected(), expected, "Checkbox selection mismatch");
    }

    /**
     * Verifies that an element is visible on the page.
     * Used in: AmazonAddToCartTest, TestAutomationPracticePageTest, HideTextboxTest
     * @param element The WebElement to check
     */
    public static void verifyElementVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element should be visible");
    }

    /**
     * Verifies that an element is not visible on the page.
     * Used in: HideTextboxTest
     * @param element The WebElement to check
     */
    public static void verifyElementNotVisible(WebElement element) {
        Assert.assertFalse(element.isDisplayed(), "Element should not be visible");
    }

    /**
     * Verifies that two strings are equal.
     * Used in: AmazonAddToCartTest, TestAutomationPracticePageTest
     * @param actual The actual string
     * @param expected The expected string
     */
    public static void verifyTextEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Text does not match");
    }

    /**
     * Verifies that a WebElement is present (not null).
     * Used in: AmazonAddToCartTest, TestAutomationPracticePageTest
     * @param element The WebElement to check
     */
    public static void verifyElementPresent(WebElement element) {
        Assert.assertNotNull(element, "Element should be present");
    }
}
