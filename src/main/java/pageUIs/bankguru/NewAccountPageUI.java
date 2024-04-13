
package pageUIs.bankguru;

public class NewAccountPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "name=cusid";
	public static final String CUSTOMER_INIDEPOSIT_TEXTBOX = "name=inideposit";
	public static final String SUBMIT_BUTTON = "name=button2";
	public static final String ACCOUNT_TYPE_DROPDOWN = "name=selaccount";
	public static final String CREATE_ACCOUNT_SUCCESS_MESSAGE = "xpath=//p[@class='heading3' and text()='%s']";
	public static final String ACCOUNT_CREATED_INFOR_BY_LABEL_AND_TEXT = "xpath=//table//td[text()='%s']//following-sibling::td[contains(text(),'%s')]";
	public static final String ACCOUNT_ID = "xpath=//table//td[text()='Account ID']//following-sibling::td";
}
