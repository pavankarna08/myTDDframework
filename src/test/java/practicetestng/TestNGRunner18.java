package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utilities.WebSiteUtility;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestNGRunner18 
{
	WebSiteUtility su;
	RemoteWebDriver driver;
	public ATUTestRecorder rec;
	public String vp;
	@BeforeMethod
	public void method1() throws Exception
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		vp="target\\"+sf.format(dt);
		rec=new ATUTestRecorder(vp,false);
		rec.start();
	}
	
	@AfterMethod
	public void method2() throws ATUTestRecorderException
	{
		rec.stop();
	}
}
