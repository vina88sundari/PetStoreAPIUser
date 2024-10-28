package api.utilities;


import java.text.SimpleDateFormat;

import org.apache.poi.hpsf.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtenReportGenerator implements ITestListener {
  
   public ExtentSparkReporter extentSparkReporter;
   public ExtentReports extentReports;
   public ExtentTest extentTest;
   String repname;
  
   @BeforeTest
   public void startReporter(ITestContext testContext)
   
   {
	   String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	   repname="Test-Report-"+timestamp+".html";
       extentSparkReporter  = new ExtentSparkReporter(".\\Reports"+ repname);
       extentReports = new ExtentReports();
       extentReports.attachReporter(extentSparkReporter);


       //configuration items to change the look and feel
       //add content, manage tests etc
       extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
       extentSparkReporter.config().setReportName("Test Report");
       extentSparkReporter.config().setTheme(Theme.DARK);
       extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
       
       extentReports=new ExtentReports(); 
       extentReports.attachReporter(extentSparkReporter);
       extentReports.setSystemInfo("Application", "Per Store User API");
       extentReports.setSystemInfo("Operation System", System.getProperty("os.name"));
       extentReports.setSystemInfo("Operation System", System.getProperty("user.name"));
       extentReports.setSystemInfo("Environment","QA");
       extentReports.setSystemInfo("User", "Vina");
       
   }
  
   @AfterMethod
   
   public void onTestSuccess(ITestResult result)
   
   {
	   extentTest=extentReports.createTest(result.getName());
	   extentTest.createNode(result.getName());
	   extentTest.assignCategory(result.getMethod().getGroups());
	   extentTest.log(Status.PASS,"Test Passed");
   }
   
   //@AfterMethod
	/*
	 * public void onTestFailure(ITestResult result)
	 * 
	 * { extentTest=extentReports.createTest(result.getName());
	 * extentTest.createNode(result.getName());
	 * extentTest.assignCategory(result.getMethod().getGroups());
	 * extentTest.log(Status.FAIL,"Test Failed");
	 * extentTest.log(Status.FAIL,result.getThrowable().getMessage()); }
	 * // @AfterMethod public void onTestSkipped(ITestResult result)
	 * 
	 * { extentTest=extentReports.createTest(result.getName());
	 * extentTest.createNode(result.getName());
	 * extentTest.assignCategory(result.getMethod().getGroups());
	 * extentTest.log(Status.SKIP,"Test Skipped");
	 * extentTest.log(Status.SKIP,result.getThrowable().getMessage()); }
	 */
   
   @AfterTest
   public void tearDown(ITestContext testContext) {
       //to write or update test information to the reporter
       extentReports.flush();
   }
}
