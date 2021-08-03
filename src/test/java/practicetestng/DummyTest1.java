package practicetestng;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DummyTest1 
{
	public RemoteWebDriver driver;
	@Test(priority=1)
	public void method1()
	{
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.get("http://www.google.co.in");
	}
	
	@Test(priority=2)
	public void method2()
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("http://www.google.co.in");
	}
}
