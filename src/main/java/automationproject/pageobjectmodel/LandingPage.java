package automationproject.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationproject.AbstractComponenets.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement mail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='toast-bottom-right toast-container']")
	WebElement errorMsg;
	
	public ProductCatalogue login(String email, String password) {
		mail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue product = new ProductCatalogue(driver);
		return product;
	}
	
	public String getErrorMsg() {
		waitForWebElement(errorMsg);
		return errorMsg.getText();
		
	}
	
	public void goTo(String url) {
		driver.get(url);
		
	}
	

	

}
