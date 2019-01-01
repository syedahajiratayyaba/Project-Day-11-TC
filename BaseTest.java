package com.ibm.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.UserPage;
import com.ibm.utilities.DBUtil;
import com.ibm.utilities.ExcelReader;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

public class BaseTest extends ExcelReader{
	WebDriverWait wait;
	WebDriver driver;	 
    @Test()
    
    public void testcase11() throws InterruptedException, IOException, SQLException{
    	
    	FileInputStream file = new FileInputStream("./TestData/data.properties");
    	Properties prop = new Properties();
    	prop.load(file);
    	String url = prop.getProperty("url");
    	String username = prop.getProperty("user");
    	String password = prop.getProperty("password");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Login login = new Login(driver, wait);
		driver.get(url);
		
		login.enterEmailAddress(username);
		login.enterPassword(password);
		login.clickOnLogin();
		
		WebElement catalogEle=driver.findElement(By.linkText("Catalog"));
		catalogEle.click();
		
		WebElement bannersEle=driver.findElement(By.linkText("Banners"));
		bannersEle.click();
		
		int expCount= DBUtil.countQuery("SELECT count(name) from as_banner");
		System.out.println("Count beofre adding banner : "+expCount);
		Thread.sleep(3000);
		
		WebElement newBannerEle=driver.findElement(By.cssSelector("i.fa.fa-plus"));
		newBannerEle.click();
		
		WebElement bannerName=driver.findElement(By.name("name"));
		bannerName.sendKeys("Test_Banner_Test");
		
		WebElement codeEle=driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/form/div[2]/div/div/div[2]/div[4]/span"));
		codeEle.click();
		
		String winHandleBefore = driver.getWindowHandle();
		
		WebElement frame=driver.switchTo().activeElement();
		
		frame.sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
		//codeEle.sendKeys("C:/Users/Public/Pictures/Sample Pictures/Chrysanthemum.jpg");
		Thread.sleep(3000);
		driver.switchTo().window(winHandleBefore);
		
		WebElement saveEle=driver.findElement(By.cssSelector("i.fa.fa-save"));
		saveEle.click();
		Thread.sleep(3000);
		
		String exp="Test_Banner_Test";
		String act= DBUtil.singleDataQuery("SELECT name from as_banner where name=\"Test_Banner_Test\"");
		
		//validation of banner header
		Assert.assertEquals(act,exp);
		Reporter.log("Assertion on banner added present in database");
		
		//validation in database
		int actCount=DBUtil.countQuery("SELECT count(name) from as_banner");
		System.out.println("COunt after adding banner"+actCount);
		
		Assert.assertEquals(actCount,expCount+1);
		Reporter.log("Assertion on banner added present in database");
		
		Thread.sleep(3000);
    }

}