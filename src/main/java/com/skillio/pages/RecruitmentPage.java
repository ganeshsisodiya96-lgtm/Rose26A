package com.skillio.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class RecruitmentPage {

    private static final By REC_MENU       = By.xpath("//span[text()='Recruitment']");
    private static final By ADD_BTN        = By.xpath("//button[normalize-space()='Add']");
    // Vacancy Name — first text input on Add Vacancy form
    private static final By VACANCY_NAME   = By.xpath("(//div[contains(@class,'oxd-input-group')]//input[contains(@class,'oxd-input')])[1]");
    // Job Title — first select on Add Vacancy form
    private static final By JOB_TITLE_DD   = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    // Positions — second input on Add Vacancy form
    private static final By POSITIONS      = By.xpath("(//div[contains(@class,'oxd-input-group')]//input[contains(@class,'oxd-input')])[2]");
    private static final By SAVE_BTN       = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_TOAST  = By.cssSelector(".oxd-toast--success, .oxd-toast-content-text");
    private static final By REQUIRED_ERR   = By.xpath("//span[text()='Required']");
    private static final By EMAIL_FIELD    = By.xpath("//label[normalize-space()='Email']/following::input[1]");
    private static final By EMAIL_ERROR    = By.xpath("//span[contains(text(),'Expected format')]");
    private static final By FIRST_NAME     = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
    private static final By LAST_NAME      = By.xpath("//label[normalize-space()='Last Name']/following::input[1]");

    public RecruitmentPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    private WebDriver driver() { return Keyword.getDriver(); }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(30));
    }

    // Find element fresh and click — avoids PageFactory proxy serialization crash with Actions
    private void clickDropdown(By dropdownBy) {
        WaitFor.loaderToBeInvisible();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        WaitFor.elementToBeVisible(dropdownBy);
        WebElement el = driver().findElement(dropdownBy);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        el.click();
    }

    private void jsClick(By btnBy) {
        WaitFor.loaderToBeInvisible();
        WaitFor.elementToBeVisible(btnBy);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].click();", driver().findElement(btnBy));
    }

    public void navigateToRecruitment() {
        WaitFor.elementToBeClickable(REC_MENU);
        driver().findElement(REC_MENU).click();
    }

    public void clickAddVacancy() {
        WaitFor.elementToBeClickable(ADD_BTN);
        driver().findElement(ADD_BTN).click();
    }

    public void enterVacancyName(String name) {
        WaitFor.elementToBeVisible(VACANCY_NAME);
        WebElement el = driver().findElement(VACANCY_NAME);
        el.clear();
        el.sendKeys(name);
    }

    public void selectJobTitle(String title) {
        clickDropdown(JOB_TITLE_DD);
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + title + "']");
        WaitFor.elementToBeVisible(option);
        driver().findElement(option).click();
    }

    public void enterPositionCount(String count) {
        WaitFor.elementToBeVisible(POSITIONS);
        WebElement el = driver().findElement(POSITIONS);
        el.clear();
        el.sendKeys(count);
    }

    public void openVacancy(String name) {
        By link = By.xpath(
            "//div[contains(@class,'oxd-table-body')]//a[contains(normalize-space(),'" + name + "')]" +
            " | //div[contains(@class,'oxd-table-body')]//div[contains(normalize-space(),'" + name + "')]");
        WaitFor.elementToBeVisible(link);
        driver().findElement(link).click();
    }

    public void clickAddCandidate() {
        WaitFor.elementToBeClickable(ADD_BTN);
        driver().findElement(ADD_BTN).click();
    }

    public void enterCandidateName(String first, String last) {
        WaitFor.elementToBeVisible(FIRST_NAME);
        WebElement fn = driver().findElement(FIRST_NAME);
        fn.clear(); fn.sendKeys(first);
        WaitFor.elementToBeVisible(LAST_NAME);
        WebElement ln = driver().findElement(LAST_NAME);
        ln.clear(); ln.sendKeys(last);
    }

    public void enterCandidateEmail(String email) {
        WaitFor.elementToBeVisible(EMAIL_FIELD);
        WebElement el = driver().findElement(EMAIL_FIELD);
        el.clear();
        el.sendKeys(email);
    }

    public void selectVacancyInSearch(String name) {
        By vacDd = By.xpath(
            "//label[normalize-space()='Vacancy']/following::div[contains(@class,'oxd-select-text')][1]");
        WaitFor.loaderToBeInvisible();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        WaitFor.elementToBeVisible(vacDd);
        WebElement dd = driver().findElement(vacDd);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].scrollIntoView({block:'center'});", dd);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        dd.click();
        By option = By.xpath(
            "//div[@role='listbox']//span[contains(normalize-space(),'" + name + "')]");
        WaitFor.elementToBeVisible(option);
        driver().findElement(option).click();
    }

    public void clickSave() {
        jsClick(SAVE_BTN);
    }

    public boolean isSuccessToastDisplayed() {
        try { WaitFor.elementToBeVisible(SUCCESS_TOAST);
              return driver().findElement(SUCCESS_TOAST).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isRequiredErrorDisplayed() {
        try { WaitFor.elementToBeVisible(REQUIRED_ERR);
              return driver().findElement(REQUIRED_ERR).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isEmailErrorDisplayed() {
        try { WaitFor.elementToBeVisible(EMAIL_ERROR);
              return driver().findElement(EMAIL_ERROR).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}