package commons;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalConstants {
	private static GlobalConstants globalInstance;

	private GlobalConstants() {

	}

	public static synchronized GlobalConstants getGlobalConstants() {
		if (globalInstance == null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}

	private final String portalPageUrl = "https://demo.nopcommerce.com/";
	private final String adminPageUrl = "https://admin-demo.nopcommerce.com/";
	private final String projectPath = System.getProperty("user.dir");
	private final String javaVersion = System.getProperty("java.version");

	private final String osName = System.getProperty("os.name");
	private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String downloadFile = projectPath + File.separator + "downloadFiles";
	private final String browserLog = projectPath + File.separator + "browserLogs" + File.separator;
	private final String drapDropHTML5 = projectPath + File.separator + "dragDropHTML5";
	private final String autoITScript = projectPath + File.separator + "autoIT";
	private final String reportingScreenshot = projectPath + File.separator + "ReportNGScreenShots" + File.separator;
	private final String extendPath = projectPath + File.separator + "ExtentReportV2" + File.separator;

	private final String dbDevUrl = "32.18.252.185:9860";
	private final String dbDevUser = "automationfc";
	private final String dbDevPassword = "P@sswOrld1@";

	private final String dbTestUrl = "32.18.195.23:9860";
	private final String dbTestUser = "automationfc";
	private final String dbTestPassword = "P@sswOrld1@";

	private final long shortTimeOut = 5;
	private final long mediumTimeOut = 15;
	private final long longTimeOut = 30;
	private final long retryTestFail = 3;

	private final String browserUsername = "nhungnguyen_9rLsbA";
	private final String browserAutomateKey = "UevjakxKzz38VqX9KH8h";
	private final String browserStackUrl = "https://" + browserUsername + ":" + browserAutomateKey + "@hub-cloud.browserstack.com/wd/hub";

	private final String sauceUsername = "oauth-nhungnth8.neu-16f5f";
	private final String sauceAutomateKey = "d8137b9f-f381-475c-8fa8-279e8a5aed2e";
	private final String sauceUrl = "https://" + sauceUsername + ":" + sauceAutomateKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

}
