package com.facebook.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;
	private WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void User_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();

		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());

		// Verify true - mong đợi confirm email display (true)
		loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}

	@Test
	public void User_02_Verify_Element_Undisplayed_In_DOM() {
		// Nếu như mong đợi 1 hàm vừa verify displayed/ undisplayed thì không được kết hợp wait
		// isElementDisplayed

		// Verify False - mong đợi confirm email undisplayed (false)
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUnDisplayed());

	}

	@Test
	public void User_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);

		// Khi close form register đi thì confirm email không còn trong DOM nữa
		// Nên hàm findElement sẽ bị fail vì không tìm thấy element
		// 1 - Nó sẽ chờ hết timeout của implicit: 30s
		// 2 - Nó sẽ đánh fail testcase tại đúng step findElement => Nên add thêm try catch để bắt exception
		// 3 - Không có chạy các step còn lại
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUnDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
