package com.ibm.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ibm.pages.AdminPage;
import com.ibm.utilities.PropertiesFileHandler;

public class Login extends BaseTest{
	
	@FindBy(name="email")
	WebElement emailEle;

	@FindBy(name="pword")
	WebElement passEle;

	@FindBy(xpath="/html/body/div/div/div/div[2]/form/button")
	WebElement loginEle;
	WebDriverWait wait;
	WebDriver driver;
	
	public Login(WebDriver driver,WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}

	public void enterEmailAddress(String user)
	{
		emailEle.sendKeys(user);
	}
	public void enterPassword(String password)
	{
		passEle.sendKeys(password);
	}
	public void clickOnLogin()
	{
		loginEle.click();
	}

}
