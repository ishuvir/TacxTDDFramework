package tests;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class InitialEmulatorClass {
//	AppiumDriver<MobileElement> driver;
	AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void setup() {
		
		try  {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
 //   	dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
     	dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
 		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0"); 
  //   	dc.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
   //  	dc.setCapability(MobileCapabilityType.VERSION, 10);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	//	dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
//		dc.setCapability(MobileCapabilityType.EVENT_TIMINGS, "true");
//		dc.setCapability("uiautomator2ServerInstallTimeout", 50000);
	//	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "tacx.android");
//		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "tacx.android.core.MainActionBarActivity");
	//	dc.setCapability("appPackage","tacx.android");
  //     dc.setCapability("appActivity",".ui.startup.SplashActivity");
      dc.setCapability(MobileCapabilityType.APP,"C:\\Users\\Ishuvir singh\\eclipse-workspace\\TacxSmokeAutomation\\src\\test\\resources\\apps\\Tacx Training_v4.5.0_apkpure.com.apk");
  
        URL url=new URL("http://127.0.0.1:4723/wd/hub");
 //       driver=new AppiumDriver<MobileElement>(url,dc);
        driver=new AndroidDriver<AndroidElement>(url,dc);
		}
		catch(Exception exp) {
			System.out.println("Reason is :"+exp.getCause());
			System.out.println("Message is :"+exp.getMessage());
			exp.printStackTrace();
		}
      
	}
	

	
	@AfterTest
	public void teardown() {
		
	}
	
}
