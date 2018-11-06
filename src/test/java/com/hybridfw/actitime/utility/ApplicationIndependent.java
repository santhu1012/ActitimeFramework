package com.hybridfw.actitime.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ApplicationIndependent 
{
	
	public static Properties property(String FileName)
	{
		FileInputStream fin=null;
		Properties prop =null;
		try
		{
			fin=new FileInputStream(FileName);
			prop=new Properties();
			prop.load(fin);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fin.close();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	public static String getDateTime(String sDateFormat)
	{
		String sDateTime = null;
		
		try
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
			sDateTime = sdf.format(cal.getTime());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sDateTime;
	}
	
	public static void getScreenShotFromApplication(WebDriver oBrowser,String destFileName)
	{
		File srcFile = null;
		
		try
		{
			srcFile = ((TakesScreenshot)oBrowser).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(destFileName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
