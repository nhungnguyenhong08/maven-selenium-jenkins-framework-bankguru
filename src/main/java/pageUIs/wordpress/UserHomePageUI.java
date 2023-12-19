package pageUIs.wordpress;

public class UserHomePageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h2/a[text()='%s']";
	public static final String POST_CURRENT_DATE_BY_POST_TITLE = "xpath=//article//a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']/parent::h2/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
	public static final String POST_CURRENT_DATE_BY_POST_TITLE_EDITED = "xpath=//article//a[text()='%s']/parent::h2/following-sibling::div//time[@class='entry-date published' and text()='%s']";
	public static final String SEARCH_TEXTBOX = "xpath=//h2[text()='Search post']/following-sibling::form[@class='search-form']//input[@class='search-field']";
	public static final String SEARCH_BUTTON = "xpath=//h2[text()='Search post']/following-sibling::form[@class='search-form']//input[@class='search-submit']";
}
