package FSAID.FSAAID;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;


@Listeners({ ScreenshotUtility.class })
public class RunTests {
	

	FSATest singleTests = new FSATest();
	FSATest2_Multi multiTests = new FSATest2_Multi();

	
	static IOSDriver driver;
	public WebDriverWait wait;
	Set<String> contextNames;
	public String nativeApp;
	public String webApp;

	WebElement el = null;
	String elId = null;

	String elClass = null;
	String elValue = null;

	String elText = null;
	String elTagname = null;
	Point xyPoint = null;

	@SuppressWarnings("rawtypes")
	@BeforeTest
	public void setUp() throws MalformedURLException {
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


		// caps.setCapability("bundleid", "com.example.apple-samplecode.UICatalog");

		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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
	
@Test
public void testRun() throws InterruptedException, IOException {
	singleTests.testiOS();
}

}
