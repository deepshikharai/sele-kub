package demojar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SimpleKubSeleniumGridTest {

	WebDriver driver;
	String url="https://www.google.com/";
	Properties pro;
	@Before
	public void setUp() throws Exception {
		try{
			/*File src= new File("Property.properties");
			FileInputStream fis = new FileInputStream(src);*/
			pro=new Properties();
			pro.load(SimpleKubSeleniumGridTest.class.getResourceAsStream("/Property.properties"));
			//pro.load(fis);
		}catch(Exception e){
			System.out.println("Exception is=="+e.getMessage());
		}
		DesiredCapabilities dcp = new DesiredCapabilities();
		dcp.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
		dcp.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
		dcp.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
		dcp.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
		dcp.setCapability("name", "KubernetesGridTest");
		dcp.setCapability("idleTimeout", 150);
		//driver = new RemoteWebDriver(new URL("http://35.193.7.170:4444/wd/hub"),dcp);
		//System.out.println(pro.getProperty("selenium.url"));
		driver = new RemoteWebDriver(new URL("http://35.185.83.18:4444/wd/hub"),dcp);
		//System.setProperty("webdriver.chrome.driver","D:\\driver\\chromedriver_win32\\chromedriver.exe");
		/*System.setProperty("webdriver.chrome.driver",pro.getProperty("chrome.driver").toString());
		//Open browser instance
		driver = new ChromeDriver();*/
		driver.get(url);
		System.out.println("Opening Google");
		Thread.sleep(100);
	}

	@Test
	public void test() throws Exception {
		System.out.println("Page Title:"+driver.getTitle());
		//driver.findElement(By.id("lst-ib")).sendKeys("Kubernetes");
		driver.findElement(By.id(pro.getProperty("searchbox"))).sendKeys(pro.getProperty("searchword"));
		System.out.println("Data entered to search");
		Thread.sleep(100);
		//driver.findElement(By.name("btnK")).click();
		//driver.findElement(By.id("lst-b")).sendKeys(Keys.ENTER);
		driver.findElement(By.id(pro.getProperty("searchbox"))).sendKeys(Keys.ENTER);
		System.out.println("search clicked");
		Thread.sleep(100);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
