package Test;

import org.testng.annotations.Test;

public class RewardTest extends BaseClass {
	
	@Test(dependsOnMethods = "Test.LoginTest.validatelogin")
	public void reward() throws InterruptedException
	{
		reward.validatereward();
	}

}
