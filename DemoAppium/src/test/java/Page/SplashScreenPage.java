package Page;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SplashScreenPage {
	IOSDriver driver;
	WebDriverWait wait;
	
	
	public SplashScreenPage(IOSDriver driver)
	{
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"GET STARTED\"]")
	WebElement btn_started;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"NEXT\"]")
	WebElement btn_next;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"NEXT\"]")
	WebElement btn_next2;
	
	@FindBy(xpath="///XCUIElementTypeStaticText[@name=\"FINISH\"]")
	WebElement btn_next3;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"SIGN IN\"]")
	WebElement signin;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"SIGN IN WITH EMAIL\"]")
	WebElement btn_signin;
	
	
	public void splashtest() throws InterruptedException
	{

		System.out.println("Splash Screen Test Started Running on AWS device farm");
		wait.until(ExpectedConditions.elementToBeClickable(btn_started)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_next));
		btn_next.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_next2));
		btn_next2.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_next3));
		btn_next3.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(signin));
		signin.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_signin));
		btn_signin.click();
		
		System.out.println("Splash Screen Verified");
	}
	
}
