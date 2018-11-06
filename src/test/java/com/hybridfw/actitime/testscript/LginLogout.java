package com.hybridfw.actitime.testscript;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hybridfw.actitime.driverscript.DriverScript;
import com.hybridfw.actitime.utility.ApplicationDependent;

public class LginLogout extends DriverScript
{
	
	public static String login(WebDriver oBrowser)
	{
		Pattern pattern=null;
		String arrTestData[]=null;
		String arrObjectMap[]=null;
		String sScriptStatus="Fail";
		try
		{
			log.info("the method login execution started here...");
			String sExpected=oExpectedResult.getProperty(sExpectedResultColumn);
			pattern=Pattern.compile(",");
			arrTestData=pattern.split(testdatacolumn);
			arrObjectMap=pattern.split(sObjectMapColumn);
			
			String user=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[0], 2);
			String pwd=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[1], 2);
			
			oBrowser.findElement(oObjectMap.getLocator(arrObjectMap[0])).sendKeys(user);
			oBrowser.findElement(oObjectMap.getLocator(arrObjectMap[1])).sendKeys(pwd);
			Thread.sleep(4000);
			oBrowser.findElement(oObjectMap.getLocator(arrObjectMap[2])).click();
			Thread.sleep(5000);
			By by=By.xpath("//td[text()='"+sExpected+"']");
			if (ApplicationDependent.isObjectPresent(by)==true)
			{
				sScriptStatus="Pass";
			}
			log.info("the method login execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the login ..."+e);
		}
		return sScriptStatus;
	}
	
	public static String logout(WebDriver oBrowser)
	{
		Pattern pattern = null;
		String arrObjetMap[] = null;
		String sExpected,sActual;
		String sScriptStatus = "Fail";
		
		try
		{
			log.info("the method logout execution started here...");
			sExpected=oExpectedResult.getProperty(sExpectedResultColumn);
			pattern = Pattern.compile(" ,");
			
			arrObjetMap = pattern.split(sObjectMapColumn);
			oBrowser.findElement(oObjectMap.getLocator(arrObjetMap[0])).click();
			Thread.sleep(5000);
			sActual=oBrowser.getTitle();
			if (sExpected.equals(sActual))
			{
				sScriptStatus="Pass";
			}
			log.info("the method logout execution ended here...");
			
		}
		catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the logout ..."+e);
		}
		return sScriptStatus;
	}

}
