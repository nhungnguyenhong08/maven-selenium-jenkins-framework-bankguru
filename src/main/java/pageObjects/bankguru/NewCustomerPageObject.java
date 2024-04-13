
package pageObjects.bankguru;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.NewCustomerPageUI;

public class NewCustomerPageObject extends BasePage {
	WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to '{label}' textbox by text value is '{text}'")
	public void inputToTextboxByLabelName(String label, String text) {
		waitForElementVisible(NewCustomerPageUI.CUSTOMER_TEXTBOX_BY_LABEL, label);
		senkeyToElement(NewCustomerPageUI.CUSTOMER_TEXTBOX_BY_LABEL, text, label);
	}

	@Step("Send keys: '{key}' to '{label}' textbox ")
	public void sendkeysToNewCustomerAreaTextboxBylabel(String label, Keys key) {
		waitForElementClickable(NewCustomerPageUI.CUSTOMER_TEXTBOX_BY_LABEL, label);
		senkeyToElement(NewCustomerPageUI.CUSTOMER_TEXTBOX_BY_LABEL, Keys.chord(key), label);
	}

	@Step("Verify error message: '{message}' at '{label}' textbox is displayed")
	public boolean isErrorMessageBylabeAndTextDisplayed(String label, String message) {
		waitForElementVisible(NewCustomerPageUI.CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT, label, message);
		return isElementDisPlayed(NewCustomerPageUI.CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT, label, message);
	}

	@Step("Select 'Gender' radio checkbox By label is '{gender}'")
	public void selectGenderRadioCheckboxByTextlabel(String gender) {
		if (gender.toLowerCase().startsWith("m")) {
			gender = "m";
		} else {
			gender = "f";
		}
		waitForElementClickable(NewCustomerPageUI.GENDER_CHECKBOX_BY_TEXT_LABEL, gender);
		checkToDefaultCheckboxOrRadio(NewCustomerPageUI.GENDER_CHECKBOX_BY_TEXT_LABEL, gender);
	}

	@Step("Click To new customer 'Submit' button")
	public void clickToSubmitButton() {
		waitForElementClickable(NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(NewCustomerPageUI.SUBMIT_BUTTON);
	}

	@Step("Get 'CustomerID' in table customer infor")
	public String getCustomerID() {
		waitForElementVisible(NewCustomerPageUI.CUSTOMER_ID);
		return getElementText(NewCustomerPageUI.CUSTOMER_ID);
	}

	@Step("Verify create succes message: '{message}' is diplayed")
	public boolean isCreateSuccessMessageDiplayedWithText(String message) {
		waitForElementVisible(NewCustomerPageUI.CREATE_SUCCESS_MESSAGE, message);
		return isElementDisPlayed(NewCustomerPageUI.CREATE_SUCCESS_MESSAGE, message);
	}

	@Step("Verify infor: '{text}' at '{label}' textbox is displayed")
	public boolean isInfoNewCustomerBylabelAndTextDisplayed(String label, String text) {
		if (label.equals("Gender")) {
			text = text.toLowerCase();
		}
		waitForElementVisible(NewCustomerPageUI.CUSTOMER_CREATED_INFOR_BY_LABEL_AND_TEXT, label, text);
		return isElementDisPlayed(NewCustomerPageUI.CUSTOMER_CREATED_INFOR_BY_LABEL_AND_TEXT, label, text);
	}
}
