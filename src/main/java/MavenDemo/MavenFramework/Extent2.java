package MavenDemo.MavenFramework;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Extent2 {
	
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/BrillioExtentReport.html",true);
		extent
        .addSystemInfo("Host Name", "Brillio")
        .addSystemInfo("Environment", "QA")
		.addSystemInfo("User Name","Tarun");
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
}

	public static String getScreenshot(WebDriver driver,String screenshotName) throws Exception {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination=new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	} 



@Test
public void passTest() {
	logger=extent.startTest("passTest");
	
	Assert.assertTrue(true);
	logger.log(LogStatus.PASS, "Test Case passTest has Passed");
}


@Test
public void failTest() {
	logger=extent.startTest("failTest");
	WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
	
	driver.get("https://blazedemo.com/");
	String URL=driver.getCurrentUrl();
Assert.assertEquals(URL,"abc");
logger.log(LogStatus.PASS, "Test Case failTest status is passed");
}


@Test
public void skipTest() {
	logger=extent.startTest("skipTest");
	throw new SkipException("Skipping this method- it is not ready for testng");
}


@AfterMethod
public void getResult(ITestResult result) throws Exception{
	if(result.getStatus()==ITestResult.FAILURE) {
		logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		String screenshotPath=Extent2.getScreenshot(driver, result.getName());
		
		//to add to extent report
		logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
	}
	else if(result.getStatus()==ITestResult.SKIP) {
		logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	}
	
	extent.endTest(logger);
}

@AfterTest
public void endReport() {
	extent.flush(); //write to report
	extent.close();
}

}