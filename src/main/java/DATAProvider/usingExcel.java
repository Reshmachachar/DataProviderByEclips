package DATAProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class usingExcel {
WebDriver driver;
	
	@BeforeMethod
	public void setuUp()
	{
		WebDriverManager.chromedriver().setup();
    	driver=new ChromeDriver();
 
    	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    	driver.manage().deleteAllCookies();
    	driver.manage().window().maximize();
    	driver.get("https://ui.freecrm.com/");
    	
	}
	@DataProvider
	public Object[][] getLoginData() throws EncryptedDocumentException, IOException
	{
	Object data[] [] =	TestUtil.getTestData("login");
	return data;
	}
	@Test(dataProvider="getLoginData")
	public void loginTest(String username,String password)
	{
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	driver.findElement(By.xpath("//*[text()='Login']")).click();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
