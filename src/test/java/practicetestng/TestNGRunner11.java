package practicetestng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGRunner11 
{
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Before method will executed before every @test method");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("After method ewill be executed after every @test method");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Before class will always execute prior to @before method and @test method");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("After class will always execute later to @After Method and @Test Method");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Before test will always execute prior to Before class,Before Method and test method");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("After test will always execute later to after class,after Method and test method");
	}
	
	@BeforeSuite
	public void beforesuite()
	{
		System.out.println("Before suite will always execute prior to all annotations or tests in the suite");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("After suite will always execute at last when all the annotationsmor tests in the suite have run.");
	}
	
	@Test(priority=1)
	public void testcase1()
	{
		System.out.println("This is my first test case");
	}
	
	@Test(priority=2)
	public void testcase2()
	{
		System.out.println("This is my second test case");
	}
	

}
