package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManagerNopCommerce;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointsPageObject rewardPointsPage;

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
	public void User_01_Register_Login() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSucessRegisterMessage(), "Your registration completed");

		homePage = registerPage.clickToHomeLink();

		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);

		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplay());
	}

	@Test
	public void User_02_Dynamic_Page() {

		// Customer Infor -> Address
		addressPage = customerInforPage.openAddressPage(driver);

		// Address -> My product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);

		// My product review -> Reward point
		rewardPointsPage = myProductReviewPage.openRewardPointsPage(driver);

		// Reward point -> My product review
		myProductReviewPage = rewardPointsPage.openMyProductReviewPage(driver);

		// My prodruct review -> Address
		addressPage = myProductReviewPage.openAddressPage(driver);
	}

	@Test
	public void User_03_Dynamic_Page_01() {

		// Address -> My product review
		myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openPageAtMyAccountByName(driver, "My product reviews");

		// My product review -> Reward point
		rewardPointsPage = (UserRewardPointsPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");

		// Reward point -> Address
		addressPage = (UserAddressPageObject) rewardPointsPage.openPageAtMyAccountByName(driver, "Addresses");

		// Address -> Reward point
		rewardPointsPage = (UserRewardPointsPageObject) addressPage.openPageAtMyAccountByName(driver, "Reward points");

		// Reward point -> Customer info
		customerInforPage = (UserCustomerInforPageObject) rewardPointsPage.openPageAtMyAccountByName(driver, "Customer info");
	}

	@Test
	public void User_04_Dynamic_Page_02() {

		// Customer info -> My product review
		customerInforPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManagerNopCommerce.getUserMyProductReviewPage(driver);

		// My product review -> Reward point
		myProductReviewPage.openPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointsPage = PageGeneratorManagerNopCommerce.getUserRewardPointsPage(driver);

		// Reward point -> Address
		rewardPointsPage.openPageAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManagerNopCommerce.getUserAddressPage(driver);

		// Address -> Reward point
		addressPage.openPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointsPage = PageGeneratorManagerNopCommerce.getUserRewardPointsPage(driver);

		// Reward point -> Customer info
		rewardPointsPage.openPageAtMyAccountByPageName(driver, "Customer info");
		customerInforPage = PageGeneratorManagerNopCommerce.getUserCustomerInforPage(driver);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
