package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.bankguru.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inPutToUserIDTextbox(String userID) {
		waitForElementVisible(LoginPageUI.USER_ID_TEXTBOX);
		senkeyToElement(LoginPageUI.USER_ID_TEXTBOX, userID);
	}

	public void inPutToPasswordTextbox(String userPassword) {
		waitForElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(LoginPageUI.PASSWORD_TEXTBOX, userPassword);
	}

	public ManagePageObject clickToLoginButton() {
		waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
		clickToElement(LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getManagePage(driver);
	}

}