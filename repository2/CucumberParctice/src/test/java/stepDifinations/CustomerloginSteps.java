package stepDifinations;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CustomerLoginPage;

public class CustomerloginSteps {

	public static WebDriver driver;
	public CustomerLoginPage lp;

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser()
	{
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	lp= new CustomerLoginPage(driver);
	}
	@When("User Opens URL {string}")
	public void user_Opens_URL(String url)
	{
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) 
	{
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() 
	{
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) 
	{
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
			driver.close();
			Assert.assertTrue(false);
		}else 
		{
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Log out Link")
	public void user_click_on_Log_out_Link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(2000);
	}

	@Then("Close browser")
	public void close_browser()
	{
		driver.close();
	
}
	
	
}
