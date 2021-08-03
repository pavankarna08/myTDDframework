package Dummy;

import Utilities.ExcelFileUtility;

public class Dummy3 
{
	public static void main(String[] args) throws Exception
	{
		ExcelFileUtility obj = new ExcelFileUtility(System.getProperty("user.dir")+"\\src\\test\\resources\\datafiles\\logintestdata.xlsx");
		obj.openSheet("sheetl");
		int rc = obj.getRowsCount();
		int cc= obj.getColumnCount(0);
		for(int i=0;i<rc;i++)
		{
			System.out.println(obj.getCellValue(i,i)+" ");
		}
		System.out.println();
		obj.setCellValue(5,3,"pavankumar");
		obj.createResultColumn(cc);
		obj.saveAndCloseExcel();
	}
}