
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.FundTransferPageUI;

public class FundTransferPageObject extends BasePage {
	WebDriver driver;

	public FundTransferPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'Payers Account No' textbox by text value is '{text}'")
	public void inputToPayerAccountNoTextbox(String text) {
		waitForElementVisible(FundTransferPageUI.PAYERS_ACCOUNT_NO_TEXTBOX);
		senkeyToElement(FundTransferPageUI.PAYERS_ACCOUNT_NO_TEXTBOX, text);
	}

	@Step("Input to 'Payees Account No' textbox by text value is '{text}'")
	public void inputToPayeeAccountNoTextbox(String text) {
		waitForElementVisible(FundTransferPageUI.PAYEES_ACCOUNT_NO_TEXTBOX);
		senkeyToElement(FundTransferPageUI.PAYEES_ACCOUNT_NO_TEXTBOX, text);
	}

	@Step("Input to 'Amount' textbox by text value is '{text}'")
	public void inputToAmountTextbox(String text) {
		waitForElementVisible(FundTransferPageUI.AMOUNT_TEXTBOX);
		senkeyToElement(FundTransferPageUI.AMOUNT_TEXTBOX, text);
	}

	@Step("Input to 'Description' textbox by text value is '{text}'")
	public void inputToDescriptionTextbox(String text) {
		waitForElementVisible(FundTransferPageUI.DESCRIPTION_TEXTBOX);
		senkeyToElement(FundTransferPageUI.DESCRIPTION_TEXTBOX, text);
	}

	@Step("Click To'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(FundTransferPageUI.SUBMIT_BUTTON);
		clickToElement(FundTransferPageUI.SUBMIT_BUTTON);
	}

	@Step("Verify fund transfer succes message: '{message}' is diplayed")
	public boolean isFundTransfeSuccessMessageDiplayedWithText(String message) {
		waitForElementVisible(FundTransferPageUI.FUND_TRANSFER_SUCCESS_MESSAGE, message);
		return isElementDisPlayed(FundTransferPageUI.FUND_TRANSFER_SUCCESS_MESSAGE, message);
	}

	@Step("Verify infor: '{text}' at '{label}' textbox is displayed")
	public boolean isInfoNewCustomerBylabelAndTextDisplayed(String label, String text) {
		waitForElementVisible(FundTransferPageUI.FUND_TRANSFER_INFOR_BY_LABEL_AND_TEXT, label, text);
		return isElementDisPlayed(FundTransferPageUI.FUND_TRANSFER_INFOR_BY_LABEL_AND_TEXT, label, text);
	}
}
