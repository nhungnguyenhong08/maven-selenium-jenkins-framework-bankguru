
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.NewAccountPageUI;

public class NewAccountPageObject extends BasePage {
	WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'CustomerID' textbox by text value is '{text}'")
	public void inputToCustomerIDTextbox(String text) {
		waitForElementVisible(NewAccountPageUI.CUSTOMER_ID_TEXTBOX);
		senkeyToElement(NewAccountPageUI.CUSTOMER_ID_TEXTBOX, text);
	}

	@Step("Input to 'Initial deposit' textbox by text value is '{text}'")
	public void inputToInitialDepositTextbox(String text) {
		waitForElementVisible(NewAccountPageUI.CUSTOMER_INIDEPOSIT_TEXTBOX);
		senkeyToElement(NewAccountPageUI.CUSTOMER_INIDEPOSIT_TEXTBOX, text);
	}

	@Step("Select item in 'Account Type' dropdown by text value is '{text}'")
	public void selectItemInAccountTypeDropdown(String text) {
		waitForElementClickable(NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInDefaultDropDown(NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, text);
	}

	@Step("Click To new customer 'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(NewAccountPageUI.SUBMIT_BUTTON);
		clickToElement(NewAccountPageUI.SUBMIT_BUTTON);
	}

	@Step("Verify create succes message: '{message}' is diplayed")
	public boolean isCreateSuccessMessageDiplayedWithText(String message) {
		waitForElementVisible(NewAccountPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE, message);
		return isElementDisPlayed(NewAccountPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE, message);
	}

	@Step("Verify infor: '{text}' at '{label}' textbox is displayed")
	public boolean isInfoNewCustomerBylabelAndTextDisplayed(String label, String text) {
		waitForElementVisible(NewAccountPageUI.ACCOUNT_CREATED_INFOR_BY_LABEL_AND_TEXT, label, text);
		return isElementDisPlayed(NewAccountPageUI.ACCOUNT_CREATED_INFOR_BY_LABEL_AND_TEXT, label, text);
	}

	@Step("Get 'AccountID' in table customer infor")
	public String getAccountID() {
		waitForElementVisible(NewAccountPageUI.ACCOUNT_ID);
		return getElementText(NewAccountPageUI.ACCOUNT_ID);
	}
}
