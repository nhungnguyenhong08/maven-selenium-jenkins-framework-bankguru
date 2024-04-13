
package pageUIs.bankguru;

public class FundTransferPageUI {
	public static final String PAYERS_ACCOUNT_NO_TEXTBOX = "name=payersaccount";
	public static final String PAYEES_ACCOUNT_NO_TEXTBOX = "name=payeeaccount";
	public static final String AMOUNT_TEXTBOX = "name=ammount";
	public static final String DESCRIPTION_TEXTBOX = "name=desc";
	public static final String SUBMIT_BUTTON = "name=AccSubmit";
	public static final String FUND_TRANSFER_SUCCESS_MESSAGE = "xpath=//p[@class='heading3' and text()='%s']";
	public static final String FUND_TRANSFER_INFOR_BY_LABEL_AND_TEXT = "xpath=//table//td[text()='%s']//following-sibling::td[contains(text(),'%s')]";
}
