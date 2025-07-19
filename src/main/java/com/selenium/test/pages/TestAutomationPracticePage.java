package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestAutomationPracticePage {
    // Name input
    @FindBy(css = "input[name='name']")
    public WebElement nameInput;

    // Email input
    @FindBy(css = "input[name='email']")
    public WebElement emailInput;

    // Phone input
    @FindBy(css = "input[name='phone']")
    public WebElement phoneInput;

    // Address textarea
    @FindBy(css = "textarea[name='address']")
    public WebElement addressTextarea;

    // Gender radio buttons
    @FindBy(css = "input[name='gender'][value='male']")
    public WebElement genderMaleRadio;
    @FindBy(css = "input[name='gender'][value='female']")
    public WebElement genderFemaleRadio;

    // Days checkboxes
    @FindBy(css = "input[type='checkbox'][value='sunday']")
    public WebElement sundayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='monday']")
    public WebElement mondayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='tuesday']")
    public WebElement tuesdayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='wednesday']")
    public WebElement wednesdayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='thursday']")
    public WebElement thursdayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='friday']")
    public WebElement fridayCheckbox;
    @FindBy(css = "input[type='checkbox'][value='saturday']")
    public WebElement saturdayCheckbox;

    // Country dropdown
    @FindBy(css = "select[name='country']")
    public WebElement countryDropdown;

    // Date picker
    @FindBy(css = "input[id='datepicker']")
    public WebElement datePicker;

    // Submit button
    @FindBy(css = "input[type='submit'][value='Submit']")
    public WebElement submitBtn;

    // Alert button
    @FindBy(xpath = "//button[contains(text(),'Alert')]" )
    public WebElement alertBtn;

    // Double click button
    @FindBy(xpath = "//button[contains(text(),'Double-Click Me To See Alert')]" )
    public WebElement doubleClickBtn;

    // Drag and drop elements
    @FindBy(id = "draggable")
    public WebElement draggable;
    @FindBy(id = "droppable")
    public WebElement droppable;

    // Table
    @FindBy(xpath = "//table[@name='BookTable']")
    public WebElement bookTable;

    // iFrame
    @FindBy(id = "frame-one1434677811")
    public WebElement demoFrame;

    public TestAutomationPracticePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
