package Dummy;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SolveStaleIssueViaPom 
{
	//Properties
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	
	@FindBy(how=How.NAME,using="identifier")
	public WebElement e;
	
	//Constructor method
	public SolveStaleIssueViaPom(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
		this.wait=wait;
	}
	//main method to run
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
		driver.get("http://www.gmail.com");
		Thread.sleep(5000);
		//Create object to page class
		SolveStaleIssueViaPom obj = new SolveStaleIssueViaPom(driver,wait);
		obj.e.sendKeys("pavankumar.karna1997",Keys.ENTER);
		Thread.sleep(5000);
		//back to previous page
		driver.navigate().back();
		Thread.sleep(5000);
		//operta that element again(//no chance of staleelementreferenceexception)
		obj.e.clear();
		obj.e.sendKeys("testernew51");
	}

}
