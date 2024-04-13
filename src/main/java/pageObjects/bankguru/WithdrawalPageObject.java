package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.WithdrawalPageUI;

public class WithdrawalPageObject extends BasePage {
	WebDriver driver;

	public WithdrawalPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'Account No' textbox by text value is '{text}'")
	public void inputToAccountNoTextbox(String text) {
		waitForElementVisible(WithdrawalPageUI.ACCOUNT_NO_TEXTBOX);
		senkeyToElement(WithdrawalPageUI.ACCOUNT_NO_TEXTBOX, text);
	}

	@Step("Input to 'Amount' textbox by text value is '{text}'")
	public void inputToAmountTextbox(String text) {
		waitForElementVisible(WithdrawalPageUI.AMOUNT_TEXTBOX);
		senkeyToElement(WithdrawalPageUI.AMOUNT_TEXTBOX, text);
	}

	@Step("Input to 'Description' textbox by text value is '{text}'")
	public void inputToDescriptionTextbox(String text) {
		waitForElementVisible(WithdrawalPageUI.DESCRIPTION_TEXTBOX);
		senkeyToElement(WithdrawalPageUI.DESCRIPTION_TEXTBOX, text);
	}

	@Step("Click To'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(WithdrawalPageUI.SUBMIT_BUTTON);
		clickToElement(WithdrawalPageUI.SUBMIT_BUTTON);
	}

	@Step("Verify withdrawal succes message: '{message}' is diplayed")
	public boolean isWithdrawalSuccessMessageDiplayedWithText(String message) {
		waitForElementVisible(WithdrawalPageUI.WITHDRAWAL_SUCCESS_MESSAGE, message);
		return isElementDisPlayed(WithdrawalPageUI.WITHDRAWAL_SUCCESS_MESSAGE, message);
	}
}
