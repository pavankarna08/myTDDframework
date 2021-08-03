package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class WebSiteUtility 
{
	//Operational methods
	public RemoteWebDriver openBrowser(String browsername)
	{
		RemoteWebDriver driver;
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			//set IE browser zoom level to 100% manually
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		return(driver);	
	}
	
	public FluentWait<RemoteWebDriver> defineWait(RemoteWebDriver driver) throws Exception
	{

		String temp1 = PropertiesFileUtility.getValueInPropertiesFile("maxwait");
		int value1 = Integer.parseInt(temp1);
		String temp2 = PropertiesFileUtility.getValueInPropertiesFile("interval");
		int value2 = Integer.parseInt(temp2);
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(value1));
		wait.pollingEvery(Duration.ofMillis(value2));
		return(wait);
	}
	
	public void launchSite(RemoteWebDriver driver) throws Exception
	{
		String temp = PropertiesFileUtility.getValueInPropertiesFile("url");
		driver.get(temp);
		driver.manage().window().maximize();
	}
	
	public String captureScreenshot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		String fp = System.getProperty("user.dir")+"\\target\\"+sf.format(dt)+".png";
		File dest = new File(fp);
		File src = driver.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,dest);
		return(dest.getAbsolutePath());
	}
	
	public String fullPageScreenshot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt = new Date();
		String fn = System.getProperty("user+dir")+"\\target\\"+sf.format(dt)+".png";
		File dest = new File(fn);
		AShot as = new AShot();
		ShootingStrategy shs = ShootingStrategies.viewportPasting(1000);
		Screenshot ss = as.shootingStrategy(shs).takeScreenshot(driver);
		ImageIO.write(ss.getImage(),"PNG",dest);
		return(dest.getAbsolutePath());
		
	}
	
	public void closeSite(RemoteWebDriver driver) throws Exception
	{
		driver.quit();
	}
	
	public By getByFromWebElement (WebElement e)
	{
		String p[] = e.toString().split("->");
		String q[] = p[1].split(":");
		String Locatortype = q[0].trim();
		String Locatorvalue = q[1].substring(0,q[1].length()-1).trim();
		By b = null;
		if(Locatortype.equals("name"))
		{
			b= By.name(Locatorvalue);
		}
		else if(Locatortype.equals("id"))
		{
			b= By.id(Locatorvalue);
		}
		else if(Locatortype.equals("classname"))
		{
			b= By.className(Locatorvalue);
		}
		else if(Locatortype.equals("tagname"))
		{
			b= By.tagName(Locatorvalue);
		}
		else if(Locatortype.equals("linktext"))
		{
			b= By.linkText(Locatorvalue);
		}
		else if(Locatortype.equals("partiallinktext"))
		{
			b= By.partialLinkText(Locatorvalue);
		}
		else if(Locatortype.equals("xpath"))
		{
			b= By.xpath(Locatorvalue);
		}
		else
		{
			b= By.cssSelector(Locatorvalue);
		}
		return (b);
	}
	
	
}
