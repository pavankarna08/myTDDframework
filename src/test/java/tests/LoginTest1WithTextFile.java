package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.TextFileUtility;
import Utilities.WebSiteUtility;
import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

public class LoginTest1WithTextFile 
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	public SoftAssert sa;
	
	@Test(priority=1)
	public void LoginTest() throws Exception
	{
		sa= new SoftAssert();
		for(int i=1;i<=15;i++)
		{
			String pieces[] = TextFileUtility.getValueInTextFile("src\\test\\resources\\datafiles\\logintestdata.txt",i);
			String bn = pieces[0];
			String u = pieces[1];
			String uc = pieces[2];
			String p = pieces[3];
			String pc = pieces[4];
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
				hp.uidFill(u);
				hp.uidNextClick();
				Thread.sleep(5000);
				//Userid Testing
				if(uc.equalsIgnoreCase("blank"))
				{
					if(hp.isBlankUseridError()) 
					{
						Reporter.log("uid blank test paseed");
						sa.assertTrue(true);
					}
					else
					{
						Reporter.log("uid blank test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
					}
				}
				else if(uc.equalsIgnoreCase("invalid"))
				{
					if(hp.isInValidUseridError())
					{
						Reporter.log("Invalid uid test passed");
						sa.assertTrue(true);
					}
					else
					{
						Reporter.log("Invalid uid test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
					}
				}
				else
				{
					if(hp.isPasswordDisplayed())
					{
						Reporter.log("valid uid test passed");
						sa.assertTrue(true);
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
								Reporter.log("blank pwd test passed");
								sa.assertTrue(true);
							}
							else
							{
								Reporter.log("Blank pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}
						}
						else if(pc.equalsIgnoreCase("Invalid"))
						{
							if(lp.isInvalidPwdError()) 
							{
								Reporter.log("Invalid pwd test paseed");
								sa.assertTrue(true);
							}
							else
							{
								Reporter.log("Invalid pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}
						}
						else //valid password
						{
							if(lp.isComposeDispalyed())
							{
								Reporter.log("Valid pwd test passed");
								sa.assertTrue(true);
								//Do logout
								lop.clickProfilePic();
								lop.clickSignOut();
							}
							else
							{
								Reporter.log("valid pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}
						}
					}
					else
					{
						Reporter.log("Valid uid test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
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
				sa.assertTrue(false);
			}
		}//loop closing
		//Collate (collect and check) all assertions
		sa.assertAll();
	}
}
