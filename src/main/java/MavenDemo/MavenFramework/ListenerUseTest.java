package MavenDemo.MavenFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners(sel1.ListernerTestNG.class)
public class ListenerUseTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.firefoxdriver().setup();
         driver = new FirefoxDriver();
	}


	
	@Test(priority=0)  //failedTest
	public void OpenBrowser() {
        driver.get("https://www.google.co.in/");
        String expected2 = "Googleadasdasdas";
        Assert.assertEquals(driver.getTitle(), expected2);
	}
	
	@Test(priority=1) //success Test
	public void CloseBrowser() {
		driver.close();
		Reporter.log("Driver Closed After Testing",true);
	}
	
	@Test (priority=2)//skippedTest
	public void SkipTest() {
		throw new SkipException("Skipping the Test Method");
	}
}
