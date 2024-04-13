
package com.bankguru;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Create_New_Customer;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankguru.DeleteCustomerPageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ManagePageObject;
import pageObjects.bankguru.RegisterPageObject;
import utilities.SeverName;

public class User_03_Delete_Customer extends BaseTest {

	@Parameters({ "envName", "severName", "browserName", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String severName, @Optional("chrome") String browserName, @Optional("192.168.1.2") String ipAddress, @Optional("5555") String portNumber,
			@Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("120") String browserVersion) {
		ConfigFactory.setProperty("env", severName);
		severname = ConfigFactory.create(SeverName.class);
		driver = getBrowserDriverAll(envName, severname.appUrl(), browserName, ipAddress, portNumber, osName, osVersion, browserVersion);
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		userPassword = Common_01_Create_New_Customer.userPassword;
		userID = Common_01_Create_New_Customer.userID;

		loginPage = registerPage.openLoginPage();

		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);
		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));

		managePage.openMenuItemByText("Edit Customer");

		managePage.clickToAvertimentDismissButton();

		deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

	@Test
	public void DC_01_Customer_ID_Cannot_Be_Empty() {

		deleteCustomer.sendkeysToCustomerIDTextbox(Keys.TAB);

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Customer ID is required"));
	}

	@Test
	public void DC_02_Customer_ID_Cannot_Be_Numeric() {

		deleteCustomer.openMenuItemByText("Delete Customer");

		deleteCustomer.inputToCustomerIDTextbox("abc");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Characters are not allowed"));

		deleteCustomer.inputToCustomerIDTextbox("name1234");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Characters are not allowed"));
	}

	@Test
	public void DC_03_Customer_ID_Cannot_Have_Special_Characters() {

		deleteCustomer.openMenuItemByText("Delete Customer");

		deleteCustomer.inputToCustomerIDTextbox("#@!");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Special characters are not allowed"));

		deleteCustomer.inputToCustomerIDTextbox("name#@!");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Special characters are not allowed"));
	}

	@Test
	public void DC_04_Customer_ID_Cannot_Have_Blank_Space() {

		deleteCustomer.openMenuItemByText("Delete Customer");

		deleteCustomer.inputToCustomerIDTextbox("123 12");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("Characters are not allowed"));
	}

	@Test
	public void DC_05_Customer_ID_Cannot_Have_First_Character_As_Blank_Space() {

		deleteCustomer.openMenuItemByText("Delete Customer");

		deleteCustomer.inputToCustomerIDTextbox(" 123");

		Assert.assertTrue(deleteCustomer.isErrorMessageByTextDisplayed("First character can not have space"));
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private SeverName severname;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private ManagePageObject managePage;
	private DeleteCustomerPageObject deleteCustomer;
	private String userPassword, userID;

}
