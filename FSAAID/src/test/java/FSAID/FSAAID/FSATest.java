package FSAID.FSAAID;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FSATest {

	@SuppressWarnings("rawtypes")
	private IOSDriver driver;
	public WebDriverWait wait;
	Set<String> contextNames;
	public String nativeApp;
	public String webApp;

	@SuppressWarnings("rawtypes")
	@BeforeMethod
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

	public void touchWraper(String xpathStr, String typeOfAction) {
		try {

			WebElement el = null;
			TouchAction touch = new TouchAction(driver);
			String elId = null;

			String elClass = null;
			String elValue = null;

			String elText = null;
			String elTagname = null;
			Point xyPoint = null;

			Integer xOffset = 15;
			Integer yOffset = 15;

			Thread.sleep(1000);
			Integer count = 0;
			// while the following loop runs, the DOM changes -
			// page is refreshed, or element is removed and re-added
			while (count < 7) {

				count++;
				el = driver.findElement(By.xpath(xpathStr));

				xyPoint = el.getLocation();
				elId = el.getAttribute("id");

				elClass = el.getAttribute("class");
				elValue = el.getAttribute("value");

				elText = el.getText();
				elTagname = el.getTagName();
				// Try multiple times unless the coordinates are available for both
				try {
					if (xyPoint.getX() == 0 && xyPoint.getY() == 0) {

						Thread.sleep(1000);
						
					}else if(xyPoint.getY() == 0) {
						//SOmetimes the y coordinates are hidden under the screen space so tap on it and wait for it to generate a coordinate
						Thread.sleep(1000);
						xyPoint = el.getLocation();
					}

					else {
						break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("Acting on Element " + el + " Location === " + xyPoint + " id = " + elId + " class = " + elClass + " value = " + elValue + " Tagname = " + elTagname + " Text = " + elText);

			if (typeOfAction.toLowerCase().equals("tap")) {

				touch.tap(xyPoint.getX() + xOffset, xyPoint.getY() + yOffset).release().perform();
				// if (!elId.equals("")) {
				// try {
				// driver.findElement(By.id(elId)).click();
				// }catch(Exception e) {
				// //Do nothing
				// System.out.println("Could not clikc on the element ID, Trying with location coordinates");
				// touch.tap(xyPoint.getX()+10, xyPoint.getY()).release().perform();
				// }
				//
				// } else {
				// // use coordinates
				// touch.tap(xyPoint.getX()+10, xyPoint.getY()).release().perform();
				//
				// }
				//

			} else if (typeOfAction.toLowerCase().equals("longpress")) {

				touch.longPress(xyPoint.getX() + yOffset, xyPoint.getY() + yOffset).release().perform();

				// touch.moveTo(xyPoint.getX(),xyPoint.getY()).release().perform();
			} else if (typeOfAction.toLowerCase().equals("scroll")) {

				touch.press(xyPoint.getX() + yOffset, xyPoint.getY() + yOffset).moveTo(xyPoint.getX() + yOffset, xyPoint.getY() + yOffset + 50).release().perform();
				// touch.moveTo(xyPoint.getX(),xyPoint.getY()).release().perform();
			}

			else if (typeOfAction.toLowerCase().equals("js")) {

				// JavascriptExecutor js = (JavascriptExecutor) driver;
				// HashMap<String, String> tapObject = new HashMap<String, String>();
				// tapObject.put("x", String.valueOf(el.getSize().getWidth() / 2));
				// tapObject.put("y", String.valueOf(el.getSize().getHeight() / 2));
				// tapObject.put("element", ((RemoteWebElement) el).getId());
				// js.executeScript("mobile:tap", tapObject);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("outer exception " + e);
		}
	}
	
	public String getDateTime(int year, int month, int day){
	    Calendar cal = Calendar.getInstance();
	    cal.set(year, month, day);
	    String format = "dd-MM-yyyy";
	    return new SimpleDateFormat(format).format(cal.getTime());
	}

	public void setSelectedWrapper(String xpathStr, String value) throws InterruptedException {
		Thread.sleep(1000);
		
		System.out.println("Acting on Element " + xpathStr);

		driver.findElement(By.xpath(xpathStr)).click();
		driver.context(nativeApp);

		List<IOSElement> pickerField = (List<IOSElement>) driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		pickerField.get(0).sendKeys(value);
		driver.findElement(By.name("Done")).click();
		driver.context(webApp);

	}
	
	public void setDateWrapper(String xpathStr, String DateFormatArray) throws InterruptedException {
		System.out.println("Acting on Element " + xpathStr);

		DateFormat dateFormat2 = new SimpleDateFormat("dd"); 
        Date date2 = new Date();

        String today = dateFormat2.format(date2); 
		System.out.println("today = " + today);
		
		System.out.println("today = " + getDateTime(2019, 12, 2));

		Thread.sleep(2000);
		driver.findElement(By.xpath(xpathStr)).click();

		driver.context(nativeApp);

		List<IOSElement> wheels = (List<IOSElement>) driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		System.out.println("Wheels = " + wheels.size() + " Wheels Name = " + wheels);
		Thread.sleep(3000);

		try {

			for (int i = 0; i < wheels.size(); i++) {
				System.out.println(" Wheels Value = " + wheels.get(i).getAttribute("value"));

			}

			wheels.get(0).sendKeys("02/06/18");
			Thread.sleep(1000);

			//driver.findElement(By.xpath("//*[. = 'End Date and Time']//*[@class = 'x-input-el']")).sendKeys("30/07/2019 4:45 PM");
			wheels.get(1).sendKeys("9");

			driver.findElement(By.name("Done")).click();

		}

		catch (Exception e) {
			System.out.println("Date Picker Eception - " + e);
		}
		// driver.getKeyboard().pressKey(Keys.ENTER);
		driver.context(webApp);

	}

	public void sendKeyWrapper(String xpathStr, String textStr) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Acting on Element " + xpathStr);

		driver.findElement(By.xpath(xpathStr)).sendKeys(textStr);
	}

	public void login() throws InterruptedException, IOException {
		driver.findElement(By.id("svmx_splash_signin")).click();
		Thread.sleep(6000);
		// driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
		// driver.findElement(By.id("password")).sendKeys("svmx123#");
		driver.findElement(By.id("username")).sendKeys("fsa2@bugbash.com");
		driver.findElement(By.id("password")).sendKeys("servicemax2");
		driver.findElement(By.id("Login")).click();

		Thread.sleep(8000);
		driver.findElement(By.id("oaapprove")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Calendar']")));
		Thread.sleep(3000);
	}

	@Test
	public void testiOS() throws InterruptedException, IOException {
		// login();

		touchWraper("//*[text() = 'Explore']", "tap");
		// touchWraper("//div[. = 'AppiumSearch']/..", "tap");

		touchWraper("//*[text() = 'DC SEARCH']", "tap");
		touchWraper("//*[@class = 'x-listitem-body']/*[@class ='x-innerhtml']/*[contains(.,'Work Orders (')]", "tap");

		touchWraper("//*[text() = 'WO-00000899']", "tap");
		touchWraper("//*[text() = 'Actions']", "tap");
		
//		touchWraper("//*[text() = 'New Event']", "tap");
//
//		setDateWrapper("//*[@data-componentid ='ext-svmx-field-datetime-2']//input", "");
//
//		sendKeyWrapper("//*[. = 'Subject']//*[@class = 'x-input-el']", "heyyy");
//		sendKeyWrapper("//*[. = 'Description']//*[@class = 'x-input-el']", "heyyy");
//		
//		touchWraper("//*[text() = 'Save']", "tap");
//		touchWraper("//*[text() = 'Yes']", "tap");

		
		touchWraper("//*[text() = 'Record T&M']", "tap");
		
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@data-componentid='ext-svmx-field-picklist-2']//input", "Starts With");
		touchWraper("//*[. = '12345']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		
		touchWraper("//*[contains(text(),'Travel (')]", "longpress");

	
		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "longpress");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		touchWraper("//*[text() = 'Yes']", "tap");
		

		/*
		 * File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(scrFile, new File("/Users/Downloads/g2.jpg")); ;//*[contains(.,'Work Orders')]"))
		 * 
		 * "//*[@id='ext-element-1048']/div"
		 * 
		 * "//*[@class = 'x-listitem-body']/*[@class = 'x-innerhtml']/*[contains(.,'Work Orders \(')]"
		 * 
		 * driver.findElement(By.xpath("//*[@data-componentid = 'ext-textareainput-2']//textarea[@class = 'x-input-el']")).sendKeys("Hallo");
		 */

	}

	@AfterMethod
	public void tearDown() {
		// driver.quit();
	}

}
