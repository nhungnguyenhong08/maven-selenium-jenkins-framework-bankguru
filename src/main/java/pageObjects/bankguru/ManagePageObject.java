
package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.bankguru.ManagePageUI;

public class ManagePageObject extends BasePage {
	WebDriver driver;

	public ManagePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify 'Manager' page message: '{message}' is diplayed")
	public boolean isManagerPageMessageDiplayedWithText(String message) {
		waitForElementVisible(ManagePageUI.MANAGER_PAGE_MESSAGE_BY_TEXT, message);
		return isElementDisPlayed(ManagePageUI.MANAGER_PAGE_MESSAGE_BY_TEXT, message);
	}

	@Step("Click to avertiment 'Close' Button")
	public void clickToAvertimentDismissButton() {
		if (!isElementUndisplayed(ManagePageUI.ADVERTISEMENT_IFRAME)) {
			switchToFrameIframe(ManagePageUI.ADVERTISEMENT_IFRAME);

			if (!isElementUndisplayed(ManagePageUI.ADVERTISEMENT_DISMISS_BUTTON)) {
				waitForElementClickable(ManagePageUI.ADVERTISEMENT_DISMISS_BUTTON);
				clickToElement(ManagePageUI.ADVERTISEMENT_DISMISS_BUTTON);
			} else if (!isElementUndisplayed(ManagePageUI.ADVERTISEMENT_SUB_IFRAME)) {
				switchToFrameIframe(ManagePageUI.ADVERTISEMENT_SUB_IFRAME);
				waitForElementClickable(ManagePageUI.ADVERTISEMENT_DISMISS_BUTTON);
				clickToElement(ManagePageUI.ADVERTISEMENT_DISMISS_BUTTON);
				switchToParentFrame();
			}
			switchToDefaultContent();
		}
	}
}
