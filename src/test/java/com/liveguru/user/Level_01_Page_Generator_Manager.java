package com.liveguru.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerLiveGuru;
import pageObject.liveGuru.user.UserHomePageObject;
import pageObject.liveGuru.user.UserLoginPageObject;
import pageObject.liveGuru.user.UserMyDashboardPageObject;
import pageObject.liveGuru.user.UserRegisterPageObject;

public class Level_01_Page_Generator_Manager extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyDashboardPageObject myDashboardPage;
	private String email, password, firstName, lastName;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("http://live.techpanda.org/");
		homePage = PageGeneratorManagerLiveGuru.getUserHomePage(driver);

		email = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		firstName = "Automation";
		lastName = "FC";
	}

	@Test
	public void Create_Account_01__Sucess() {
		loginPage = homePage.clickToMyAccountLink();

		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextboxCreate(email);
		registerPage.inputToPasswordTextboxCreate(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		myDashboardPage = registerPage.clickToRegisterButton();

		Assert.assertEquals(myDashboardPage.getRegisterSucessMessage(), "Thank you for registering with Main Website Store.");

	}

	@Test
	public void Login_02_Sucess() {
		myDashboardPage.clickToAccountWrapper();

		homePage = myDashboardPage.clickToLogoutLink();

		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextboxLogin(email);
		loginPage.inputToPasswordTextboxLogin(password);

		loginPage.clickToLoginButton();
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
