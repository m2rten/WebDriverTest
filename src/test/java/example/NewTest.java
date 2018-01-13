package example;		

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.Assert;		
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;		
public class NewTest {		
	   private WebDriver driver;	
	    

		
		@Test				
		public void testEasy() {	
			driver.get("http://tallinn.dev.ridango.com/");  
	        try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String title = driver.getTitle();	
			System.out.println(title);
			Assert.assertTrue(title.contains("Tallinn")); 		
		}	
		@BeforeTest
		public void beforeTest() {	
		  //  driver = new FirefoxDriver();  
	    	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	    	ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
		    
		}		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		
}	