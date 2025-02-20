package Test;

import org.testng.annotations.Test;

public class SettingTest extends BaseClass {

	@Test
	public void validatesetting() throws InterruptedException
	{
		set.verifysetting();
	}
}
