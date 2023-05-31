package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.Reports;

public class Listners extends Base implements ITestListener {
	ExtentTest test;
	ExtentReports report = Reports.getReports();
	

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.pass("Test Pass !");
		System.out.println("Test is Successfull");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Here I need to write Screenshot method
		test.fail("Test Failed");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String pathOfScreenShot=null;
		try {
			pathOfScreenShot = takeScreenshotOnFailure(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		test.addScreenCaptureFromPath(pathOfScreenShot, result.getMethod().getMethodName());
		System.out.println("Failure testcase");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
