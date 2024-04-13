package factoryEnvironment;

import org.openqa.selenium.WebDriver;

import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.SafariDriverManager;

public class LocalFactory {
	private String browserName;
	private WebDriver driver;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case H_CHROME:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case H_FIREFOX:
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case IE:
			driver = new IEDriverManager().getBrowserDriver();
			break;
		case SAFARI:
			driver = new SafariDriverManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
