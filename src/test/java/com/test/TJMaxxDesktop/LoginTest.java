package com.test.TJMaxxDesktop;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.PageObject.LoginRegistrationPage;
import com.test.PageObject.globalObject;
import com.test.Utils.BaseClass;

public class LoginTest extends BaseClass {
	globalObject globalObject;
	LoginRegistrationPage loginRegistrationPage;
	

	@Test(priority =1)
	public void login() {
		globalObject = new globalObject(driver);
		loginRegistrationPage= new LoginRegistrationPage(driver);
		globalObject.accountLogin().click();
		log.info("Click on the login button");
		loginRegistrationPage.login(dataProvider.getCellData("login", "UserName"),dataProvider.getCellData("login", "Password"));
		Assert.assertTrue(false);
	}
}
