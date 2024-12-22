package automationproject.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationproject.AbstractComponenets.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	By waitProduct=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");;
	By toast=By.cssSelector("#toast-container");
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement ele;
	
	public List<WebElement> getProductsList(){
		waitForElement(waitProduct);
		return products;
		
	}
	public WebElement getProduct(String item) {
		WebElement prod=getProductsList().stream().filter(product->product.getText().contains(item)).findFirst().orElse(null);
		return prod;
	}
	public void AddProduct(String item) {
		getProduct(item).findElement(addToCart).click();
		waitForElement(toast);
		waitForElementtoDisappear(ele);
	}
	
	

	

}
