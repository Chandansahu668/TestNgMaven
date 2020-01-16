package com.test.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	
	public Logger log;
	public ReadConfig readConfig = new ReadConfig();
	public DataProvider dataProvider = new DataProvider();
	@Parameters("browser")
	@BeforeTest
	public void Setup(String browser) {
		log = Logger.getLogger("TJX");
		PropertyConfigurator.configure("log4J.properties");
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}
		log.info("Starting TestCase");
		driver.get(readConfig.getbaseUrl());
		log.info("Loading the site");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.navigate().refresh();
		log.info("Refreshing it.");
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("After Method");
		String screenshotPath="./Screenshots/testfail.png";
		try {
			File f = new File (screenshotPath);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src =ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, f);
			driver.quit();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	
}
