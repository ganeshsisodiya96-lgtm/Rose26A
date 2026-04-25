package com.skillio.AdminTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.skillio.base.Keyword.*;

import java.io.IOException;

import com.skillio.base.Keyword;
import com.skillio.base.Hooks;
import com.skillio.pages.AddUserPage;
import com.skillio.pages.AdminPage;
import com.skillio.pages.HomePage;
import com.skillio.pages.LoginPage;
import com.skillio.utils.App;
import com.skillio.utils.Locator;
import com.skillio.utils.PropUtil;
import com.skillio.utils.WaitFor;




/**
 * This class contains test case related to AdminMenu of OrangeHRM application
 * The app Url is https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
 */
public class AdminTestCase  {

    @BeforeMethod
    public void setUpTest() {
        // Initialize browser for TestNG tests
        Keyword.openBrowser(App.getBrowserName());
        Keyword.launchUrl(App.getappUrl("qa"));
    }

    @AfterMethod
    public void tearDownTest() {
        // Quit browser after each test method
        Keyword.quitBrowser();
    }
    
    @Test(enabled = false)
    public void verifyIfUserIsCreatedAndPresentInAdminUSers() throws InterruptedException {

        Thread.sleep(4000);

        Keyword.getDriver().findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
        Thread.sleep(2000);
        Keyword.getDriver().findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin123");
        Thread.sleep(2000);
        Keyword.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(7000);
        Keyword.getDriver().findElement(By.xpath("//span[text()='Admin']")).click();
        Thread.sleep(5000);
        Keyword.getDriver().findElement(By.xpath("//button[normalize-space()='Add']")).click();
        Thread.sleep(5000);
        Keyword.getDriver().findElement(By.xpath("//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]")).click();
        Thread.sleep(3000);
        Keyword.getDriver().findElement(By.xpath("//div[@role='listbox']//span[normalize-space()='Admin']")).click();
        Thread.sleep(4000);
        Keyword.getDriver().findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("mahesh lende");
        Thread.sleep(6000);

        // Retry mechanism: attempt up to 3 times to get suggestions and click the matching one.
        boolean clicked = false;
        By listboxLocator = By.xpath("//div[@role='listbox']");
        for (int attempt = 1; attempt <= 3 && !clicked; attempt++) {
            try {
                // small pause to let server process
                Thread.sleep(2000 * attempt);
                WaitFor.elementToBeVisible(listboxLocator);
                java.util.List<org.openqa.selenium.WebElement> suggestions = Keyword.getDriver().findElements(By.xpath("//div[@role='listbox']//span"));
                for (org.openqa.selenium.WebElement el : suggestions) {
                    String text = el.getText();
                    if (text != null && text.toLowerCase().contains("mahesh")) {
                        el.click();
                        clicked = true;
                        break;
                    }
                }
                if (!clicked) {
                    // clear and re-type to trigger suggestions again
                    Keyword.getDriver().findElement(By.xpath("//input[@placeholder='Type for hints...']")).clear();
                    Keyword.getDriver().findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("mahesh lende");
                }
            } catch (Exception e) {
                // ignore and retry
            }
        }
        if (!clicked) {
            throw new AssertionError("Expected suggestion containing 'mahesh' not found after retries.");
        }

        Thread.sleep(5000);

        Keyword.getDriver().findElement(
                By.xpath("//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')]")
                ).click();

        Thread.sleep(3000);

        Keyword.getDriver().findElement(By.xpath("//div[@role='listbox']//span[normalize-space()='Enabled']")).click();

        Thread.sleep(4000);

        Keyword.getDriver().findElement(By.xpath("//label[normalize-space()='Username']/following::input[1]")).sendKeys("Admin777");

        Thread.sleep(2000);

        Keyword.getDriver().findElement(By.xpath("//label[normalize-space()='Password']/following::input[1]")).sendKeys("Admin@123");
        Thread.sleep(2000);

        Keyword.getDriver().findElement(By.xpath("//label[normalize-space()='Confirm Password']/following::input[1]"))
                .sendKeys("Admin@123");

        Thread.sleep(3000);

        Keyword.getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        Thread.sleep(6000);

        System.out.println("User created successfully");

    }
    	
    /**
     * This test case is same as above but we are using keywords to perform the actions on the web elements
     * @throws IOException 
     */


    @Test
    public void verifyIfUserIsCreatedAndPresentInAdminUSersUsingKeywords() throws IOException {

        // 🔹 Login
        By username = By.xpath(Locator.username);
        WaitFor.elementToBeVisible(username);
        enterText("xpath", Locator.username, "Admin");
        enterText("xpath", Locator.password, "admin123");
        clickOn("xpath", Locator.signInButton);

        // 🔹 Navigate to Admin
        WaitFor.elementToBeVisible(By.xpath(Locator.Admin));
        clickOn("xpath", Locator.Admin);

        // 🔹 Click Add
        WaitFor.elementToBeVisible(By.xpath(Locator.addButton));
        clickOn("xpath", Locator.addButton);

        // 🔹 Select User Role
        WaitFor.elementToBeVisible(By.xpath(Locator.userRole));
        clickOn("xpath", Locator.userRole);

        WaitFor.elementToBeVisible(By.xpath(Locator.userRoleAdmin));
        clickOn("xpath", Locator.userRoleAdmin);

        // 🔹 Enter Employee Name (FIXED PART)
        enterText("xpath", Locator.employeeName, "manda akhil user");

        // 🔹 Wait for suggestion
        By suggestion = By.xpath("//div[@role='listbox']//span[normalize-space()='manda akhil user']");
        WaitFor.elementToBeVisible(suggestion);

        // 🔹 Click suggestion
        clickOn("xpath", "//div[@role='listbox']//span[normalize-space()='manda akhil user']");

        // 🔹 Select Status
        clickOn("xpath", Locator.status);
        WaitFor.elementToBeVisible(By.xpath(Locator.statusEnabled));
        clickOn("xpath", Locator.statusEnabled);

        // 🔹 Enter User Details
        enterText("xpath", Locator.usernameForNewUser, "Admin777");
        enterText("xpath", Locator.passwordForNewUser, "Admin@123");
        enterText("xpath", Locator.confirmPasswordForNewUser, "Admin@123");

        // 🔹 Save
        clickOn("xpath", Locator.saveButton);

        System.out.println("User created successfully");
    }
    @Test
    public void verifyIfUserIsCreatedAndPresentInAdminUSersUsingPOM() {
        // Wait for login page and perform login via POM
        By username = By.xpath(Locator.username);
        WaitFor.elementToBeVisible(username);
        LoginPage loginPage = PageFactory.initElements(Keyword.threadLocal.get(), LoginPage.class);
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickSignInBtn();

        // Navigate to Admin menu via HomePage
        HomePage homePage = new HomePage();
        homePage.waitForAdminMenuToBeVisible();
        homePage.clickAdminMenu();

        // On Admin page click Add
        AdminPage adminPage = PageFactory.initElements(Keyword.threadLocal.get(), AdminPage.class);
        adminPage.clickAdd();

        // Fill Add User form using AddUserPage
        AddUserPage addUserPage = PageFactory.initElements(Keyword.threadLocal.get(), AddUserPage.class);
        addUserPage.selectUserRoleAdmin();
        addUserPage.enterEmployeeNameAndSelect("Alice akhil user");
        addUserPage.selectStatusEnabled();
        addUserPage.enterUsername("Admin777");
        addUserPage.enterPasswordAndConfirm("Admin@123");
        addUserPage.clickSave();

        // Verify user is created: wait for success toast or search the user listing
        // Here we will search in the users table for the username we created
        By createdUser = By.xpath("//div[contains(@class,'oxd-table-body')]//div[text()='Admin777']");
        WaitFor.elementToBeVisible(createdUser);

        Assert.assertTrue(Keyword.threadLocal.get().findElements(createdUser).size() > 0,
                "Expected newly created user Admin777 to be present in the users table");

    }
    
    
    
    
    
    
}