package FSAID.FSAAID.initiatorClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Initiator {
	
	public static IOSDriver<IOSElement> driver;
	public static WebDriverWait wait;
	public static Set<String> contextNames;
	public static String nativeApp;
	public static String webApp;

	public static WebElement el = null;
	public static String elId = null;

	public static String elClass = null;
	public static String elValue = null;

	public static String elText = null;
	public static String elTagname = null;
	public static Point xyPoint = null;
	
	

	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "11.2.6");
		capabilities.setCapability("deviceName", "IPAD AIR2 : FSA ARA : 2");
		capabilities.setCapability("automationName", "XCUITest");

		// capabilities.setCapability("app","/auto/appium/3.2.6.309_FSA_SAND.ipa");
		capabilities.setCapability("app", "/auto/appium/FSA_3.2.7.361_Sand.ipa");

		capabilities.setCapability("udid", "011b900b94d772578a443dc5617e7c53032be901");
		capabilities.setCapability("xcodeOrgId", "UZ47KHA3AB");
		capabilities.setCapability("xcodeSigningId", "iPhone Developer: Rajesh Rao (76X824PR66)");
		capabilities.setCapability("updatedWDABundleId", "com.servicemaxinc.WebDriverAgentRunner");
		capabilities.setCapability("startIWDP", true);
		capabilities.setCapability("autoWebview", true);
		capabilities.setCapability("browserName", "iOS");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("newCommandTimeout", 15);
		capabilities.setCapability("sendKeyStrategy","grouped");
		capabilities.setCapability("autoAcceptAlerts",true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("locationServicesAuthorized", true);
		capabilities.setCapability("clearSystemFiles",true);


		// caps.setCapability("bundleid", "com.example.apple-samplecode.UICatalog");

		try {
			driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setting default timeouts to avoid page load issues
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// already initialized to make wait public so not calling as "WebDriver
		wait = new WebDriverWait(driver, 30000);
		// wait = new Webdriver();"

		contextNames = driver.getContextHandles();
		// prints out something like NATIVE_APP \n WEBVIEW_1 since each time the
		// WEBVIEW_2,_3,_4 name is appended by a new number we need to store is a
		// global variable to access across
		System.out.println(contextNames);

		nativeApp = contextNames.toArray()[0].toString();
		webApp = contextNames.toArray()[1].toString();

	}

}
