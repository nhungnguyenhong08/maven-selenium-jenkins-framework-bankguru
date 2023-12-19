package pageObject.sauceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sauceLab.LoginPagePageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextbox(String userName) {
		waitForElementVisible(driver, LoginPagePageUI.USER_NAME_TEXTBOX, userName);
		sendkeyToElement(driver, LoginPagePageUI.USER_NAME_TEXTBOX, userName);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPagePageUI.PASSWORD_TEXTBOX, password);
		sendkeyToElement(driver, LoginPagePageUI.PASSWORD_TEXTBOX, password);
	}

	public ProductPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPagePageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPagePageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPage(driver);
	}

}
