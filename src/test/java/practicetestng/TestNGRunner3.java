package practicetestng;

import org.testng.annotations.Test;

public class TestNGRunner3 
{
	@Test(dataProvider="mydata",dataProviderClass=TestNGRunner2.class)
	public void method2(int x,int y,int z)
	{
		int w = x+y+z;
		System.out.println(w);
	}
	
	@Test(dataProvider="mydata",dataProviderClass=TestNGRunner2.class)
	public void method3(String x,String y,String z)
	{
		String p = x+"-"+y+"-"+z;
		System.out.println(p);
	}

}
