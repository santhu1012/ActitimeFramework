package com.hybridfw.actitime.driverscript;

import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.hybridfw.actitime.datatable.Datatable;
import com.hybridfw.actitime.testscript.Initialize;
import com.hybridfw.actitime.utility.ApplicationIndependent;
import com.hybridfw.actitime.utility.ObjectMap;
import com.hybridfw.actitime.utility.ReportUtil;

public class DriverScript 
{
	public static WebDriver oBrowser=null;
	public static Datatable oDatatable=null;
	public static String sControllerExcelFile=null;
	public static String sScenarioExcelFile=null;
	public static String testdatacolumn=null;
	public static Properties oConfig;
	public static Properties oExpectedResult;
	public static String sExpectedResultColumn=null;
	public static String sObjectMapColumn=null;
	public static ObjectMap oObjectMap=null;
	public static String sTestScriptStatus=null;
	public static Logger log=Logger.getLogger("ActiTime Automation Logs...");
	
	
	@BeforeSuite
	public void startAutomationSuite()
	{
		try
		{
			log.info("the method startAutomationSuite execution started here...");
			String teststarttime = ApplicationIndependent.getDateTime("dd-MMM-YYYY hh:mm:ss z");
			String sReportFileName = System.getProperty("user.dir")+"\\Results\\ResultReports\\AutomationTestResultsReport.html";
			ReportUtil.createReport(sReportFileName, teststarttime, "QA Testing");
			log.info("the method startAutomationSuite execution ended here...");
			
		}
		catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the startAutomationSuite ..."+e);
		}
		
	}
	
	@BeforeClass
	public void loadFiles()
	{
		try
		{
			log.info("the method loadFiles execution started here...");
			//Load confiration.properties file
			String sConfigFile=System.getProperty("user.dir")+"\\Configuration\\configuration.properties";
			oConfig=ApplicationIndependent.property(sConfigFile);
			
			// Load ExpectedResults.properties File
			String sExpectedResultFile=System.getProperty("user.dir")+"\\TestScriptDataFiles\\ExpectedResults.properties";
			oExpectedResult=ApplicationIndependent.property(sExpectedResultFile);
			
			// Load objectmap.properties File
			String sObjectMapfile=System.getProperty("user.dir")+"\\ObjectMap\\objectmap.properties";
			oObjectMap=new ObjectMap(sObjectMapfile);
			
			// Create instance for Datatable class
			oDatatable=new Datatable();
			
			// Provide the Controller.xlsx file path
			sControllerExcelFile=System.getProperty("user.dir")+"\\Controller\\data_Controller.xlsx";
			log.info("the method loadFiles execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the loadFiles ..."+e);
		}
	}
	
	@Test
	public void executeTestScenarios()
	{
		String sTestStartTime=null;
		String sTestEndTime=null;
		
		try
		{
			log.info("the method executeTestScenarios execution started here...");
			sTestStartTime = ApplicationIndependent.getDateTime("dd-MMM-YYYY hh:mm:ss z");
			ReportUtil.startScenarios("Scenarios");
			int iControllerRowCount=oDatatable.rowCount(sControllerExcelFile, "Scenarios");
			for (int tcid=0;tcid<iControllerRowCount;tcid++)
			{
				String testcaseid=oDatatable.getCellData(sControllerExcelFile, "Scenarios", "TestcaseID", tcid+2);
				String testcasename=oDatatable.getCellData(sControllerExcelFile, "Scenarios", "TestcaseName", tcid+2);
				String testcasedesc=oDatatable.getCellData(sControllerExcelFile, "Scenarios", "Description", tcid+2);
				String runStatus=oDatatable.getCellData(sControllerExcelFile, "Scenarios", "RunStatus", tcid+2);
				
				System.out.println("testcaseid  :"+testcaseid);
				System.out.println("testcasename  :"+testcasename);
				System.out.println("testcasedesc  :"+testcasedesc);
				System.out.println("runStatus  :"+runStatus);
				System.out.println("+++++++++++++++++++++");
				
				if (runStatus.equalsIgnoreCase("yes"))
				{
					oBrowser=Initialize.launchBrowser();
					
					Class driver[]=new Class[1];
					driver[0]=WebDriver.class;
					
					sScenarioExcelFile=System.getProperty("user.dir")+"\\TestScriptDataFiles\\"+testcasename+".xlsx";
					int iScenarioRowCount=oDatatable.rowCount(sScenarioExcelFile, testcaseid);
					
					for (int tsid=0;tsid<iScenarioRowCount;tsid++)
					{
						String testscriptid=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "TestScriptID", tsid+2);
						String description=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "Description", tsid+2);
						String methodname=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "MethodName", tsid+2);
						String pkgclassname=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "PackageClassName", tsid+2);
						testdatacolumn=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "TestDataColumn", tsid+2);
						sExpectedResultColumn=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "ExpectedResultColumn", tsid+2);
						sObjectMapColumn=oDatatable.getCellData(sScenarioExcelFile, testcaseid, "ObjectMapColumn", tsid+2);
						
						System.out.println(" testscriptid  :"+testscriptid);
						System.out.println(" description  :"+description);
						System.out.println(" methodname  :"+methodname);
						System.out.println(" pkgclassname  :"+pkgclassname);
						System.out.println(" testdatacolumn  :"+testdatacolumn);
						System.out.println(" ExpectedResultColumn :"+sExpectedResultColumn);
						System.out.println(" sObjectMapColumn :"+sObjectMapColumn);
						
						Class cls=Class.forName(pkgclassname);
						Object obj=cls.newInstance();
						
						Method method=obj.getClass().getMethod(methodname, driver);
						String sScriptExecutionResult=(String) method.invoke(obj, oBrowser);
						sTestEndTime = ApplicationIndependent.getDateTime("dd-MMM-YYYY hh:mm:ss z");
						
						String sScreenshotpath = System.getProperty("user.dir")+"\\Results\\ScreenShots";
						String sScreenshotName = sScreenshotpath+"ScreenShot_"+testcasename+"_"+testscriptid+"_"+methodname+".jpg";
						if(sScriptExecutionResult.equalsIgnoreCase("fail"))
						{
							ApplicationIndependent.getScreenShotFromApplication(oBrowser, sScreenshotName);
						}
						ReportUtil.addArrayList(testcaseid, description, methodname, pkgclassname, sScriptExecutionResult, sScreenshotName);
						sTestScriptStatus+=sScriptExecutionResult;
						log.info("In the Scenario :"+testcasename+" the method "+methodname+" has got the status as :"+sScriptExecutionResult );
					}
					if(sTestScriptStatus.contains("Fail"))
					{
						ReportUtil.writeTestResults(testcaseid, testcasename, "Failed", sTestStartTime, sTestEndTime);
					}
					else
					{
						ReportUtil.writeTestResults(testcaseid, testcasename, "Passed", sTestStartTime, sTestEndTime);
					}
					log.info("+++++++++++++++++++++++++++++++++++");
				}
			}
			ReportUtil.endScenarios();
		}
		catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the executeTestScenarios ..."+e);
		}
		log.info("the method executeTestScenarios execution ended here...");
	}
	
	
	@AfterSuite
	public void endAutomationSuite()
	{
		String sTestEndTime = null;
		
		try
		{
			log.info("the method endAutomationSuite execution started here...");
			sTestEndTime = ApplicationIndependent.getDateTime("dd-MMM-YYYY hh:mm:ss z");
			ReportUtil.updateEndTime(sTestEndTime);
			log.info("the method endAutomationSuite execution ended here...");
		}
		catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the endAutomationSuite ..."+e);
		}
	}


}
