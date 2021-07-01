package MavenDemo.MavenFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowser {
WebDriver driver;
	
	@BeforeTest
	@Parameters("browser")
     public void setup(String browser) throws Exception {
		if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		
		else if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		
		else {
			throw new Exception("Browser is not correct");
		}
		
	}
	

	@Test
	public void demo1() throws InterruptedException {
        driver.get("https://petstore.octoperf.com/actions/Account.action");
        
        
       driver.findElement(By.name("username")).sendKeys("j2ee");
        Thread.sleep(10000);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("j2ee");
        Thread.sleep(10000);
        driver.findElement(By.name("signon")).click();
        
        driver.quit();

}
}
