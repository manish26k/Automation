package com.flipkart.testcases;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.flipkart.base.flipkart_createdriver;
import com.flipkart.pages.flipkart_Cart;
import com.flipkart.pages.flipkart_Search;
import com.flipkart.pages.flipkart_assertions;
import com.flipkart.pages.flipkart_homepage;
import com.flipkart.pages.flipkart_login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automationtesting.excelreport.Xl;

public class flipkart_TestCases 

{
	public ChromeDriver driver;
	public ResourceBundle rb;
	public WebDriverWait wait;
	
	boolean result;
	SoftAssert softAssert= new SoftAssert();
	
	public ResourceBundle bundle;
	
	
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	public static Logger log=LogManager.getLogger(flipkart_TestCases.class.getName());
	
	@BeforeClass
	public void openBrowser()
	{
		
		extentReport= new ExtentReports("./Report/report.html", true);
		extentReport.addSystemInfo("Name", "Automation");
		
		rb= ResourceBundle.getBundle("Config");
		log.info("Config properties files gets uploaded");
		//System.setProperty("webdriver.chrome.driver","C:/Users/dell/Desktop/Selenium/software/chromedriver/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		log.info("Chrome driver gets intialized");
		
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(rb.getString("URL"));
		log.info("Navigate to the Flipkart ecommerce website");
		driver.manage().window().maximize();
		wait=new WebDriverWait(driver,5);
		//driver.manage().deleteAllCookies();
		
	}
	
	
	@Test(priority=0, description="Flipkart Login Test Case")
	public void flipkart_Login()
	{
		log.info("Flipkart login");
		
		extentTest=extentReport.startTest("flipkart_Login");

		flipkart_login FL= new flipkart_login(driver);
        FL.flipkart_login();
        log.info("user gets logined into the flipkart site");
        
        extentTest.log(LogStatus.PASS, "flipkart_login Passed");
        extentReport.endTest(extentTest);
        
	}
	
	
	
	@Test(priority=2,description="Flipkart Search Test Case")
	public void flipkart_Search() throws InterruptedException
	{
		driver.get(rb.getString("URL"));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(bundle.getString("Searchtextboxcss"))));
		
		extentTest=extentReport.startTest("flipkart_Search");
		flipkart_Search FS= new flipkart_Search(driver);
        //FS.flipkart_Search_prod("Mobile");
		
		FS.searchTooltoptext("searchToolTipcss");
        FS.selectOptionWithText("mobile watch");
		
	
		/*for(int k=0;k<2;k++)
		{
			try
			{
				FS.searchTooltoptext("searchToolTipcss");
		        FS.selectOptionWithText("mobile watch");	
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}*/
        
	}
	
	/*@Test(dependsOnMethods = {"flipkart_Login"},alwaysRun=true, description="Flipkart HomePage Test Case")
	public void flipkart_homepage () throws IOException, InterruptedException
	{
		extentTest=extentReport.startTest("flipkart_homepage");
		
		
		result=flipkart_assertions.Validatepagetitle(driver, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More");
	    softAssert.assertTrue(result, "[message- HomePage title didn't get matched]");
	    
	    extentTest.log(LogStatus.PASS, "Page title matched");
		
		
		flipkart_homepage FH= new flipkart_homepage(driver);
		
		
		FH.flipkart_headerMenu();
		
		//here validating that feature brand heading text is displayed or not
        result=flipkart_assertions.ValidateFeaturebrandtext(driver, "xpath", "//div[@class='_1OuFHs']//h2");
        softAssert.assertTrue(result, "[message- feature Brand heading title text is not displayed]");
				
		FH.flipkart_FeatureBrands();
		
		
		driver.get(rb.getString("URL"));
		
		
		//here validating weather the random2prod is displayed or not.
        result=flipkart_assertions.ValidateRandom2ndprod(driver, "xpath","(//div[@class='_1GRhLX _3JslKL required-tracking'][3]//div[2]//a)[2]");
        softAssert.assertTrue(result, "[message- Random2prod is not present]");
		
		FH.flipkart_RandomOffers();
		
		
		softAssert.assertAll();
		
		extentReport.endTest(extentTest);
	}*/
	
	@Test(priority=3, description="Flipkart Cart Page Test Case" )
	public void flipkart_cartPage() throws InterruptedException, IOException
	{
		driver.get(rb.getString("URL"));
        flipkart_Cart FC= new flipkart_Cart(driver);
        FC.flipkart_cartQunatity();
        FC.fullcartProductname();
        
        
        //here validating the product is added in the cart by getting the text fron the cart.
        result=flipkart_assertions.ValidatePRODaddedinCART(driver, "xpath","(//a[@class='_325-ji _3ROAwx'])[1]");
        softAssert.assertTrue(result, "[message- Product is not added into the Cart]");
        
        softAssert.assertAll();
	}
	
	@AfterClass
	public void quitDriver()
	{
		extentReport.flush();
		driver.quit();
	}
	
	@AfterSuite
	public void generateReport() throws Exception
	{
		Xl.generateReport("excel-report.xlsx");
	}
	
	
	

}
