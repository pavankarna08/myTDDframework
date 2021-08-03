package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.WebSiteUtility;
import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

public class LoginTest3WithDp 
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	
	@DataProvider(name="logintestdata", parallel=false)
	public Object[][] method1()
	{
		Object[][] x = new Object[][] {
			                                {"chrome","testernew51","valid","123456@mail","valid"},
				                            {"chrome","","blank","N/A","N/A"},
				                            {"chrome","tester51","invalid","N/A","N/A"},
				                            {"chrome","testernew51","valid","","blank"},
				                            {"chrome","testernew51","valid","123456mail","invalid"},
		                              };
		       return(x);
	}
	
	@Test(priority=1,dataProvider="logintestdata")
	public void logintest(String bn,String u,String uc,String p,String pc) throws Exception 
	{
		//common code
		//Create object to utility classes( have common methods)
		su = new WebSiteUtility();
		//open browser
		driver = su.openBrowser(bn);
		wait = su.defineWait(driver);
		//login testing
		try
		{
			//Define object to page classes
			hp = new HomePage(driver,wait);
			lp = new LoginPage(driver,wait);
			cp = new ComposePage(driver,wait);
			lop = new LogoutPage(driver,wait);
			//Launch site by using URL in config.properties file
			su.launchSite(driver);
			//Enter userid and click next
			hp.uidFill(u); //paramertization
			hp.uidNextClick();
			Thread.sleep(5000);
			//Userid Testing
			if(uc.equalsIgnoreCase("blank"))
			{
				if(hp.isBlankUseridError()) 
				{
					Reporter.log("uid blank test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("uid blank test failed and see");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else if(uc.equalsIgnoreCase("invalid"))
			{
				if(hp.isInValidUseridError())
				{
					Reporter.log("Invalid uid test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("Invalid uid test failed and see");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else //for valid userid
			{
				if(hp.isPasswordDisplayed())
				{
					Reporter.log("valid uid test passed");
					Assert.assertTrue(true);
					//Password testing
					//Fill password and click next
					lp.pwdfill(p);
					lp.pwdnextclick();
					Thread.sleep(5000);
					//Password testing
					if(pc.equalsIgnoreCase("blank"))
					{
						if(lp.isBlankpwdError())
						{
							Reporter.log("valid uid test passed");
							Assert.assertTrue(true);
						}
						else
						{
							Reporter.log("Blank pwd test failed and see");
							String fp = su.captureScreenshot(driver);
							Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
							Assert.fail();
						}
					}
					else if(pc.equalsIgnoreCase("Invalid"))
					{
						if(lp.isInvalidPwdError()) 
						{
							Reporter.log("Invalid pwd test passed");
							Assert.assertTrue(true);
						}
						else
						{
							Reporter.log("Invalid pwd test failed and see");
							String fp = su.captureScreenshot(driver);
							Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
							Assert.fail();
						}
					}
					else //valid password
					{
						if(lp.isComposeDispalyed())
						{
							Reporter.log("Valid pwd test passed");
							Assert.assertTrue(true);
							//Do logout
							lop.clickProfilePic();
							lop.clickSignOut();
						}
						else
						{
							Reporter.log("valid pwd test failed and see");
							String fp = su.captureScreenshot(driver);
							Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
							Assert.fail();
						}
					}
				}
				else
				{
					Reporter.log("Valid uid test failed and see");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			//close site
			su.closeSite(driver);
		}
		catch (Exception ex)
		{
			Reporter.log(ex.getMessage()+"and see :");
			String fp = su.captureScreenshot(driver);
			Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
			su.closeSite(driver);
			Assert.fail();
		}
	}
}
		
