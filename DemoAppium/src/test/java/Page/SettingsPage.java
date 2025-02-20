package Page;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public class SettingsPage {
	
	IOSDriver driver;
	WebDriverWait wait;
	Constant cons;
	
	public SettingsPage(IOSDriver driver)
	{
		this.driver=driver;
		this.wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		this.cons = new Constant(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"icn advance setting\"]")
	WebElement btn_setting;
	
	By.ByXPath profile_section = new By.ByXPath("//XCUIElementTypeApplication[@name=\"Speed Wallet Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]");
	
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"Currency\"]")
	WebElement click_currency;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"United Arab Emirates Dirham\"]")
	WebElement select_country;
	
	public void profiletext() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(btn_setting)).click();
		System.out.println("Settings button gets clicked");
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		
		WebElement container = driver.findElement(profile_section);
		
		List<WebElement> nestedElements = container.findElements(By.xpath(".//*"));
		for(WebElement nestedElement : nestedElements)
		{
			String nestedText = nestedElement.getText();
			
			System.out.println(" "+ nestedText);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(profile_section)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(cons.back)).click();
		Thread.sleep(2000);		
		wait.until(ExpectedConditions.elementToBeClickable(cons.back)).click();
		Thread.sleep(1000);
		
	}
	
	public void changecountry()
	{
		wait.until(ExpectedConditions.elementToBeClickable(click_currency)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select_country)).click();
		
		System.out.println("Country Changed Successfully");
	}
	
	public void verifysetting() throws InterruptedException
	{
		profiletext();
		System.out.println("Setting Test is Completed");
	}

}
