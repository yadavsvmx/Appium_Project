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
import FSAID.FSAAID.objectRepo.Pg_login;
import FSAID.FSAAID.objectRepo.Pg_calendar;
import FSAID.FSAAID.objectRepo.Pg_explore;
import FSAID.FSAAID.utility.ScreenshotUtility;
import FSAID.FSAAID.wrapper.Wrapper;

//@Listeners({ ScreenshotUtility.class })

public class FSATest_1 {
	Initiator init = null;

	Wrapper wrpr = null;

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setup() throws IOException {
		wrpr = new Wrapper();
		init = new Initiator();
		init.setUp();
	}

	@Test(priority = 0)
	public void testiOS() throws InterruptedException, IOException {
		System.out.println("in test1");
		wrpr.execSahiScript("/auto/sahi_pro/userdata/scripts/Sahi_Project_Lightning/svmx/test_lab/test_cases/backOffice/appium_createWO.sah");
		// Start the appium driver, as the server usualy times out if no commands are sent
		init.setUp();
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		Pg_login.login("vinod.tharavath@ge.com", "svmx123#");

		wrpr.takeScreenShotWrapper();

		Pg_explore.createEvent("Work Order Search 2", "WO-00005081", "default", "default", "new event");

		wrpr.touchWraper(Pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");
		wrpr.touchWraper(Pg_explore.btn_recordTM, "tap");
		wrpr.touchWraper(Pg_explore.btn_parts_add, "tap");
		wrpr.setSelectedWrapper(Pg_explore.txt_picker_search, "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");

		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "GE Product");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper(Pg_explore.btn_picklist_addSelected, "tap");

		wrpr.touchWraper(Pg_explore.btn_travel_add, "tap");
		wrpr.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(Pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(Pg_explore.txt_linePricePerUnit, "20");
		wrpr.touchWraper(Pg_explore.btn_done, "tap");

		wrpr.touchWraper(Pg_explore.btn_labour_add, "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.setSelectedWrapper(Pg_explore.btn_activityType, "Cleanup");
		wrpr.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(Pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(Pg_explore.txt_linePricePerUnit, "20");

		wrpr.touchWraper(Pg_explore.btn_done, "tap");

		wrpr.touchWraper(Pg_explore.btn_save, "tap");
		// wrpr.touchWraper(Pg_explore.btn_yes, "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");

		wrpr.touchWraper(Pg_explore.btn_printServiceReport, "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_explore.btn_report_done, "click");

		// We need to roate to landscape before rotating to portraite
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

	}

	@Test(priority = 1)
	public void testiOS1() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		Pg_login.login("vinod.tharavath@ge.com", "svmx123#");

		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'WO-00005081')]//*[@class='sfmevent-location-container']", "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");
		wrpr.touchWraper(Pg_explore.btn_recordTM, "tap");
		wrpr.touchWraper(Pg_explore.btn_parts_add, "tap");
		wrpr.setSelectedWrapper(Pg_explore.txt_picker_search, "Starts With");

		wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");

		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "GE Product");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='GE Product'][@class = 'x-gridcell']", "tap");
		wrpr.touchWraper(Pg_explore.btn_picklist_addSelected, "tap");

		wrpr.touchWraper(Pg_explore.btn_travel_add, "tap");
		wrpr.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(Pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(Pg_explore.txt_linePricePerUnit, "20");
		wrpr.touchWraper(Pg_explore.btn_done, "tap");

		wrpr.touchWraper(Pg_explore.btn_labour_add, "tap");
		wrpr.touchWraper("//*[. = 'Part']//*[@class = 'x-input-el']", "tap");
		wrpr.sendKeyWrapper(Pg_explore.btn_picklist_serach, "BlueLake Men Watch");
		wrpr.touchWraper(Pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']", "tap");

		wrpr.setSelectedWrapper(Pg_explore.btn_activityType, "Cleanup");
		wrpr.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		wrpr.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		wrpr.sendKeyWrapper(Pg_explore.txt_lineQty, "100");
		wrpr.sendKeyWrapper(Pg_explore.txt_linePricePerUnit, "20");

		wrpr.touchWraper(Pg_explore.btn_done, "tap");

		wrpr.touchWraper(Pg_explore.btn_save, "tap");
		// wrpr.touchWraper(Pg_explore.btn_yes, "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");

		wrpr.touchWraper(Pg_explore.btn_printServiceReport, "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'WO-00005081')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_explore.btn_report_done, "click");

		// We need to roate to landscape before rotating to portraite
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);
		wrpr.execSahiScript("/auto/sahi_pro/userdata/scripts/Sahi_Project_Lightning/svmx/test_lab/test_cases/backOffice/dummy1.sah");

	}

	@AfterMethod
	public void tearDown() {
		init.driver.close();

	}

}
