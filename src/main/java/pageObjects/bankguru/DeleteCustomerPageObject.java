
package pageObjects.bankguru;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.DeleteCutomerPageUI;

public class DeleteCustomerPageObject extends BasePage {
	WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to CustomerID textbox by text value is '{text}'")
	public void inputToCustomerIDTextbox(String text) {
		waitForElementVisible(DeleteCutomerPageUI.CUSTOMERID_TEXTBOX);
		senkeyToElement(DeleteCutomerPageUI.CUSTOMERID_TEXTBOX, text);
	}

	@Step("send keys '{key}' To CustomerID Textbox")
	public void sendkeysToCustomerIDTextbox(Keys key) {
		waitForElementVisible(DeleteCutomerPageUI.CUSTOMERID_TEXTBOX);
		pressKeyToElement(DeleteCutomerPageUI.CUSTOMERID_TEXTBOX, key);
	}

	@Step("is Error Message '{message}' at CustomerID textbox Displayed")
	public boolean isErrorMessageByTextDisplayed(String message) {
		waitForElementVisible(DeleteCutomerPageUI.TEXTBOX_MESSAGE_BY_TEXT, message);
		return isElementDisPlayed(DeleteCutomerPageUI.TEXTBOX_MESSAGE_BY_TEXT, message);
	}

	@Step("Click To'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(DeleteCutomerPageUI.SUBMIT_BUTTON);
		clickToElement(DeleteCutomerPageUI.SUBMIT_BUTTON);
	}

	public void acceptDeleteAlert() {
		acceptAlert();
	}
}
