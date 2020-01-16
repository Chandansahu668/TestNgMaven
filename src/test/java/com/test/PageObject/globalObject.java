package com.test.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class globalObject {

	LoginRegistrationPage loginRegistartionPage;


	public globalObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[id='accountInfo' i]") WebElement accountLoginIcon;

	public WebElement accountLogin() {
		return accountLoginIcon;
	}

	public void navigateToLoginPage() {

		try{
			accountLogin().click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
	