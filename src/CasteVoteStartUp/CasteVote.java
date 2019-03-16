package CasteVoteStartUp;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class CasteVote {

	 public WebDriver driver; 
	   /**
	    * This sets the chrome driver
	   */
	    @BeforeSuite 
	    public void SetDriverPaths()
	    {
		  File file = new File("driver/chromedriver");
		  System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		 
	    }
	    
	 /**
	  * This method castes a vote
	 * @throws InterruptedException 
	 * @throws IOException 
	  */
	 @Test
	  public void casteVote() throws InterruptedException, IOException {
		 
	     int sleepduration = 1000;
	     int totalvotes = 1;

	     for (int i=0; i<=totalvotes; i++) {
	    	 
		Random rand = new Random();
		int n = rand.nextInt(500000); 
		
		Map prefs = new HashMap();
		prefs.put("profile.default_content_settings.cookies", 2);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);	 

		 driver.get("https://showcase.votion.co/bracket/7df05f19-3b6b-11e9-98d0-0a8ea37c1ed2");
		 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
					.withTimeout(30, TimeUnit.SECONDS) 			
					.pollingEvery(5, TimeUnit.SECONDS) 			
					.ignoring(NoSuchElementException.class);
	     
	     WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"App-header\"]")));
	     
	     //By executing a java script
	     JavascriptExecutor exe = (JavascriptExecutor) driver;
	     Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
	     System.out.println("Number of iframes on the page are " + numberOfFrames);
	     
	     //By finding all the web elements using iframe tag
	     List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
	     System.out.println("The total number of iframes are " + iframeElements.size());
	     
	     WebElement iframe = driver.findElement(By.id("iFrameResizer0"));
	     driver.switchTo().frame(iframe);
	     WebElement botKeeperVote = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Matchups-round is-visible']/form/div[@class='Matchup-content']/div[@class='Matchup-competitors']/div[@class='Matchup-competitorsMediaContainer']/div[contains(@class,'Matchup-competitor')]/div[@class='Matchup-openForSelecting Matchup-voteButtonContainer']/div[@class='SubmitButton']/div[2]/button[@class='Matchup-voteBar-voteButton'][contains(@aria-label, \"Vote for botkeeper\")]")));
	     botKeeperVote.click();
	     for (int k=0; k<=10; k++) {
		     Thread.sleep(1000);
		     }		     
	     Thread.sleep(sleepduration);
	     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	     File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	     // Now you can do whatever you need to do with it, for example copy somewhere
	     FileUtils.copyFile(scrFile, new File("screenshots/vote" + n + ".png"));
	     driver.quit();
	     System.out.println("Sleeping for 5 minutes" );
	     for (int j=0; j<=1; j++)
	     Thread.sleep(9000);
	     }	     
	  }	 
}
