package practicetestng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGRunner8 
{
	@Test
	@Parameters({"country","firstname","lastname"})
	public void testmethod(String c,String fn,String ln)
	{
		System.out.println(fn+" "+ln+" belongs to "+c);
	}

}
