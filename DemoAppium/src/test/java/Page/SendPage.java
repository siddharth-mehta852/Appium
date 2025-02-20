package Page;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SendPage {

	IOSDriver driver;
	WebDriverWait wait;
	SplashScreenPage splash;
	LoginPage login;
	HistoryPage hist;
	Constant cons;

	public SendPage(IOSDriver driver) {
		this.driver = driver;
		this.login = new LoginPage(driver);
		this.splash = new SplashScreenPage(driver);
		this.hist = new HistoryPage(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.cons = new Constant(driver);

		PageFactory.initElements(driver, this);
	}

	public void lnurlsend() throws InterruptedException {
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("paymentadd1@tryspeed.dev", "40");
		cons.sendPaymentAndVerify();
		System.out.println("LnUrl Send Payment Test Completed");
		hist.historytest();
	}

	public void usdtsend() throws InterruptedException {
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.elementToBeClickable(cons.usdt_btn)).click();
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("ethereum:0x2b0007d8402c318e9e6a91316097c43cd7fc3a68?amount=1", "5");
		cons.sendPaymentAndVerify();
		System.out.println("USDT Send Test Completed");
		Thread.sleep(1000);
		hist.historytest();

		
	}

	public void oneqrsend() throws InterruptedException {
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("lnurl1dp68gurn8ghj7ctswpshq6fww3e8jumsv4jkgtnyv4mz7mrww4exc0m384jkzc338qcxvcmr89nx2vrxx3jrsvmxvgck2wp4xf3kzdfnxcmrwcmxv43nxcmzxsekve34vcerwv33v5ungepnxsmnydnpxyunjd3jhuku0g",
				"3");
		cons.sendPaymentAndVerify();
		System.out.println("One QR Send Payment Completed");
		hist.historytest();
	}

	public void paymentadddsend() throws InterruptedException {
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("paymentadd1@tryspeed.dev", "4");
		cons.sendPaymentAndVerify();
		System.out.println("Payment Add Send Completed");
		hist.historytest();
	}

	public void onchain() throws InterruptedException {
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("tb1qxq5kaztah7c39hw4upflgngaxq4ycu5q5yrc74", "1000");
		cons.sendPaymentAndVerify();
		System.out.println("On-Chain Payment Completed");
		hist.historytest();		
	}
	
	public void tron() throws InterruptedException
	{
		cons.clickSendAndInvoice();
		cons.enterAddressAmount("tron:TSsWm1bvzmDp3rDTVQgbhFeY1HynASoaUP?amount=1.0", "3");
		cons.sendPaymentAndVerify();
		System.out.println("Tron Payment Completed");
		
	}

	public void sendInvoicesFromExcel(String excelFilePath) throws EncryptedDocumentException, IOException, InterruptedException {
		FileInputStream fis = new FileInputStream(excelFilePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		
		DataFormatter formatter = new DataFormatter();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String address = formatter.formatCellValue(row.getCell(0));	
			
			
			try {
				cons.clickSendAndInvoice();
				cons.verifyaddress(address);
				
				System.out.println("Invoice verify to: " + address);
			} catch (NoSuchElementException e) {
				System.out.println("Error in verifying invoice to: " + address + " - " + e.getMessage());
				continue; // Proceed to the next row even if an error occurs
			}
		}

		workbook.close();
		fis.close();

	}

	public void validatepayment() throws InterruptedException, EncryptedDocumentException, IOException {
		System.out.println("Send Screen Test Started Running on AWS device farm");
		lnurlsend();
		System.out.println("Send Test Completed");

	}
}
