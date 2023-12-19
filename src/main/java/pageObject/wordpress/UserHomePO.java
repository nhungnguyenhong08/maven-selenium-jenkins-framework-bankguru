package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
	WebDriver driver;

	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInforDisplayedWithPostBody(String postTitle, String postBody) {
		waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
		return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
	}

	public boolean isPostInforDisplayedWithPostAuthor(String postTitle, String authorName) {
		waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInforDisplayedWithPostCurrentDay(String postTitle, String currentDay) {
		waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_BY_POST_TITLE, postTitle, currentDay);
	}

	public UserPostDetailPO clikToPostTitle(String postTitle) {
		waitForElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPageDetailPage(driver);
	}

	public boolean isPostInforDisplayedWithPostCurrentDayEdited(String editPostTile, String currentDay) {
		waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_BY_POST_TITLE_EDITED, editPostTile, currentDay);
		return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_BY_POST_TITLE_EDITED, editPostTile, currentDay);
	}

	public boolean isPostInforUndisplayedWithPostTitle(String editPostTile) {
		return isElementUndisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, editPostTile);
	}

	public void enterToSearchTextbox(String editPostTile) {
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTile);
		sendkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTile);
	}

	public UserSearchPostPO clickToSearchButton() {
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getUserSearchPostPage(driver);
	}

}
