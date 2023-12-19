package commons;

import java.io.File;

public class GlobalConstantsLiveGuru {
	public static final String PORTAL_PAGE_URL = "http://live.techpanda.org/";
	public static final String ADMIN_PAGE_URL = "http://live.techpanda.org/index.php/backendlogin";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";

	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "P@sswOrld1@";

	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "automationfc";
	public static final String DB_TEST_PASS = "P@sswOrld1@";

	public static final long SHORT_TIME_OUT = 5;
	public static final long LONG_TIME_OUT = 30;
	public static final long RETRY_TEST_FAIL = 3;

}
