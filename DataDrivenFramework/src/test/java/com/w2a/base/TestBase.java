package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	public static WebDriver driver;
    public static Properties config = new Properties();
	public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log= Logger.getLogger("devpinoyLogger");
    
	@BeforeSuite	
public void setUp(){
	if(driver==null){
		
		try {
			fis = new FileInputStream("C:\\Users\\Gangadhar\\workspace\\DataDrivenFramework\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("config file loaded!!!!!!!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fis = new FileInputStream("C:\\Users\\Gangadhar\\workspace\\DataDrivenFramework\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(config.getProperty("browser").equals("firefox")){
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");	
			driver=new FirefoxDriver();
		}
		else if(config.getProperty("browser").equals("chrome")){
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\Gangadhar\\workspace\\DataDrivenFramework\\src\\test\\resources\\executables\\geckodriver.exe");
			
			driver = new ChromeDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
	}
}

@AfterSuite
public void tearDown(){
	if(driver!=null){ 
	}
driver.quit();	
}
}
