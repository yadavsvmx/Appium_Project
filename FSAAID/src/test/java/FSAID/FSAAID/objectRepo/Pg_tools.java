package FSAID.FSAAID.objectRepo;

import org.openqa.selenium.By;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.Wrapper;

public class Pg_tools {
	Initiator init = new Initiator();
	static Wrapper wrpr = new Wrapper();
	public static String btn_tools = "//*[text() = 'Tools']";
	public static String btn_syncDataNow = "//*[text() = 'Sync Data Now']";
	public static String btn_startSync = "//*[text() = 'Start Sync']";
	public static String txt_syncSuccess = "//*[text() = 'Success']";
	
	public static void doDataSync() {
		
		wrpr.touchWraper(btn_tools, "tap");
		wrpr.touchWraper(btn_syncDataNow, "tap");
		wrpr.touchWraper(btn_startSync, "tap");


		try {
			if (wrpr.FetchElementWrapper(txt_syncSuccess) != null) {
				System.out.println("Sync completed successfully");
			}else {
				
				System.out.println("Sync Failed !");

			}
			
		} catch (Exception e) {
			System.out.println("Sync error : " + e);
		}
		
	}


}
