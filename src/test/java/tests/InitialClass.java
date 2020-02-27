package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class InitialClass {

//	AppiumDriver<MobileElement> driver;
	AndroidDriver<AndroidElement> driver;
	String mobileType="Redmi";
	DesiredCapabilities dc=new DesiredCapabilities();
	
	@BeforeClass
	public void setup() {
		try  {
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		if(mobileType.equalsIgnoreCase("Samsung")) {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0"); 
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy A70");
		   	dc.setCapability(MobileCapabilityType.UDID, "RZ8M50SS8KB");
		}
		else {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1"); 
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 3");
	     	dc.setCapability(MobileCapabilityType.UDID, "d22eb408");
		}
		//Other Capabilities
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		dc.setCapability(MobileCapabilityType.EVENT_TIMINGS, "true");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability("uiautomator2ServerInstallTimeout", 50000);
		//Tacx Application capabilities
		dc.setCapability("appPackage","tacx.android");
       dc.setCapability("appActivity",".ui.startup.SplashActivity");
 //     dc.setCapability(MobileCapabilityType.APP,"C:\\Users\\Ishuvir singh\\eclipse-workspace\\TacxSmokeAutomation\\src\\test\\resources\\apps\\Tacx Training_v4.5.0_apkpure.com.apk");
        
       driverSetup();
		}
		catch(Exception exp) {
			System.out.println("Reason is :"+exp.getCause());
			System.out.println("Message is :"+exp.getMessage());
			exp.printStackTrace();
		}
      
	}
	
	public void driverSetup() {
		try {
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		 //       driver=new AppiumDriver<MobileElement>(url,dc);
		        driver=new AndroidDriver<AndroidElement>(url,dc);
	}
	catch(Exception excx) {
   System.out.println(excx.getMessage());
   System.out.println(excx.getCause());
   excx.printStackTrace();
}  }
	
	
	@AfterClass
	public void teardown() {
		driver.quit();
		
	}
	
}
