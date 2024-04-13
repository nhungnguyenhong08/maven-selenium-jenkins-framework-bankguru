
package pageUIs.bankguru;

public class NewCustomerPageUI {
	public static final String SUBMIT_BUTTON = "name=sub";
	public static final String CUSTOMER_TEXTBOX_BY_LABEL = "xpath=//td[text()='%s']//following-sibling::td/*[1]";
	public static final String CUSTOMER_MESSAGE_BY_LABEL_AND_TEXT = "xpath=//td[text()='%s']//following-sibling::td/label[text()='%s']";
	public static final String GENDER_CHECKBOX_BY_TEXT_LABEL = "xpath=//td[text()='Gender']//following-sibling::td//input[@value='%s']";
	public static final String CUSTOMER_ID = "xpath=//table//td[text()='Customer ID']//following-sibling::td";
	public static final String CREATE_SUCCESS_MESSAGE = "xpath=//p[@class='heading3' and text()='%s']";
	public static final String CUSTOMER_CREATED_INFOR_BY_LABEL_AND_TEXT = "xpath=//table//td[text()='%s']//following-sibling::td[contains(text(),'%s')]";
}
