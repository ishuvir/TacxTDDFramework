
package tests;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pages.ScreenLocators;

public class SmokeValidRegisteredTest extends InitialClass {
	String bluetoothReq="Yes";
	ScreenLocators ScObject =new ScreenLocators();
	String emailID="singh.ivsingh@gmail.com";
	String passWord="Singh@2302";
	String cityName="koppenberg";
	
	
	@Test
//	@Parameters({"Email","Password"})
	public void LoginValidation()  {
		try {	
	System.out.println("Running Login screen validation Test");
	Reporter.log("Running Login screen validation Test");
	Thread.sleep(5000);
	AndroidElement logo=(AndroidElement) driver.findElementById(ScObject.tacxLogo);
	Assert.assertTrue(logo.isDisplayed());  //Logo validation on Screen
	boolean logostatus=logo.isDisplayed();
	System.out.println("Tacx logo is displayed :"+logostatus);
	Reporter.log("Tacx logo is displayed :"+logostatus);
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.emailField)));
    driver.findElement(By.id(ScObject.emailField)).click();
    
    Actions act=new Actions(driver);
    act.sendKeys(emailID).perform();
    Reporter.log("User has entered EmailAddress");
    //Password Field
    driver.pressKey(new KeyEvent(AndroidKey.BACK));
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.passField)));
	driver.findElement(By.id(ScObject.passField)).click();
	Actions act2=new Actions(driver);
	act2.sendKeys(passWord).perform();
	Reporter.log("User has entered password");
	Thread.sleep(3000);
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.loginButton)));
	driver.findElement(By.id(ScObject.loginButton)).click();
    Reporter.log("User has clicked on login button");
	Thread.sleep(3000);
	
		}
		catch(Exception e) {
			System.out.println("Cause of Failure is :"+e.getCause());
			System.out.println("Failure Message is:"+e.getMessage());
			e.printStackTrace();
			Reporter.log("Test Case caught an Exception :Failed");
			}
	}	
	
@Test (dependsOnMethods = { "LoginValidation" })
public void HomePageValidation()  {
	Reporter.log("Running HomePAge validation test");
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.tutorialSkip)));
    driver.findElement(By.id(ScObject.tutorialSkip)).click();
    Reporter.log("User Skipped the tacx tutorial screen");
    //Login verification 
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.homeTacxLogo)));
    AndroidElement loginLogo=(AndroidElement) driver.findElementById(ScObject.homeTacxLogo);
	boolean Loginstatus=loginLogo.isDisplayed();
	System.out.println("User is successfully login :"+Loginstatus);
	Reporter.log("User loggedIn displayed with Tacx logo :"+Loginstatus);
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.newRideButton)));
    System.out.println("New Ride tab is Displayed on Home Screen");
    Reporter.log("New Ride tab is Displayed on Home Screen");
}
	
@Test (dependsOnMethods = { "HomePageValidation" })
public void NewRideValidation()  {
	Reporter.log("New Ride Section validation Test");
	try {
    driver.findElement(By.xpath(ScObject.newRideButton)).click();
    Reporter.log("User selected New Ride ");
    Thread.sleep(3000);
    //Clicking on search microscope bar
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className(ScObject.SearchBar)));
    Reporter.log("Tacx New Ride screen is displayed with search bar");
    driver.findElement(By.className(ScObject.SearchBar)).click();
    Reporter.log("User clicked on Search bar to search for new location");
		
} catch(Exception exp1) {
	System.out.println("Cause of Failure is :"+exp1.getCause());
	System.out.println("Failure Message is:"+exp1.getMessage());
	exp1.printStackTrace();
	Reporter.log("Test Case caught an Exception :Failed");
	
}
	
	
}
@Test (dependsOnMethods = { "NewRideValidation" })
// @Parameters("CityName")
public void LocationSearchValidation()  {
	Reporter.log("Search Result validation Test");
	driver.findElement(By.id(ScObject.locSearch)).click();
    Actions act3=new Actions(driver);
    act3.sendKeys(cityName).perform();
    Reporter.log("User searched with city name as :"+cityName);
    //Close the keyboard
    driver.pressKey(new KeyEvent(AndroidKey.BACK));
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.firstsearchResult)));
    Reporter.log("Search result displayed with selected location");
    driver.findElement(By.id(ScObject.firstsearchResult)).click();
    Reporter.log("User selected the search result");
}

@Test (dependsOnMethods = { "LocationSearchValidation" })
public void NewTripValidation()  {
	Reporter.log("New Trip validation Test");
	try {
	 //start the trip
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.tripStartbutton)));
    Reporter.log("Start button is displayed");
    driver.findElement(By.xpath(ScObject.tripStartbutton)).click();
    Reporter.log("User Selected start button :Trip Started"); 
    System.out.println("Bluetooth is not Enabled");
    Reporter.log("Enabling bluetooth");
    if(bluetoothReq.equalsIgnoreCase("Yes")) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.bluetoothB)));
    	driver.findElement(By.id(ScObject.bluetoothB)).click();
    }
    else {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.bluetoothSkip)));
    	driver.findElement(By.id(ScObject.bluetoothSkip)).click();
    	
    }
    Reporter.log("Bluetooth is Enabled now");
    System.out.println("Bluetooth is enabled now");
    Thread.sleep(4000);
    //location permission
 AndroidElement locationPerB=(AndroidElement) driver.findElement(By.xpath(ScObject.locationB));
    if(locationPerB.isDisplayed()){
    	Reporter.log("Permission required for location");
    	locationPerB.click();
    	Reporter.log("Permission granted for Location");
   }
    Thread.sleep(3000);
    Alert alert = driver.switchTo().alert();
    alert.accept();
    Reporter.log("Permission granted for Location:Location Enabled");
    }
	catch(Exception e2){
	System.out.println("Cause of Failure is :"+e2.getCause());
	System.out.println("Failure Message is:"+e2.getMessage());
	e2.printStackTrace();
	Reporter.log("Test Case caught an Exception :Failed");
}
}

@Test (dependsOnMethods = { "NewTripValidation" })
public void DemoTripValidation()  {
	Reporter.log("Demo Trip validation Test");
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.demolabel)));
	driver.findElement(By.id(ScObject.demolabel)).click();
	Reporter.log("Demo Started by User");
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.trainingB)));
	Reporter.log("Training Screen is displayed");
    driver.findElement(By.xpath(ScObject.trainingB)).click();
    Reporter.log("Training started :Awaiting new trip..");
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.tripStartB)));
    Reporter.log("Start now option is displayed to user in trip");
    driver.findElement(By.xpath(ScObject.tripStartB)).click();
    Reporter.log("Trip Started by User");
}

@Test (dependsOnMethods = { "DemoTripValidation" })
public void TacxFeatureValidation()  {
	Reporter.log("Tacx Feature validation Test");
	try {
		Thread.sleep(5000);
		   List<AndroidElement> list=driver.findElements(By.id(ScObject.tacxIndicator));
		   int listcount=list.size();
		   for(int i=0;i<listcount;i++) {
			   if(i==0) {
			   Reporter.log("Power in Watt is :"+list.get(i).getAttribute("text"));
			   System.out.println("Power in Watt is :"+list.get(i).getAttribute("text"));
		   }
			   else if(i==1) {
				   Reporter.log("Percentage value is :"+list.get(i).getAttribute("text"));
				   System.out.println("Percentage value is :"+list.get(i).getAttribute("text"));
			   }
			   else if(i==2) {
				   Reporter.log("Current Speed in KM/H is :"+list.get(i).getAttribute("text"));
				   System.out.println("Current Speed in KM/H is :"+list.get(i).getAttribute("text"));
			   }
			   else if(i==3) {
				   Reporter.log("RPM  value is :"+list.get(i).getAttribute("text"));
				   System.out.println("RPM  value is :"+list.get(i).getAttribute("text"));
			   }
			   else if(i==4) {
				   Reporter.log("BPM value is :"+list.get(i).getAttribute("text"));
				   System.out.println("BPM value is :"+list.get(i).getAttribute("text"));
			   }
			   else {
				   Reporter.log("Calorie in KCAL is :"+list.get(i).getAttribute("text"));
				   System.out.println("Calorie in KCAL is :"+list.get(i).getAttribute("text"));   
			   }
		   }
		   
		  Reporter.log("Tacx trip screen is displayed is validated succesfully"); 
		  new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.menuIndicator)));
		  driver.findElement(By.id(ScObject.menuIndicator)).click();
		  Reporter.log("Tacx Training menu is selected by User");
		  AndroidElement progressBar=(AndroidElement) driver.findElement(By.id(ScObject.progressBar));	  
		   Assert.assertTrue(progressBar.isDisplayed());
		   System.out.println("Distance progress bar is displayed");
		   Reporter.log("Distance progress bar is displayed");
		   
	}
	catch(Exception exc){
		System.out.println("Cause of Failure is :"+exc.getCause());
		System.out.println("Failure Message is:"+exc.getMessage());
		exc.printStackTrace();
		Reporter.log("Test Case caught an Exception :Failed");
	}
	
}

@Test (dependsOnMethods = { "TacxFeatureValidation" })
public void RideStopvalidation()  {
	Reporter.log("Tacx Ride stop validation Test");
	try {
		Thread.sleep(2000);
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.stoptrip)));
		Reporter.log("Stop button is dispalyed in Menu");
		driver.findElement(By.xpath(ScObject.stoptrip)).click();
		Reporter.log("Reporter Selected the Stop trip option");
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.stopPopup)));
		driver.findElement(By.xpath(ScObject.stopPopup)).click();
		}
	catch(Exception exp){
			System.out.println("Cause of Failure is :"+exp.getCause());
			System.out.println("Failure Message is:"+exp.getMessage());
			exp.printStackTrace();
			Assert.fail();
			Reporter.log("Test Case caught an Exception :Failed");
		}
	}

  @Test (dependsOnMethods = { "RideStopvalidation" })
    public void TacxLogoutFeatureTest()  {
	Reporter.log("Tacx Logout Feature validation Test");
	try {
    driver.pressKey(new KeyEvent(AndroidKey.BACK));
    Thread.sleep(3000);
    driver.findElement(By.id(ScObject.locSearch)).clear();
    Thread.sleep(2000);
    driver.findElement(By.id(ScObject.SearchCross)).click();
     new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.SettingIcon)));
    driver.findElement(By.xpath(ScObject.SettingIcon)).click();
    Reporter.log("User selected the setting ICON :ICON displayed");
    System.out.println("User selected the setting ICON :ICON displayed");
    Reporter.log("Correct user account name is displayed");
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.logOutButton)));
    driver.findElement(By.xpath(ScObject.logOutButton)).click();
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.LogoutPopup)));
    driver.findElement(By.xpath(ScObject.LogoutPopup)).click();
    Reporter.log("User is successfully logged from account");
    Thread.sleep(3000);
    AndroidElement logo=(AndroidElement) driver.findElementById(ScObject.tacxLogo);
	Assert.assertTrue(logo.isDisplayed());  
	boolean logostatus=logo.isDisplayed();
	System.out.println("Tacx logo is displayed :"+logostatus);
	Reporter.log("Tacx logo is displayed :"+logostatus);
	}
	catch(Exception ex) {
		System.out.println("Cause of Failure is :"+ex.getCause());
		System.out.println("Failure Message is:"+ex.getMessage());
		ex.printStackTrace();
		Assert.fail();
		Reporter.log("Test Case caught an Exception :Failed");
}
	
}
}