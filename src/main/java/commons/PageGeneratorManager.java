
package commons;

import org.openqa.selenium.WebDriver;

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

public class PageGeneratorManager {
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static ManagePageObject getManagePage(WebDriver driver) {
		return new ManagePageObject(driver);
	}

	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}

	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}

	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}

	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}

	public static DepositPageObject getDepositPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}

	public static WithdrawalPageObject getWithdrawalPage(WebDriver driver) {
		return new WithdrawalPageObject(driver);
	}

	public static DeleteAccountPageObject getDeleteAccountPage(WebDriver driver) {
		return new DeleteAccountPageObject(driver);
	}

	public static FundTransferPageObject getFundTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
}
