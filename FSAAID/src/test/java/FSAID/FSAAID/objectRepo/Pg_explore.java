package FSAID.FSAAID.objectRepo;

import org.openqa.selenium.WebElement;

import FSAID.FSAAID.initiator.Initiator;
import FSAID.FSAAID.wrapper.*;

public class Pg_explore {

	Initiator init = new Initiator();
	static Wrapper wrpr = new Wrapper();
	
//	@FindBy(xpath="//android.view.View[@content-desc='Explore']")
	private String btn_exploreFact = "//*[text() = 'Explore']";
	public WebElement btn_explore() 
	{
		return (WebElement) wrpr.fetchElementWrapper(btn_exploreFact);
	}

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

	public  Pg_explore navigateToExplore() {
		btn_explore().click();
return this;
	}

	public  Pg_explore searchForWorOrder(String searchName, String workOrderNumber) {
		navigateToExplore();
		wrpr.fetchElementWrapper("//*[text() = '" + searchName + "']").tap();
		wrpr.fetchElementWrapper(txt_search).sendKeyWrapper( workOrderNumber);
		return this;

	}

	public  Pg_explore searchAndSelectWorOrder(String searchName, String workOrderNumber) {
		searchForWorOrder(searchName, workOrderNumber);
		wrpr.fetchElementWrapper(btn_search).tap();
		wrpr.fetchElementWrapper("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'" + workOrderNumber + "')]").tap();
		return this;

	}

	public  Pg_explore createEvent(String searchName, String workOrderNumber, String startDate, String endDate, String Subject) {
		searchAndSelectWorOrder(searchName, workOrderNumber);

		wrpr.fetchElementWrapper(btn_actions).tap();
		wrpr.fetchElementWrapper(btn_newEvent).tap();
		wrpr.setDateWrapper(txt_startDateAndTime, startDate);

		wrpr.setDateWrapper(txt_endDateAndTime, endDate);
		wrpr.fetchElementWrapper(txt_subject).sendKeyWrapper( Subject);
		wrpr.fetchElementWrapper(btn_save).tap();

		if (wrpr.FetchElementDetails(btn_yes) != null) {
			wrpr.fetchElementWrapper(btn_yes).tap();
		}
		return this;


	}
}
