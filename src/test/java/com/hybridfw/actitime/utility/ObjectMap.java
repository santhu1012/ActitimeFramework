package com.hybridfw.actitime.utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap 
{
	
	public static Properties prop =null;
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param 
	 * @return 
	 * purpose:
	 * Description:
	 */
	public ObjectMap(String FileName)
	{
		FileInputStream fin=null;
		
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
	}
	
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param 
	 * @return 
	 * purpose:
	 * Description:
	 */
	public By getLocator(String logicalname)
	{
		By by=null;
		try
		{
			String locator=prop.getProperty(logicalname);
			String s[]=locator.split(">");
			String locatorName=s[0];
			String locatorValue=s[1];
			if (locatorName.equalsIgnoreCase("id"))
			{
				by=By.id(locatorValue);
			}
			else if (locatorName.equalsIgnoreCase("name"))
			{
				by=By.name(locatorValue);
			}
			else if (locatorName.equalsIgnoreCase("xpath"))
			{
				by=By.xpath(locatorValue);
			}
			else if (locatorName.equalsIgnoreCase("linktext"))
			{
				by=By.linkText(locatorValue);
			}
			else if (locatorName.equalsIgnoreCase("cssselector"))
			{
				by=By.cssSelector(locatorValue);
			}
			else if (locatorName.equalsIgnoreCase("classname"))
			{
				by=By.className(locatorValue);
			}
			else
			{
				System.out.println("Invalid Locator Name !!!!!");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return by;
	}

}
