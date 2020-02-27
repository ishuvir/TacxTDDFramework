package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidElement;
import pages.ScreenLocators;


public class RegisterationValidationTest extends InitialClass {
	ScreenLocators ScObject =new ScreenLocators(); 
	String Username="IshuvirE";
	String EmailID="reweereqW@mvon.com";
	String PassWord="Singh@2302";
	String TCLegalMessage="We take your privacy seriously. Our privacy policy is available for your review";
	
	@Test
	public void CreateAccountTest()  {
		try {
			
	System.out.println("Creating new User Account Test");
	Reporter.log("Creating new User Account Test");
	Thread.sleep(5000);
	AndroidElement logo=(AndroidElement) driver.findElementById(ScObject.tacxLogo);
	Assert.assertTrue(logo.isDisplayed());  //Logo validation on Screen
	boolean logostatus=logo.isDisplayed();
	System.out.println("Tacx logo is displayed :"+logostatus);
	Reporter.log("Tacx logo is displayed :"+logostatus);
	new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.NewAccountB)));
    driver.findElement(By.xpath(ScObject.NewAccountB)).click();
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.NewNameField)));
    driver.findElement(By.id(ScObject.NewNameField)).click();
    Actions act=new Actions(driver);
    act.sendKeys(Username).perform();
    Reporter.log("User has entered Name");
    
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.NewEmailField)));
    driver.findElement(By.id(ScObject.NewEmailField)).click();
    Actions act2=new Actions(driver);
    act2.sendKeys(EmailID).perform();
    Reporter.log("User has entered EmailAddress");
    
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.NewPassField)));
    driver.findElement(By.id(ScObject.NewPassField)).click();
    Actions act3=new Actions(driver);
    act3.sendKeys(PassWord).perform();
    Reporter.log("User has entered Password");
    
    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.RegisterField)));
    driver.findElement(By.id(ScObject.RegisterField)).click();
    Reporter.log("User Opted for Registeration");
    Thread.sleep(5000);
    
    WebElement ele=driver.findElement(By.id(ScObject.TCMessage));
    String tcMessage=ele.getText();
    System.out.println("Legal T&C Message is:"+tcMessage);
    Reporter.log("Legal T&C Message is:"+tcMessage);
    Assert.assertEquals(tcMessage, TCLegalMessage);
   
    
    List<AndroidElement> checkB=driver.findElements(By.xpath(ScObject.TCCheckbox));
    for(int i=0;i<checkB.size();i++) {
    	checkB.get(i).click();
    	Reporter.log("Terms & conditon ,Legal box accepted");
    }
    
   if(driver.findElement(By.xpath(ScObject.ProceedB)).isEnabled()) {
	   Thread.sleep(2000);
    driver.findElement(By.xpath(ScObject.ProceedB)).click();
    Reporter.log("Proceed button is selected ");
    
    } 
   else {
	   Reporter.log("Proceed button is not enabled:Issue with legal box");
	   System.out.println("Proceed button is not enabled:Issue with legal box");
   }
		}
		catch(Exception e) {
			System.out.println("Cause of Failure is :"+e.getCause());
			System.out.println("Failure Message is:"+e.getMessage());
			e.printStackTrace();
			Assert.fail();
			Reporter.log("Test Case caught an Exception :Failed");
			}
}
	
	@Test (dependsOnMethods = { "CreateAccountTest" })
	public void NewUserValidation()  {
		Reporter.log("New User homepage validation test");
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
	
}
