package MavenDemo.MavenFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class eCommerce {
	
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
	public void AddingToCart()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        for(int i=1;i<3;i++) {
        	driver.findElement(By.xpath("//div[@class='products']/div[1]/div[2]")).click();
        }
        
     
        driver.findElement(By.xpath("//div[@class='products']/div[1]/div[3]")).click();
        
        
        driver.findElement(By.xpath("//div[@class='products']/div[2]/div[2]")).click();
        driver.findElement(By.xpath("//div[@class='products']/div[2]/div[3]")).click();
       
        driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
        
        //driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        
        String winHandleBefore = driver.getWindowHandle();
        driver.switchTo().window(winHandleBefore);
        
       
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click(); 
       
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
       
        String winHandleAfter = driver.getWindowHandle();
        driver.switchTo().window(winHandleAfter);
      
        driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]")).sendKeys("Ind");
        
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
       
        driver.findElement(By.xpath("//button[contains(text(),'Proceed')]")).click();
	
        driver.quit();
}
}
