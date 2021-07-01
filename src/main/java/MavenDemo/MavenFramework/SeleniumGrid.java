package MavenDemo.MavenFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SeleniumGrid{
	 
	@Test
	 public void mailTest() throws MalformedURLException{
	 DesiredCapabilities dr=null;
	 dr=DesiredCapabilities.firefox();
	 dr.setBrowserName("firefox");
	 dr.setPlatform(Platform.WINDOWS);
	 RemoteWebDriver driver=new RemoteWebDriver(new 
	URL("http://localhost:4444/wd/hub"), dr);
	 driver.navigate().to("http://gmail.com");
	 driver.close();
	}

}
