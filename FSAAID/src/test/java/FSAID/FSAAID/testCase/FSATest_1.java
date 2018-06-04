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
import FSAID.FSAAID.objectRepo.Pg_tools;
import FSAID.FSAAID.objectRepo.Pg_calendar;
import FSAID.FSAAID.objectRepo.Pg_explore;
import FSAID.FSAAID.utility.ApiServices;
import FSAID.FSAAID.utility.ScreenshotUtility;
import FSAID.FSAAID.wrapper.Wrapper;

//@Listeners({ ScreenshotUtility.class })

public class FSATest_1 {
	Initiator init = null;
	Wrapper wrpr = null;
	String woNum = null;
	Pg_login Pg_login = null;
	Pg_calendar Pg_calendar = null;
	Pg_explore Pg_explore = null;
	Pg_tools Pg_tools = null;
	String resultCommonPath = "/auto/appium/Appium_Project/FSAAID/src/test/java/FSAID/FSAAID/workBench/resultCommon.txt";

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setup() throws IOException {
		wrpr = new Wrapper();
		init = new Initiator();
		Pg_login = new Pg_login();
		Pg_calendar = new Pg_calendar();
		Pg_explore = new Pg_explore();
		Pg_tools = new Pg_tools();

		init.startDriver();
	}

	@Test(priority = 0)
	public void testiOS() {

		// From API

		ApiServices appServices = new ApiServices();
		try {
			appServices.getAccessToken();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sWOJsonData = "{\"SVMXC__City__c\":\"Delhi\",\"SVMXC__Zip__c\":\"110003\",\"SVMXC__Country__c\":\"India\",\"SVMXC__State__c\":\"Haryana\"}";
		try {
			woNum = appServices.getWOName(appServices.getWOORecordID(sWOJsonData));
			System.out.println("WO NUMBER FETCHED " + woNum);
			wrpr.writeTextFile(resultCommonPath, "true," + woNum);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Start the appium driver, as the server usualy times out if no commands are sent
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		Pg_login.login(init.un, init.pwd);

		Pg_tools.doDataSync();
		wrpr.touchWraper(Pg_calendar.btn_calendar, "tap");

		wrpr.takeScreenShotWrapper();

		Pg_explore.createEvent("Work Order Search 2", woNum, "default", "default", "new event");

		wrpr.touchWraper(Pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'" + woNum + "')]/div[@class='sfmevent-location-container']", "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");
		wrpr.touchWraper(Pg_explore.btn_recordTM, "tap");
		wrpr.touchWraper(Pg_explore.btn_parts_add, "tap");
		wrpr.setSelectedWrapper(Pg_explore.txt_picker_search, "Starts With");

		// wrpr.touchWraper("//*[.='Include Online']/..//*[@type='checkbox']/..", "tap");

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

		wrpr.touchWraper(Pg_explore.btn_save, "tap"); // wrpr.touchWraper(Pg_explore.btn_yes, "tap");

		wrpr.touchWraper(Pg_explore.btn_actions, "tap");

		wrpr.touchWraper(Pg_explore.btn_printServiceReport, "tap");

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'" + woNum + "')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_explore.btn_report_done, "click");
		
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		// We need to rotate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);

		if (Pg_tools.doDataSync() == true) {
			// FRom UI Execute a Sahi Pro script and then proceed only if it has passed
			String[] commonFileValArray = wrpr.execSahiScript("backOffice/appium_verifyWorkDetails.sah");
			if (!commonFileValArray[0].equals("true")) {
				System.out.println("Stopping Execution !");
				return;
			}

			try {
				wrpr.writeTextFile(resultCommonPath, "true," + woNum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				wrpr.writeTextFile("/auto/appium/Appium_Project/FSAAID/src/test/java/FSAID/FSAAID/workBench/resultCommon.txt", "false," + woNum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test(priority = 1)
	public void testiOS1() throws InterruptedException, IOException {

		init.driver.rotate(ScreenOrientation.PORTRAIT);

		Pg_login.login(init.un, init.pwd);
		Pg_tools.doDataSync();

		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_calendar.btn_calendar, "tap");

		wrpr.touchWraper("//div[contains(.,'" + woNum + "')]/div[@class='sfmevent-location-container']", "tap");

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
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'" + woNum + "')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		wrpr.takeScreenShotWrapper();

		wrpr.touchWraper(Pg_explore.btn_report_done, "click");
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);
		
		// We need to roate to landscape before rotating to portraite
		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);
		Pg_tools.doDataSync();
		// FRom UI Execute a Sahi Pro script and then proceed only if it has passed
		String[] commonFileValArray = wrpr.execSahiScript("backOffice/appium_verifyWorkDetails.sah");
		if (!commonFileValArray[0].equals("true")) {
			System.out.println("Stopping Execution !");
			return;
		}

	}

	@AfterMethod
	public void tearDown() {
		init.driver.close();

	}

}
