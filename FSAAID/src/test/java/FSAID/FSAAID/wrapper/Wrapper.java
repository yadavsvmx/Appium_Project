package FSAID.FSAAID.wrapper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import java.io.File;
import java.io.IOException;
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

	
	 /** Fetches the init.elements by handling all the DOM load dinit.elays and location not found issues and sets the global init.elemental properties for init.el,init.xyPoint = init.el.getLocation(); init.elId , init.elClass, init.elValue, init.elText, init.elTagname,
			 * 
			 * @param xpathStr
			 * @return
			 */
			public WebElement FetchElementWrapper(String xpathStr) {

				try {
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
						
						}catch(Exception e) {
							System.out.println("Exception in Fetching element : "+e);
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
			 * Performs Tap, longpress, click and scroll
			 * 
			 * @param xpathStr
			 * @param typeOfAction
			 */
			public void touchWraper(String xpathStr, String typeOfAction) {

				init.el = FetchElementWrapper(xpathStr);
				TouchAction touch = new TouchAction(init.driver);
				Integer xOffset = 15;
				Integer yOffset = 18;

				if (typeOfAction.toLowerCase().equals("tap")) {
					System.out.println("Acting on location Tap " + init.el);

					touch.tap(init.xyPoint.getX() + xOffset, init.xyPoint.getY() + yOffset).release().perform();

				} else if (typeOfAction.toLowerCase().equals("longpress")) {
					System.out.println("Acting on location longpress " + init.el);

					touch.longPress(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset).release().perform();

					// touch.moveTo(init.xyPoint.getX(),init.xyPoint.getY()).rinit.elease().perform();
				} else if (typeOfAction.toLowerCase().equals("scroll")) {
					System.out.println("Acting on location scroll " + init.el);

					touch.press(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset).moveTo(init.xyPoint.getX() + yOffset, init.xyPoint.getY() + yOffset + 50).release().perform();
					// touch.moveTo(init.xyPoint.getX(),init.xyPoint.getY()).rinit.elease().perform();
				}

				else if (typeOfAction.toLowerCase().equals("js")) {

					// JavascriptExecutor js = (JavascriptExecutor) init.driver;
					// HashMap<String, String> tapObject = new HashMap<String, String>();
					// tapObject.put("x", String.valueOf(init.el.getSize().getWidth() / 2));
					// tapObject.put("y", String.valueOf(init.el.getSize().getHeight() / 2));
					// tapObject.put("init.element", ((RemoteWebinit.element) init.el).getId());
					// js.executeScript("mobile:tap", tapObject);
				} else if (typeOfAction.toLowerCase().equals("click")) {
					System.out.println("Acting on init.element click " + init.el);

					init.el.click();

				} else if (typeOfAction.toLowerCase().equals("clickid")) {
					System.out.println("Acting on Id click " + init.driver.findElement(By.id(init.elId)));

					init.driver.findElement(By.id(init.elId)).click();

				} else if (typeOfAction.toLowerCase().equals("clickxpath")) {
					System.out.println("Acting on xpath click " + init.driver.findElement(By.xpath(xpathStr)));

					init.driver.findElement(By.xpath(xpathStr)).click();

				}

			
			}

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
			public void setSelectedWrapper(String xpathStr, String value) throws InterruptedException {
				init.el = FetchElementWrapper(xpathStr);
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
			public void setDateWrapper(String xpathStr, String DateFormat) throws InterruptedException {
				init.el = FetchElementWrapper(xpathStr);
				
			
				DateFormat dateFormat2 = new SimpleDateFormat("dd");
				Date date2 = new Date();

				String futureStart = dateFormat2.format(date2);
				System.out.println("futureStart = " +futureStart + " & " + date2);
				
				init.el.click();

				init.driver.context(init.nativeApp);

				List<IOSElement> wheels = (List<IOSElement>) init.driver.findElements(By.xpath("//XCUIElementTypePickerWheel"));
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

						init.driver.findElement(By.name("Done")).click();

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
					
//					if (wheels.get(3).getAttribute("value").equals("PM")) {
//						wheels.get(3).sendKeys("AM");
//					} else {
//						wheels.get(3).sendKeys("PM");
		//
//					}
					init.driver.findElement(By.name("Done")).click();

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
//						if (wheels.get(3).getAttribute("value").equals("PM")) {
//							wheels.get(3).sendKeys("PM");
//						} else {
//							wheels.get(3).sendKeys("AM");
		//
//						}

						init.driver.findElement(By.name("Done")).click();

					}

					catch (Exception e) {
						System.out.println("Date Picker Eception - " + e);
					}

				}else {
					
//					wheels.get(0).setValue("Wed 16 May");
		//
//					wheels.get(0).sendKeys("Wed 16 May");

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
			public void sendKeyWrapper(String xpathStr, String textStr) throws InterruptedException {
				init.el = FetchElementWrapper(xpathStr);
				try {
				init.el.clear();
				}catch(Exception e) {
					System.out.println("Send Keys Eception - " + e);

				}
				
				try {
				init.el.sendKeys(textStr);
				}catch(Exception e) {
					System.out.println("Send Keys Eception - " + e);

				}
			}

			public void takeScreenShotWrapper() throws IOException {
				Random rand = new Random();

				int  n = rand.nextInt(500) + 1;
				
				File scrFile = ((TakesScreenshot) init.driver).getScreenshotAs(OutputType.FILE);
				  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
				
				  FileUtils.copyFile(scrFile, new File("/auto/appium/sch/"+n+".jpg"));

			}

}
