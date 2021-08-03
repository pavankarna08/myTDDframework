package practicetestng;


import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.TextFileUtility;

public class TestNGRunner4 
{
	@DataProvider(name="textfiledata")
	public Object[][] method1(Method m) throws Exception 
	{
		if(m.getName().equalsIgnoreCase("method2"))
		{
			Object[][] data = new Object[15][5];
			for(int i=0;i<15;i++)
			{
				//but line number starts with "1" in text file
				String temp[] = TextFileUtility.getValueInTextFile("src\\test\\resources\\datafiles\\logintestdata.txt",i+1);
				for(int j=0;j<5;j++)
				{
					data[i][j] = temp[j];
				}
			}
			return(data);
		}
		else
		{
			return(null);
		}
	}
	
	@Test(dataProvider="textfiledata")
	public void method2(String x,String y,String z,String p,String q)
	{
		String value = x+"-"+y+"-"+z+"-"+p+"-"+q;
		System.out.println(value);
	}
	

}
