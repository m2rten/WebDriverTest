package userBeforeLogin;		
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;	
import java.lang.reflect.Method;
public class LoginSuccessfull {		
	   private WebDriver driver;	
	   private String testname;
	
		@Test
		public void testLoginToClientWeb()
		{
	    	By loginLocator = By.className("login-item");
	        By usernameLocator = By.name("username");
	        By passwordLocator = By.id("password");
	        By loginFormLocator =  By.xpath("//*[@id='login-form']/div[2]/a");
	        By messageLocator =  By.className("alert-box-content");
	        
	    	
	        String baseUrl = "http://tallinn.dev.ridango.com/";
	        String expectedTitle = "Tallinn";
	        String actualTitle = "";
	        String username="marten@ridango.com";
	        String password="test2";
	        // launch Fire fox and direct it to the Base URL
	        driver.get(baseUrl);
	        driver.findElement(loginLocator).click();
	        // get the actual value of the title
	        // Username field
	        waitElementVisibleOnPage(driver, usernameLocator ,1,"Element Username not visible");
	        driver.findElement(usernameLocator).sendKeys(username);

	        actualTitle = driver.getTitle();
	        /*
	         * compare the actual title of the page with the expected one and print
	         * the result as "Passed" or "Failed"
	         */

	        // Password field
	        driver.findElement(passwordLocator).sendKeys(password);

	        // Login
	        
	        driver.findElement(loginFormLocator).submit();
	        /* Check that the user authentication works correctly,
	        the message "Login successful" have to be displayed */
	        String messageLogin = driver.findElement(messageLocator).getText();
	        messageLogin = messageLogin.trim(); // Remove the blank space before and after the string message

	        Assert.assertEquals(messageLogin, "Sisselogimine õnnestus");
	        
	        driver.close();
		}
	    public static void waitElementVisibleOnPage(WebDriver driver,By elementLocator,int timer,String messageFault){
	        boolean elementNotFound = false;
	        WebDriverWait waitElement = new WebDriverWait(driver, timer);
	        try{
	        WebElement element = waitElement.until(
	                ExpectedConditions.visibilityOfElementLocated(elementLocator));
	        }catch(Exception e){
	            elementNotFound = true;
	            Assert.assertFalse(elementNotFound,messageFault+" "+ elementLocator);
	        }
	    }

		@BeforeTest
		public void beforeTest(final ITestContext testContext) {	
		  //  driver = new FirefoxDriver();  
			System.out.println(testContext.getName());
			
	    	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	    	ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
		    
		}		
		@AfterMethod
		public void tearDown(ITestResult result, Method method) {
				testname = method.getName();
			   if (result.getStatus() == ITestResult.FAILURE) {
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					// Now you can do whatever you need to do with it, for example copy somewhere
					try {
						FileUtils.copyFile(scrFile, new File("target\\sh\\"+testname+".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				      //your screenshooting code goes here
				   }       
		}
		@AfterTest
		public void afterTest() {

			driver.quit();			
		}		
}	