package com.bankguru;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankguru.DeleteAccountPageObject;
import pageObjects.bankguru.DeleteCustomerPageObject;
import pageObjects.bankguru.DepositPageObject;
import pageObjects.bankguru.EditCustomerPageObject;
import pageObjects.bankguru.FundTransferPageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.ManagePageObject;
import pageObjects.bankguru.NewAccountPageObject;
import pageObjects.bankguru.NewCustomerPageObject;
import pageObjects.bankguru.RegisterPageObject;
import pageObjects.bankguru.WithdrawalPageObject;
import utilities.DataHelper;
import utilities.SeverName;

public class User_04_Payment_Customer extends BaseTest {

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

		addressUpdate = dataFaker.getAddress();
		cityUpdate = dataFaker.getCityName();
		stateUpdate = dataFaker.getState();
		pinUpdate = dataFaker.getPINCode();
		mobileNumberUpdate = dataFaker.getPhone();

		registerPage.inPutToEmailTextbox(userEmail);
		registerPage.clickToSubmitButton();
		userID = registerPage.getUserID();
		userPassword = registerPage.getUserPassword();
	}

	@Test
	public void PM_01_Create_New_Customer_And_Verify() {

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

		Assert.assertTrue(newCustomerPage.isCreateSuccessMessageDiplayedWithText("Customer Registered Successfully!!!"));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Customer Name", customerName));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Gender", gender));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Birthdate", changeFormartDate(dateOfBirth, "yyyy-dd-MM")));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Address", address));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("City", city));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("State", state));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Pin", pin));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Mobile No.", mobileNumber));
		Assert.assertTrue(newCustomerPage.isInfoNewCustomerBylabelAndTextDisplayed("Email", email));
	}

	@Test
	public void PM_02_Edit_Customer_And_Verify() {

		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();

		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));

		managePage.openMenuItemByText("Edit Customer");

		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		editCustomerPage.inputToCustomerTextbox(customerID);
		editCustomerPage.clickToCustomerIDSubmitButton();

		editCustomerPage.inputToTextboxByLabelName("Address", addressUpdate);
		editCustomerPage.inputToTextboxByLabelName("City", cityUpdate);
		editCustomerPage.inputToTextboxByLabelName("State", stateUpdate);
		editCustomerPage.inputToTextboxByLabelName("PIN", pinUpdate);
		editCustomerPage.inputToTextboxByLabelName("Mobile Number", mobileNumberUpdate);
		editCustomerPage.clickToEditCustomerSubmitButton();
		editCustomerPage.acceptEditedCustomerAlert();

		editCustomerPage.inputToCustomerTextbox(customerID);
		editCustomerPage.clickToCustomerIDSubmitButton();

		Assert.assertTrue(editCustomerPage.isEditCutomerAddressByTextDisplayed(addressUpdate));
		Assert.assertTrue(editCustomerPage.isInfoEditCutomerBylabelAndTextDisplayed("City", cityUpdate));
		Assert.assertTrue(editCustomerPage.isInfoEditCutomerBylabelAndTextDisplayed("State", stateUpdate));
		Assert.assertTrue(editCustomerPage.isInfoEditCutomerBylabelAndTextDisplayed("PIN", pinUpdate));
		Assert.assertTrue(editCustomerPage.isInfoEditCutomerBylabelAndTextDisplayed("Mobile Number", mobileNumberUpdate));
	}

	@Test
	public void PM_03_Add_New_Account_And_Verify() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("New Account");

		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		newAccountPage.inputToCustomerIDTextbox(customerID);
		newAccountPage.selectItemInAccountTypeDropdown("Savings");
		newAccountPage.inputToInitialDepositTextbox("50000");
		newAccountPage.clickToSubmitButton();
		accountID = newAccountPage.getAccountID();
		newAccountPage.isCreateSuccessMessageDiplayedWithText("Account Generated Successfully!!!");
		newAccountPage.isInfoNewCustomerBylabelAndTextDisplayed("Customer ID", customerID);
		newAccountPage.isInfoNewCustomerBylabelAndTextDisplayed("Customer Name", customerName);
		newAccountPage.isInfoNewCustomerBylabelAndTextDisplayed("Email", email);
		newAccountPage.isInfoNewCustomerBylabelAndTextDisplayed("Current Amount", "50000");
	}

	@Test
	public void PM_04_Tranfer_Money_Account() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("Deposit");

		depositPage = PageGeneratorManager.getDepositPage(driver);
		depositPage.inputToAccountNoTextbox(accountID);
		depositPage.inputToAmountTextbox("5000");
		depositPage.inputToDescriptionTextbox("Deposit money");
		depositPage.clickToSubmitButton();
	}

	@Test
	public void PM_05_Withdrawal_Money_Account() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("Withdrawal");

		withdrawalPage = PageGeneratorManager.getWithdrawalPage(driver);
		withdrawalPage.inputToAccountNoTextbox(accountID);
		withdrawalPage.inputToAmountTextbox("10000");
		withdrawalPage.inputToDescriptionTextbox("Withdrawal money");
		withdrawalPage.clickToSubmitButton();
		Assert.assertTrue(withdrawalPage.isWithdrawalSuccessMessageDiplayedWithText("Transaction details of Withdrawal for Account " + accountID));
	}

	@Test
	public void PM_06_Withdrawal_Money_Account() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("New Account");

		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		newAccountPage.inputToCustomerIDTextbox(customerID);
		newAccountPage.selectItemInAccountTypeDropdown("Savings");
		newAccountPage.inputToInitialDepositTextbox("45000");
		newAccountPage.clickToSubmitButton();
		newAccountPage.isCreateSuccessMessageDiplayedWithText("Account Generated Successfully!!!");
		accountIDPayer = newAccountPage.getAccountID();
		newAccountPage.openMenuItemByText("Fund Transfer");

		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		fundTransferPage.inputToPayerAccountNoTextbox(accountIDPayer);
		fundTransferPage.inputToPayeeAccountNoTextbox(accountID);
		fundTransferPage.inputToAmountTextbox("10000");
		fundTransferPage.inputToDescriptionTextbox("Fund transfer money");
		fundTransferPage.clickToSubmitButton();

		Assert.assertTrue(fundTransferPage.isFundTransfeSuccessMessageDiplayedWithText("Fund Transfer Details"));
		fundTransferPage.isInfoNewCustomerBylabelAndTextDisplayed("From Account Number", accountIDPayer);
		fundTransferPage.isInfoNewCustomerBylabelAndTextDisplayed("To Account Number", accountID);
		fundTransferPage.isInfoNewCustomerBylabelAndTextDisplayed("Amount", "10000");
		fundTransferPage.isInfoNewCustomerBylabelAndTextDisplayed("Description", "Fund transfer money");
	}

	@Test
	public void PM_07_Delete_Account_Exist() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("Delete Account");

		deleteAccountPage = PageGeneratorManager.getDeleteAccountPage(driver);
		deleteAccountPage.inputToAccountNoTextbox(accountID);
		deleteAccountPage.clickToSubmitButton();
		deleteAccountPage.acceptDeleteAlert();
	}

	@Test
	public void PM_08_Delete_Customer_Exist() {
		loginPage = newCustomerPage.openLoginPage();
		loginPage.inPutToUserIDTextbox(userID);
		loginPage.inPutToPasswordTextbox(userPassword);

		managePage = loginPage.clickToLoginButton();
		Assert.assertTrue(managePage.isManagerPageMessageDiplayedWithText("Welcome To Manager's Page of Guru99 Bank"));
		managePage.openMenuItemByText("Delete Customer");

		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToCustomerIDTextbox(customerID);
		deleteCustomerPage.clickToSubmitButton();
		deleteCustomerPage.acceptDeleteAlert();
		deleteCustomerPage.acceptDeleteAlert();
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
	private EditCustomerPageObject editCustomerPage;
	private NewAccountPageObject newAccountPage;
	private DepositPageObject depositPage;
	private WithdrawalPageObject withdrawalPage;
	private FundTransferPageObject fundTransferPage;
	private DeleteAccountPageObject deleteAccountPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	public String userEmail, userPassword, userID, customerID, accountID, accountIDPayer;
	private String customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, password, email;
	private String addressUpdate, cityUpdate, stateUpdate, pinUpdate, mobileNumberUpdate;

}