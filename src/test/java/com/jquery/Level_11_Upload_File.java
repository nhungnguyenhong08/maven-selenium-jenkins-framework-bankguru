package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

public class Level_11_Upload_File extends BaseTest {
	String csharpFileName = "CSharp.jpg";
	String javaFileName = "Java.jpg";
	String pythonFileName = "Python.jpg";
	String rubyFileName = "Ruby.jpg";

	String[] multipleFileNames = { csharpFileName, javaFileName, pythonFileName, rubyFileName };

	private WebDriver driver;
	private HomePageObject homaPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		homaPage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		// Step 1 - Load single file
		homaPage.uploadMultipleFile(driver, csharpFileName);

		// Step 2: Verify single file loaded success
		Assert.assertTrue(homaPage.isFileLoadedByName(csharpFileName));

		// Step 3: Click to Start button
		homaPage.clickToStartButton();

		// Step 4: Verify single file link uploaded success
		Assert.assertTrue(homaPage.isFileLinkUploadedByName(csharpFileName));

		// Step 5: Verify single file image uploaded success
		Assert.assertTrue(homaPage.isFileImageUploadedByName(csharpFileName));

	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homaPage.refreshCurrentPage(driver);
		// Step 1 - Load multiple file
		homaPage.uploadMultipleFile(driver, multipleFileNames);

		// Step 2: Verify multiple file loaded success
		Assert.assertTrue(homaPage.isFileLoadedByName(csharpFileName));
		Assert.assertTrue(homaPage.isFileLoadedByName(javaFileName));
		Assert.assertTrue(homaPage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homaPage.isFileLoadedByName(rubyFileName));

		// Step 3: Click to Start button
		homaPage.clickToStartButton();

		// Step 4: Verify single file link uploaded success
		Assert.assertTrue(homaPage.isFileLinkUploadedByName(csharpFileName));
		Assert.assertTrue(homaPage.isFileLinkUploadedByName(javaFileName));
		Assert.assertTrue(homaPage.isFileLinkUploadedByName(pythonFileName));
		Assert.assertTrue(homaPage.isFileLinkUploadedByName(rubyFileName));

		// Step 5: Verify single file image uploaded success
		Assert.assertTrue(homaPage.isFileImageUploadedByName(csharpFileName));
		Assert.assertTrue(homaPage.isFileImageUploadedByName(javaFileName));
		Assert.assertTrue(homaPage.isFileImageUploadedByName(pythonFileName));
		Assert.assertTrue(homaPage.isFileImageUploadedByName(rubyFileName));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
