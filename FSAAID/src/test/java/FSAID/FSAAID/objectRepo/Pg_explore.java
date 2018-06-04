package FSAID.FSAAID.objectRepo;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.*;

public class Pg_explore {

	Initiator init = new Initiator();
	static Wrapper wrpr = new Wrapper();

	public  String btn_explore = "//*[text() = 'Explore']";
	public  String txt_search = "//input[@placeholder='Search']";
	public  String btn_search = "//*[.='Search'][@class = 'x-button-label']";
	public  String btn_actions = "//*[text() = 'Actions']";
	public  String btn_newEvent = "//*[text() = 'New Event']";
	public  String txt_startDateAndTime = "//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input";
	public  String txt_endDateAndTime = "//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input";
	public  String txt_subject = "//*[. = 'Subject']//*[@class = 'x-input-el']";
	public  String btn_save = "//*[text() = 'Save']";
	public  String btn_yes = "//*[text() = 'Yes']";
	public  String txt_lineQty = "//*[. = 'Line Qty']//*[@class = 'x-input-el']";
	public  String txt_linePricePerUnit = "//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']";
	public  String btn_done = "//*[text() = 'Done']";
	public  String btn_recordTM = "//*[text() = 'Record T&M']";
	public  String txt_picker_search = "//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']";
	public  String btn_parts_add = "//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]";
	public  String btn_picklist_serach = "//input[@placeholder='Search'][@class='x-input-el']";
	public  String btn_picklist_addSelected = "//*[. = 'Add Selected']";
	public  String btn_travel_add = "//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]";
	public  String btn_labour_add = "//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]";
	public  String txt_part = "//*[. = 'Part']//*[@class = 'x-input-el']";
	public  String btn_activityType = "//*[. = 'Activity Type']//input";
	public  String btn_printServiceReport = "//*[text() = 'Print Service Report']";
	public  String btn_report_done = "//input[@value ='Done']";

	public  void navigateToExplore() {
		wrpr.touchWraper(btn_explore, "tap");

	}

	public  void searchForWorOrder(String searchName, String workOrderNumber) {
		navigateToExplore();
		wrpr.touchWraper("//*[text() = '" + searchName + "']", "tap");
		wrpr.touchWraper("//*[.='Include Online Items']/..//*[@data-componentid = 'ext-toggleslider-1']", "tap");
		wrpr.sendKeyWrapper(txt_search, workOrderNumber);

	}

	public  void searchAndSelectWorOrder(String searchName, String workOrderNumber) {
		searchForWorOrder(searchName, workOrderNumber);
		wrpr.touchWraper(btn_search, "tap");
		wrpr.touchWraper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'" + workOrderNumber + "')]", "tap");

	}

	public  void createEvent(String searchName, String workOrderNumber, String startDate, String endDate, String Subject) {
		searchAndSelectWorOrder(searchName, workOrderNumber);

		wrpr.touchWraper(btn_actions, "tap");
		wrpr.touchWraper(btn_newEvent, "tap");
		wrpr.setDateWrapper(txt_startDateAndTime, startDate);

		wrpr.setDateWrapper(txt_endDateAndTime, endDate);
		wrpr.sendKeyWrapper(txt_subject, Subject);
		wrpr.touchWraper(btn_save, "tap");

		if (wrpr.FetchElementWrapper(btn_yes) != null) {
			wrpr.touchWraper(btn_yes, "tap");
		}

	}
}
