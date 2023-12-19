package pageObject.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
	}

	public boolean isProductNameSortByAscedingLamda() {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortNames = new ArrayList<String>(names);
		Collections.sort(sortNames);
		return names.equals(sortNames);
	}

	public boolean isProductNameSortByDescedingLamda() {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortNames = new ArrayList<String>(names);
		Collections.sort(sortNames);
		Collections.reverse(sortNames);
		return names.equals(sortNames);
	}

	public boolean isProductNameSortByAsceding() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lăp for để getText và add vào arrayList (ban đầu)
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product name ở trên UI: " + productName.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng k
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList bằng collection
		Collections.sort(productSortList);

		for (String productName : productSortList) {
			System.out.println("Product name sau khi sort: " + productName);
		}

		// So sánh productSortList và productUIList có bằng nhau k
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByDesceding() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lăp for để getText và add vào arrayList (ban đầu)
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product name ở trên UI: " + productName.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng k
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList bằng collection
		Collections.sort(productSortList);

		for (String productName : productSortList) {
			System.out.println("Product name sau khi sort ASC: " + productName);
		}

		// Reverse lại productSortList
		Collections.reverse(productSortList);

		for (String productName : productSortList) {
			System.out.println("Product name sau khi sort DESC: " + productName);
		}

		// So sánh productSortList và productUIList có bằng nhau k
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAsceding() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		// Dùng vòng lăp for để getText và add vào arrayList (ban đầu)
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product name ở trên UI: " + productPrice.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng k
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList bằng collection
		Collections.sort(productSortList);

		for (Float productPrice : productSortList) {
			System.out.println("Product name sau khi sort: " + productPrice);
		}

		// So sánh productSortList và productUIList có bằng nhau k
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDesceding() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		// Dùng vòng lăp for để getText và add vào arrayList (ban đầu)
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product name ở trên UI: " + productPrice.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem đúng k
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort productSortList bằng collection
		Collections.sort(productSortList);

		for (Float productName : productSortList) {
			System.out.println("Product name sau khi sort ASC: " + productName);
		}

		// Reverse lại productSortList
		Collections.reverse(productSortList);

		for (Float productName : productSortList) {
			System.out.println("Product name sau khi sort DESC: " + productName);
		}

		// So sánh productSortList và productUIList có bằng nhau k
		return productSortList.equals(productUIList);
	}
}
