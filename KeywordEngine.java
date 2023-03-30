package com.qa.hs.keyword.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.qa.hs.keyword.base.Base;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public WebElement element=null;
	
	public Base base;
	
	
	public final String SCENARIO_SheetPath="C:\\Users\\maruti.bandgar\\eclipse-workspace\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\ManualScenarioTest.xls";
	
	public void startExecution(String sheetName)
	{
		
		FileInputStream file = null;
		String locatorName=null;
		/*
		try {
			file = new FileInputStream(SCENARIO_SheetPath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e)
		{
			
			e.printStackTrace();
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}
		*/
		try
		{
			file= new FileInputStream(new File(SCENARIO_SheetPath));
		} 
		catch (FileNotFoundException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(file);
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HSSFSheet sheet = workbook.getSheet(sheetName);
		//System.out.println("gth- "+sheet);
		
		System.out.println("RowCount--"+sheet.getLastRowNum());
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			try
			{
				String locatorValue=sheet.getRow(i+1).getCell(k+1).toString().trim();
				
				if(!locatorValue.equals("NA"))
				{
					locatorName= locatorValue.split("=")[0].trim();
				}
				
				String actionName =sheet.getRow(i+1).getCell(k+2).toString().trim();
			
				String testData =sheet.getRow(i+1).getCell(k+3).toString().trim();
				String locatorData =sheet.getRow(i+1).getCell(k+3).toString().trim();
				
				System.out.println("locatorValue- "+locatorValue +" actionName- "+actionName + " testData "+testData);
			switch (actionName)
			 {
				case "OpenBrowser":
					System.out.println("case OpenBrowser");
					base = new Base();
					//System.out.println("*******");
					driver = base.init_driver(testData);
					driver.get(testData);
					//prop=base.init_properties();
					//System.out.println("prop-"+prop);
					
					
					if(testData.isEmpty()||testData.equals("Chrome"))
					{
						System.out.println("@@@@*");
						driver = base.init_driver(prop.getProperty("browser"));
					}
					else
					{
						System.out.println("*******");
						driver = base.init_driver(testData);
						driver.get(testData);
					}
					
					break;
				case "EnterURL":
					System.out.println("Case EnterURL");
					if(testData.isEmpty()||testData.equals("NA"))
					{
						driver.get(prop.getProperty("url"));
					}
					else
					{
						driver.get(testData);
					}
					
					break;
				case "Quit":
					driver.quit();
					break;
				
				default:
					break;
			 }
			switch (locatorName)
			{
			
			case "id":
				System.out.println("we are in id block");
				
					element = driver.findElement(By.id(locatorValue));
					element.sendKeys(testData);
					File pic =element.getScreenshotAs(OutputType.FILE);
					System.out.println("pic-"+pic);
					
					String path1 = "C:\\Users\\maruti.bandgar\\eclipse-workspace\\KeyWordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\capturedImages\\executionImages\\img1.png";
					FileHandler.copy(pic, new File(path1));
					System.out.println("Element-->"+element);
					if(actionName.equalsIgnoreCase("SendKeys"))
					{
						element.clear();
						element.sendKeys(testData);
					}
					else if (actionName.equalsIgnoreCase("Click")) {
						element.click();
						
					}
					locatorName=null;
					break;
			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				element.click();
			default:
				break;
			}
			
			}
			catch(Exception e)
			{
				
			}
				
		}
		
	}
}
