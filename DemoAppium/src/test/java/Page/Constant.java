package Page;

import java.awt.Container;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Constant {
	IOSDriver driver;
	WebDriverWait wait;

	public Constant(IOSDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"Wallet\"]")
	WebElement wallet;

	@FindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther")
	WebElement usdt_btn;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"SEND\"]")
	WebElement btn_send;

	@FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Speed Wallet Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton")
	WebElement clck_invoice;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value=\"Add address or invoice\"]")
	WebElement entr_lnaddress;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"SEND\"]")
	WebElement send_lnadd;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value=\"1\"]")
	WebElement clear_amount;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value=\"0\"]")
	WebElement enter_amount;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"Done\"]")
	WebElement amount_done;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"SEND\"]")
	WebElement send_payment;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"PAYMENT SENT\"]")
	WebElement payment_status;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"BACK TO HOME\"]")
	WebElement payment_process;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Payment failed\"]")
	WebElement payment_failed;

	@FindBy(xpath = "//XCUIElementTypeButton[@name=\"arrow.backward\"]")
	WebElement back;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Select payment mode\"]")
	WebElement payment_popup;

	@FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Speed Wallet Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeButton")
	WebElement payment_lightning;

	@FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Speed Wallet Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeButton")
	WebElement payment_bitcoin;
	
	@FindBy(xpath="//XCUIElementTypeButton[@name=\"Not Now\"]")
	WebElement notnow;
	
	@FindBy(xpath="//XCUIElementTypeOther[XCUIElementTypeStaticText[@value='Current Balance']]/XCUIElementTypeStaticText[2]")
	WebElement balance;
	
	//By.ByXPath balance = new By.ByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]");
	


	By.ByXPath click_transaction = new By.ByXPath("(//XCUIElementTypeCell[@name=\"TransactionTableViewCell\"])[2]]");


	public void clickSendAndInvoice() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(btn_send)).click();
		wait.until(ExpectedConditions.elementToBeClickable(clck_invoice)).click();
	}
	
	public void verifybalance()
	{
		wait.until(ExpectedConditions.visibilityOf(balance));
		 System.out.println("BTC Balance: " + balance.getText());
	}

	public void enterAddressAmount(String address, String amount) {
		wait.until(ExpectedConditions.elementToBeClickable(entr_lnaddress)).sendKeys(address);
		wait.until(ExpectedConditions.elementToBeClickable(send_lnadd)).click();

		if (clear_amount.isDisplayed()) {
			clear_amount.click();
			clear_amount.clear();
		}

		wait.until(ExpectedConditions.elementToBeClickable(enter_amount)).sendKeys(amount);
		wait.until(ExpectedConditions.elementToBeClickable(amount_done)).click();
	}

	public void sendPaymentAndVerify() {

		wait.until(ExpectedConditions.elementToBeClickable(send_payment)).click();
		try {
			wait.until(ExpectedConditions.visibilityOf(payment_status));
			System.out.println("Payment is Successfull");
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.visibilityOf(payment_process));
				System.out.println("Payment is processing");
				payment_process.click();
			} catch (Exception ex) {
				System.out.println("Payment Failed");
			}
		}
	}

	public void verifyaddress(String address) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(entr_lnaddress)).sendKeys(address);
		wait.until(ExpectedConditions.elementToBeClickable(send_lnadd)).click();
		
		WebDriverWait shortwait = new WebDriverWait(driver, Duration.ofSeconds(1))	;
		try
		{
		
			if(shortwait.until(ExpectedConditions.visibilityOf(payment_popup)).isDisplayed())
			{
				selectPaymentMethod("lightning");
				
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(back)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(btn_send)).click();
				wait.until(ExpectedConditions.elementToBeClickable(clck_invoice)).click();
				wait.until(ExpectedConditions.elementToBeClickable(entr_lnaddress)).sendKeys(address);
				wait.until(ExpectedConditions.elementToBeClickable(send_lnadd)).click();
				
				if(shortwait.until(ExpectedConditions.visibilityOf(payment_popup)).isDisplayed())
				{
					selectPaymentMethod("bitcoin");
				}
				wait.until(ExpectedConditions.elementToBeClickable(back)).click();
			}
		}
		catch (Exception e) {
			System.out.println("Payment popup did not appear for this address");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(back)).click();
		}
	}
	
	
	private void selectPaymentMethod(String method)
	{
		switch(method.toLowerCase())
		{
		case "lightning":
			wait.until(ExpectedConditions.elementToBeClickable(payment_lightning)).click();
			System.out.println("Selected payment method: Lightning");
			break;
		
		case "bitcoin":
			wait.until(ExpectedConditions.elementToBeClickable(payment_bitcoin)).click();
			System.out.println("Selected payment method: Bitcoin");
			break;
		
		default:
			throw new IllegalArgumentException("Unknown payment method:" + method);
		}	
	}
}
