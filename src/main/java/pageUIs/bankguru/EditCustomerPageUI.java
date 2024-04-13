
package pageUIs.bankguru;

public class EditCustomerPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "name=cusid";
	public static final String CUSTOMERID_SUBMIT_BUTTON = "name=AccSubmit";
	public static final String EDIT_CUSTOMER_SUBMIT_BUTTON = "name=sub";
	public static final String EDIT_CUSTOMER_TEXTBOX_BY_LABEL = "xpath=//td[text()='%s']//following-sibling::td/*[1]";
	public static final String EDIT_CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT = "xpath=//td[text()='%s']//following-sibling::td/label[text()='%s']";
	public static final String EDIT_CUSTOMER_INFOR_BY_LABEL_AND_TEXT = "xpath=//table//td[text()='%s']//following-sibling::td/input[@value='%s']";
	public static final String ADDRESS_INFOR_BY_TEXT = "xpath=//table//td[text()='Address']//following-sibling::td/textarea[text()='%s']";
}
