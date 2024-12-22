package automationproject.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import automationproject.TestComponents.BaseTest;
import automationproject.pageobjectmodel.Cart;
import automationproject.pageobjectmodel.LandingPage;
import automationproject.pageobjectmodel.OrderPage;
import automationproject.pageobjectmodel.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public Cart cartProduct;
	public OrderPage order;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchApp();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password) {
		 productCatalogue = landingPage.login(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.AddProduct(productName);
	}
	@When("^And Checkout (.+) and submit the order$")
	public void and_Checkout_and_submit_the_order(String productName) {
		cartProduct = productCatalogue.goToCart();
		Boolean match = cartProduct.ValidateCartProducts(productName);
		Assert.assertTrue(match);
		OrderPage order = cartProduct.CheckoutClick();
		order.EnterOrderDetails("india");
		order.PlaceOrder();
	}
	@Then("{string} message is displayed on Confirmation")
	public void message_is_displayed_on_Confirmation(String string) {
		String confirmMessage = order.OrderConfirm();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	

}
