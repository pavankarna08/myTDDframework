package practicetestng;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DummyTest3 
{
	@Test(priority=1)
	public void method1(ITestContext tc)
	{
		int a=10;
		int b=30;
		int z=a+b;
		ISuite s = tc.getSuite();
		//setting an attributewith name and its value at suite level
		s.setAttribute("output",z);
		
	}

}
