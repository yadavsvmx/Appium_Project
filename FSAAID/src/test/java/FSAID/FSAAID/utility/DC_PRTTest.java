package FSAID.FSAAID.utility;

import java.io.IOException;
import org.ge.dclibrary.AppServices;
import org.ge.dclibrary.BaseLib;
import org.ge.dclibrary.GenericLib;
import org.ge.pageobjects.DC_DispatchConsolePage;
import org.ge.pageobjects.DC_SalesforcePage;
import org.ge.pageobjects.DC_ServiceMaxSetupPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.kirwa.nxgreport.NXGReports;
import com.kirwa.nxgreport.logging.LogAs;
import com.kirwa.nxgreport.selenium.reports.CaptureScreen;
import com.kirwa.nxgreport.selenium.reports.CaptureScreen.ScreenshotOf;

@Test
public class DC_PRTTest extends AppServices
{
	//WebDriver driver=null;
	Alert alert = null;
	Actions actions = null;
	Select selList = null;
	Screen scn = null;
	DC_SalesforcePage dc_salesforPage = null;
	DC_ServiceMaxSetupPage dc_servicemaxsetupPage = null;
	DC_DispatchConsolePage dc_dispatchconsolePage = null;
	AppServices appServices = null;
	Pattern pattern =null;
	String sTestCaseID=null;
	String sTeam=null;	String sTech=null; String sStreet=null; String sCity=null; String sState=null;	String sZip=null; String sCountry=null;
	String sWorkOrderID=null; String sFromDate = null; String sToDate =null; String sTechName = null; String sStartDate = null; String sEndDate = null;
	String sStartTime = null;String sEndTime = null; String sWOJsonData = null; String sWOName=null; String sTechZip = null; String sTechStreet = null;
	String sTerritoryName = null; String sParentName = null;
	
	@BeforeClass()
	public void login() throws InterruptedException, IOException
	{		
		dc_salesforPage = new DC_SalesforcePage(driver);
		dc_servicemaxsetupPage = new DC_ServiceMaxSetupPage(driver);
		dc_dispatchconsolePage = new DC_DispatchConsolePage();
		dc_salesforPage.login(GenericLib.getCongigValue(GenericLib.sConfigFile, "USERNAME"), GenericLib.getCongigValue(GenericLib.sConfigFile, "PASSWORD"));
		dc_salesforPage.sParentWnd=driver.getWindowHandle();
		appServices = new AppServices();
		appServices.getAccessToken();
		//dc_dispatchconsolePage = new DC_DispatchConsolePage();
	}
	
	@AfterClass
	public void logout()
	{
		//logout
	}
	
	@AfterMethod
	public void baseStateHome() throws Exception
	{
		driver.switchTo().window(dc_salesforPage.sParentWnd);
		dc_salesforPage.navigatehome();
		driver.switchTo().defaultContent();
	}
	
	
	@Test(priority=8, enabled=true, description="RS_3709A_RS_3720: Verify creating Territory and adding Territory coverage/Verify the functionality of Service org - Service team and technician creation ")
	public void createTerritoryAddCoverage() throws Exception
	{
		sTestCaseID="RS_3709A";	
		sState=GenericLib.getExcelData(sTestCaseID, "State");
		sTerritoryName=GenericLib.getExcelData(sTestCaseID, "TerritoryName"); sParentName=GenericLib.getExcelData(sTestCaseID, "ParentName");
		try {	
			dc_servicemaxsetupPage.navigateTerritoryMngmt();
			dc_servicemaxsetupPage.SearchAddTerritory(sParentName, null);	
			dc_servicemaxsetupPage.getEleTerritoryRdBtn(sParentName).click();
			dc_servicemaxsetupPage.getEleAddCoverageBtn().click();
			selList = new Select(dc_servicemaxsetupPage.getEleTerrCoverageLst());
			selList.selectByValue("State");
			dc_servicemaxsetupPage.getEleTerrCoverageTxtFld().sendKeys(sState);
			dc_servicemaxsetupPage.getEleSaveBtn().click();
			Assert.assertTrue(dc_servicemaxsetupPage.getEleSuccessTxt().isDisplayed(), "Territory coverage is not added to territory");
			NXGReports.addStep("Territory coverage is successfully added to Territory", LogAs.PASSED,null);
		
			dc_servicemaxsetupPage.addDispatcher();
			dc_servicemaxsetupPage.SearchAddTerritory(sTerritoryName, sParentName);		
			dc_servicemaxsetupPage.getEleTerritoryRdBtn(sTerritoryName).click();
			Thread.sleep(GenericLib.iLowSleep);
			dc_servicemaxsetupPage.addDispatcher();
			
			dc_salesforPage.navigatehome();
			dc_salesforPage.naviagateDC();
			
			scn = new Screen();
			NXGReports.addStep("Launching DC", LogAs.PASSED,null);
			scn.click(new Pattern(dc_dispatchconsolePage.sServiceTeamViewLst));
			scn.click(new Pattern(dc_dispatchconsolePage.sTerritoryViewTxt));
			scn.click(new Pattern(dc_dispatchconsolePage.sExpandAllLnk));
			Thread.sleep(GenericLib.iMedSleep);
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sChennaiTerrLst))!=null),"Territory is not expanded in DC");
			NXGReports.addStep("Territory is expanded and displayed in DC", LogAs.PASSED,null);
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sKarnatakaTerrLst))!=null),"Territory is not expanded in DC");
			NXGReports.addStep("Territory is expanded and displayed in DC", LogAs.PASSED,null);
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sExpandedTerrLst))!=null),"Multiple Territories are not expanded in DC");
			NXGReports.addStep("Multiple Territories are expanded and displayed in DC", LogAs.PASSED,null);
		
			scn.click(new Pattern(dc_dispatchconsolePage.sCollapseAllLnk));
			Thread.sleep(GenericLib.iMedSleep);
			Assert.assertTrue(((scn.exists(new Pattern(dc_dispatchconsolePage.sChennaiTerrCollapseImg))!=null)||((scn.exists(new Pattern(dc_dispatchconsolePage.sKarnatakaTerrCollapseImg))!=null))),"Territories are not collapsed in DC");
			NXGReports.addStep("Territories are collapsed in DC", LogAs.PASSED,null);
			scn.click(new Pattern(dc_dispatchconsolePage.sTerritoryViewLst));
			scn.click(new Pattern(dc_dispatchconsolePage.sServiceTeamViewTxt));
			Thread.sleep(GenericLib.iMedSleep);
			scn.click(new Pattern(dc_dispatchconsolePage.sExpandAllLnk));
			Thread.sleep(GenericLib.iMedSleep);
			Assert.assertTrue(((scn.exists(new Pattern(dc_dispatchconsolePage.sMysoreTeamLst))!=null)),"Teams are not expanded in DC");
			NXGReports.addStep("Teams are expanded in DC", LogAs.PASSED,null);
			scn.click(new Pattern(dc_dispatchconsolePage.sCollapseAllLnk));
			Thread.sleep(GenericLib.iMedSleep);
			Assert.assertTrue(((scn.exists(new Pattern(dc_dispatchconsolePage.sMysoreTeamCollapseImg))!=null)),"Teams are not expanded in DC");
			NXGReports.addStep("Teams are collapsed in DC", LogAs.PASSED,null);
			}
		catch(Exception e){
			NXGReports.addStep("Testscript Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
		finally 
		{	try {
			if(dc_salesforPage.sDCWnd!=null) {
			driver.switchTo().window(dc_salesforPage.sDCWnd).close();}
			driver.switchTo().window(dc_salesforPage.sParentWnd);}catch(Exception e) {}
			
		}
		}
	
	
	@Test(priority=9, enabled=true, description="RS_3731: Event Subject rules ")
	public void dcEventSubjectRule() throws Exception
	{
		sTestCaseID="RS_3731";	
		sWOJsonData="{\"SVMXC__City__c\":\"Delhi\",\"SVMXC__Zip__c\":\"110003\",\"SVMXC__Country__c\":\"India\",\"SVMXC__State__c\":\"Haryana\"}";
		
		sTeam=GenericLib.getExcelData(sTestCaseID, "Team");  sStreet = GenericLib.getExcelData(sTestCaseID, "Street"); sCity =GenericLib.getExcelData(sTestCaseID, "City"); sState = GenericLib.getExcelData(sTestCaseID, "State"); sZip = GenericLib.getExcelData(sTestCaseID, "Zip"); sCountry = GenericLib.getExcelData(sTestCaseID, "Country");
		sTech=GenericLib.getExcelData(sTestCaseID, "Tech"); sTechZip = GenericLib.getExcelData(sTestCaseID, "TechZip"); sTechStreet=GenericLib.getExcelData(sTestCaseID, "Tech_street");
		sFromDate = GenericLib.getExcelData(sTestCaseID, "FromDate"); sToDate =GenericLib.getExcelData(sTestCaseID, "ToDate"); sTechName = GenericLib.getExcelData(sTestCaseID, "TechName"); sStartDate = GenericLib.getExcelData(sTestCaseID, "StartDate"); sEndDate = GenericLib.getExcelData(sTestCaseID, "EndDate");
		sStartTime = GenericLib.getExcelData(sTestCaseID, "StartTime"); sEndTime = GenericLib.getExcelData(sTestCaseID, "EndTime");
		
		try 
		{	
			sWorkOrderID=appServices.getWOORecordID(sWOJsonData);
			sWOName =appServices.getWOName(sWorkOrderID);
		
			dc_servicemaxsetupPage.navigateTeamTechnicianMngmt();
			dc_servicemaxsetupPage.searchTeam(sTeam,sTech, sStreet, sCity, sState, sZip, sCountry,true,true,dc_salesforPage);
			dc_servicemaxsetupPage.searchTech(sTeam,sTech, sTechStreet, sCity, sState, sTechZip, sCountry, true,true,dc_salesforPage);
			dc_servicemaxsetupPage.getEleTeamRdBtn(sTeam).click();
			Thread.sleep(GenericLib.iLowSleep);
			dc_servicemaxsetupPage.addDispatcher();
			dc_salesforPage.navigatehome();

			dc_servicemaxsetupPage.navigateServiceMaxSetup();
			dc_servicemaxsetupPage.getEleDispatchManagementBtn().click();
			Thread.sleep(GenericLib.iLowSleep);
			NXGReports.addStep("Navigation to Dispatch Management module", LogAs.PASSED,null);
			dc_servicemaxsetupPage.createEventSubjectRule();
			
			dc_salesforPage.navigatehome();
			dc_salesforPage.naviagateDC();
			scn = new Screen();
			dc_dispatchconsolePage.gridfilterSearch(scn, "W", sWOName);
			dc_dispatchconsolePage.setGanttCalender(scn, sFromDate, sToDate);
			dc_dispatchconsolePage.searchTechnician(scn, sTechName, dc_dispatchconsolePage.sKarolBaghTechTxt);
		
			if(scn.exists(new Pattern(dc_dispatchconsolePage.sGanttExpandIcn))!=null)
			{scn.click(new Pattern(dc_dispatchconsolePage.sGanttExpandIcn));	}
			Thread.sleep(GenericLib.iMedSleep);
			dc_dispatchconsolePage.dragDropWO(scn, dc_dispatchconsolePage.sEventTimeImg);
			dc_dispatchconsolePage.createEvent(scn, sStartDate, sStartTime, sEndDate, sEndTime);
		
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sAssignedTxt))!=null), "Event is not assigend to technician");
			NXGReports.addStep("Event is created and assigned to Technician", LogAs.PASSED,null);
			
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sWOEventTxt))!=null), "Event is not displayed on the gantt");
			NXGReports.addStep("Created event is displayed on the gantt", LogAs.PASSED,null);
			
			scn.doubleClick(new Pattern(dc_dispatchconsolePage.sWOEventTxt));
			Thread.sleep(5000);
			
			Assert.assertTrue((scn.exists(new Pattern(dc_dispatchconsolePage.sEventSubjectTxt))!=null), "Event Subject is not displayed on the edit event window");
			NXGReports.addStep("Event Subject text is displayed on the edit window", LogAs.PASSED,null);
			scn.click(new Pattern(dc_dispatchconsolePage.sSaveBtn));
			
		}
		catch(Exception e){
		NXGReports.addStep("Testscript Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		throw e;
		}
		finally 
		{	try {
			if(dc_salesforPage.sDCWnd!=null) {
			driver.switchTo().window(dc_salesforPage.sDCWnd).close();}
			driver.switchTo().window(dc_salesforPage.sParentWnd);}catch(Exception e) {}
			
		}
	}
	
	
}
