package MavenDemo.MavenFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionExample {
	
	@Test
	public void assertPass() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikas.m\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        //Test Condition: If Title Matches it will pass
        driver.get("https://www.google.com");
        String expected = "Google";
        Assert.assertEquals(driver.getTitle(), expected);
        Thread.sleep(3000);
        
        driver.close();
	}
	
	
	/*@Test
	public void assertFail() throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "D:\\Soft_Dumps\\Selenium\\support\\June21\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        
        //Test Condition: If Title does not match test case will fail and throw exception
        driver.get("https://www.google.com");
        String expected2 = "Googleadasdasdas";
        Assert.assertEquals(driver.getTitle(), expected2);
        Thread.sleep(3000);
        
        driver.close();
	}
*/	

}
