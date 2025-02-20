package Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

public class SendTest extends BaseClass {
	
	@Test
	public void validatepayment() throws InterruptedException, EncryptedDocumentException, IOException
	{
		lnaddsend.validatepayment();
	}
}
		