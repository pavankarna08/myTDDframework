 package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ComposePage 
{
	//properties for locating element
	private RemoteWebDriver driver;
	private FluentWait<RemoteWebDriver> wait;
	
	@FindBy (how=How.XPATH,using="//*[text()='Compose']")
	private WebElement compose;
	
	@FindBy (how=How.NAME,using="to")
	private WebElement toaddress;
	
	@FindBy (how=How.NAME,using="subjectbox")
	private WebElement subject;
	
	@FindBy (how=How.XPATH,using="//div[@aria-label='Message Body']")
	private WebElement body;
	
	@FindBy (how=How.XPATH,using="//input[@name='Filedata' and @type='file']")
	private WebElement attachment;
	
	@FindBy (how=How.XPATH,using="//*[contains(@aria-label,'Uploading attachment')]")
	private WebElement uploading;
	
	@FindBy (how=How.XPATH,using="//*[contains(@aria-label,' attachment')]")
	private WebElement uploaded;
	
	@FindBy (how=How.XPATH,using="//*[text()='Send']")
	private WebElement send;
	
	@FindBy (how=How.XPATH,using="//*"
			+ "[contains(text(),'Message sent')]")
	private WebElement outputmsg;
	
	//Constructor methods for operating element in runner class
	public ComposePage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		AjaxElementLocatorFactory af = new AjaxElementLocatorFactory(driver,50);
		PageFactory.initElements(af,this);
		this.driver=driver;
		this.wait=wait;
	}
	
	//operational methods for operations and observations
	public void clickCompose()
	{
		wait.until(ExpectedConditions.elementToBeClickable(compose)).click();
	}
	
	public void fillTo(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(toaddress)).sendKeys(x);
	}
	
	public void fillSubject(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(subject)).sendKeys(x);
	}
	
	public void fillBody(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(body)).sendKeys(x);
	}
	
	public void attachment (String x) throws Exception
	{
		//for hidden element,presenceOfElementLocated() method can take "By" as argument
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='Filedata' and @type='file']"))).sendKeys(x);
		//for visible element, visibilityOf() can take "WebElement" as argument
		try
		{
			//If attachment file is big, status bar will be generated
			wait.until(ExpectedConditions.visibilityOf(uploading));
			wait.until(ExpectedConditions.visibilityOf(uploaded));
		}
		catch(Exception ex)
		{
			//may be attached file size is very small,so status bar is generated
			wait.until(ExpectedConditions.visibilityOf(uploaded));
		}
	}
	
	public void clickSend()
	{
		wait.until(ExpectedConditions.elementToBeClickable(send)).click();
	}
	
	public String getOutputMessage() 
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(outputmsg));
			return(outputmsg.getText());
		}
		catch(Exception ex)
		{
			return("Sorry, Mail has not been sent");
		}
	}

}
