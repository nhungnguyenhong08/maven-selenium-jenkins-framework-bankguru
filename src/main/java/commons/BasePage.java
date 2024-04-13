package commons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pageObjects.bankguru.LoginPageObject;
import pageUIs.bankguru.BasePageBankGuruUI;

/**
 * @author Admin
 *
 */
public class BasePage {
	private WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Opens the specified page URL.
	 *
	 * @param pageUrl The URL of the page to be opened.
	 * @author ThachNk
	 */
	protected void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	/**
	 * Retrieves the title of the current page.
	 *
	 * @return The title of the current page.
	 * @author ThachNk
	 */
	protected String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * Retrieves the URL of the current page.
	 *
	 * @return The URL of the current page.
	 * @author ThachNk
	 */
	protected String getPageUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Retrieves the source code of the current page.
	 *
	 * @return The source code of the current page.
	 * @author ThachNk
	 */
	protected String getPageSourceCode() {
		return driver.getPageSource();
	}

	/**
	 * Refreshes the current page.
	 * 
	 * @author ThachNk
	 */
	protected void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	/**
	 * Retrieves all cookies present on the current page.
	 *
	 * @return A set of cookies on the current page.
	 * @author ThachNk
	 */
	protected Set<Cookie> getAllCookies() {
		return driver.manage().getCookies();
	}

	/**
	 * Sets the provided cookies for the current page.
	 *
	 * @param cookies The set of cookies to be set.
	 * @author ThachNk
	 */
	protected void setCookies(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	/**
	 * Navigates back to the previous page.
	 * 
	 * @author ThachNk
	 */
	protected void backToPage() {
		driver.navigate().back();
	}

	/**
	 * Waits for the presence of an alert and returns the alert instance.
	 *
	 * @return The alert instance once present.
	 * @author ThachNk
	 */
	protected Alert waitForAlertPresence() {
		WebDriverWait explicitwait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		return explicitwait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Accepts the currently present alert.
	 * 
	 * @author ThachNk
	 */
	protected void acceptAlert() {
		waitForAlertPresence().accept();
	}

	/**
	 * Dismisses the currently present alert.
	 * 
	 * @author ThachNk
	 */
	protected void cancelAlert() {
		waitForAlertPresence().dismiss();
	}

	/**
	 * Retrieves the text content of the currently present alert.
	 *
	 * @return The text content of the alert.
	 * @author ThachNk
	 */
	protected String getlAlertText() {
		return waitForAlertPresence().getText();
	}

	/**
	 * Sends keys to the currently present alert.
	 *
	 * @param textValue The text to be sent to the alert.
	 * @author ThachNk
	 */
	protected void sendKeyTolAlert(String textValue) {
		waitForAlertPresence().sendKeys(textValue);
	}

	/**
	 * Switches to a window identified by its ID.
	 *
	 * @param windowID The ID of the window to switch to.
	 * @author ThachNk
	 */
	protected void switchToWindowByID(String WindowsID) {
		Set<String> allWindowsIDs = driver.getWindowHandles();
		for (String id : allWindowsIDs) {
			if (!id.equals(WindowsID)) {
				driver.switchTo().window(id);
			}
		}
	}

	/**
	 * Switches to a window identified by its page title.
	 *
	 * @param tabTitle The title of the window to switch to.
	 * @author ThachNk
	 */
	protected void switchToWindowByPageTitle(String tabTitle) {
		Set<String> allWindowsIDs = driver.getWindowHandles();
		for (String id : allWindowsIDs) {
			driver.switchTo().window(id);
			String actuaPageTitle = driver.getTitle();
			if (actuaPageTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	/**
	 * Closes all windows except the parent window.
	 *
	 * @param parentID The ID of the parent window.
	 * @author ThachNk
	 */
	protected void closeAllWindow_WithoutParent(String parentID) {
		Set<String> allWindowsIDs = driver.getWindowHandles();
		for (String id : allWindowsIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/**
	 * Retrieves the By object based on the provided locator type and value.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The By object corresponding to the locator type and value.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		String[] locatorParts = locatorType.split("=", 2);
		if (locatorParts.length != 2) {
			throw new IllegalArgumentException("Invalid locator format");
		}
		String typeLocator = locatorParts[0].trim().toLowerCase();
		String locator = locatorParts[1].trim();

		switch (typeLocator) {
		case "id":
			by = By.id(locator);
			break;
		case "class":
			by = By.className(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "xpath":
			by = By.xpath(locator);
			break;
		case "css":
			by = By.cssSelector(locator);
			break;
		default:
			throw new IllegalArgumentException("LocatorType is not supported");
		}
		return by;
	}

	/**
	 * Retrieves the dynamic XPath by formatting it with the provided values.
	 *
	 * @param locatorType The type of the locator (e.g., xpath).
	 * @param value       The values to be used in formatting the XPath.
	 * @return The formatted dynamic XPath.
	 * @author ThachNk
	 */
	private String getDynamicXpath(String locatorType, String... value) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) value);
		}
		return locatorType;
	}

	/**
	 * Retrieves the WebElement based on the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The WebElement corresponding to the locator type.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	public WebElement getWebElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	/**
	 * Retrieves a list of WebElements based on the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The list of WebElements corresponding to the locator type.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected List<WebElement> getListWebElement(String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	/**
	 * Clicks on the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void clickToElement(String locatorType) {
		WebElement element = driver.findElement(getByLocator(locatorType));
		highlightElement(locatorType);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(locatorType);
			sleepInSecond(3);
		} else {
			element.click();
		}
	}

	/**
	 * Clicks on the WebElement identified by the provided dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void clickToElement(String locatorType, String... dynamicValues) {
		WebElement element = driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
		highlightElement(locatorType, dynamicValues);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(locatorType, dynamicValues);
			sleepInSecond(3);
		} else {
			element.click();
		}
	}

	/**
	 * Sends keys to the WebElement identified by the provided locator type after clearing its existing value.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @param textValue   The text value to be sent to the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void senkeyToElement(String locatorType, String textValue) {
		WebElement element = getWebElement(locatorType);
		highlightElement(locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * Sends keys to the WebElement identified by the dynamic locator type after clearing its existing value.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param textValue     The text value to be sent to the WebElement.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void senkeyToElement(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		highlightElement(locatorType, dynamicValues);
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * Sends keys to the WebElement identified by the provided locator type after clearing its existing value, using the "Delete" key to clear the value.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @param textValue   The text value to be sent to the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void senkeyToElementUserClearByDeleteKey(String locatorType, String textValue) {
		WebElement element = getWebElement(locatorType);
		waitForElementClickable(locatorType);
		clickToElement(locatorType);
		clearValueInElementByDeleteKey(locatorType);
		element.sendKeys(textValue);
	}

	/**
	 * Sends keys to the WebElement identified by the dynamic locator type after clearing its existing value, using the "Delete" key to clear the value.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param textValue     The text value to be sent to the WebElement.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void senkeyToElementUserClearByDeleteKey(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		waitForElementClickable(getDynamicXpath(locatorType, dynamicValues));
		clickToElement(getDynamicXpath(locatorType, dynamicValues));
		clearValueInElementByDeleteKey(getDynamicXpath(locatorType, dynamicValues));
		element.sendKeys(textValue);
	}

	/**
	 * Clears the value in the WebElement identified by the provided locator type using the "Delete" key.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void clearValueInElementByDeleteKey(String locatorType) {
		WebElement element = getWebElement(locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	/**
	 * Selects an item by visible text in the default dropdown identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @param textItem    The visible text of the item to be selected.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void selectItemInDefaultDropDown(String locatorType, String textItem) {
		Select select = new Select(getWebElement(locatorType));
		select.selectByVisibleText(textItem);
	}

	/**
	 * Selects an item by visible text in the default dropdown identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param textItem      The visible text of the item to be selected.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void selectItemInDefaultDropDown(String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	/**
	 * Retrieves the visible text of the first selected item in the default dropdown identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The visible text of the first selected item.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getSelectedItemDefaultDropdown(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Checks if the default dropdown identified by the provided locator type allows multiple selections.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the dropdown allows multiple selections, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isDropdownMultiple(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.isMultiple();
	}

	/**
	 * Selects an item by visible text in a custom dropdown identified by parent and child XPath.
	 *
	 * @param parentXpath      The XPath of the parent element to open the custom dropdown.
	 * @param childXpath       The XPath of the child elements within the dropdown.
	 * @param expectedTextItem The visible text of the item to be selected.
	 * @throws IllegalArgumentException If the XPath format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedTextItem) {
		clickToElement(parentXpath);
		sleepInSecond(1);

		WebDriverWait explicitwait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		List<WebElement> speeDropdownItems = explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : speeDropdownItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	/**
	 * Pauses the execution for the specified duration in seconds.
	 *
	 * @param timeInSeconds The duration to pause execution in seconds.
	 * @throws IllegalArgumentException If the provided time is negative.
	 * @author ThachNk
	 */
	protected void sleepInSecond(double d) {
		try {
			Thread.sleep((long) (d * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the value of the specified attribute of the WebElement identified by the provided locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param attributeName The name of the attribute whose value is to be retrieved.
	 * @return The value of the specified attribute.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementAttribute(String locatorType, String attributeName) {
		return getWebElement(locatorType).getAttribute(attributeName);
	}

	/**
	 * Retrieves the value of the specified attribute of the WebElement identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param attributeName The name of the attribute whose value is to be retrieved.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return The value of the specified attribute.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementAttribute(String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	/**
	 * Retrieves the visible text of the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The visible text of the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementText(String locatorType) {
		return getWebElement(locatorType).getText();
	}

	/**
	 * Retrieves the visible text of the WebElement identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return The visible text of the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementText(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	/**
	 * Retrieves the value of the specified CSS property of the WebElement identified by the provided locator type.
	 *
	 * @param locatorType  The type of the locator (e.g., id, class, xpath).
	 * @param propertyName The name of the CSS property whose value is to be retrieved.
	 * @return The value of the specified CSS property.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementCssValue(String locatorType, String propertyName) {
		return getWebElement(locatorType).getCssValue(propertyName);
	}

	/**
	 * Retrieves the value of the specified CSS property of the WebElement identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param propertyName  The name of the CSS property whose value is to be retrieved.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return The value of the specified CSS property.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected String getElementCssValue(String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	/**
	 * Converts an RGBA color value to its hexadecimal representation.
	 *
	 * @param rgbaValue The RGBA color value to be converted.
	 * @return The hexadecimal representation of the RGBA color value.
	 * @throws IllegalArgumentException If the provided RGBA color value is invalid.
	 * @author ThachNk
	 */
	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	/**
	 * Retrieves the number of WebElements identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The number of WebElements.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected int getElementSize(String locatorType) {
		return getListWebElement(locatorType).size();
	}

	/**
	 * Retrieves the number of WebElements identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return The number of WebElements.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected int getElementSize(String locatorType, String... dynamicValues) {
		return getListWebElement(getDynamicXpath(locatorType, dynamicValues)).size();
	}

	/**
	 * Checks a default checkbox or radio button identified by the provided locator type. If it is not already selected, it will be selected.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void checkToDefaultCheckboxOrRadio(String locatorType) {
		WebElement element = getWebElement(locatorType);
		highlightElement(locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Checks a default checkbox or radio button identified by the dynamic locator type. If it is not already selected, it will be selected.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void checkToDefaultCheckboxOrRadio(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		highlightElement(locatorType, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Unchecks a default checkbox or radio button identified by the provided locator type. If it is already selected, it will be unselected.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void unCheckToDefaultCheckboxRadio(String locatorType) {
		WebElement element = getWebElement(locatorType);
		highlightElement(locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Unchecks a default checkbox or radio button identified by the dynamic locator type. If it is already selected, it will be unselected.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void unCheckToDefaultCheckboxRadio(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		highlightElement(locatorType, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Checks if the WebElement identified by the provided locator type is displayed.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the WebElement is displayed, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementDisPlayed(String locatorType) {
		highlightElement(locatorType);
		try {
			return getWebElement(locatorType).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the WebElement identified by the dynamic locator type is displayed.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return True if the WebElement is displayed, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementDisPlayed(String locatorType, String... dynamicValues) {
		highlightElement(locatorType, dynamicValues);
		try {
			return getWebElement(getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the WebElement identified by the provided locator type is undisplayed.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the WebElement is undisplayed, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementUndisplayed(String locatorType) {
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getShortTimeOut());
		List<WebElement> elements = getListWebElement(locatorType);
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getLongTimeOut());

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the WebElement identified by the dynamic locator type is undisplayed.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return True if the WebElement is undisplayed, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getShortTimeOut());
		List<WebElement> elements = getListWebElement(getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getLongTimeOut());

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Overrides the implicit timeout of the WebDriver with the specified time in seconds.
	 *
	 * @param timeOut The new implicit timeout value in seconds.
	 * @throws IllegalArgumentException If the provided time is negative.
	 * @author ThachNk
	 */
	protected void overrideImplicitTimeout(long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * Checks if the WebElement identified by the provided locator type is enabled.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the WebElement is enabled, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementEnable(String locatorType) {
		return getWebElement(locatorType).isEnabled();
	}

	/**
	 * Checks if the WebElement identified by the provided locator type is selected.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the WebElement is selected, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementSelected(String locatorType) {
		return getWebElement(locatorType).isSelected();
	}

	/**
	 * Checks if the WebElement identified by the dynamic locator type is selected.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return True if the WebElement is selected, false otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected boolean isElementSelected(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	/**
	 * Switches to the frame or iframe identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void switchToFrameIframe(String locatorType) {
		driver.switchTo().frame(getWebElement(locatorType));
	}

	/**
	 * Switches back to the default content.
	 *
	 * @author ThachNk
	 */
	protected void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Switches back to the Parent Frame.
	 *
	 * @author ThachNk
	 */
	protected void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	/**
	 * Hovers the mouse over the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void hoverMouseToElement(String locatorType) {
		Actions action = new Actions(driver);
		highlightElement(locatorType);
		action.moveToElement(getWebElement(locatorType)).perform();
	}

	/**
	 * Hovers the mouse over the WebElement identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void hoverMouseToElement(String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		highlightElement(locatorType, dynamicValues);
		action.moveToElement(getWebElement(getDynamicXpath(locatorType, dynamicValues))).perform();
	}

	/**
	 * Presses the specified key to the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @param key         The key to be pressed (e.g., Keys.ENTER, Keys.ARROW_UP).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void pressKeyToElement(String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(locatorType), key).perform();
	}

	/**
	 * Presses the specified key to the WebElement identified by the dynamic locator type.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param key           The key to be pressed (e.g., Keys.ENTER, Keys.ARROW_UP).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	/**
	 * Scrolls to the bottom of the page using JavaScriptExecutor.
	 *
	 * @throws JavaScriptException If the execution of JavaScript fails.
	 * @author ThachNk
	 */
	protected void scrollToBottomPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Highlights the WebElement identified by the provided locator type. The element is briefly outlined with a red dashed border.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void highlightElement(String locatorType) {
		WebElement element = getWebElement(locatorType);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(0.2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	/**
	 * Highlights the WebElement identified by the provided locator type. The element is briefly outlined with a red dashed border.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @author ThachNk
	 */
	protected void highlightElement(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(0.2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	/**
	 * Clicks the WebElement identified by the provided locator type using JavaScriptExecutor.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see #getWebElement(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void clickToElementByJS(String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locatorType));
	}

	/**
	 * Clicks the WebElement identified by the dynamic locator type using JavaScriptExecutor.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see #getWebElement(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void clickToElementByJS(String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	/**
	 * Scrolls the browser window to bring the WebElement identified by the provided locator type into view using JavaScriptExecutor.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected void scrollToElement(String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
	}

	/**
	 * Scrolls the browser window to bring the WebElement identified by the dynamic locator type into view using JavaScriptExecutor.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see #getWebElement(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void scrollToElement(String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	/**
	 * Gets the value of the specified attribute using JavaScriptExecutor for the WebElement identified by the provided XPath.
	 *
	 * @param xpathLocator The XPath locator for the WebElement.
	 * @return The value of the attribute specified in the JavaScriptExecutor.
	 * @throws IllegalArgumentException If the XPath locator format is invalid or not supported.
	 * @throws JavaScriptException      If the execution of JavaScript fails.
	 * @author ThachNk
	 */
	protected String getElementValueByJSXpath(String xpathLocator) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		if (xpathLocator.startsWith("xpath=")) {
			xpathLocator = xpathLocator.replace("xpath=", "");
		}
		return (String) jsExcutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}

	/**
	 * Gets the value of the specified attribute using JavaScriptExecutor for the WebElement identified by the dynamic XPath.
	 *
	 * @param xpathLocator  The XPath locator for the WebElement.
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return The value of the attribute specified in the JavaScriptExecutor.
	 * @throws IllegalArgumentException If the XPath locator format is invalid or not supported.
	 * @throws JavaScriptException      If the execution of JavaScript fails.
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected String getElementValueByJSXpath(String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		if (getDynamicXpath(xpathLocator, dynamicValues).startsWith("xpath=")) {
			xpathLocator = getDynamicXpath(xpathLocator, dynamicValues).replace("xpath=", "");
		}
		return (String) jsExcutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}

	/**
	 * Removes the specified attribute from the WebElement identified by the provided locator type using JavaScriptExecutor.
	 *
	 * @param locatorType     The type of the locator (e.g., id, class, xpath).
	 * @param attributeRemove The attribute to be removed.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @throws JavaScriptException      If the execution of JavaScript fails.
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected void removeAttributeInDOM(String locatorType, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locatorType));
	}

	/**
	 * Checks if jQuery and JavaScript are loaded successfully.
	 *
	 * @return {@code true} if jQuery and JavaScript are loaded successfully, {@code false} otherwise.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see WebDriverWait
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see JavascriptExecutor#executeScript(String, Object...)
	 * @author ThachNk
	 */
	protected boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/**
	 * Gets the shadow DOM of the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The shadow DOM of the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see JavascriptExecutor#executeScript(String, Object...)
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected WebElement getShadowDOM(String locatorType) {
		return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot;", getWebElement(locatorType));
	}

	/**
	 * Gets the validation message of the WebElement identified by the provided locator type.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return The validation message of the WebElement.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see JavascriptExecutor#executeScript(String, Object...)
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected String getElementValidationMessage(String locatorType) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(locatorType));
	}

	/**
	 * Checks if the image identified by the provided locator type is loaded.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return {@code true} if the image is loaded, {@code false} otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see JavascriptExecutor#executeScript(String, Object...)
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected boolean isImageLoaded(String locatorType) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the image identified by the dynamic locator type is loaded.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @return {@code true} if the image is loaded, {@code false} otherwise.
	 * @throws IllegalArgumentException If the locator format is invalid or not supported.
	 * @see JavascriptExecutor#executeScript(String, Object...)
	 * @see #getWebElement(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected boolean isImageLoaded(String locatorType, String... dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Waits for the element identified by the provided locator type to be visible.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #overrideImplicitTimeout(long)
	 * @author ThachNk
	 */
	protected void waitForElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getLongTimeOut());
	}

	/**
	 * Waits for the dynamic element identified by the provided locator type to be visible.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForElementVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Waits for the element identified by the provided locator type to become invisible.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForElementInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getMediumTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/**
	 * Waits for the dynamic element identified by the provided locator type to become invisible.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForElementInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getMediumTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Waits for all elements identified by the provided locator type to become visible.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForAllElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	/**
	 * Waits for all dynamic elements identified by the provided locator type to become visible.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForAllElementVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Waits for all elements identified by the provided locator type to become invisible.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNkk
	 */
	protected void waitForAllElementInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getMediumTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locatorType)));
	}

	/**
	 * Waits for all dynamic elements identified by the provided locator type to become invisible.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see ExpectedCondition#apply(WebDriver)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNkk
	 */
	protected void waitForAllElementInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getMediumTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Waits for an element identified by the provided locator type to become invisible.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @see #overrideImplicitTimeout(long)
	 * @author ThachNk
	 */
	protected void waitForElementUndisplay(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getShortTimeOut());
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getShortTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getLongTimeOut());
	}

	/**
	 * Waits for a dynamic element identified by the provided locator type to become invisible.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @see #overrideImplicitTimeout(long)
	 * @author ThachNk
	 */
	protected void waitForElementUndisplay(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getShortTimeOut());
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getShortTimeOut());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(GlobalConstants.getGlobalConstants().getLongTimeOut());
	}

	/**
	 * Waits for an element identified by the provided locator type to become clickable.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForElementClickable(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	/**
	 * Waits for a dynamic element identified by the provided locator type to become clickable.
	 *
	 * @param locatorType   The type of the locator (e.g., id, class, xpath).
	 * @param dynamicValues The dynamic values to be used in formatting the XPath.
	 * @throws TimeoutException If the specified timeout is exceeded.
	 * @see #getByLocator(String)
	 * @see #getDynamicXpath(String, String...)
	 * @author ThachNk
	 */
	protected void waitForElementClickable(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.getGlobalConstants().getLongTimeOut());
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Uploads multiple files.
	 *
	 * @param fileNames The names of the files to be uploaded.
	 * @see GlobalConstants#getGlobalConstants()
	 * @see #getWebElement(String)
	 * @author ThachNk
	 */
	protected void uploadMultipleFiles(String... fileNames) {
		// Đường dẫn của thư muc Upload file : Windows\ Mac\ Linux
		String filePath = GlobalConstants.getGlobalConstants().getUploadFile();

		// Đường dẩn của all File
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(BasePageBankGuruUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	/**
	 * Checks if all items have text names sorted in ascending order.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the items are sorted in ascending order; false otherwise.
	 * @see #sleepInSecond(long)
	 * @see #getListWebElement(String)
	 * @author ThachNk
	 */
	protected boolean isAllItemHaveTextNameSortByAscending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		System.out.println("Sorted list (sortedNames): " + sortedNames);
		return names.equals(sortedNames);
	}

	/**
	 * Checks if all items have text names sorted in descending order.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the items are sorted in descending order; false otherwise.
	 * @see #sleepInSecond(long)
	 * @see #getListWebElement(String)
	 * @author ThachNk
	 */
	protected boolean isAllItemHaveTextNameSortByDescending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		Collections.reverse(sortedNames);
		return names.equals(sortedNames);

	}

	/**
	 * Checks if all items have prices sorted in ascending order.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the items are sorted in ascending order; false otherwise.
	 * @see #sleepInSecond(long)
	 * @see #getListWebElement(String)
	 * @author ThachNk
	 */
	protected boolean isAllItemHavePrice$SortByAscending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<Float> names = elementLists.stream().map(n -> Float.valueOf(n.getText().replace(",", "").replace("$", ""))).collect(Collectors.toList());
		List<Float> sortedNames = new ArrayList<Float>(names);
		Collections.sort(sortedNames);
		return names.equals(sortedNames);
	}

	/**
	 * Checks if all items have prices sorted in descending order.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the items are sorted in descending order; false otherwise.
	 * @see #sleepInSecond(long)
	 * @see #getListWebElement(String)
	 * @author ThachNk
	 */
	protected boolean isAllItemHavePrice$SortByDescending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<Float> names = elementLists.stream().map(n -> Float.valueOf(n.getText().replace(",", "").replace("$", ""))).collect(Collectors.toList());
		List<Float> sortedNames = new ArrayList<Float>(names);
		Collections.sort(sortedNames);
		Collections.reverse(sortedNames);
		return names.equals(sortedNames);
	}

	/**
	 * Checks if all items have dates sorted in ascending order.
	 *
	 * @param locatorType The type of the locator (e.g., id, class, xpath).
	 * @return True if the items are sorted in ascending order; false otherwise.
	 * @see #sleepInSecond(long)
	 * @see #getListWebElement(String)
	 * @see #convertStringToDate(String)
	 * @author ThachNk
	 */
	protected boolean isAllItemHaveDateSortByAscending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<Date> dates = elementLists.stream().map(n -> convertStringToDate(n.getText())).collect(Collectors.toList());
		List<Date> sortedDates = dates.stream().sorted().collect(Collectors.toList());
		return dates.equals(sortedDates);
	}

	protected boolean isAllItemHaveDateSortByDescending(String locatorType) {
		sleepInSecond(3);
		List<WebElement> elementLists = getListWebElement(locatorType);
		List<Date> dates = elementLists.stream().map(n -> convertStringToDate(n.getText())).collect(Collectors.toList());
		List<Date> sortedDates = dates.stream().sorted((d1, d2) -> d2.compareTo(d1)).collect(Collectors.toList());
		return dates.equals(sortedDates);
	}

	/**
	 * Converts a date string to a {@code Date} object.
	 *
	 * @param dateInString The input date string to be converted.
	 * @return A {@code Date} object representing the parsed date.
	 * @throws IllegalArgumentException If the input date string is in an invalid format.
	 * @author ThachNk
	 */
	private Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(",", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Enter to dynamic textbox by id
	 *
	 * @author ThachNK
	 * @param driver
	 * @param textBoxID
	 * @param value
	 */
	public void inPutToTextboxByID(String textBoxID, String value) {
		waitForElementVisible(BasePageBankGuruUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
		senkeyToElement(BasePageBankGuruUI.DYNAMIC_TEXTBOX_BY_ID, value, textBoxID);
	}

	/**
	 * Click to dynamic button by text
	 *
	 * @author ThachNk
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(String buttonText) {
		waitForElementClickable(BasePageBankGuruUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(BasePageBankGuruUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	/**
	 * Select item in dropdown by name atribute
	 *
	 * @author ThachNk
	 * @param driver
	 * @param dropdownAtributeName
	 * @param itemValue
	 */
	public void selectToDropdownByName(String dropdownAtributeName, String itemValue) {
		waitForElementClickable(BasePageBankGuruUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAtributeName);
		selectItemInDefaultDropDown(BasePageBankGuruUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAtributeName);
	}

	/**
	 * Click To dynamic radio button by label name
	 *
	 * @author ThachNk
	 * @param driver
	 * @param checkBoxLabelname
	 */
	public void clickToRadioButtonByLabel(String checkBoxLabelname) {
		waitForElementClickable(BasePageBankGuruUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkBoxLabelname);
		checkToDefaultCheckboxOrRadio(BasePageBankGuruUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, checkBoxLabelname);
	}

	/**
	 * Click To dynamic checkbox by label name
	 *
	 * @author ThachNk
	 * @param driver
	 * @param checkBoxLabelname
	 */
	public void clickToCheckboxByLabel(String checkBoxLabelname) {
		waitForElementClickable(BasePageBankGuruUI.DYNAMIC_CHECKBOX_BY_LABEL, checkBoxLabelname);
		checkToDefaultCheckboxOrRadio(BasePageBankGuruUI.DYNAMIC_CHECKBOX_BY_LABEL, checkBoxLabelname);
	}

	/**
	 * Get value in textbox by textboxID
	 *
	 * @author ThachNk
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	public String getTextboxValueByID(String textboxID) {
		waitForElementVisible(BasePageBankGuruUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(BasePageBankGuruUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}

	public LoginPageObject openLoginPage() {
		openPageUrl(BasePageBankGuruUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	@Step("Open to MenuItem by text value is '{menuItem}'")
	public void openMenuItemByText(String menuItem) {
		waitForElementClickable(BasePageBankGuruUI.MENU_ITEM_BY_TEXT, menuItem);
		clickToElement(BasePageBankGuruUI.MENU_ITEM_BY_TEXT, menuItem);
	}
}