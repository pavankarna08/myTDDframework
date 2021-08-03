package Dummy;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import Utilities.WebSiteUtility;

public class Dummy4 
{
	public static void main(String[] args) throws Throwable 
	{
		WebSiteUtility obj = new WebSiteUtility();
		RemoteWebDriver driver = obj.openBrowser("opera");
		FluentWait<RemoteWebDriver> w = obj.defineWait(driver);
		obj.launchSite(driver);
		System.out.println(obj.captureScreenshot(driver));
		obj.closeSite(driver);
		

		

	}

}
