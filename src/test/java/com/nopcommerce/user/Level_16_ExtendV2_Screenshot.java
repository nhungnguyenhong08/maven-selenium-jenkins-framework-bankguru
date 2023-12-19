package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_16_ExtendV2_Screenshot extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManagerNopCommerce.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
		validPassword = "123456";
	}

	@Test
	public void User_01_Register(Method method) {
		/*
		 * ExtentTestManager.startTest(method.getName(), "User_01_Register"); ExtentTestManager.getTest().log(LogStatus.INFO,
		 * "Register - Step 01: Navigate to 'Register' page"); registerPage = homePage.clickToRegisterLink();
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		 * registerPage.inputToFirstnameTextbox(firstName);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		 * registerPage.inputToLastnameTextbox(lastName);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		 * registerPage.inputToEmailTextbox(emailAddress);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
		 * registerPage.inputToPasswordTextbox(validPassword);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to Confirm password textbox with value is '" + validPassword + "'");
		 * registerPage.inputToConfirmPasswordTextbox(validPassword);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 07: Enter to 'Register' button"); registerPage.clickToRegisterButton();
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 08: Verify success message is displayed");
		 * Assert.assertEquals(registerPage.getSucessRegisterMessage(), "Your registration completed...");
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 09: Click to Logout link"); homePage = registerPage.clickToHomeLink();
		 * ExtentTestManager.endTest();
		 */

	}

	@Test
	public void User_02_Login(Method method) {
		/*
		 * ExtentTestManager.startTest(method.getName(), "User_02_Login"); ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
		 * homePage = registerPage.clickToHomeLink(); loginPage = homePage.clickToLoginLink();
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		 * loginPage.inputToEmailTextbox(emailAddress);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
		 * loginPage.inputToPasswordTextbox(validPassword);
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click to Login button"); homePage = loginPage.clickToLoginButton();
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
		 * Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to 'My Account' link"); customerInforPage = homePage.clickToMyAccountLink();
		 * 
		 * ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 07: Verify 'Customer Infor' link is displayed");
		 * Assert.assertFalse(customerInforPage.isCustomerInforPageDisplay()); ExtentTestManager.endTest();
		 */
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
