package automationproject.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
 
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//tr//td[2]")
	List<WebElement> orders;
	
	@FindBy(xpath="//div[@class='subtotal cf ng-star-inserted']//button")
	WebElement checkout;
	
	//List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
	public Boolean ValidateOrderProducts(String item) {
		Boolean match = orders.stream().anyMatch(p->p.getText().contains(item));
		return match;
	}
}
