
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.DeleteAccountPageUI;

public class DeleteAccountPageObject extends BasePage {
	WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'Account No' textbox by text value is '{text}'")
	public void inputToAccountNoTextbox(String text) {
		waitForElementVisible(DeleteAccountPageUI.ACCOUNT_NO);
		senkeyToElement(DeleteAccountPageUI.ACCOUNT_NO, text);
	}

	@Step("Click To'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(DeleteAccountPageUI.SUBMIT_BUTTON);
		clickToElement(DeleteAccountPageUI.SUBMIT_BUTTON);
	}

	public void acceptDeleteAlert() {
		acceptAlert();
	}
}
