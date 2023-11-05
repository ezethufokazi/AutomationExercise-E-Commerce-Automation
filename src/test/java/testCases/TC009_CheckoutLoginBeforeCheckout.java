package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import testBase.BaseClass;

public class TC009_CheckoutLoginBeforeCheckout extends BaseClass {
	
	@Test(groups= {"Regression", "Master"})
	public void verifyLoginBeforeCheckout() {
		try {
			logger.info("**** Starting TC009_CheckoutLoginBeforeCheckout ****");
			
			// Navigate to Signup/Login page
			HomePage hp= new HomePage(driver);
			hp.clickSignupLogin();
			logger.info("Navigated to Signup/Login page");
			
			// Enter login credentials
			LoginPage lp= new LoginPage(driver);
			String email= p.getProperty("email");
			String password= p.getProperty("password");
			lp.setLoginCredentials(email, password);
			logger.info("Entered credentials for: " + email);
			
			lp.clickLogin();
			logger.info("Login button clicked");
			
			// Verify successful login
			boolean targetPage= hp.loginSuccess();
			Assert.assertTrue(targetPage, "Login failed");
			
			// Add product to cart from Home Page
			hp.addProductToCart();
			logger.info("Product added to cart");
	
			// Navigate to cart
			logger.info("Navigating to cart");
			hp.clickViewCart();
	
			// Navigate to checkout
			logger.info("Clicking Proceed To Checkout button");
			CartPage cp = new CartPage(driver);
			cp.proceedToCheckout();
			
			// Verify address details and place order
			CheckoutPage cop = new CheckoutPage(driver);
			Assert.assertTrue(cop.verifyAddressesDisplayed(),
					"Address details not displayed");
			logger.info("Address details verified");
			cop.addOrderComment(p.getProperty("orderComment"));
			cop.clickPlaceOrder();
	
			logger.info("Entering payment details");
			PaymentPage pp = new PaymentPage(driver);
			String cardname= p.getProperty("cardName");
			String number= p.getProperty("cardNumber");
			String cvc= p.getProperty("cardCVC");
			String expirymonth= p.getProperty("cardExpiryMonth");
			String expiryyear= p.getProperty("cardExpiryYear");
	
			pp.enterPaymentDetails(cardname, number, cvc, expirymonth, expiryyear);
	
			// Pay and confirm order
			pp.clickPayAndConfirm();
	
			// Verify order confirmed
			OrderConfirmationPage ocp= new OrderConfirmationPage(driver);
			Assert.assertTrue(ocp.isOrderConfirmed(),"Order not placed");
			logger.info("Order placed successfully");
	
			logger.info("***** TC009_CheckoutLoginBeforeCheckout PASSED *****");
			
		} catch (Exception e) {
			logger.error("TC009_CheckoutLoginBeforeCheckout FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		}
}
