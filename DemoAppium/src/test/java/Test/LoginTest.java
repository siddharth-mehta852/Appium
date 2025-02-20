package Test;

import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
	
	@Test(dependsOnMethods = "Test.SplashScreenTest.splashScreenPage")
	public void validatelogin() throws InterruptedException
	{
		login.verifylogin();
	}
}

