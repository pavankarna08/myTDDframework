package practicetestng;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DummyTest4 
{
	@Test(priority=1)
	public void mathod1(ITestContext tc)
	{
		//Retriving arrtibute value set in ITestContext
		ISuite s = tc.getSuite();
		System.out.println("output is "+s.getAttribute("output"));
	}

}
