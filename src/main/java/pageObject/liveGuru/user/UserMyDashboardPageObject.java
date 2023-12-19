package pageObject.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagerLiveGuru;
import pageUIs.liveGuru.MyDashboardPageUI;

public class UserMyDashboardPageObject extends BasePage {
	private WebDriver driver;

	public UserMyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, MyDashboardPageUI.LOGOUT_LINK);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_LINK);
		return PageGeneratorManagerLiveGuru.getUserHomePage(driver);
	}

	public String getRegisterSucessMessage() {
		waitForElementVisible(driver, MyDashboardPageUI.REGISTER_SUCESS_MESSAGE);
		return getElementText(driver, MyDashboardPageUI.REGISTER_SUCESS_MESSAGE);

	}

	public void clickToAccountWrapper() {
		waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_WRAPPER);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_WRAPPER);
	}

}
