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
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ ScreenshotUtility.class })

public class FSATest2_Multi {

	static IOSDriver<IOSElement> driver;
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
		capabilities.setCapability("sendKeyStrategy","grouped");
		capabilities.setCapability("autoAcceptAlerts",true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("locationServicesAuthorized", true);
		capabilities.setCapability("clearSystemFiles",true);


		// caps.setCapability("bundleid", "com.example.apple-samplecode.UICatalog");

		driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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

	/**
	 * Fetches the elements by handling all the DOM load delays and location not found issues and sets the global elemental properties for el,xyPoint = el.getLocation(); elId , elClass, elValue, elText, elTagname,
	 * 
	 * @param xpathStr
	 * @return
	 */
	public WebElement FetchElementWrapper(String xpathStr) {

		try {
			el = null;
			 elId = null;

			 elClass = null;
			 elValue = null;

			 elText = null;
			 elTagname = null;
			 xyPoint = null;
			 
			 
			Integer count = 0;
			// while the following loop runs, the DOM changes -
			// page is refreshed if the element is removed and re-added
			while (count < 3) {

				count++;
				try {
				el = driver.findElement(By.xpath(xpathStr));
				

				xyPoint = el.getLocation();
				elId = el.getAttribute("id");

				elClass = el.getAttribute("class");
				elValue = el.getAttribute("value");

				elText = el.getText();
				elTagname = el.getTagName();

				// Try multiple times unless the coordinates are available for both
			
					if (xyPoint.getX() == 0 && xyPoint.getY() == 0) {
						System.out.println("waiting... for Element " + el);
						Thread.sleep(1000);

					} else if (xyPoint.getY() == 0) {
						System.out.println("waiting... for Element " + el);

						// SOmetimes the y coordinates are hidden under the screen space so tap on it and wait for it to generate a coordinate
						Thread.sleep(1000);
						xyPoint = el.getLocation();
					}

					else {
						break;
					}
				
				}catch(Exception e) {
					System.out.println("Exception in Fetching Element : "+e);
					System.out.println("Retrying to Fetch Element ");

				}

			}

			System.out.println("Fetching Element Details " + el + " Location === " + xyPoint + " id = " + elId + " class = " + elClass + " value = " + elValue + " Tagname = " + elTagname + " Text = " + elText);

		} catch (Exception e) {
			System.out.println("DOM exception " + e);
		}
	
		return el;

	}

	/**
	 * Performs Tap, longpress, click and scroll
	 * 
	 * @param xpathStr
	 * @param typeOfAction
	 */
	public void touchWraper(String xpathStr, String typeOfAction) {

		el = FetchElementWrapper(xpathStr);
		TouchAction touch = new TouchAction(driver);
		Integer xOffset = 15;
		Integer yOffset = 18;

		if (typeOfAction.toLowerCase().equals("tap")) {
			System.out.println("Acting on location Tap " + el);

			touch.tap(xyPoint.getX() + xOffset, xyPoint.getY() + yOffset).release().perform();

		} else if (typeOfAction.toLowerCase().equals("longpress")) {
			System.out.println("Acting on location longpress " + el);

			touch.longPress(xyPoint.getX() + yOffset, xyPoint.getY() + yOffset).release().perform();

			// touch.moveTo(xyPoint.getX(),xyPoint.getY()).release().perform();
		} else if (typeOfAction.toLowerCase().equals("scroll")) {
			System.out.println("Acting on location scroll " + el);

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
		} else if (typeOfAction.toLowerCase().equals("click")) {
			System.out.println("Acting on element click " + el);

			el.click();

		} else if (typeOfAction.toLowerCase().equals("clickid")) {
			System.out.println("Acting on Id click " + driver.findElement(By.id(elId)));

			driver.findElement(By.id(elId)).click();

		} else if (typeOfAction.toLowerCase().equals("clickxpath")) {
			System.out.println("Acting on xpath click " + driver.findElement(By.xpath(xpathStr)));

			driver.findElement(By.xpath(xpathStr)).click();

		}

	
	}

	public String getDateTime(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		String format = "dd-MM-yyyy";
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	/**
	 * Sets and selects the selection Picker wheel value
	 * 
	 * @param xpathStr
	 * @param value
	 * @throws InterruptedException
	 */
	public void setSelectedWrapper(String xpathStr, String value) throws InterruptedException {
		el = FetchElementWrapper(xpathStr);
		el.click();
		driver.context(nativeApp);

		List<IOSElement> pickerField = (List<IOSElement>) driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		pickerField.get(0).sendKeys(value);
		driver.findElement(By.name("Done")).click();
		driver.context(webApp);

	}

	/**
	 * Sets the date in datepicker
	 * 
	 * @param xpathStr
	 * @param DateFormatArray
	 * @throws InterruptedException
	 */
	public void setDateWrapper(String xpathStr, String DateFormat) throws InterruptedException {
		el = FetchElementWrapper(xpathStr);
		
	
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();

		String futureStart = dateFormat2.format(date2);
		System.out.println("futureStart = " +futureStart + " & " + date2);
		
		el.click();

		driver.context(nativeApp);

		List<IOSElement> wheels = (List<IOSElement>) driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		System.out.println("Wheels = " + wheels.size() + " Wheels Name = " + wheels);
		for (int i = 0; i < wheels.size(); i++) {
			System.out.println(" Wheels Value = " + wheels.get(i).getAttribute("value"));

		}
		
		if (DateFormat.toLowerCase().equals("default")) {
			//Do nothing
			String SplitTime[] = wheels.get(2).getAttribute("value").split(" ");
			//Forwarding minutes by 5
				Integer tempInt = Integer.parseInt(SplitTime[0]) + 10;
				wheels.get(2).sendKeys(tempInt.toString());

			driver.findElement(By.name("Done")).click();

		}
		else if (DateFormat.toLowerCase().equals("futurestart")) {

			// do nothing
			// TO set future dates
			
			//The return value is in string for mat 12 ,0 cliok, so we need to split it to get the integer value which later needs to be parsed to int
			String[] SplitTime = wheels.get(1).getAttribute("value").split(" ");
			if (SplitTime[0].equals("12")) {
				wheels.get(1).sendKeys("1");
			} else {
				Integer tempInt = Integer.parseInt(SplitTime[0]) + 10;
				wheels.get(1).sendKeys(tempInt.toString());

			}
			
			wheels.get(2).sendKeys("01");
			
//			if (wheels.get(3).getAttribute("value").equals("PM")) {
//				wheels.get(3).sendKeys("AM");
//			} else {
//				wheels.get(3).sendKeys("PM");
//
//			}
			driver.findElement(By.name("Done")).click();

		} else if (DateFormat.toLowerCase().equals("futureend")) {

			try {
				
				//The return value is in string for mat 12 ,0 cliok, so we need to split it to get the integer value which later needs to be parsed to int
				String[] SplitTime = wheels.get(1).getAttribute("value").split(" ");
				if (SplitTime[0].equals("12")) {
					wheels.get(1).sendKeys("1");
				} else {
					Integer tempInt = Integer.parseInt(SplitTime[0]) + 1;
					wheels.get(1).sendKeys(tempInt.toString());

				}

				wheels.get(0).sendKeys("Wed 16 May");

				// driver.findElement(By.xpath("//*[. = 'End Date and Time']//*[@class = 'x-input-el']")).sendKeys("30/07/2019 4:45 PM");
				// wheels.get(1).sendKeys("9");
				wheels.get(2).sendKeys("55");

				// TO set future dates
//				if (wheels.get(3).getAttribute("value").equals("PM")) {
//					wheels.get(3).sendKeys("PM");
//				} else {
//					wheels.get(3).sendKeys("AM");
//
//				}

				driver.findElement(By.name("Done")).click();

			}

			catch (Exception e) {
				System.out.println("Date Picker Eception - " + e);
			}

		}else {
			
//			wheels.get(0).setValue("Wed 16 May");
//
//			wheels.get(0).sendKeys("Wed 16 May");

		}
		// driver.getKeyboard().pressKey(Keys.ENTER);
		driver.context(webApp);
	

	}

	/**
	 * Enters the text after clearing the text field
	 * 
	 * @param xpathStr
	 * @param textStr
	 * @throws InterruptedException
	 */
	public void sendKeyWrapper(String xpathStr, String textStr) throws InterruptedException {
		el = FetchElementWrapper(xpathStr);
		el.clear();
		el.sendKeys(textStr);
	}

	public void takeScreenShotWrapper() throws IOException {
		Random rand = new Random();

		int  n = rand.nextInt(500) + 1;
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		
		  FileUtils.copyFile(scrFile, new File("/auto/appium/sch/"+n+".jpg"));

	}
	public void login() throws InterruptedException, IOException {
		
		if(driver.findElements(By.id("svmx_splash_signin")).size() != 0) {
			
			driver.findElement(By.id("svmx_splash_signin")).click();
			Thread.sleep(6000);
			// driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
			// driver.findElement(By.id("password")).sendKeys("svmx123#");
			driver.findElement(By.id("username")).sendKeys("vinod.tharavath@ge.com");
			driver.findElement(By.id("password")).sendKeys("svmx123#");
			driver.findElement(By.id("Login")).click();

			Thread.sleep(8000);
			driver.findElement(By.id("oaapprove")).click();
			System.out.println("Login Successfull");

		}else {
			//do nothing
			System.out.println("Already Logged in skipping Login");
		}
		System.out.println("Waiting for Landing Page");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Calendar']")));

		Thread.sleep(3000);
	}

	@Test(priority = 0)
	public void testiOS() throws InterruptedException, IOException {

		driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		takeScreenShotWrapper();
		touchWraper("//*[text() = 'Explore']", "tap");
		// touchWraper("//div[. = 'AppiumSearch']/..", "tap");

		// touchWraper("//*[text() = 'DC SEARCH']", "tap");
		// touchWraper("//*[@class = 'x-listitem-body']/*[@class ='x-innerhtml']/*[contains(.,'Work Orders (')]", "tap");

		touchWraper("//*[text() = 'Work Order Search 2']", "tap");
		touchWraper("//*[.='Include Online Items']/..//*[@data-componentid = 'ext-toggleslider-1']", "tap");
		sendKeyWrapper("//input[@placeholder='Search']", "WO-00005081");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");

		touchWraper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'WO-00005081')]", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'New Event']", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "default");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "default");
		sendKeyWrapper("//*[. = 'Subject']//*[@class = 'x-input-el']", "100");
		touchWraper("//*[text() = 'Save']", "tap");
		
		if(FetchElementWrapper(" //*[text() = 'Yes']") != null) {
			 touchWraper("//*[text() = 'Yes']", "tap");
		}

		touchWraper("//*[text() = 'Calendar']", "tap");
		
		touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'Record T&M']", "tap");
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		

		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		// touchWraper("//*[text() = 'Yes']", "tap");

		touchWraper("//*[text() = 'Actions']", "tap");

		touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		takeScreenShotWrapper();

		touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	@Test(priority = 1)
	public void testiOS1() throws InterruptedException, IOException {

		driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		takeScreenShotWrapper();
		

		touchWraper("//*[text() = 'Calendar']", "tap");
		
		touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'Record T&M']", "tap");
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		

		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		// touchWraper("//*[text() = 'Yes']", "tap");

		touchWraper("//*[text() = 'Actions']", "tap");

		touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		takeScreenShotWrapper();

		touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	@Test(priority = 3)

	public void testiOS2() throws InterruptedException, IOException {

		driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		takeScreenShotWrapper();
		
		touchWraper("//*[text() = 'Calendar']", "tap");
		
		touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'Record T&M']", "tap");
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		

		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		// touchWraper("//*[text() = 'Yes']", "tap");

		touchWraper("//*[text() = 'Actions']", "tap");

		touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		takeScreenShotWrapper();

		touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	@Test(priority = 4)

	public void testiOS3() throws InterruptedException, IOException {

		
		driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		takeScreenShotWrapper();
	

		touchWraper("//*[text() = 'Calendar']", "tap");
		
		touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'Record T&M']", "tap");
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		

		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		// touchWraper("//*[text() = 'Yes']", "tap");

		touchWraper("//*[text() = 'Actions']", "tap");

		touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		takeScreenShotWrapper();

		touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	
	@Test(priority = 5)

public void testiOS4() throws InterruptedException, IOException {

		
		driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		takeScreenShotWrapper();
		

		touchWraper("//*[text() = 'Calendar']", "tap");
		
		touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		touchWraper("//*[text() = 'Actions']", "tap");
		touchWraper("//*[text() = 'Record T&M']", "tap");
		touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		touchWraper("//*[. = 'Add Selected']", "tap");
		

		touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		touchWraper("//*[text() = 'Done']", "tap");

		touchWraper("//*[text() = 'Save']", "tap");
		// touchWraper("//*[text() = 'Yes']", "tap");

		touchWraper("//*[text() = 'Actions']", "tap");

		touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		takeScreenShotWrapper();

		touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
@Test(priority = 6)

public void testiOS5() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}
	
@Test(priority = 7)

public void testiOS6() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 8)

public void testiOS7() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 9)

public void testiOS8() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 10)

public void testiOS9() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 11)

public void testiOS10() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 12)

public void testiOS11() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 13)
public void testiOS12() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 14)
public void testiOS13() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 15)
public void testiOS14() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 16)
public void testiOS15() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 17)
public void testiOS16() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 18)
public void testiOS17() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 19)
public void testiOS18() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 20)
public void testiOS19() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 21)
public void testiOS20() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	
	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 22)
public void testiOS21() throws InterruptedException, IOException {

	
	driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	takeScreenShotWrapper();
	

	touchWraper("//*[text() = 'Calendar']", "tap");
	
	touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	touchWraper("//*[text() = 'Actions']", "tap");
	touchWraper("//*[text() = 'Record T&M']", "tap");
	touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	touchWraper("//*[. = 'Add Selected']", "tap");
	

	touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	touchWraper("//*[text() = 'Done']", "tap");

	touchWraper("//*[text() = 'Save']", "tap");
	// touchWraper("//*[text() = 'Yes']", "tap");

	touchWraper("//*[text() = 'Actions']", "tap");

	touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	takeScreenShotWrapper();

	touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	driver.rotate(ScreenOrientation.LANDSCAPE);
	driver.rotate(ScreenOrientation.PORTRAIT);

	

}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
