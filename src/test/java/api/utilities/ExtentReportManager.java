package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repname;

	public void onStart(ITestContext testcontext) {
		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repname="Test Report-"+Timestamp+".html";

		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repname ); //specify location for report

		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of report
		sparkReporter.config().setReportName("Pet Store User API");  //Name of Report
		sparkReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("Operating system", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Sailesh Kumar");

	}

	public void onTestSuccess(ITestResult result) {

		test=extent.createTest(result.getName());	
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test is Pass");

	}

	public void onTestFailure(ITestResult result) {

		test=extent.createTest(result.getName());	
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test is Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {

		test=extent.createTest(result.getName());	
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test is Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}

}
