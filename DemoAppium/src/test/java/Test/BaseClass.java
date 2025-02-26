package Test;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Page.SendPage;
import Page.SettingsPage;
import Page.Constant;
import Page.HistoryPage;
import Page.LoginPage;
import Page.RewardPage;
import Page.SplashScreenPage;
import Page.SwapPage;
import io.appium.java_client.ios.IOSDriver;

public class BaseClass {
	
	public static IOSDriver driver;
	public SplashScreenPage splash;
	public LoginPage login;
	public SendPage lnaddsend;
	public HistoryPage hist;
	public SettingsPage set;
	public RewardPage reward;
	public SwapPage swap;
	public Constant cons;
	public static Properties props = new Properties();
	
	
	   // ExtentReports objects
    public static ExtentReports extent;
    public static ExtentTest test;
	
	@BeforeSuite
	public void setup() throws MalformedURLException, InterruptedException
	{	
//		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
//		extent = new ExtentReports();
//		extent.attachReporter(spark);	
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("appium:automationName", "XCUITest");
		capabilities.setCapability("appium:bundleId", "com.app.speed1.dev");
		capabilities.setCapability("appium:useNewWDA", true);
		capabilities.setCapability("appium:autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("appium:deviceName", "iPhone 12 mini");
		capabilities.setCapability("appium:platformVersion", "17.5");
		capabilities.setCapability("appium:udid", "00008101-001935913AC2001E");
		capabilities.setCapability("appium:app", "/Users/speed/Desktop/Speed2.ipa");

		
		
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		
		driver = new IOSDriver(url,capabilities);	
		
		System.out.println("Application Started on AWS Device Farm");
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		
//		try {
//			FileReader reader = new FileReader("/Users/speed/Downloads/DemoAppium/src/test/resources/config.properties");
//			props.load(reader);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	
	@BeforeClass
	public void initObject()
	{	
		splash = new SplashScreenPage(driver);
		login = new LoginPage(driver);
		lnaddsend = new SendPage(driver);
		hist = new HistoryPage(driver);
		reward = new RewardPage(driver);
		swap = new SwapPage(driver);
		cons = new Constant(driver);
		set = new SettingsPage(driver);
	}
	
//	@BeforeMethod
//	public void beforeMethod(ITestResult result) {
//	    test = extent.createTest(result.getMethod().getMethodName());
//	}

	
//	@AfterMethod
//	public void getResult(ITestResult result)
//	{
//		if(result.getStatus() == ITestResult.FAILURE)
//		{
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
//			
//			String screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
//			test.addScreenCaptureFromBase64String(screenshot,"Screenshot on Failure");
//			
//		}
//		else if(result.getStatus() == ITestResult.SUCCESS)
//		{
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
//		}
//		else if(result.getStatus() == ITestResult.SKIP)
//		{
//			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.YELLOW));
//		}
//		
//	}
	
	
	@AfterSuite
	public void close()
	{
		driver.quit();
	}

}
