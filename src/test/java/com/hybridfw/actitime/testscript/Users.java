package com.hybridfw.actitime.testscript;

import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hybridfw.actitime.driverscript.DriverScript;
import com.hybridfw.actitime.utility.ApplicationDependent;

public class Users extends DriverScript
{
	public static String firstName = null;
	public static String lastName = null;
	
	
	
	public static String createUser(WebDriver oBrowser)
	{
		Pattern pattern=null;
		String arrTestData[]=null;
		String sScriptStatus = "Fail";
		
		try
		{
			log.info("the method createUser execution started here...");
			pattern=Pattern.compile(",");
			arrTestData=pattern.split(testdatacolumn);
			firstName=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[0], 2);
			lastName=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[1], 2);
			String email=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[2], 2);
			String username=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[3], 2);
			String password=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[4], 2);
			String passwordCopy=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[5], 2);
			
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//div[text()='USERS']")).click();
			Thread.sleep(3000);
			//oBrowser.findElement(By.id("gettingStartedShortcutsPanelId")).click();
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//div[@class='i']/div/span[contains(text(),'User')]")).click();
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='firstName' and @id='userDataLightBox_firstNameField']")).sendKeys(firstName);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='lastName' and @id='userDataLightBox_lastNameField']")).sendKeys(lastName);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='email' and @id='userDataLightBox_emailField']")).sendKeys(email);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='username' and @id='userDataLightBox_usernameField']")).sendKeys(username);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='password' and @id='userDataLightBox_passwordField']")).sendKeys(password);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//input[@name='passwordCopy' and @id='userDataLightBox_passwordCopyField']")).sendKeys(passwordCopy);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//div[@id='userDataLightBox_commitBtn']/div/span[contains(text(),'Create User')]")).click();
			Thread.sleep(5000);
			String userName = lastName+", "+firstName;
			By by = By.xpath("//span[text()='"+userName+"']");
			if(ApplicationDependent.isObjectPresent(by)==true)
			{
				sScriptStatus = "Pass";
			}
			
			log.info("the method createUser execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the createUser ..."+e);
		}
		return sScriptStatus;
			
	}
	
	public static String modifyUser(WebDriver oBrowser)
	{
		Pattern pattern = null;
		String arrTestData[] = null;
		String sExpected , sActual;
		String sScriptStatus = "Fail";
		
		try
		{
			log.info("the method modifyUser execution started here...");
			sExpected = oExpectedResult.getProperty(sExpectedResultColumn);
			pattern = Pattern.compile(",");
			arrTestData = pattern.split(testdatacolumn);
			
			String password=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[1], 2);
			String passwordCopy=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[2], 2);
			
			oBrowser.findElement(By.xpath("//span[text()='User1, demo']")).click();
			Thread.sleep(2000);
			oBrowser.findElement(By.name("password")).sendKeys(password);
			Thread.sleep(2000);
			oBrowser.findElement(By.name("passwordCopy")).sendKeys(passwordCopy);
			Thread.sleep(2000);
			oBrowser.findElement(By.xpath("//span[text()='Save Changes']")).click();
			Thread.sleep(5000);
			sActual=oBrowser.getTitle();
			if (sExpected.equals(sActual))
			{
				sScriptStatus="Pass";
			}
			
			log.info("the method modifyUser execution ended here...");
		}
		catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the modifyUser ..."+e);
		}
		return sScriptStatus;
	}
	
	public static String deleteUser(WebDriver oBrowser)
	{
		String sScriptStatus = "Fail";
		try
		{
			String sExpected = oExpectedResult.getProperty(sExpectedResultColumn);
			String userName = lastName+", "+firstName;
			log.info("the method deleteUser execution started here...");
			oBrowser.findElement(By.xpath("//span[text()='User1, demo']")).click();
			Thread.sleep(2000);
			oBrowser.findElement(By.id("userDataLightBox_deleteBtn")).click();
			Thread.sleep(2000);
			boolean firstvalidation = ApplicationDependent.isAlertPresent();
			Alert alert=oBrowser.switchTo().alert();
			String sActual = alert.getText();
			boolean secondvalidation = sExpected.equals(sActual);
			alert.accept();
			Thread.sleep(5000);
			
			if((firstvalidation==true) && (secondvalidation==true))
			{
				sScriptStatus="Pass";
			}
			log.info("the method deleteUser execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the deleteUser ..."+e);
		}
		return sScriptStatus;
	}

}
