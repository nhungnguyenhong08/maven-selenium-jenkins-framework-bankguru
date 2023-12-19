package pageObject.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerLiveGuru;
import pageUIs.liveGuru.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToCreateAnAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return PageGeneratorManagerLiveGuru.getRegisterPage(driver);
	}

	public void inputToPasswordTextboxLogin(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX_LOGIN);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX_LOGIN, password);

	}

	public void inputToEmailTextboxLogin(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX_LOGIN);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX_LOGIN, email);

	}

	public UserMyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagerLiveGuru.getMyDashboardPage(driver);
	}

}
