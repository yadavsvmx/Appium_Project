package FSAID.FSAAID.objectRepo;
import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.*;

public class pg_explore {
	
	Initiator init = new Initiator();
	static Wrapper wrpr = new Wrapper();
		 
	public static String btn_explore = "//*[text() = 'Explore']";
	public static String txt_search ="//input[@placeholder='Search']";
	public static String btn_search ="//*[.='Search'][@class = 'x-button-label']";
	public static String btn_actions = "//*[text() = 'Actions']";
	public static String btn_newEvent ="//*[text() = 'New Event']";
	public static String txt_startDateAndTime ="//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input";
	public static String txt_endDateAndTime ="//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input";
	public static String txt_subject ="//*[. = 'Subject']//*[@class = 'x-input-el']";
	public static String btn_save ="//*[text() = 'Save']";
	public static String btn_yes ="//*[text() = 'Yes']";
	public static String txt_lineQty ="//*[. = 'Line Qty']//*[@class = 'x-input-el']";
	public static String txt_linePricePerUnit ="//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']";
	public static String btn_done ="//*[text() = 'Done']";
	public static String btn_recordTM ="//*[text() = 'Record T&M']";
	public static String txt_picker_search ="//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']";
	public static String btn_parts_add ="//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]";
	public static String btn_picklist_serach ="//input[@placeholder='Search'][@class='x-input-el']";
	public static String btn_picklist_addSelected ="//*[. = 'Add Selected']";
	public static String btn_travel_add ="//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]";
	public static String btn_labour_add ="//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]";
	public static String txt_part ="//*[. = 'Part']//*[@class = 'x-input-el']";
	public static String btn_activityType ="//*[. = 'Activity Type']//input";
	public static String btn_printServiceReport ="//*[text() = 'Print Service Report']";
	public static String btn_report_done ="//input[@value ='Done']";
	
	

	public static void navigateToExplore() {
		wrpr.touchWraper(pg_explore.btn_explore, "tap");
		
	}
	
	public static void searchForWorOrder(String searchName,String workOrderNumber) {
		navigateToExplore();
		wrpr.touchWraper("//*[text() = '"+searchName+"']", "tap");
		wrpr.touchWraper("//*[.='Include Online Items']/..//*[@data-componentid = 'ext-toggleslider-1']", "tap");
		try {
			wrpr.sendKeyWrapper(pg_explore.txt_search, workOrderNumber);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void searchAndSelectWorOrder(String searchName,String workOrderNumber) {
		searchForWorOrder(searchName, workOrderNumber);
		wrpr.touchWraper(pg_explore.btn_search, "tap");
		wrpr.touchWraper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'"+workOrderNumber+"')]", "tap");
		
	}
	
	public static void createEvent(String searchName,String workOrderNumber,String startDate,String endDate,String Subject) throws InterruptedException {
		pg_explore.searchAndSelectWorOrder(searchName,workOrderNumber) ;

		wrpr.touchWraper(pg_explore.btn_actions, "tap");
		wrpr.touchWraper(pg_explore.btn_newEvent, "tap");
		wrpr.setDateWrapper(pg_explore.txt_startDateAndTime, startDate);
		wrpr.setDateWrapper(pg_explore.txt_endDateAndTime, endDate);
		wrpr.sendKeyWrapper(pg_explore.txt_subject, Subject);
		wrpr.touchWraper(pg_explore.btn_save, "tap");
		
		if(wrpr.FetchElementWrapper(pg_explore.btn_yes) != null) {
			 wrpr.touchWraper(pg_explore.btn_yes, "tap");
		}
		
	}
}
