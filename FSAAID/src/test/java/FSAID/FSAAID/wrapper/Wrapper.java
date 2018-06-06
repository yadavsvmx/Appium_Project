package FSAID.FSAAID.wrapper;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import FSAID.FSAAID.initiator.Initiator;

/**
 * 
 * @author yadavthyagaraj
 *
 * @description The wrapper class contains all the methods and relevant complexities involved in fetching and performing actions on the elements
 *
 *
 */
public class Wrapper {
	Initiator init = new Initiator();
	TouchAction touch = new TouchAction(init.driver);
	Integer xOffset = 15;
	Integer yOffset = 18;

	/**
	 * Fetches the init.elements by handling all the DOM load dinit.elays and location not found issues and sets the global init.elemental properties for init.el,init.xyPoint = init.el.getLocation(); init.elId , init.elClass, init.elValue, init.elText, init.elTagname,
	 * 
	 * @param xpathStr
	 * @return
	 */
	public WebElement FetchElementDetails(String xpathStr) {

		try {
			init.xpathStr = null;
			init.el = null;
			init.elId = null;

			init.elClass = null;
			init.elValue = null;

			init.elText = null;
			init.elTagname = null;
			init.xyPoint = null;

			Integer count = 0;
			// while the following loop runs, the DOM changes -
			// page is refreshed if the init.element is removed and re-added
			while (count < 3) {

				count++;
				try {
					init.xpathStr = xpathStr;
					init.el = init.driver.findElement(By.xpath(xpathStr));

					init.xyPoint = init.el.getLocation();
					init.elId = init.el.getAttribute("id");

					init.elClass = init.el.getAttribute("class");
					init.elValue = init.el.getAttribute("value");

					init.elText = init.el.getText();
					init.elTagname = init.el.getTagName();

					// Try multiple times unless the coordinates are available for both

					if (init.xyPoint.getX() == 0 && init.xyPoint.getY() == 0) {
						System.out.println("waiting... for element " + init.el);
						Thread.sleep(1000);

					} else if (init.xyPoint.getY() == 0) {
						System.out.println("waiting... for element " + init.el);

						// SOmetimes the y coordinates are hidden under the screen space so tap on it and wait for it to generate a coordinate
						Thread.sleep(1000);
						init.xyPoint = init.el.getLocation();
					}

					else {
						break;
					}

				} catch (Exception e) {
					System.out.println("Exception in Fetching element : " + e);
					System.out.println("Retrying to Fetch element ");

				}

			}

			System.out.println("Fetching element Details " + init.el + " Location === " + init.xyPoint + " id = " + init.elId + " class = " + init.elClass + " value = " + init.elValue + " Tagname = " + init.elTagname + " Text = " + init.elText);

		} catch (Exception e) {
			System.out.println("DOM exception " + e);
		}

		return init.el;

	}
	/**
	 * Fetches the element details 
	 * @param xpathStr
	 */
	public Wrapper fetchElementWrapper(String xpathStr) {

		init.el = FetchElementDetails(xpathStr);
		return this;

	}

	/**
	 * Tap on element
	 * Chain with fetcheElementWrapper();
	 * @return
	 */
	public Wrapper tap() {

		System.out.println("Acting on location Tap " + init.el);

		touch.tap(init.xyPoint.getX() + xOffset, init.xyPoint.getY() + yOffset).release().perform();
		return this;

	}

	/**
	 * longPress on element
	 * Chain with fetcheElementWrapper();
	 * @return
	 */
	public Wrapper longPress() {
		System.out.println("Acting on location longpress " + init.el);

		touch.longPress(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset).release().perform();
		return this;

	}
	/**
	 * click on element
	 * Chain with fetcheElementWrapper();
	 * @return
	 */
	public Wrapper click() {
		System.out.println("Acting on init.element click " + init.el);

		init.el.click();
		return this;

	}
	/**
	 * clickId on element
	 * Chain with fetcheElementWrapper();
	 * @return
	 */
	public Wrapper clickId() {

		System.out.println("Acting on Id click " + init.driver.findElement(By.id(init.elId)));

		init.driver.findElement(By.id(init.elId)).click();
		return this;

	}
	/**
	 * clickXpath
	 *  on element
	 * Chain with fetcheElementWrapper();
	 * @return
	 */
	public Wrapper clickXpath() {

		System.out.println("Acting on xpath click " + init.driver.findElement(By.xpath(init.xpathStr)));

		init.driver.findElement(By.xpath(init.xpathStr)).click();
		return this;

	}

	
//	public void touchWraper(String xpathStr, String typeOfAction) {
//
//		init.el = FetchElementDetails(xpathStr);
//		TouchAction touch = new TouchAction(init.driver);
//		Integer xOffset = 15;
//		Integer yOffset = 18;
//
//		if (typeOfAction.toLowerCase().equals("tap")) {
//			System.out.println("Acting on location Tap " + init.el);
//
//			touch.tap(init.xyPoint.getX() + xOffset, init.xyPoint.getY() + yOffset).release().perform();
//
//		} else if (typeOfAction.toLowerCase().equals("longpress")) {
//			System.out.println("Acting on location longpress " + init.el);
//
//			touch.longPress(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset).release().perform();
//
//			// touch.moveTo(init.xyPoint.getX(),init.xyPoint.getY()).rinit.elease().perform();
//		} else if (typeOfAction.toLowerCase().equals("scroll")) {
//			System.out.println("Acting on location scroll " + init.el);
//
//			touch.press(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset).moveTo(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset + 50).release().perform();
//			// touch.moveTo(init.xyPoint.getX(),init.xyPoint.getY()).rinit.elease().perform();
//		}
//
//		else if (typeOfAction.toLowerCase().equals("js")) {
//
//			// JavascriptExecutor js = (JavascriptExecutor) init.driver;
//			// HashMap<String, String> tapObject = new HashMap<String, String>();
//			// tapObject.put("x", String.valueOf(init.el.getSize().getWidth() / 2));
//			// tapObject.put("y", String.valueOf(init.el.getSize().getHeight() / 2));
//			// tapObject.put("init.element", ((RemoteWebinit.element) init.el).getId());
//			// js.executeScript("mobile:tap", tapObject);
//		} else if (typeOfAction.toLowerCase().equals("click")) {
//			System.out.println("Acting on init.element click " + init.el);
//
//			init.el.click();
//
//		} else if (typeOfAction.toLowerCase().equals("clickid")) {
//			System.out.println("Acting on Id click " + init.driver.findElement(By.id(init.elId)));
//
//			init.driver.findElement(By.id(init.elId)).click();
//
//		} else if (typeOfAction.toLowerCase().equals("clickxpath")) {
//			System.out.println("Acting on xpath click " + init.driver.findElement(By.xpath(xpathStr)));
//
//			init.driver.findElement(By.xpath(xpathStr)).click();
//
//		}
//
//	}

	public String getDateTime(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		String format = "dd-MM-yyyy";
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	/**
	 * Sets and sinit.elects the sinit.election Picker wheinit.el value
	 * 
	 * @param xpathStr
	 * @param value
	 * @throws InterruptedException
	 */
	public void setSelectedWrapper(String xpathStr, String value) {
		init.el = FetchElementDetails(xpathStr);
		init.el.click();
		init.driver.context(init.nativeApp);

		List<IOSElement> pickerField = (List<IOSElement>) init.driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		pickerField.get(0).sendKeys(value);
		init.driver.findElement(By.name("Done")).click();
		init.driver.context(init.webApp);

	}

	/**
	 * Sets the date in datepicker
	 * 
	 * @param xpathStr
	 * @param DateFormatArray
	 * @throws InterruptedException
	 */
	public void setDateWrapper(String xpathStr, String DateFormat) {
		init.el = FetchElementDetails(xpathStr);

		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();

		String futureStart = dateFormat2.format(date2);
		System.out.println("futureStart = " + futureStart + " & " + date2);

		init.el.click();

		init.driver.context(init.nativeApp);

		List<IOSElement> wheels = (List<IOSElement>) init.driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
		System.out.println("Wheels = " + wheels.size() + " Wheels Name = " + wheels);
		for (int i = 0; i < wheels.size(); i++) {
			System.out.println(" Wheels Value = " + wheels.get(i).getAttribute("value"));

		}

		if (DateFormat.toLowerCase().equals("default")) {
			// Do nothing
			String SplitTime[] = wheels.get(2).getAttribute("value").split(" ");
			// Forwarding minutes by 5
			Integer tempInt = Integer.parseInt(SplitTime[0]) + 10;
			wheels.get(2).sendKeys(tempInt.toString());

			init.driver.findElement(By.name("Done")).click();

		} else if (DateFormat.toLowerCase().equals("futurestart")) {

			// do nothing
			// TO set future dates

			// The return value is in string for mat 12 ,0 cliok, so we need to split it to get the integer value which later needs to be parsed to int
			String[] SplitTime = wheels.get(1).getAttribute("value").split(" ");
			if (SplitTime[0].equals("12")) {
				wheels.get(1).sendKeys("1");
			} else {
				Integer tempInt = Integer.parseInt(SplitTime[0]) + 10;
				wheels.get(1).sendKeys(tempInt.toString());

			}

			wheels.get(2).sendKeys("01");

			// if (wheels.get(3).getAttribute("value").equals("PM")) {
			// wheels.get(3).sendKeys("AM");
			// } else {
			// wheels.get(3).sendKeys("PM");
			//
			// }
			init.driver.findElement(By.name("Done")).click();

		} else if (DateFormat.toLowerCase().equals("futureend")) {

			try {

				// The return value is in string for mat 12 ,0 cliok, so we need to split it to get the integer value which later needs to be parsed to int
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
				// if (wheels.get(3).getAttribute("value").equals("PM")) {
				// wheels.get(3).sendKeys("PM");
				// } else {
				// wheels.get(3).sendKeys("AM");
				//
				// }

				init.driver.findElement(By.name("Done")).click();

			}

			catch (Exception e) {
				System.out.println("Date Picker Eception - " + e);
			}

		} else {

			// wheels.get(0).setValue("Wed 16 May");
			//
			// wheels.get(0).sendKeys("Wed 16 May");

		}
		// driver.getKeyboard().pressKey(Keys.ENTER);
		init.driver.context(init.webApp);

	}

	/**
	 * Enters the text after clearing the text fiinit.eld
	 * 
	 * @param xpathStr
	 * @param textStr
	 * @throws InterruptedException
	 */
	public Wrapper sendKeyWrapper(String textStr) {
		try {
			init.el.clear();
		} catch (Exception e) {
			System.out.println("Send Keys Eception - " + e);

		}

		try {
			init.el.sendKeys(textStr);
		} catch (Exception e) {
			System.out.println("Send Keys Eception - " + e);

		}
		return this;
	}

	public void takeScreenShotWrapper() {
		Random rand = new Random();

		int n = rand.nextInt(500) + 1;

		File scrFile = ((TakesScreenshot) init.driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		try {
			FileUtils.copyFile(scrFile, new File("/auto/appium/sch/" + n + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Execute a Sahi script. The sahi_project repository has to be downloaded/cloned first before calling the scripts Will return a true or false statement from the resultCommon.txt file and user may determine weather to proceed or stop the executions
	 * 
	 * @param sahiScriptFilePath
	 * @throws IOException
	 */
	public String[] execSahiScript(String sahiScriptFilePath) {
		String resultCommon = null;
		try {

			System.out.println("Executing Sahi scripts please wait for completion !");
			// create a temp file as a shell or bat for execution
			File file = new File("/auto/appium/Appium_Project/FSAAID/src/test/java/FSAID/FSAAID/workBench/sahiExecutable.sh");

			// Create the file
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

			// Write Content an create the shell or bat file
			FileWriter writer = new FileWriter(file);
			writer.write("#!/bin/bash \n");
			writer.write("cd /auto/sahi_pro/userdata/bin \n");
			writer.write("./testrunner.sh /auto/sahi_pro/userdata/scripts/Sahi_Project_Lightning/svmx/test_lab/test_cases/" + sahiScriptFilePath + " https://test.salesforce.com chrome");
			writer.close();
			// make it executable
			Runtime.getRuntime().exec("chmod u+x " + file);

			// File fileExec = new File("/auto/appium/Appium_Project/FSAAID/src/test/java/FSAID/FSAAID/testCase/testFile1.sh");
			//// This will execute the scripts asynchronously
			// Runtime.getRuntime().exec(new String[]{"/bin/sh" ,"-c", file.getPath()});

			// This will execute the scripts synchronously
			try {
				ProcessBuilder pb = new ProcessBuilder(file.getPath());
				Process p = pb.start(); // Start the process.
				p.waitFor(); // Wait for the process to finish.

				System.out.println("Script executed successfully " + p.exitValue());
			} catch (Exception e) {
				System.out.println("Script executed FAILURE !!! " + e);
			}

		} catch (Exception e) {
			System.out.println("Script executed FAILURE !!! " + e);
		}

		// Read the recorded true or false from the resultCommon.txt file
		try {
			resultCommon = this.readTextFile("/auto/appium/Appium_Project/FSAAID/src/test/java/FSAID/FSAAID/workBench/resultCommon.txt");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] arrValues = resultCommon.split(",");
		int i = 0;
		for (String arrValRead : arrValues) {
			System.out.println("use  arrValues[" + i + "] = " + arrValRead);
			i++;
		}

		if (arrValues[0].toLowerCase().equals("true")) {

			System.out.println("Its a Match , Read File = " + resultCommon);
			// Incase you want to stop even if the script passes

		} else {
			System.out.println("Its Not a Match , Read File = " + resultCommon);

		}

		return arrValues;
	}

	public String readTextFile(String filePath) throws Exception {
		// pass the path to the file as a parameter
		// FileReader fr =
		// new FileReader(filePath);
		//
		// int i;
		// while ((i=fr.read()) != -1) {
		// System.out.print("result common file read = "+(char) i);
		//
		// }
		//
		// return fr.toString();

		String data = "";
		data = new String(Files.readAllBytes(Paths.get(filePath)));

		System.out.println("resultCommon.txt file read as = " + data);
		return data;

	}

	public void writeTextFile(String filePath, String data) throws IOException {

		File file = new File(filePath);
		// Create the file
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}

		// Write Content an create the shell or bat file
		FileWriter writer = new FileWriter(file);
		writer.write(data);
		writer.close();
		System.out.println("resultCommon.txt file Write as = " + data);

	}
	
	
//	@FindBy(xpath="//android.view.View[@content-desc='Explore']")
	private String eleExploreIcn = "//*[text() = 'Explore']";
	public WebElement eleExploreIcn(String eleExploreIcn) 
	{
		return (WebElement) this.fetchElementWrapper(eleExploreIcn);
	}

}
