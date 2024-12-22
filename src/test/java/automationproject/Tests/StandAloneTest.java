package automationproject.Tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automationproject.pageobjectmodel.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String item="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		LandingPage obj=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("abcxyz@def.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abc@1234");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod= products.stream().filter(product->product.getText().contains(item)).findFirst().orElse(null);
		prod.findElement(By.xpath("(//div[@class='card-body']//button)[2]")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[contains(@routerlink,'/dashboard/cart')]")).click();
		List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().contains(item));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']//button")).click();
		driver.findElement(By.xpath("//div[@class='user__address']//input")).sendKeys("India");
		driver.findElement(By.xpath("//section//button//span[text()=' India']")).click();
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		String confirmMessage=driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
		
		
		
	}
	

}
