package practicetestng;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DummyTest2
{
	public RemoteWebDriver driver;
	@Test(priority=1)
	public void method1()
	{
		WebDriverManager.edgedriver().setup();
	    driver = new EdgeDriver();
		driver.get("http://www.google.co.in");
	}
	
	@Test(priority=2)
	public void method2()
	{
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.get("http://www.google.co.in");
	}

}
