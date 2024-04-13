
package com.bankguru.common;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ManagePageObject;
import pageObjects.bankguru.NewCustomerPageObject;
import pageObjects.bankguru.RegisterPageObject;
import utilities.DataHelper;
import utilities.SeverName;

public class Common_01_Create_New_Customer extends BaseTest {

	@Parameters({ "envName", "severName", "browserName", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeTest(description = "Create new common User for all classes Test")
	public void PreconditionRegister(@Optional("local") String envName, @Optional("dev") String severName, @Optional("chrome") String browserName, @Optional("192.168.1.2") String ipAddress, @Optional("5555") String portNumber,
			@Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("120") String browserVersion) {
		ConfigFactory.setProperty("env", severName);
		severname = ConfigFactory.create(SeverName.class);
		driver = getBrowserDriverAll(envName, severname.appUrl(), browserName, ipAddress, portNumber, osName, osVersion, browserVersion);
		dataFaker = DataHelper.getDataHelper();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		userEmail = dataFaker.getEmailAddress();
		customerName = dataFaker.getFullname();
		gender = dataFaker.getGenderMaleAndFemale();
		dateOfBirth = dataFaker.getBirthDay("MM/dd/yyyy");
		address = dataFaker.getAddress();
		city = dataFaker.getCityName();
		state = dataFaker.getState();
		pin = dataFaker.getPINCode();
		mobileNumber = dataFaker.getPhone();
		password = "123456";
		email = dataFaker.getEmailAddressByRandomNumber();

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
		newCustomerPage.inputToTextboxByLabelName("Customer Name", customerName);
		newCustomerPage.selectGenderRadioCheckboxByTextlabel(gender);
		newCustomerPage.inputToTextboxByLabelName("Date of Birth", dateOfBirth);
		newCustomerPage.inputToTextboxByLabelName("Address", address);
		newCustomerPage.inputToTextboxByLabelName("City", city);
		newCustomerPage.inputToTextboxByLabelName("State", state);
		newCustomerPage.inputToTextboxByLabelName("PIN", pin);
		newCustomerPage.inputToTextboxByLabelName("Mobile Number", mobileNumber);
		newCustomerPage.inputToTextboxByLabelName("E-mail", email);
		newCustomerPage.inputToTextboxByLabelName("Password", password);

		newCustomerPage.clickToSubmitButton();

		customerID = newCustomerPage.getCustomerID();

		closeBrowserDriver();
	}

	private WebDriver driver;
	private SeverName severname;
	private DataHelper dataFaker;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private ManagePageObject managePage;
	private NewCustomerPageObject newCustomerPage;
	public static String userEmail, userPassword, userID, customerID;
	private String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, password, email;
}
