
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankguru.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inPutToEmailTextbox(String email) {
		waitForElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserID() {
		waitForElementVisible(RegisterPageUI.USER_ID);
		return getElementText(RegisterPageUI.USER_ID).trim();
	}

	public String getUserPassword() {
		waitForElementVisible(RegisterPageUI.USER_PASSWORD);
		return getElementText(RegisterPageUI.USER_PASSWORD).trim();
	}
}
