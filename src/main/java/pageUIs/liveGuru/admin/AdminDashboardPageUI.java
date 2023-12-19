package pageUIs.liveGuru.admin;

public class AdminDashboardPageUI {

	public static final String DASHBOARD_HEADER = "xpath=//div[@class='header-top']/a/img[@class='logo']";
	public static final String POP_UP = "css=div#message-popup-window";
	public static final String CLOSE_POPUP_BUTTON = "xpath=//a[@title='close']";

	public static final String HEADER_TEXTBOX_BY_LABLE = "xpath=//input[@name='%s']";

	public static final String SEARCH_RESULT = "xpath=//td[contains(text(),'%s')]//following-sibling::td[contains(text(),'%s')]";

}
