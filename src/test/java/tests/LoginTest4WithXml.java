package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.WebSiteUtility;
import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

public class LoginTest4WithXml 
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	
	@BeforeClass
	public void setup() throws Exception
	{
		//Create object to utility class(have common code)
		su = new WebSiteUtility();
	}
	
	@Test(priority=1)
	@Parameters({"browser"})
	public void launch(String bn) throws Exception
	{
		driver = su.openBrowser(bn);
		wait = su.defineWait(driver);
		hp = new HomePage(driver,wait);
		lp = new LoginPage(driver,wait);
		cp = new ComposePage(driver,wait);
		lop = new LogoutPage(driver,wait);
		//Launch site by using url in properties files
		su.launchSite(driver);
	}
	
	@Test(priority=2)
	@Parameters({"uid","uidcriteria"})
	public void uidTest(String u,String uc) throws Exception
	{
		//user-id testing
		try
		{
			//Enter userid and clicknext
			hp.uidFill(u);
			hp.uidNextClick();
			Thread.sleep(5000);
			if(u.length()==0)
			{
				if(hp.isBlankUseridError())
				{
					Reporter.log("uid blank test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("uid blank test failed");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else if(u.length()!=0 && uc.equalsIgnoreCase("invalid"))
			{
				if(hp.isInValidUseridError())
				{
					Reporter.log("uid invalid test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("uid invalid test failed");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else  //valid uid
			{
				if(hp.isPasswordDisplayed())
				{
					Reporter.log("valid uid test passed");
					Assert.assertTrue(true);	
				}
				else
				{
					Reporter.log("VAlid uid test failed and see:");
					String fp = su.captureScreenshot(driver);
					Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
		}
		catch(Exception ex)
		{
			Reporter.log(ex.getMessage()+"and see :");
			String fp = su.captureScreenshot(driver);
			Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
			Assert.fail();
		}
	}
	
	@Test(priority=3 ,dependsOnMethods= {"uidTest"})
	@Parameters({"pwd","pwdcriteria"})
	public void pwdTest(String p,String pc) throws Exception
	{
		if(p.equals("N/A") || pc.equals("N/A"))
		{
			Reporter.log("No need to test password");
			Assert.assertTrue(true);
		}
		else
		{
			//password testing
			try
			{
				//Fill password and clicknext
				lp.pwdfill(p); //parameterization
				lp.pwdnextclick();
				Thread.sleep(5000);
				if(p.length()==0) //blank pwd
				{
					if(lp.isBlankpwdError())
					{	
						Reporter.log("blank pwd test passed");
						Assert.assertTrue(true);	
					}
					else
					{
						Reporter.log("blank pwd test failed");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						Assert.fail();
					}
				}
				else if(p.length()!=0 && pc.equalsIgnoreCase("invalid")) //invalid pwd
				{
					if(lp.isInvalidPwdError())
					{
						Reporter.log("invalid pwd test passed");
						Assert.assertTrue(true);	
					}
					else
					{
						Reporter.log("invalid pwd test failed");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						Assert.fail();
					}
				}
				else //valid pwd
				{
					if(lp.isComposeDispalyed())
					{
						Reporter.log("valid pwd test passed");
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
			catch(Exception ex)
			{
				Reporter.log(ex.getMessage()+" and see ");
				String fp = su.captureScreenshot(driver);
				Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
				Assert.fail();
			}
		}
	}
	@Test(priority=4)
	public void close() throws Exception
	{
		su.closeSite(driver);
	}
}

