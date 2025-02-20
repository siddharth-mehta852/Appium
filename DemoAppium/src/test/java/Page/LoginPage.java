package Page;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.touch.offset.ElementOption;

public class LoginPage {
	IOSDriver driver;
	WebDriverWait wait;
	SplashScreenPage splash;
	Constant cons;

	public LoginPage(IOSDriver driver)
	{
		this.driver=driver;
		this.splash = new SplashScreenPage(driver);
		this.cons = new Constant(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//XCUIElementTypeTextField[@value=\"Enter your email address\"]")
	WebElement email;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"NEXT\"]")
	WebElement btn_next;
	
	@FindBy(xpath="//XCUIElementTypeSecureTextField[@value=\"Enter your password\"]")
	WebElement password;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"SIGN IN\"]")
	WebElement btn_login;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"SEND\"]")
	WebElement btn_send;
	
	@FindBy(xpath="//XCUIElementTypeApplication[@name=\"Speed Wallet Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton")
	WebElement clck_invoice;
	
	@FindBy(xpath="//XCUIElementTypeTextField[@value=\"Select\"]")
	WebElement state_dropdown;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"Goa\"]")
	WebElement state_select;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"SUBMIT\"]")
	WebElement submit_button;

	
	public void logintest() throws InterruptedException
	{
		System.out.println("Login Screen Test Started Running on AWS device farm");
		
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.click();
		email.sendKeys("two@mailinator.com");
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_next));
		btn_next.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.click();
		password.sendKeys("Test@123");
		
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_login));
		btn_login.click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		

        // Check for state dropdown and proceed accordingly
        try {
            wait.until(ExpectedConditions.visibilityOf(state_dropdown));
            if (state_dropdown.isDisplayed()) {
                System.out.println("State Dropdown found, interacting...");
                wait.until(ExpectedConditions.elementToBeClickable(state_dropdown)).click();
                wait.until(ExpectedConditions.elementToBeClickable(state_select)).click();
                System.out.println("State is selected");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("State Dropdown not found, proceeding further...");
        }
//        wait.until(ExpectedConditions.elementToBeClickable(cons.notnow)).click();
	}
	public void verifylogin() throws InterruptedException
	{
		logintest();
		cons.verifybalance();
		System.out.println("Login Test Completed");
		
	}
}
