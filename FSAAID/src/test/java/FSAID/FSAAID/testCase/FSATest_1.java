package FSAID.FSAAID.testCase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.objectRepo.pg_calendar;
import FSAID.FSAAID.objectRepo.pg_explore;
import FSAID.FSAAID.objectRepo.pg_login;
import FSAID.FSAAID.utility.ScreenshotUtility;
import FSAID.FSAAID.wrapper.Wrapper;

@Listeners({ ScreenshotUtility.class })

public class FSATest_1 {
	Initiator init = null;

	Wrapper wrpr = null;

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setup() throws IOException {

	
		init = new Initiator();
		init.setUp();
		wrpr = new Wrapper();

	}

	public void login() throws InterruptedException, IOException {
		if (init.driver.findElements(By.id(pg_login.btn_signin)).size() != 0) {

			init.driver.findElement(By.id(pg_login.btn_signin)).click();
			Thread.sleep(6000);
			// init.driver.findElement(By.id("username")).sendKeys("Vinod.tharavath@ge.com");
			// init.driver.findElement(By.id("password")).sendKeys("svmx123#");
			init.driver.findElement(By.id(pg_login.txt_username)).sendKeys("vinod.tharavath@ge.com");
			init.driver.findElement(By.id(pg_login.txt_password)).sendKeys("svmx123#");
			init.driver.findElement(By.id(pg_login.btn_Login)).click();

			Thread.sleep(8000);
			init.driver.findElement(By.id(pg_login.btn_oaapprove)).click();
			System.out.println("Login Successfull");

		} else {
			// do nothing
			System.out.println("Already Logged in skipping Login");
		}
		System.out.println("Waiting for Landing Page");

		init.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pg_calendar.btn_calendar)));

		Thread.sleep(3000);
	}

	@Test(priority = 0)
	public void testiOS() throws InterruptedException, IOException {

		wrpr.execSahiScript("/auto/sahi_pro/userdata/scripts/Sahi_Project/svmx/test_lab/test_cases/backOffice/dummy1.sah");

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();
		// wrpr.touchWraper(pg_explore.btn_explore, "tap");
		// wrpr.touchWraper("//div[. = 'AppiumSearch']/..", "tap");

		// wrpr.touchWraper("//*[text() = 'DC SEARCH']", "tap");
		// wrpr.touchWraper("//*[@class = 'x-listitem-body']/*[@class ='x-innerhtml']/*[contains(.,'Work Orders (')]", "tap");

		// wrpr.touchWraper("//*[text() = 'Work Order Search 2']", "tap");
		// wrpr.touchWraper("//*[.='Include Online Items']/..//*[@data-componentid = 'ext-toggleslider-1']", "tap");
		// wrpr.sendKeyWrapper(pg_explore.txt_search, "WO-00005081");
		// wrpr.touchWraper(pg_explore.btn_search, "tap");
		//
		// wrpr.touchWraper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'WO-00005081')]", "tap");
		//

		pg_explore.createEvent("Work Order Search 2", "WO-00005081", "default", "default", "new event");

		wrpr.touchWraper(pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");

		wrpr.touchWraper(pg_explore.btn_actions, "tap");
		wrpr.touchWraper(pg_explore.btn_recordTM, "tap");
		wrpr.touchWraper(pg_explore.btn_parts_add, "tap");
		wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");

		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");

		wrpr.touchWraper(pg_explore.btn_travel_add, "tap");
		wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
		wrpr.touchWraper(pg_explore.btn_done, "tap");

		wrpr.touchWraper(pg_explore.btn_labour_add, "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup");
		wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");

		wrpr.touchWraper(pg_explore.btn_done, "tap");

		wrpr.touchWraper(pg_explore.btn_save, "tap");
		// wrpr.touchWraper(pg_explore.btn_yes, "tap");

		wrpr.touchWraper(pg_explore.btn_actions, "tap");

		wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(pg_explore.btn_report_done, "click");

		// We need to roate to landscape before rotating to portraite
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

	}

	@Test(priority = 1)
	public void testiOS1() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		login();
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");

		wrpr.touchWraper(pg_explore.btn_actions, "tap");
		wrpr.touchWraper(pg_explore.btn_recordTM, "tap");
		wrpr.touchWraper(pg_explore.btn_parts_add, "tap");
		wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");

		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");

		wrpr.touchWraper(pg_explore.btn_travel_add, "tap");
		wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
		wrpr.touchWraper(pg_explore.btn_done, "tap");

		wrpr.touchWraper(pg_explore.btn_labour_add, "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup");
		wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");

		wrpr.touchWraper(pg_explore.btn_done, "tap");

		wrpr.touchWraper(pg_explore.btn_save, "tap");
		// wrpr.touchWraper(pg_explore.btn_yes, "tap");

		wrpr.touchWraper(pg_explore.btn_actions, "tap");

		wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(pg_explore.btn_report_done, "click");

		// We need to roate to landscape before rotating to portraite
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

	}

	/*
	 * @Test(priority = 3)
	 * 
	 * public void testiOS2() throws InterruptedException, IOException {
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 4)
	 * 
	 * public void testiOS3() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 5)
	 * 
	 * public void testiOS4() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 6)
	 * 
	 * public void testiOS5() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 7)
	 * 
	 * public void testiOS6() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 8)
	 * 
	 * public void testiOS7() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 9)
	 * 
	 * public void testiOS8() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 10)
	 * 
	 * public void testiOS9() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 11)
	 * 
	 * public void testiOS10() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 12)
	 * 
	 * public void testiOS11() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(priority = 13) public void testiOS12() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 14) public void testiOS13() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 15) public void testiOS14() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 16) public void testiOS15() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 17) public void testiOS16() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 18) public void testiOS17() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 19) public void testiOS18() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 20) public void testiOS19() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 21) public void testiOS20() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 22) public void testiOS21() throws InterruptedException, IOException {
	 * 
	 * 
	 * init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * login(); wrpr.takeScreenShotWrapper();
	 * 
	 * 
	 * wrpr.touchWraper(pg_calendar.btn_calendar, "tap");
	 * 
	 * wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap"); wrpr.touchWraper(pg_explore.btn_recordTM, "tap"); wrpr.touchWraper(pg_explore.btn_parts_add, "tap"); wrpr.setSelectedWrapper(pg_explore.txt_picker_search, "Starts With");
	 * 
	 * wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "GE Product"); wrpr.touchWraper(pg_explore.btn_search, "tap"); wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap"); wrpr.touchWraper(pg_explore.btn_picklist_addSelected, "tap");
	 * 
	 * 
	 * wrpr.touchWraper(pg_explore.btn_travel_add, "tap"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100"); wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit,
	 * "20"); wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_labour_add, "tap"); wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap"); wrpr.sendKeyWrapper(pg_explore.btn_picklist_serach, "BlueLake Men Watch"); wrpr.touchWraper(pg_explore.btn_search, "tap");
	 * wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");
	 * 
	 * wrpr.setSelectedWrapper(pg_explore.btn_activityType, "Cleanup"); wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, "futureStart"); wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, "futureEnd"); wrpr.sendKeyWrapper(pg_explore.txt_lineQty, "100");
	 * wrpr.sendKeyWrapper(pg_explore.txt_linePricePerUnit, "20");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_done, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_save, "tap"); // wrpr.touchWraper(pg_explore.btn_yes, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_actions, "tap");
	 * 
	 * wrpr.touchWraper(pg_explore.btn_printServiceReport, "tap");
	 * 
	 * try { if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) { System.out.println("Opened the document page successfully"); } } catch (Exception e) { System.out.println("Document error : " + e); } wrpr.takeScreenShotWrapper();
	 * 
	 * wrpr.touchWraper(pg_explore.btn_report_done, "click");
	 * 
	 * //We need to roate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);
	 * 
	 * 
	 * 
	 * }
	 */
	@AfterMethod
	public void tearDown() {
		init.driver.close();
	}

}
