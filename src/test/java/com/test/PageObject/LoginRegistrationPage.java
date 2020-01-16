package com.test.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginRegistrationPage {
	

	
	public LoginRegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="input[name=loginemail i]") WebElement userName;
	@FindBy(css="input[name=loginpassword i]") WebElement password;
	@FindBy(css="button[type=submit i]") WebElement login;
	
	
	public WebElement username()
	{
		return userName;
	}
	
	public WebElement Password()
	{
		return password;
	}
	
	public WebElement Submit()
	{
		return login;
	}
	public void login(String uNmae, String pswd) {
		try {
			username().sendKeys(uNmae);
			Password().sendKeys(pswd);
			Submit().click();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	

}
