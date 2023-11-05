package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	
	@Override
	public void onStart(ITestContext testContext) {
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report" + timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(
		System.getProperty("user.dir") + "\\reports\\" + reportName);
	
		sparkReporter.config().setDocumentTitle("AutomationExercise Report");
		sparkReporter.config().setReportName("AutomationExercise Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "AutomationExercise");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		// Get os and browser from xml 
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		if(browser != null) {
		    extent.setSystemInfo("Browser", browser);
		}	
		
		// Get groups
		List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString()); 
		}	
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " passed successfully");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ " failed");
		
		// Log error message safely
		String errorMsg = result.getThrowable() != null?
				result.getThrowable().getMessage() : "No error message available";
		test.log(Status.INFO, errorMsg);
		
		// Attach screenshot
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " was skippped");
		
		// Log skip reason safely
		if (result.getThrowable() != null) {
			test.log(Status.INFO, result.getThrowable().getMessage());
		}
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
	    extent.flush(); 

	    try {
	        String reportPath = System.getProperty("user.dir") 
	            + "\\reports\\" + reportName;
	        File report = new File(reportPath);
	        Desktop.getDesktop().browse(report.toURI());
	    } catch(IOException e) {
	        e.printStackTrace();
	        System.out.println("Report saved at: \\reports\\" + reportName);
	    }
	}
	
}