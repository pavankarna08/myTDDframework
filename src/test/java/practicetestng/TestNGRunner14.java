package practicetestng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGRunner14
{
	@Test(priority=1)
	public void method1()
	{
		System.out.println("hi");
		Assert.assertTrue(false);
		System.out.println("bye");
	}
	
	@Test(priority=1)
	public void method2()
	{
		SoftAssert sa = new SoftAssert();
		System.out.println("hi");
		sa.assertTrue(false);
		System.out.println("bye");
		sa.assertAll();
	}
}
