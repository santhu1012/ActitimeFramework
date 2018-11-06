package com.hybridfw.actitime.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

public class ReportUtil 
{
	
	public static String sResultReportFile=null;
	public static String sReportReportFolder=null;
	public static String sScenarioName=null;
	public static ArrayList<String> oTestscriptID=new ArrayList<String>();
	public static ArrayList<String> oTestdescription=new ArrayList<String>();
	public static ArrayList<String> oTestmethodname=new ArrayList<String>();
	public static ArrayList<String> oTestpackagename=new ArrayList<String>();
	public static ArrayList<String> oTeststatus=new ArrayList<String>();
	public static ArrayList<String> oTestscreenshots=new ArrayList<String>();
	
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param 
	 * @return 
	 * purpose:
	 * Description:
	 */
	public static void createReport(String FileName,String teststarttime,String environment)
	{
		BufferedWriter bw=null;
		try
		{
			sResultReportFile=FileName;
			sReportReportFolder=FileName.substring(0, FileName.lastIndexOf("\\"));
			String sConfigFile=System.getProperty("user.dir")+"\\Configuration\\configuration.properties";
			Properties oConfig=ApplicationIndependent.property(sConfigFile);
			
			bw=new BufferedWriter(new FileWriter(FileName));
			bw.write("<html>");
			bw.write("<head><title>ActiTime Automation Results</title></head>");
			bw.newLine();
			bw.write("<body>");
			bw.write("<h1 align=center>ActiTime Automation Results</h1>");
			bw.newLine();
			bw.write("<table border=2>");
			bw.write("<h3>Automation Summary</h3>");
			bw.write("<tr>");
			bw.write("<th align=center width=150>Item Name</th>");
			bw.write("<th align=center width=250>Item Value</th>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Application Name</td>");
			bw.write("<td width=250>"+oConfig.getProperty("applicationname")+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Application Version</td>");
			bw.write("<td width=250>"+oConfig.getProperty("applicationversion")+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Browser Name</td>");
			bw.write("<td width=250>"+oConfig.getProperty("browserName")+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Application URL</td>");
			bw.write("<td width=250>"+oConfig.getProperty("applicationurl")+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Environment</td>");
			bw.write("<td width=250>"+environment+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Automation Start Time</td>");
			bw.write("<td width=250>"+teststarttime+"</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("<tr>");
			bw.write("<td width=150>Automation End Time</td>");
			bw.write("<td width=250>END_TIME</td>");
			bw.write("</tr>");
			bw.newLine();
			bw.write("</table>");
			bw.write("</body>");
			bw.write("</html>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.flush();
				bw.close();
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
	public static void startScenarios(String ScenarioName)
	{
		BufferedWriter bw=null;
		try
		{
			sScenarioName=ScenarioName.replaceAll(" ", "_");
			bw=new BufferedWriter(new FileWriter(sResultReportFile,true));
			bw.newLine();
			bw.write("<table border=2 width=100%>");
			bw.write("<h3>Automation Execution Details</h3>");
			bw.write("<tr>");
			bw.write("<th align=center width=15%>Testcase ID</th>");
			bw.write("<th align=center width=20%>Testcase Name</th>");
			bw.write("<th align=center width=15%>Status</th>");
			bw.write("<th align=center width=25%>Test Start Time</th>");
			bw.write("<th align=center width=25%>Test End Time</th>");
			bw.write("</tr>");
			bw.newLine();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.flush();
				bw.close();
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
	public static void endScenarios()
	{
		BufferedWriter bw=null;
		try
		{
			bw=new BufferedWriter(new FileWriter(sResultReportFile,true));
			bw.write("</table>");
			bw.newLine();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.flush();
				bw.close();
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
	public static void addArrayList(String testscriptid,String description,String methodname,String pkgname,String status,String screenshot)
	{
		try
		{
			oTestscriptID.add(testscriptid);
			oTestdescription.add(description);
			oTestmethodname.add(methodname);
			oTestpackagename.add(pkgname);
			oTeststatus.add(status);
			oTestscreenshots.add(screenshot);
		}catch(Exception e)
		{
			e.printStackTrace();
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
	public static void writeTestResults(String tcid,String testname,String tcstatus,String teststarttime,String testendtime)
	{
		BufferedWriter bw=null;
		String FileName=null;
		try
		{
			FileName=sReportReportFolder+"\\"+sScenarioName+"_"+testname+"_Results.html";
			bw=new BufferedWriter(new FileWriter(FileName));
			bw.write("<html>");
			bw.write("<head><title>"+testname+" Detail Results</title></head>");
			bw.write("<body>");
			bw.write("<h1 align=center>"+testname+" Detail Results</h1>");
			bw.newLine();
			bw.write("<table border=1 width=100%>");
			bw.write("<h2 align=left>"+testname+" Detail Results</h2>");
			bw.write("<tr>");
			bw.write("<th width=10%>TestScriptID</th>");
			bw.write("<th width=20%>Description</th>");
			bw.write("<th width=15%>TestScriptName</th>");
			bw.write("<th width=20%>PackageName</th>");
			bw.write("<th width=10%>Status</th>");
			bw.write("<th width=25%>ScreenShotName</th>");
			bw.write("</tr>");
			if (oTestmethodname != null)
			{
				
				for (int i=0;i<oTestmethodname.size();i++)
				{
					bw.write("<tr>");
					bw.write("<td width=10%>"+oTestscriptID.get(i)+"</td>");
					bw.write("<td width=20%>"+oTestdescription.get(i)+"</td>");
					bw.write("<td width=15%>"+oTestmethodname.get(i)+"</td>");
					bw.write("<td width=20%>"+oTestpackagename.get(i)+"</td>");
					if (oTeststatus.get(i).equalsIgnoreCase("pass"))
					{
						bw.write("<td width=10%>"+oTeststatus.get(i)+"</td>");
						bw.write("<td width=25%>&nbsp</td>");
					}
					else
					{
						bw.write("<td width=10%>"+oTeststatus.get(i)+"</td>");
						bw.write("<td width=25%><a href=file:///"+oTestscreenshots.get(i)+">ScreenShotName</a></td>");
					}
					bw.write("</tr>");
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.flush();
				bw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			bw=new BufferedWriter(new FileWriter(sResultReportFile,true));
			bw.write("<tr>");
			bw.write("<td width=15% align=center>"+tcid+"</td>");
			bw.write("<td width=20% align=center>"+testname+"</td>");
			bw.write("<td width=15% align=center><a href=file:///"+FileName+">"+tcstatus+"</a></td>");
			bw.write("<td width=25% align=center>"+teststarttime+"</td>");
			bw.write("<td width=25% align=center>"+testendtime+"</td>");
			bw.write("</tr>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bw.flush();
				bw.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	 oTestscriptID=new ArrayList<String>();
	 oTestdescription=new ArrayList<String>();
	 oTestmethodname=new ArrayList<String>();
	 oTestpackagename=new ArrayList<String>();
	 oTeststatus=new ArrayList<String>();
	 oTestscreenshots=new ArrayList<String>();
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
	public static void updateEndTime(String endTime)
	{
		try
		{
			StringBuffer str=new StringBuffer();
			FileInputStream fin=new FileInputStream(sResultReportFile);
			DataInputStream dis=new DataInputStream(fin);
			InputStreamReader fr=new InputStreamReader(dis);
			BufferedReader br=new BufferedReader(fr);
			String strLine=null;
			while((strLine=br.readLine())!=null)
			{
				if (strLine.indexOf("END_TIME")!=-1)
				{
					strLine=strLine.replace("END_TIME", endTime);
				}
				str.append(strLine);
			}
			fin.close();
			FileOutputStream fout=new FileOutputStream(sResultReportFile);
			DataOutputStream out=new DataOutputStream(fout);
			out.writeBytes(str.toString());
			
			fout.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
