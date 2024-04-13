
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.EditCustomerPageUI;

public class EditCustomerPageObject extends BasePage {
	WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to 'CustomerID' Textbox by text value is '{text}'")
	public void inputToCustomerTextbox(String text) {
		waitForElementVisible(EditCustomerPageUI.CUSTOMER_ID_TEXTBOX);
		senkeyToElement(EditCustomerPageUI.CUSTOMER_ID_TEXTBOX, text);
	}

	@Step("Click To CustomerID 'Submit' Button")
	public void clickToCustomerIDSubmitButton() {
		waitForElementClickable(EditCustomerPageUI.CUSTOMERID_SUBMIT_BUTTON);
		clickToElement(EditCustomerPageUI.CUSTOMERID_SUBMIT_BUTTON);
	}

	@Step("Input to '{label}' textbox by text value is '{text}'")
	public void inputToTextboxByLabelName(String label, String text) {
		waitForElementVisible(EditCustomerPageUI.EDIT_CUSTOMER_TEXTBOX_BY_LABEL, label);
		senkeyToElement(EditCustomerPageUI.EDIT_CUSTOMER_TEXTBOX_BY_LABEL, text, label);
	}

	@Step("Verify error message: '{message}' at '{label}' textbox is displayed")
	public boolean isErrorMessageBylabeAndTextDisplayed(String label, String message) {
		waitForElementVisible(EditCustomerPageUI.EDIT_CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT, label, message);
		return isElementDisPlayed(EditCustomerPageUI.EDIT_CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT, label, message);
	}

	@Step("Click To Edit Customer 'Submit' Button")
	public void clickToEditCustomerSubmitButton() {
		waitForElementClickable(EditCustomerPageUI.EDIT_CUSTOMER_SUBMIT_BUTTON);
		clickToElement(EditCustomerPageUI.EDIT_CUSTOMER_SUBMIT_BUTTON);
	}

	public void acceptEditedCustomerAlert() {
		acceptAlert();
	}

	@Step("Verify infor: '{value}' at 'Adress' area textbox is displayed")
	public boolean isEditCutomerAddressByTextDisplayed(String text) {
		waitForElementVisible(EditCustomerPageUI.ADDRESS_INFOR_BY_TEXT, text);
		return isElementDisPlayed(EditCustomerPageUI.ADDRESS_INFOR_BY_TEXT, text);
	}

	@Step("Verify infor: '{value}' at '{label}' textbox is displayed")
	public boolean isInfoEditCutomerBylabelAndTextDisplayed(String label, String value) {
		waitForElementVisible(EditCustomerPageUI.EDIT_CUSTOMER_INFOR_BY_LABEL_AND_TEXT, label, value);
		return isElementDisPlayed(EditCustomerPageUI.EDIT_CUSTOMER_INFOR_BY_LABEL_AND_TEXT, label, value);
	}
}
