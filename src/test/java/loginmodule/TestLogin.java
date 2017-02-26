package loginmodule;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import pagefactory.HomePage;
import pagefactory.SignInPage;

import com.relevantcodes.extentreports.LogStatus;

public class TestLogin extends Base {

	Logger login_Log = Logger.getLogger(TestLogin.class);
	
	@Test(dataProvider="commondp", dataProviderClass=dataprovider.Dp_login.class)
	public void testlogin(Map<String,String> loginMap) throws Exception{
		
		String scriptName = loginMap.get("Script");
		String tcid = loginMap.get("TC_ID");
		String order = loginMap.get("Order");
		String uname = loginMap.get("Uname");
		String pwd = loginMap.get("Pwd");
		String expectedValue = loginMap.get("Exp_msg");
		
		startTest = eReports.startTest(tcid);
		login_Log.info("Starting test:" + tcid + " and order: " + order);
		startTest.log(LogStatus.PASS, "Starting test","Starting test:" + tcid + " and order: " + order);
		
		HomePage homePage = new HomePage(driver);
		homePage.hm_login.click();
		login_Log.info("Clicked on Sigin Link");
		
		SignInPage signInPage = new SignInPage(driver);
		signInPage.doSignIn(uname, pwd);
		login_Log.info("Entered User name and Password & Login clicked");
		
		if(scriptName.equalsIgnoreCase("InvalidLogin")){
			String actualErrorMsg = signInPage.errMsg.getText();
			if(actualErrorMsg.equalsIgnoreCase(expectedValue)){
				startTest.log(LogStatus.PASS, "InvalidLogin user validation","Passed as user is unable to login" + startTest.addScreenCapture(getElementScreen(signInPage.errMsg)));
				System.out.println("Error message for Invalid login is found successfull");
			}else {
				System.out.println("Error message found incorrect");
			}
		}
		
		if(scriptName.equalsIgnoreCase("ValidLogin")){
			String actualWelcomeMsg = signInPage.errMsg.getText();
			if(actualWelcomeMsg.equalsIgnoreCase(expectedValue)){
				login_Log.info("Passed as user logged in");
				startTest.log(LogStatus.PASS, "Valid user validation","Passed as user logged in" + startTest.addScreenCapture(getElementScreen(homePage.lbl_username)));
				System.out.println("User looged in successfully");
			}else {
				login_Log.error("Failed as user una ble to login");
				startTest.log(LogStatus.FAIL, "Valid user validation","Failed as user unable to login" + startTest.addScreenCapture(getElementScreen(homePage.lbl_username)));
				System.out.println("User Login failed with valid credentials");
			}
		  }		
		
		
	}

}
