package loginmodule;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utility.ReadConfigProperty;

public class Base  {
	
	WebDriver driver;
	public static ExtentReports eReports;
	public ExtentTest startTest;
	
	@BeforeSuite
	void initReport(){
		eReports=new ExtentReports("C:\\Shiv\\SeleniumWorld\\eReport\\proj_" + getDateTimeStamp() + ".html",false);
	}
	
//	launch app
	@BeforeMethod
	public void launchurl() throws Exception{
		ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	 driver = new FirefoxDriver();
	 driver.get(readConfigProperty.getURLink());
	 driver.manage().window().maximize();
		
	}
	
// close app
	@AfterMethod
	public void close(){
		eReports.endTest(startTest);
		eReports.flush();
		driver.quit();
	}
	
//	getDateTimeStamp
	String getDateTimeStamp(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String customDate=simpleDateFormat.format(date);
		return customDate;
	}
//
//	get the screen shot
	public String getScreen() throws IOException{
		
		TakesScreenshot screen=(TakesScreenshot)driver;
		File screenshotAs = screen.getScreenshotAs(OutputType.FILE);
		String path="E:\\FebReports\\snapshot_" + getDateTimeStamp() + ".png";
		FileUtils.copyFile(screenshotAs, new File(path));
		return path;
	}
	
//	get Element Screenshot
	public String getElementScreen(WebElement ele) throws Exception{

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
		    eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		String path="C:\\Shiv\\SeleniumWorld\\screenShots\\snapshot_" + getDateTimeStamp() + ".png";
		// Copy the element screenshot to disk
		FileUtils.copyFile(screenshot, new File(path));
		
		return path;
		
		
		
	}

}
