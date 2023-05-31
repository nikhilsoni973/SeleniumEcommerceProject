package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	public static ExtentReports getReports() {
		String myPath = System.getProperty("user.dir")+"/myReports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(myPath);
		reporter.config().setDocumentTitle("Nikhil Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}

}
