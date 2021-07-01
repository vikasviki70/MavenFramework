package MavenDemo.MavenFramework;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelExample2 {
	
	WebDriver driver;
	WebDriverWait Wait;
	
	String appURL ="https://www.linkedin.com/";
	
	private By bySignInLink=By.linkText("Sign in");
	private By byEmail= By.name("session_key");
	private By byPassword= By.name("session_password");
	private By bySignIn=By.xpath("//button[@type='submit']");
	private By byError=By.id("error-for-username");
	
	@BeforeClass
	public void testSetup() {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		Wait = new WebDriverWait(driver,10);
	}
	
	@Test(dataProvider="inputdata")
	public void verifyInvalidLogin(String userName, String password) {
		
		driver.get(appURL);
		driver.findElement(bySignInLink).click();
	    driver.findElement(byEmail).sendKeys(userName);
        driver.findElement(byPassword).sendKeys(password);
        Wait.until(ExpectedConditions.visibilityOfElementLocated(bySignIn));
        driver.findElement(bySignIn).click();
        
        Wait.until(ExpectedConditions.presenceOfElementLocated(byError));
       String actualErrorMessage=driver.findElement(byError).getText();
       String requiredErrorMessage="Please enter a valid username";
       Assert.assertEquals(actualErrorMessage, requiredErrorMessage);
       
	}
	
	@DataProvider(name="inputdata")
	public Object [][] getCellData() throws IOException {
		
		// locate excel file
		FileInputStream file = new FileInputStream("./sampledoc1.xlsx");
		//create workbook instance
		XSSFWorkbook wb=new XSSFWorkbook(file);
		//go to desired sheet
		XSSFSheet s=wb.getSheet("sheet1");
		int rowcount = s.getLastRowNum()+1;
		int cellcount=s.getRow(0).getLastCellNum();
		
		Object data[][] = new Object[rowcount][cellcount];
		
		for(int i=0;i<rowcount;i++) {
			Row r = s.getRow(i);
			 
		    for(int j=0;j<cellcount;j++) {
		    	Cell c=r.getCell(j);
		    	data[i][j]=c.getStringCellValue();
		    }
		}
		wb.close();
		return data;
	}
}
