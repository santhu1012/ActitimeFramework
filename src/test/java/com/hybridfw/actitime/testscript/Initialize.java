package com.hybridfw.actitime.testscript;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.hybridfw.actitime.driverscript.DriverScript;

public class Initialize extends DriverScript
{
	public static WebDriver oBrowser=null;
	
	
	public static WebDriver launchBrowser()
	{
		String sBrowserType=null;
		try
		{
			log.info("the method launchBrowser execution started here...");
			sBrowserType=oConfig.getProperty("browserType");
			if (sBrowserType.equalsIgnoreCase("ch"))
			{
				String path=System.getProperty("user.dir");
				System.out.println(path);
				System.setProperty("webdriver.chrome.driver", path+"\\library\\drivers\\chromedriver.exe");
				oBrowser=new ChromeDriver();
				oBrowser.manage().window().maximize();
				Thread.sleep(3000);
			}
			else if(sBrowserType.equalsIgnoreCase("ff"))
			{
				String path=System.getProperty("user.dir");
				System.out.println(path);
				System.setProperty("webdriver.gecko.driver", path+"\\library\\drivers\\geckodriver.exe");
				oBrowser=new FirefoxDriver();
			}
			else if(sBrowserType.equalsIgnoreCase("ie"))
			{
				String path=System.getProperty("user.dir");
				System.out.println(path);
				System.setProperty("webdriver.ie.driver", path+"\\library\\drivers\\IEDriverServer.exe");
				oBrowser=new InternetExplorerDriver();
			}
			else
			{
				log.info("Invalid Browser Type !!!!!");
				
			}
			log.info("the method launchBrowser execution ended here...");	
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the launchBrowser ..."+e);
		}
		return oBrowser;
	}
	
	public static String navigate(WebDriver oBrowser)
	{
		Pattern pattern=null;
		String arrTestData[]=null;
		String sExpected,sActual;
		String sScriptStatus="Fail";
		try
		{
			log.info("the method navigate execution started here...");
			sExpected=oExpectedResult.getProperty(sExpectedResultColumn);
			pattern=Pattern.compile(",");
			arrTestData=pattern.split(testdatacolumn);
			String url=oDatatable.getCellData(sScenarioExcelFile, "testdata", arrTestData[0], 2);
			oBrowser.get(url);
			Thread.sleep(5000);
			sActual=oBrowser.getTitle();
			if (sExpected.equals(sActual))
			{
				sScriptStatus="Pass";
			}
			log.info("the method navigate execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the navigate ..."+e);
		}
		return sScriptStatus;
	}
	
	public static String closeApplication(WebDriver oBrowser)
	{
		String sScriptStatus="Fail";
		try
		{
			log.info("the method closeApplication execution started here...");
			oBrowser.quit();
			sScriptStatus="Pass";
			log.info("the method closeApplication execution ended here...");
		}catch(Exception e)
		{
			log.error("there is an exception arised during the execution of the closeApplication ..."+e);
		}
		return sScriptStatus;
	}

}
