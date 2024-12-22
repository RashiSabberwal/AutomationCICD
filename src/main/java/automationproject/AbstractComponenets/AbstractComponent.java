package automationproject.AbstractComponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationproject.pageobjectmodel.Cart;
import automationproject.pageobjectmodel.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@routerlink,'/dashboard/cart')]")
	WebElement Cart;
	
	@FindBy(xpath="//button[contains(@routerlink,'/dashboard/myorders')]")
	WebElement Order;
	
	
	public automationproject.pageobjectmodel.Cart goToCart() {
		Cart.click();
		Cart cartProduct= new Cart(driver);
		return cartProduct;
	}
	
	public OrdersPage goToOrers() {
		Order.click();
		OrdersPage orderPage= new OrdersPage(driver);
		return orderPage;
	}

	public void waitForElement(By findBy) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElement(WebElement findBy) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementtoDisappear(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
