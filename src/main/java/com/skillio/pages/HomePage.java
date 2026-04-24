package com.skillio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class HomePage {
	
	@FindBy(xpath="//a[@href='/dashboard']")
	WebElement dashboardLink;
	@FindBy(xpath="//span[text()='Admin']")
	WebElement adminMenuLink;
	
	{
		PageFactory.initElements(Keyword.threadLocal.get(), this);
	}
	
	
	public void waitForAdminMenuToBeVisible() {
		WaitFor.elementToBeVisible(By.xpath("//span[text()='Admin']"));
		
	}

	public void clickAdminMenu1() {
        WaitFor.elementToBeClickable(By.xpath("//span[text()='Admin']"));
        adminMenuLink.click();
    }

	public void clickAdminMenu() {
        // Wait for Admin menu to be clickable and click the PageFactory-initialized element
        WaitFor.elementToBeClickable(By.xpath("//span[text()='Admin']"));
        adminMenuLink.click();
    }
		
	}