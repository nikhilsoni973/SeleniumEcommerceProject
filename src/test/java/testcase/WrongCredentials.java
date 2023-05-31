package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;

public class WrongCredentials extends Base{
	
	@Test
	public void loginWithIncorrectCredentials() {
		page.loginWithCredentials("abcd@gmail.com", "aaa");
		String toastMessage = page.getToastMessage();
		System.out.println(toastMessage);
		if(toastMessage!="") {
		Assert.assertTrue(false);
		}
		
		
	}

}
