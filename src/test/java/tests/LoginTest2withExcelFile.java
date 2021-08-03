package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.ExcelFileUtility;
import Utilities.WebSiteUtility;
import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

public class LoginTest2withExcelFile 
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	public SoftAssert sa;
	public ExcelFileUtility eu;
	
	@Test(priority=1)
	public void Logintest() throws Exception
	{
		sa = new SoftAssert();
		eu = new ExcelFileUtility("src\\test\\resources\\datafiles\\logintestdata.xlsx");
		eu.openSheet("sheet1");
		int nour = eu.getRowsCount();
		int nouc = eu.getColumnCount(0);
		eu.createResultColumn(nouc);
		//First index (index is 0 ) has names of columns so start 2nd row(index i=1)
		for(int i=1;i<nour;i++)
		{
			String bn = eu.getCellValue(i,0);
			String u = eu.getCellValue(i,1);
			String uc = eu.getCellValue(i,2);
			String p = eu.getCellValue(i,3);
			String pc = eu.getCellValue(i,4);
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
						eu.setCellValue(i,nouc,"uid blank test passed");
						Reporter.log("uid blank test passed");
						sa.assertTrue(true);
					}
					else
					{
						eu.setCellValue(i,nouc,"uid blank test failed");
						Reporter.log("uid blank test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				else if(uc.equalsIgnoreCase("invalid"))
				{
					if(hp.isInValidUseridError())
					{
						eu.setCellValue(i,nouc,"Invalid uid test passed");
						Reporter.log("Invalid uid test passed");
						sa.assertTrue(true);
					}
					else
					{
						eu.setCellValue(i,nouc,"Invalid uid test failed");
						Reporter.log("Invalid uid test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				else //for valid userid
				{
					if(hp.isPasswordDisplayed())
					{
						eu.setCellValue(i,nouc,"valid uid test passed");
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
								eu.setCellValue(i,nouc,"valid uid test passed");
								Reporter.log("valid uid test passed");
								sa.assertTrue(true);
							}
							else
							{
								eu.setCellValue(i,nouc,"Blank pwd test failed");
								Reporter.log("Blank pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}
						}
						else if(pc.equalsIgnoreCase("Invalid"))
						{
							if(lp.isInvalidPwdError()) 
							{
								eu.setCellValue(i,nouc,"Invalid pwd test passed");
								Reporter.log("Invalid pwd test passed");
								sa.assertTrue(true);
							}
							else
							{
								eu.setCellValue(i,nouc,"Invalid pwd test failed");
								Reporter.log("Invalid pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}
						}
						else //valid password
						{
							if(lp.isComposeDispalyed())
							{
								eu.setCellValue(i,nouc,"Valid pwd test passed");
								Reporter.log("Valid pwd test passed");
								sa.assertTrue(true);
								//Do logout
								lop.clickProfilePic();
								lop.clickSignOut();
							}
							else
							{
								eu.setCellValue(i,nouc,"valid pwd test failed");
								Reporter.log("valid pwd test failed and see");
								String fp = su.captureScreenshot(driver);
								Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}
						}
					}
					else
					{
						eu.setCellValue(i,nouc,"Valid uid test failed");
						Reporter.log("Valid uid test failed and see");
						String fp = su.captureScreenshot(driver);
						Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				//close site
				su.closeSite(driver);
			}
			catch (Exception ex)
			{
				eu.setCellValue(i,nouc,ex.getMessage());
				Reporter.log(ex.getMessage()+"and see :");
				String fp = su.captureScreenshot(driver);
				Reporter.log("<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
				su.closeSite(driver);
				sa.fail();
			}
		}//loop closing
		eu.saveAndCloseExcel();
		//Collate (collect and check) all assertions
		sa.assertAll();
	}

}
