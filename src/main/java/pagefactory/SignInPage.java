package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	
	@FindBy(name = "logid")
	public WebElement hm_uname;
	
	@FindBy(name = "pswd")
	public WebElement hm_pwd;
			
	@FindBy(xpath = "//input[@value = 'Login']")
	public WebElement btn_login;
	

	@FindBy(xpath = "//b[contains(text(), 'Sorry we were')]")
	public WebElement errMsg;
	
	public SignInPage(WebDriver driver){
		PageFactory.initElements(driver,this);
		
	}	

	public void doSignIn(String uname, String pwd){
		hm_uname.sendKeys(uname);
		hm_pwd.sendKeys(pwd);
		btn_login.click();
	}

}
