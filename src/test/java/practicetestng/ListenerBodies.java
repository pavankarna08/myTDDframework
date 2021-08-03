package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenerBodies implements ITestListener
{
	
	SimpleDateFormat sf;
	Date dt;
	public void onTestStart(ITestResult Result) 
	{
		sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		dt=new Date();
		Reporter.log(Result.getName()+"test case started at"+sf.format(dt));
	}
	public void onTestSuccess(ITestResult Result) 
	{
		sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		dt=new Date();
		Reporter.log(Result.getName()+"is passed on"+sf.format(dt));
	}
	public void onTestFailure(ITestResult Result) 
	{
		sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		dt=new Date();
		Reporter.log(Result.getName()+"test failed on"+sf.format(dt));
	}
	public void onTestSkipped(ITestResult Result) 
	{
		sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		dt=new Date();
		Reporter.log(Result.getName()+"is skipped at"+sf.format(dt));
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		 Reporter.log("Test passed within expected success percentage");
	}
	public void onStart(ITestContext context) 
	{
		System.out.println("Welcome , Please go to reports");
	}
	public void onFinish(ITestContext context) 
	{
		Reporter.log("Thank you, bye for now");
	}


}
