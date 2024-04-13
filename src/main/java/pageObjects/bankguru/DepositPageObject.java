
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.DepositPageUI;

public class DepositPageObject extends BasePage {
	WebDriver driver;

	public DepositPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'Account No' textbox by text value is '{text}'")
	public void inputToAccountNoTextbox(String text) {
		waitForElementVisible(DepositPageUI.ACCOUNT_NO_TEXTBOX);
		senkeyToElement(DepositPageUI.ACCOUNT_NO_TEXTBOX, text);
	}

	@Step("Input to 'Amount' textbox by text value is '{text}'")
	public void inputToAmountTextbox(String text) {
		waitForElementVisible(DepositPageUI.AMOUNT_TEXTBOX);
		senkeyToElement(DepositPageUI.AMOUNT_TEXTBOX, text);
	}

	@Step("Input to 'Description' textbox by text value is '{text}'")
	public void inputToDescriptionTextbox(String text) {
		waitForElementVisible(DepositPageUI.DESCRIPTION_TEXTBOX);
		senkeyToElement(DepositPageUI.DESCRIPTION_TEXTBOX, text);
	}

	@Step("Click To'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(DepositPageUI.SUBMIT_BUTTON);
		clickToElement(DepositPageUI.SUBMIT_BUTTON);
	}
}
