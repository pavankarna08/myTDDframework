package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Utilities.WebSiteUtility;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestNGRunner17 
{
	WebSiteUtility su;
	RemoteWebDriver driver;
	public ATUTestRecorder rec;
	public String vp;
	@BeforeTest
	public void method1() throws Exception
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		vp="target\\"+sf.format(dt);
		rec=new ATUTestRecorder(vp,false);
		rec.start();
	}
	
	@AfterTest
	public void method2() throws ATUTestRecorderException
	{
		rec.stop();
	}
}
