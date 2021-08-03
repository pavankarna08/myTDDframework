package practicetestng;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGRunner7 
{
	@DataProvider(name="testdata")
	public Object[][] method1 (ITestContext tc)
	{
		Object[][] data;
		String[] groups = tc.getIncludedGroups();
		for(String group:groups)
		{
			if(group.equalsIgnoreCase("ArithematicOperations"))
			{
				data = new Object[][]
						        {  
				                  	{"10","20"},
				                  	{"30","78"}
				              	};
				           return(data);
			}
			else if(group.equalsIgnoreCase("StringManipulation")) 
			{
				data = new Object[][] {
					                        {"abdul","kalam"},
					                        {"virat","kohli"}
					                  };
			               return(data);
			}
		}
		return (null);
	}
	
	@Test(groups={"ArithematicOperations"},dataProvider="testdata")
	public void numbersaddition (String x,String y)
	{
		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		int c = a+b;
		System.out.println(c);
	}
	
	@Test(groups= {"ArithematicOperations"},dataProvider="testdata")
	public void numberssubtarct (String x,String y)
	{
		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		int c = a-b;
		System.out.println(c);
	}
	
	@Test(groups= {"StringManipulation"},dataProvider="testdata")
	public void stringConcat (String x,String y)
	{
		String z = x+y;
		System.out.println(z);
	}
}

