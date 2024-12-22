package automationproject.Tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationproject.TestComponents.BaseTest;
import automationproject.pageobjectmodel.Cart;
import automationproject.pageobjectmodel.LandingPage;
import automationproject.pageobjectmodel.OrderPage;
import automationproject.pageobjectmodel.OrdersPage;
import automationproject.pageobjectmodel.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String item="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		String country="India";
		String confirmation = "Thankyou for the order.";
		ProductCatalogue product=obj.login(input.get("email"), input.get("pass"));
		product.AddProduct(input.get("item1"));
		Cart cartProduct = product.goToCart();
		Boolean match = cartProduct.ValidateCartProducts(input.get("item1"));
		Assert.assertTrue(match);
		OrderPage order = cartProduct.CheckoutClick();
		order.EnterOrderDetails(country);
		order.PlaceOrder();
		String confirmMessage = order.OrderConfirm();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(confirmation));
		
		}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue product=obj.login("abcxyz@def.com", "Abc@1234");
		OrdersPage ordersPage = product.goToOrers();
		Assert.assertTrue(ordersPage.ValidateOrderProducts(item));
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<Object,Object> map=new HashMap<Object,Object>();
//		map.put("email", "abcxyz@def.com");
//		map.put("pass", "Abc@1234");
//		map.put("item1", "ZARA COAT 3");
//		HashMap<Object,Object> map1=new HashMap<Object,Object>();
//		map1.put("email", "abcdxyz@def.com");
//		map1.put("pass", "Abc@1234");
//		map1.put("item1", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\automationproject\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
