package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements IBrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstants().getBrowserLog() + "Firefox.log");

		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.getGlobalConstants().getDownloadFile());
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png,image/jpeg,application/pdf, text/html, text/plain, application/excel,application/vnd.ms-excel, application/x-excel, application/x-msexcel,application/octet-stream");
		options.addPreference("pdfjs.disabled", true);
		// private
		// options.addArguments("-private");

		return new FirefoxDriver(options);
	}

}
