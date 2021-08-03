package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utilities.WebSiteUtility;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestNGRunner15 
{
	WebSiteUtility su;
	RemoteWebDriver driver;
	public ATUTestRecorder rec;
	public String vp;
	
	@BeforeClass
	public void method1() throws Exception
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		vp="target\\"+this.getClass().getName()+sf.format(dt);
		rec=new ATUTestRecorder(vp,false);
		rec.start();
	}
	
	@AfterClass
	public void method2() throws ATUTestRecorderException
	{
		rec.stop();
	}
	
	@Test(priority=1)
	public void method3() throws Exception
	{
	    driver = su.openBrowser("chrome");
		driver.get("http://www.gmail.com");
		su.closeSite(driver);
	}
	
	@Test(priority=2)
	public void method4() throws Exception
	{
		driver = su.openBrowser("firefox");
		driver.get("http://www.magnitia.com");
		su.closeSite(driver);
	}
	

}
