package Page;

import java.awt.Container;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public class RewardPage {
	IOSDriver driver;
	WebDriverWait wait;
	Constant cons;
	HistoryPage hist;
	
	
	public RewardPage(IOSDriver driver)
	{
		this.driver=driver;
		this.cons=new Constant(driver);
		this.hist=new HistoryPage(driver);
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	
//	@FindBy(xpath="//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]")
//	WebElement reward_bar;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"Rewards\"]")
	WebElement btn_reward;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"LET’S GET STARTED\"]")
	WebElement btn_started;
	
	By.ByXPath reward_bar= new By.ByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]");
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"REDEEM\"]")
	WebElement btn_redeem;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"REDEEM NOW\"]")
	WebElement redeem_btn;
	
	@FindBy(xpath="//XCUIElementTypeTextField")
	WebElement txt_field;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"REDEEM\"]")
	WebElement reward_redeem;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"See history\"]")
	WebElement reward_history;
	
	By.ByXPath trans = new By.ByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[1]");
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@name=\"SUCCESSFULLY REDEEMED\"]")
	WebElement redeem_message;
	
	
	public void checkreward() throws InterruptedException
	{
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("paymentadd1@tryspeed.dev", "4000");
		
		//wait.until(ExpectedConditions.visibilityOf(reward_bar));
		WebElement rewardcontain = driver.findElement(reward_bar);
		
		List<WebElement> barelement = rewardcontain.findElements(By.xpath(".//*"));
        for (WebElement rewardbar : barelement) {


            String nestedText = rewardbar.getText();


            System.out.println(" " + nestedText);

	}
	
        
        
		System.out.println("Reward Banner is shown");
		
		wait.until(ExpectedConditions.elementToBeClickable(cons.send_payment)).click();
		
		try {
			wait.until(ExpectedConditions.visibilityOf(cons.payment_status));
			System.out.println("Payment is Successfull");
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.visibilityOf(cons.payment_process));
				System.out.println("Payment is Processing");
				cons.payment_process.click();
			} catch (Exception e2) {
				System.out.println("Payment Failed");
			}
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_reward)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_started)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_redeem)).click();
		wait.until(ExpectedConditions.elementToBeClickable(reward_history)).click();
		Thread.sleep(1000);
		WebElement transcontain = driver.findElement(trans);
		
		List<WebElement> transelement = transcontain.findElements(By.xpath(".//*"));
		for (WebElement transact : transelement)
		{
			String nestedTextt = transact
					.getText();
			
			System.out.println(" " + nestedTextt);
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(cons.back)).click();
		wait.until(ExpectedConditions.elementToBeClickable(cons.wallet)).click();
		
		
	}
	public void rewardreedem() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(btn_reward)).click();
	//	wait.until(ExpectedConditions.elementToBeClickable(btn_started)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btn_redeem)).click();
		wait.until(ExpectedConditions.elementToBeClickable(redeem_btn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(txt_field)).clear();
		wait.until(ExpectedConditions.elementToBeClickable(txt_field)).sendKeys("40");
		wait.until(ExpectedConditions.elementToBeClickable(reward_redeem)).click();
		
		
		if(wait.until(ExpectedConditions.visibilityOf(redeem_message)).isDisplayed())
		{
			System.out.println("Reward Points Redeemed Successfully");
			hist.btn_history.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.	elementToBeClickable(btn_reward)).click();
			wait.until(ExpectedConditions.elementToBeClickable(btn_redeem)).click();
			wait.until(ExpectedConditions.elementToBeClickable(reward_history)).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(cons.back)).click();
			wait.until(ExpectedConditions.elementToBeClickable(cons.wallet)).click();
			
		}
		else
		{
			System.out.println("Unable to Redeem reward points");
		}
		
		
	}
	public void validatereward() throws InterruptedException
	{
		System.out.println("Reward Page Started Running on AWS device farm");
		checkreward();
		//rewardreedem();
		System.out.println("Reward Test Completed");
	}
	
	

}
