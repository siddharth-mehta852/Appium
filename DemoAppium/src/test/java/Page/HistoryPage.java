package Page;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class HistoryPage {

	IOSDriver driver;
	WebDriverWait wait;
	Constant cons;
	Actions actions;

	public HistoryPage(IOSDriver driver) {
		this.driver = driver;
		this.cons=new Constant(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"icn transaction badge\"]")
	WebElement btn_history;

	@FindBy(xpath="(//XCUIElementTypeCell[@name=\\\"TransactionTableViewCell\\\"])[2]\"")
	WebElement click_transaction;	

//	By.ByXPath click_transaction = new By.ByXPath("(//XCUIElementTypeCell[@name=\"TransactionTableViewCell\"])[2]");

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Wallet\"]")
	WebElement btn_wallet;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"arrow.backward\"]")
	WebElement back_arrow;

	public void verifyhistory() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_history)).click();
		Thread.sleep(1000);
		System.out.println("Transaction Element Not Found");
		wait.until(ExpectedConditions.elementToBeClickable(cons.back)).click();
		Thread.sleep(2000);
	}

	public void historytest() throws InterruptedException {
		System.out.println("History Screen Test Started Running on AWS device farm");
		verifyhistory();
		System.out.println("History Page Executed");	
	}
}

