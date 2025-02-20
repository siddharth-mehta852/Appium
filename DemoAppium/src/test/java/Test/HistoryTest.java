package Test;

import org.testng.annotations.Test;

public class HistoryTest extends BaseClass{

	@Test(dependsOnMethods = "Test.SendTest.validatepayment")
	public void validatehistory() throws InterruptedException
	{
		hist.historytest();
	}
}