package com.hybridfw.actitime.datatable;

import java.io.FileInputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datatable 
{
	public static Workbook wb=null;
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param FileName
	 * @param SheetName
	 * @return rowcount
	 * purpose:
	 * Description:
	 */
	public int rowCount(String FileName,String SheetName)
	{
		FileInputStream fin=null;
		Workbook wb=null;
		Sheet sh=null;
		int rc=0;
		try
		{
			fin=new FileInputStream(FileName);
			wb=new XSSFWorkbook(fin);
			int index=wb.getSheetIndex(SheetName);
			if (index==-1) return -1;
			sh=wb.getSheet(SheetName);
			rc=sh.getPhysicalNumberOfRows();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fin.close();
				wb.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return rc-1;
	}
	
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param SheetName
	 * @return rowcount
	 * purpose:
	 * Description:
	 */
	public int rowCount(String SheetName)
	{
		Sheet sh=null;
		int rc=0;
		try
		{
			int index=wb.getSheetIndex(SheetName);
			if (index==-1) return -1;
			sh=wb.getSheet(SheetName);
			rc=sh.getPhysicalNumberOfRows();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return rc-1;
	}
	
	public boolean importExcelFile(String FileName)
	{
		FileInputStream fin=null;
		
		try
		{
			fin=new FileInputStream(FileName);
			wb=new XSSFWorkbook(fin);
			if ( wb != null) return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param FileName
	 * @param SheetName
	 * @return CellData
	 * purpose:
	 * Description:
	 */
	public String getCellData(String FileName,String SheetName,String colName,int rownum)
	{
		FileInputStream fin=null;
		Workbook wb=null;
		Sheet sh=null;
		Row row=null;
		Cell cell=null;
		String sCellData=null;
		int colNum=0;
		try
		{
			fin=new FileInputStream(FileName);
			wb=new XSSFWorkbook(fin);
			sh=wb.getSheet(SheetName);
			if (sh==null) return null;
			row=sh.getRow(0);
			for (int c=0;c<row.getLastCellNum();c++)
			{
				cell=row.getCell(c);
				String cValue=cell.getStringCellValue();
				if (cValue.equalsIgnoreCase(colName.trim()))
				{
					colNum=c;
					break;
				}
			}
			row=sh.getRow(rownum-1);
			cell=row.getCell(colNum);
			
			if (cell==null || cell.getCellTypeEnum()==cell.getCellTypeEnum().BLANK)
			{
				sCellData="";
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().STRING)
			{
				sCellData=cell.getStringCellValue();
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().BOOLEAN)
			{
				sCellData=String.valueOf(cell.getBooleanCellValue());
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().NUMERIC)
			{
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					double d=cell.getNumericCellValue();
					Calendar cal=Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					int mon=cal.get(Calendar.MONTH)+1;
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int year=cal.get(Calendar.YEAR);
					String month,day1;
					if (mon <10)
					{
						month="0"+mon;
					}
					else
					{
						month=String.valueOf(mon);
					}
					if (day <10)
					{
						day1="0"+day;
					}
					else
					{
						day1=String.valueOf(day);
					}
					sCellData=day1+"-"+(month)+"-"+year;
				}
				else
				{
					sCellData=String.valueOf(cell.getNumericCellValue());
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
				fin.close();
				wb.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return sCellData;
	}
	
	/**
	 * Author:
	 * Reviewer:
	 * Reviewed Date:
	 * @param FileName
	 * @param SheetName
	 * @return CellData
	 * purpose:
	 * Description:
	 */
	public String getCellData(String SheetName,String colName,int rownum)
	{
		Sheet sh=null;
		Row row=null;
		Cell cell=null;
		String sCellData=null;
		int colNum=0;
		try
		{
			sh=wb.getSheet(SheetName);
			if (sh==null) return null;
			row=sh.getRow(0);
			for (int c=0;c<row.getLastCellNum();c++)
			{
				cell=row.getCell(c);
				String cValue=cell.getStringCellValue();
				if (cValue.equalsIgnoreCase(colName.trim()))
				{
					colNum=c;
					break;
				}
			}
			row=sh.getRow(rownum-1);
			cell=row.getCell(colNum);
			if (cell==null || cell.getCellTypeEnum()==cell.getCellTypeEnum().BLANK)
			{
				sCellData="";
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().STRING)
			{
				sCellData=cell.getStringCellValue();
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().BOOLEAN)
			{
				sCellData=String.valueOf(cell.getBooleanCellValue());
			}
			else if (cell.getCellTypeEnum()==cell.getCellTypeEnum().NUMERIC)
			{
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					double d=cell.getNumericCellValue();
					Calendar cal=Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					int mon=cal.get(Calendar.MONTH)+1;
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int year=cal.get(Calendar.YEAR);
					String month,day1;
					if (mon <10)
					{
						month="0"+mon;
					}
					else
					{
						month=String.valueOf(mon);
					}
					if (day <10)
					{
						day1="0"+day;
					}
					else
					{
						day1=String.valueOf(day);
					}
					sCellData=day1+"-"+(month)+"-"+year;
				}
				else
				{
					sCellData=String.valueOf(cell.getNumericCellValue());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return sCellData;
	}

}
