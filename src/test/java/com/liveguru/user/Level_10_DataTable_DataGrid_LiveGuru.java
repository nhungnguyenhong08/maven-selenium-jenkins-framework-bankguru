package com.liveguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstantsLiveGuru;
import commons.PageGeneratorManagerLiveGuru;
import pageObject.liveGuru.user.UserHomePageObject;
import pageObject.liveGuru.user.UserLoginPageObject;
import pageObject.liveGuru.user.UserMyDashboardPageObject;
import pageObject.liveGuru.user.UserRegisterPageObject;
import pageObjects.liveGuru.admin.AdminDashboardPageObject;
import pageObjects.liveGuru.admin.AdminLoginPageObject;

public class Level_10_DataTable_DataGrid_LiveGuru extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyDashboardPageObject myDashboardPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String email, password, firstName, lastName, adminUserName, adminPassword;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		userHomePage = PageGeneratorManagerLiveGuru.getUserHomePage(driver);

		email = "phonixtranho26051994" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		firstName = "Phonix";
		lastName = "Tran";
		adminUserName = "user01";
		adminPassword = "guru99com";

	}

	@Test
	public void Create_Account_01__Sucess_And_Verify_At_Admin_Page() {
		loginPage = userHomePage.clickToMyAccountLink();

		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextboxCreate(email);
		registerPage.inputToPasswordTextboxCreate(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		myDashboardPage = registerPage.clickToRegisterButton();

		Assert.assertEquals(myDashboardPage.getRegisterSucessMessage(), "Thank you for registering with Main Website Store.");

		// User Home Page -> Open Admin page -> Login Page (Admin)
		userHomePage.openPageUrl(driver, GlobalConstantsLiveGuru.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManagerLiveGuru.getAdminLoginPage(driver);

		// Login as Admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminUserName, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

		adminDashboardPage.sleepInSecond(3);

		// verify pop up displayed
		Assert.assertTrue(adminDashboardPage.isPopupDisplay());

		adminDashboardPage.closePopup();

		// verify pop up undisplayed
		Assert.assertFalse(adminDashboardPage.isPopupUnDisplay());

		adminDashboardPage.enterToHeaderTextboxByLable("email", email);

		adminDashboardPage.sleepInSecond(2);

		Assert.assertTrue(adminDashboardPage.isResultDisplay(firstName + " " + lastName, email));

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}
}
