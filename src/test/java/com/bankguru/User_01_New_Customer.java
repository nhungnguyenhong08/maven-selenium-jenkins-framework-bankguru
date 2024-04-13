
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

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ManagePageObject;
import pageObjects.bankguru.NewCustomerPageObject;
import pageObjects.bankguru.RegisterPageObject;
import utilities.DataHelper;
import utilities.SeverName;

public class User_01_New_Customer extends BaseTest {

	@Parameters({ "envName", "severName", "browserName", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String severName, @Optional("chrome") String browserName, @Optional("192.168.1.2") String ipAddress, @Optional("5555") String portNumber,
			@Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("120") String browserVersion) {
		ConfigFactory.setProperty("env", severName);
		severname = ConfigFactory.create(SeverName.class);
		driver = getBrowserDriverAll(envName, severname.appUrl(), browserName, ipAddress, portNumber, osName, osVersion, browserVersion);
		dataFaker = DataHelper.getDataHelper();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		userEmail = dataFaker.getEmailAddress();
		registerPage.inPutToEmailTextbox(userEmail);
		registerPage.clickToSubmitButton();

		userID = registerPage.getUserID();
		userPassword = registerPage.getUserPassword();

		loginPage = registerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);
		managePage = loginPage.clickToLoginButton();

		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("New Customer");
		managePage.clickToAvertimentDismissButton();

		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void NC_01_Name_Cannot_Be_Empty() {

		newCustomerPage.sendkeysToNewCustomerAreaTextboxBylabel("Customer Name", Keys.TAB);

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "Customer name must not be blank"));
	}

	@Test
	public void NC_02_Name_Cannot_Be_Numeric() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("Customer Name", "1234");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "Numbers are not allowed"));

		newCustomerPage.inputToTextboxByLabelName("Customer Name", "name1234");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "Numbers are not allowed"));
	}

	@Test
	public void NC_03_Name_Cannot_Have_Special_Characters() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("Customer Name", "#@!");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "Special characters are not allowed"));

		newCustomerPage.inputToTextboxByLabelName("Customer Name", "name#@!");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "Special characters are not allowed"));
	}

	@Test
	public void NC_04_Name_Cannot_Have_First_Character_As_Blank_Space() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("Customer Name", " ");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Customer Name", "First character can not have space"));
	}

	@Test
	public void NC_05_Address_Cannot_Be_Empty() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.sendkeysToNewCustomerAreaTextboxBylabel("Address", Keys.TAB);

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Address", "Address Field must not be blank"));
	}

	@Test
	public void NC_06_Address_Cannot_Have_First_Blank_Space() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("Address", " ");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("Address", "First character can not have space"));
	}

	@Test
	public void NC_07_City_Cannot_Be_Empty() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.sendkeysToNewCustomerAreaTextboxBylabel("City", Keys.TAB);

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "City Field must not be blank"));
	}

	@Test
	public void NC_08_City_Cannot_Be_Numeric() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("City", "1234");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Numbers are not allowed"));

		newCustomerPage.inputToTextboxByLabelName("City", "city1234");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Numbers are not allowed"));
	}

	@Test
	public void NC_09_City_Cannot_Have_Special_Characters() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("City", "#@!");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Special characters are not allowed"));

		newCustomerPage.inputToTextboxByLabelName("City", "city#@!");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "Special characters are not allowed"));
	}

	@Test
	public void NC_10_City_Cannot_Have_First_Character_As_Blank_Space() {

		newCustomerPage.openMenuItemByText("New Customer");

		newCustomerPage.inputToTextboxByLabelName("City", " ");

		Assert.assertTrue(newCustomerPage.isErrorMessageBylabeAndTextDisplayed("City", "First character can not have space"));
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private SeverName severname;
	private DataHelper dataFaker;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private ManagePageObject managePage;
	private NewCustomerPageObject newCustomerPage;
	private String userEmail, userPassword, userID;
}
