package MavenDemo.MavenFramework;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
public class VideoExample {

	   private static ScreenRecorder screenRecorder;
	   
	   public static void main(String[] args) throws IOException, AWTException {
	      GraphicsConfiguration gconfig = GraphicsEnvironment
	         .getLocalGraphicsEnvironment()
	         .getDefaultScreenDevice()
	         .getDefaultConfiguration();
	      
	      screenRecorder = new ScreenRecorder(gconfig,
	         new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	         new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
	            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	            DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
	            QualityKey, 1.0f,
	            KeyFrameIntervalKey, (int) (15 * 60)),
	         new Format(MediaTypeKey, MediaType.VIDEO,
	            EncodingKey,"black", FrameRateKey, Rational.valueOf(30)), null);
	      
	      System.setProperty("webdriver.chrome.driver", "C:\\Users\\vikas.m\\Downloads\\chromedriver_win32\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	      
	      // Start Capturing the Video
	      screenRecorder.start();
	   
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	          driver.get("https://petstore.octoperf.com/actions/Account.action?signonForm=");
	          driver.findElement(By.name("username")).sendKeys("j2ee");
	          driver.findElement(By.name("password")).clear();
	          driver.findElement(By.name("password")).sendKeys("j2ee");
	          //    Thread.sleep(10000);
	          driver.findElement(By.name("signon")).click();

	      File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(screenshot, new File("C:\\screenshots\\screenshots1.jpg"));
	      
	      driver.close();
	      
	      // Stop the ScreenRecorder
	      screenRecorder.stop();
	   }
	}
