package automationproject.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationproject.AbstractComponenets.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.xpath("//div[@class='user__address']//input")).sendKeys("India");
	@FindBy(xpath="//div[@class='user__address']//input")
	WebElement cntryInput;
	
	//driver.findElement(By.xpath("//section//button//span[text()=' India']")).click();
	@FindBy(xpath="//section//button//span[text()=' India']")
	WebElement cntrySuggest;
	
	//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;
	
	@FindBy(tagName="h1")
	WebElement confirMessage;
	
	//String confirmMessage=driver.findElement(By.tagName("h1")).getText();

	public void  EnterOrderDetails(String country){
		cntryInput.sendKeys(country);
		cntrySuggest.click();
	}
	public void  PlaceOrder(){
		placeOrder.click();
	}
	//String confirmMessage=driver.findElement(By.tagName("h1")).getText();
	public String  OrderConfirm(){
		String confirm = confirMessage.getText();
		return confirm;
	}
	

	

}
