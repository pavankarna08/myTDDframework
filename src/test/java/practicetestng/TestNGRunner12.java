package practicetestng;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Utilities.WebSiteUtility;

public class TestNGRunner12 
{
	@Test
	public void method1() throws Exception
	{
		WebSiteUtility su = new WebSiteUtility();
		RemoteWebDriver driver = su.openBrowser("chrome");
		driver.manage().window().maximize();
		Reporter.log("Chrome browser opened");
		driver.get("http://www.google.co.in");
		Thread.sleep(5000);
		Reporter.log("Google site launched");
		String fp = su.captureScreenshot(driver);
		Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\"height=\"100\"width=\"100\"/></a>");
		su.closeSite(driver);
	}
	

}
