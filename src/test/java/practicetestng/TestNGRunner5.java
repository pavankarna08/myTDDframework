package practicetestng;

import org.apache.bcel.classfile.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.ExcelFileUtility;

public class TestNGRunner5 
{
	@DataProvider(name="Excelfiledata")
	public Object[][] method1 (Method m) throws Exception
	{
		if(m.getName().equalsIgnoreCase("method2"))
		{
			ExcelFileUtility eu = new ExcelFileUtility("src\\test\\resources\\datafiles\\logintestdata.xlsx");
			eu.openSheet("sheet1");
			int nour = eu.getRowsCount();
			int nouc = eu.getColumnCount(0);
			Object[][] data = new Object[nour-1][nouc]; //data provider
			//skip 1st row (index = 0) due to names of columns
			for(int i=1;i<nour;i++)
			{
				for(int j=0;j<nouc;j++)
				{
					data[i-1][j] = eu.getCellValue(i, j);
				}
			}
			eu.saveAndCloseExcel();
			return(data);
		}
		else
		{
			return(null);
		}
	}
	
	@Test(dataProvider="Excelfiledata")
	public void method(String x,String y,String z,String p,String q)
	{
		String value = x+"-"+y+"-"+z+"-"+p+"-"+q;
		System.out.println(value);
	}  

}
