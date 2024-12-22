package automationproject.Tests;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import automationproject.TestComponents.BaseTest;
import automationproject.pageobjectmodel.Cart;
import automationproject.pageobjectmodel.LandingPage;
import automationproject.pageobjectmodel.OrderPage;
import automationproject.pageobjectmodel.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test
	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		obj.login("abcxyz@deff.com", "Abc@1234");
		Assert.assertEquals("Incorrect email password.", obj.getErrorMsg());
		}
	
	@Test
	public void ProductErrorValidation() {
		String item="ZARA COAT 3";
		ProductCatalogue product=obj.login("abcdxyz@def.com", "Abc@1234");
		product.AddProduct(item);
		Cart cartProduct = product.goToCart();
		Boolean match = cartProduct.ValidateCartProducts("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
