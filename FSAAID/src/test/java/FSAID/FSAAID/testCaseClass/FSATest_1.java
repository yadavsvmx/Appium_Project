package FSAID.FSAAID.testCaseClass;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FSAID.FSAAID.initiatorClass.Initiator;
import FSAID.FSAAID.utilityClass.ScreenshotUtility;
import FSAID.FSAAID.wrapperClass.Wrapper;

@Listeners({ ScreenshotUtility.class })

public class FSATest_1 {
	Initiator init = null;
	
	Wrapper wrpr = null;

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setup() {
		 init = new Initiator();
		init.setUp();
		 wrpr = new Wrapper();
	}
	
	public void login() throws InterruptedException, IOException {
		
		if(init.driver.findElements(By.id("svmx_splash_signin")).size() != 0) {
			
			init.driver.findElement(By.id("svmx_splash_signin")).click();
			Thread.sleep(6000);
			// init.driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
			// init.driver.findElement(By.id("password")).sendKeys("svmx123#");
			init.driver.findElement(By.id("username")).sendKeys("vinod.tharavath@ge.com");
			init.driver.findElement(By.id("password")).sendKeys("svmx123#");
			init.driver.findElement(By.id("Login")).click();

			Thread.sleep(8000);
			init.driver.findElement(By.id("oaapprove")).click();
			System.out.println("Login Successfull");

		}else {
			//do nothing
			System.out.println("Already Logged in skipping Login");
		}
		System.out.println("Waiting for Landing Page");

		init.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Calendar']")));

		Thread.sleep(3000);
	}

	@Test(priority = 0)
	public void testiOS() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
		wrpr.touchWraper("//*[text() = 'Explore']", "tap");
		// wrpr.touchWraper("//div[. = 'AppiumSearch']/..", "tap");

		// wrpr.touchWraper("//*[text() = 'DC SEARCH']", "tap");
		// wrpr.touchWraper("//*[@class = 'x-listitem-body']/*[@class ='x-innerhtml']/*[contains(.,'Work Orders (')]", "tap");

		wrpr.touchWraper("//*[text() = 'Work Order Search 2']", "tap");
		wrpr.touchWraper("//*[.='Include Online Items']/..//*[@data-componentid = 'ext-toggleslider-1']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search']", "WO-00005081");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");

		wrpr.touchWraper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'WO-00005081')]", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'New Event']", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "default");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "default");
		wrpr.sendKeyWrapper("//*[. = 'Subject']//*[@class = 'x-input-el']", "100");
		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		
		if(wrpr.FetchElementWrapper(" //*[text() = 'Yes']") != null) {
			 wrpr.touchWraper("//*[text() = 'Yes']", "tap");
		}

		wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
		
		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
		wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
		

		wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

		wrpr.touchWraper("//*[text() = 'Actions']", "tap");

		wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	@Test(priority = 1)
	public void testiOS1() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
		

		wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
		
		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
		wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
		

		wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

		wrpr.touchWraper("//*[text() = 'Actions']", "tap");

		wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	/*
	@Test(priority = 3)

	public void testiOS2() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
		
		wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
		
		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
		wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
		

		wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

		wrpr.touchWraper("//*[text() = 'Actions']", "tap");

		wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	@Test(priority = 4)

	public void testiOS3() throws InterruptedException, IOException {

		
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
	

		wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
		
		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
		wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
		

		wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

		wrpr.touchWraper("//*[text() = 'Actions']", "tap");

		wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
	
	@Test(priority = 5)

public void testiOS4() throws InterruptedException, IOException {

		
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
		

		wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
		
		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
		
		wrpr.touchWraper("//*[text() = 'Actions']", "tap");
		wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
		wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
		

		wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
		wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
		
		wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
		wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
		wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
		wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
		wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
		
		wrpr.touchWraper("//*[text() = 'Done']", "tap");

		wrpr.touchWraper("//*[text() = 'Save']", "tap");
		// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

		wrpr.touchWraper("//*[text() = 'Actions']", "tap");

		wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper("//input[@value ='Done']", "click");
		
		//We need to roate to landscape before rotating to portraite 
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		

	}
	
@Test(priority = 6)

public void testiOS5() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}
	
@Test(priority = 7)

public void testiOS6() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 8)

public void testiOS7() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 9)

public void testiOS8() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 10)

public void testiOS9() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 11)

public void testiOS10() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 12)

public void testiOS11() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}

@Test(priority = 13)
public void testiOS12() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 14)
public void testiOS13() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 15)
public void testiOS14() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 16)
public void testiOS15() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 17)
public void testiOS16() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 18)
public void testiOS17() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 19)
public void testiOS18() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 20)
public void testiOS19() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 21)
public void testiOS20() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	
	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}


@Test(priority = 22)
public void testiOS21() throws InterruptedException, IOException {

	
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	login();
	wrpr.takeScreenShotWrapper();
	

	wrpr.touchWraper("//*[text() = 'Calendar']", "tap");
	
	wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	
	wrpr.touchWraper("//*[text() = 'Actions']", "tap");
	wrpr.touchWraper("//*[text() = 'Record T&M']", "tap");
	wrpr.touchWraper("//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setSelectedWrapper("//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']", "Starts With");

	wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "GE Product");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
	wrpr.touchWraper("//*[. = 'Add Selected']", "tap");
	

	wrpr.touchWraper("//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]", "tap");
	wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
	wrpr.sendKeyWrapper("//input[@placeholder='Search'][@class='x-input-el']", "BlueLake Men Watch");
	wrpr.touchWraper("//*[.='Search'][@class = 'x-button-label']", "tap");
	wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	
	wrpr.setSelectedWrapper("//*[. = 'Activity Type']//input", "Cleanup");
	wrpr.setDateWrapper("//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input", "futureStart");
	wrpr.setDateWrapper("//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input", "futureEnd");
	wrpr.sendKeyWrapper("//*[. = 'Line Qty']//*[@class = 'x-input-el']", "100");
	wrpr.sendKeyWrapper("//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']", "20");
	
	wrpr.touchWraper("//*[text() = 'Done']", "tap");

	wrpr.touchWraper("//*[text() = 'Save']", "tap");
	// wrpr.touchWraper("//*[text() = 'Yes']", "tap");

	wrpr.touchWraper("//*[text() = 'Actions']", "tap");

	wrpr.touchWraper("//*[text() = 'Print Service Report']", "tap");

	try {
		if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
			System.out.println("Opened the document page successfully");
		}
	} catch (Exception e) {
		System.out.println("Document error : " + e);
	}
	wrpr.takeScreenShotWrapper();

	wrpr.touchWraper("//input[@value ='Done']", "click");
	
	//We need to roate to landscape before rotating to portraite 
	init.driver.rotate(ScreenOrientation.LANDSCAPE);
	init.driver.rotate(ScreenOrientation.PORTRAIT);

	

}
*/
@AfterMethod
public void tearDown() {
	init.driver.close();
}


}
