package Dummy;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CacheLookUpTest 
{
	//Properties
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	
	@FindBy(how=How.LINK_TEXT,using="Gmail")
	public WebElement mylink1;
	
	@FindBy(how=How.LINK_TEXT,using="Gmail")
	@CacheLookup
	public WebElement mylink2;
	
	//Constructor method
	public CacheLookUpTest(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
		this.wait=wait;
	}
	
	public static void main(String[] args) throws Exception
	{
		//Open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(20));
		wait.pollingEvery(Duration.ofMillis(500));
		//Launch site
		driver.get("http://www.google.co.in");
		Thread.sleep(5000);
		//Create object to class
		CacheLookUpTest obj = new CacheLookUpTest(driver,wait);
		//Get text of link 1000 times
		Long x = System.currentTimeMillis();
		for(int i=1;i<=1000;i++)
		{
			obj.mylink1.getText();
		}
		Long y = System.currentTimeMillis();
		System.out.println("Time taken in seconds without cache :"+(y-x)/1000);
		//Get text of link 1000 times
		Long z = System.currentTimeMillis();
		for(int i=1;i<=1000;i++)
		{
			obj.mylink2.getText();
		}
		Long a = System.currentTimeMillis();
		System.out.println("Time taken in seconds without cache :"+(a-z)/1000);
		
	}

}
