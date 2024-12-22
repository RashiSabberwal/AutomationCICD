package automationproject.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
 
	WebDriver driver;
	public Cart(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='infoWrap']//h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//div[@class='subtotal cf ng-star-inserted']//button")
	WebElement checkout;
	
	//List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
	public Boolean ValidateCartProducts(String item) {
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().contains(item));
		return match;
	}
	//driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']//button")).click();
	public OrderPage CheckoutClick() {
		checkout.click();
		OrderPage order=new OrderPage(driver);
		return order;
	}
}
