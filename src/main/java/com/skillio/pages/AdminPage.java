package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;
import org.openqa.selenium.By;

public class AdminPage {

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameInput;

    {
        PageFactory.initElements(Keyword.threadLocal.get(), this);
    }

    public void clickAdd() {
        WaitFor.elementToBeVisible(By.xpath("//button[normalize-space()='Add']"));
        addButton.click();
    }

    public void enterEmployeeName(String name) {
        WaitFor.elementToBeVisible(By.xpath("//input[@placeholder='Type for hints...']"));
        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);
    }

}
