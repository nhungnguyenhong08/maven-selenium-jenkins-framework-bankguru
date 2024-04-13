
package com.bankguru;

import org.aeonbits.owner.ConfigFactory;
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
import pageObjects.bankguru.EditCustomerPageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ManagePageObject;
import pageObjects.bankguru.RegisterPageObject;
import utilities.SeverName;

public class User_02_Edit_Customer extends BaseTest {

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
		customerID = Common_01_Create_New_Customer.customerID;

		loginPage = registerPage.openLoginPage();

		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);
		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));

		managePage.openMenuItemByText("Edit Customer");

		managePage.clickToAvertimentDismissButton();

		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
	}

	@Test
	public void NC_01_Address_Cannot_Be_Empty() {

		editCustomerPage.inputToCustomerTextbox(customerID);

		editCustomerPage.clickToCustomerIDSubmitButton();

		editCustomerPage.inputToTextboxByLabelName("Address", "");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("Address", "Address Field must not be blank"));
	}

	@Test
	public void NC_02_City_Cannot_Be_Empty() {

		editCustomerPage.inputToTextboxByLabelName("City", "");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "City Field must not be blank"));
	}

	@Test
	public void NC_03_City_Cannot_Be_Numeric() {

		editCustomerPage.inputToTextboxByLabelName("City", "1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Numbers are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("City", "city1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Numbers are not allowed"));
	}

	@Test
	public void NC_04_City_Cannot_Have_Special_Characters() {

		editCustomerPage.inputToTextboxByLabelName("City", "#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Special characters are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("City", "city#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Special characters are not allowed"));
	}

	@Test
	public void NC_05_Stay_Cannot_Be_Empty() {

		editCustomerPage.inputToTextboxByLabelName("State", "");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("State", "State must not be blank"));
	}

	@Test
	public void NC_06_Stay_Cannot_Be_Numeric() {

		editCustomerPage.inputToTextboxByLabelName("State", "1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("State", "Numbers are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("State", "city1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("State", "Numbers are not allowed"));
	}

	@Test
	public void NC_07_Stay_Cannot_Have_Special_Characters() {

		editCustomerPage.inputToTextboxByLabelName("State", "#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("State", "Special characters are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("State", "city#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("State", "Special characters are not allowed"));
	}

	@Test
	public void NC_08_PIN_must_Be_Numeric() {

		editCustomerPage.inputToTextboxByLabelName("PIN", "pin");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "Characters are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("PIN", "pin1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "Characters are not allowed"));
	}

	@Test
	public void NC_09_PIN_Cannot_Be_Empty() {

		editCustomerPage.inputToTextboxByLabelName("PIN", "");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "PIN Code must not be blank"));
	}

	@Test
	public void NC_10_PIN_must_Have_6_Digits() {

		editCustomerPage.inputToTextboxByLabelName("PIN", "12");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "PIN Code must have 6 Digits"));

		editCustomerPage.inputToTextboxByLabelName("PIN", "1234");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "PIN Code must have 6 Digits"));
	}

	@Test
	public void NC_11_PIN_Cannot_Have_Special_Characters() {

		editCustomerPage.inputToTextboxByLabelName("PIN", "#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "Special characters are not allowed"));

		editCustomerPage.inputToTextboxByLabelName("PIN", "city#@!");

		Assert.assertTrue(editCustomerPage.isErrorMessageBylabeAndTextDisplayed("PIN", "Special characters are not allowed"));
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
	private EditCustomerPageObject editCustomerPage;
	private String userPassword, userID, customerID;
}
