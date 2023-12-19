package pageObjects.liveGuru.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplay() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}

	public boolean isPopupDisplay() {
		waitForElementVisible(driver, AdminDashboardPageUI.POP_UP);
		return isElementDisplayed(driver, AdminDashboardPageUI.POP_UP);
	}

	public void closePopup() {
		clickToElement(driver, AdminDashboardPageUI.CLOSE_POPUP_BUTTON);

	}

	public void enterToHeaderTextboxByLable(String headerLable, String value) {
		waitForElementVisible(driver, AdminDashboardPageUI.HEADER_TEXTBOX_BY_LABLE, headerLable);
		sendkeyToElement(driver, AdminDashboardPageUI.HEADER_TEXTBOX_BY_LABLE, value, headerLable);
		pressKeyToElement(driver, AdminDashboardPageUI.HEADER_TEXTBOX_BY_LABLE, Keys.ENTER, headerLable);

	}

	public boolean isResultDisplay(String userName, String email) {
		waitForElementVisible(driver, AdminDashboardPageUI.SEARCH_RESULT, userName, email);
		return isElementDisplayed(driver, AdminDashboardPageUI.SEARCH_RESULT, userName, email);
	}

	public boolean isPopupUnDisplay() {
		waitForElementInvisible(driver, AdminDashboardPageUI.POP_UP);
		return isElementDisplayed(driver, AdminDashboardPageUI.POP_UP);
	}

}
