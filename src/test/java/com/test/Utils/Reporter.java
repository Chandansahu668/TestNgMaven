package com.test.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class Reporter implements ITestListener{

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	BaseClass baseClass = new BaseClass();

	public void onStart(ITestContext testContext) {
//		String timeStamp = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
		String repName = "Test-Report"+".html";
		Path path = Paths.get(System.getProperty("user.dir")+ "/test-output/"+repName);
		if(!Files.exists(path))
		{
			try{
				Files.createFile(path);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			try{
				Files.deleteIfExists(path);
				Files.createFile(path);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		htmlReporter.loadXMLConfig("C:\\Users\\Chandan\\eclipse-workspace\\TJX\\extent-config.xml");
		htmlReporter.config().setDocumentTitle("Yankee Candle Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","Prod");
		extent.setSystemInfo("User","ZForce");

		
	}

	public void onTestSuccess (ITestResult tr) {
		test = extent.createTest(tr.getName());//Entry to test case
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}
//	public void onTestFaliure (ITestResult tr) {
//		test = extent.createTest(tr.getName());
//		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
//		String screenshotPath = "./Screenshots/"+tr.getName()+".png";
//		File f = new File(screenshotPath); 
//
//		if(f.exists())
//		{
//			try {
//				test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
//			} 
//			catch (Exception e) 
//			{
//				e.printStackTrace();
//			}
//		}
//	}
	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.YELLOW));

	}
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String screenshotPath = "./Screenshots/testfail.png";
		try {
			test.log(Status.FAIL, "Screenshot as below:",
                    MediaEntityBuilder.createScreenCaptureFromPath("." + screenshotPath).build());
//			test.fail("Sreenshot below", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath));
//			test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
