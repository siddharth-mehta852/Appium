package Page;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public class SwapPage {
	
	IOSDriver driver;
	WebDriverWait wait;
	HistoryPage hist;
	Constant con;
	
	public SwapPage(IOSDriver driver)
	{
		this.driver=driver;
		this.hist=new HistoryPage(driver);
		this.con=new Constant(driver);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
                   	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"BUY\"]")
	WebElement buy_btc;
	
	@FindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell[2]")
	WebElement btn_usdt;
	
	@FindBy(xpath="//XCUIElementTypeTextField[@value=\"0\"]")
	WebElement usdt_amount;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"GET SATS\"]")
	WebElement get_sats;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"SWAP SUCCESSFULLY\"]")
	WebElement swap_success;
	
	@FindBy(xpath="//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]")
	WebElement usdt_swap;
			
	@FindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell[2]")
	WebElement btn_btc;
	
	@FindBy(xpath="//XCUIElementTypeTextField[@value=\"0\"]")
	WebElement btc_amount;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"GET USDT\"]")
	WebElement get_usdt;
	
	
	public void btcswap() throws InterruptedException
	{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(buy_btc)).click();
			wait.until(ExpectedConditions.elementToBeClickable(btn_usdt)).click();
			wait.until(ExpectedConditions.elementToBeClickable(btc_amount)).sendKeys("10");
			wait.until(ExpectedConditions.elementToBeClickable(con.amount_done)).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(get_sats)).click();
			wait.until(ExpectedConditions.visibilityOf(swap_success)).isDisplayed();
			System.out.println("BTC Swap Successfull");
			
	}
	public void usdtswap() throws InterruptedException
	{
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(con.usdt_btn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(buy_btc)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_btc)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btc_amount)).sendKeys("20000");
		wait.until(ExpectedConditions.elementToBeClickable(con.amount_done)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(get_usdt)).click();
		System.out.println("USDT Swap Successfull");
	}
	
	public void verifyswap() throws InterruptedException
	{
		btcswap();
		hist.verifyhistory();
		usdtswap();
		hist.verifyhistory();
		
		System.out.println("Swap Test Passed Successfully");
	}

}
