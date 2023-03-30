package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base 
{
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String BrowserName)
	{
		//System.out.println("Web init method called");
		//System.out.println("propData-"+prop.getProperty("headless"));
		if(BrowserName.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","D:\\Jar Files\\chromedriver.exe");
			/*
			if(prop.getProperty("headless").equals("Yes"))
			 {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver();
			  }
			else
			   {
					System.out.println("Web init method called");
					driver = new ChromeDriver();
					//driver.get("https://app.hubspot.com/login");
				}
			*/
			System.out.println("Web init method called");
			driver = new ChromeDriver();
			//driver.get("https://app.hubspot.com/login");
			
		}
		return driver;
		
	}
	public Properties init_properties()
	{
		
		try 
		{
			FileInputStream ip = new FileInputStream("C:\\Users\\maruti.bandgar\\eclipse-workspace\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
			try 
			{
				System.out.println("prop called");
				prop.load(ip);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 } 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return prop;
	}
	
//	public static void main(String[] args)
//	{
//		Base b = new Base();
//		b.init_driver("Chrome");
//	}

}
