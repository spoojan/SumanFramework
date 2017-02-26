package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(linkText="Sign In")
	public WebElement hm_login;
	
	@FindBy(id="username")
	public WebElement lbl_username;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver,this);
		
	}	
}
