package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.SignupPage;
import testBase.BaseClass;

public class TC007_CheckoutRegisterWhileCheckout extends BaseClass{
	
	@Test(groups= {"Regression", "Master"})
	public void verifyRegisterWhileCheckout() {
		try {
			
			logger.info("**** Starting TC007_CheckoutRegisterWhileCheckout ****");
			
			// Add product to cart from Home Page
			HomePage hp= new HomePage(driver);
			hp.addProductToCart();
			logger.info("Product added to cart");
			
			// Navigate to cart
			logger.info("Navigating to cart");
			hp.clickViewCart();
			
			// Navigate to checkout 
			logger.info("Clicking Proceed To Checkout button");
			CartPage cp = new CartPage(driver);
			cp.proceedToCheckout();
			
			// Navigate to Register/Login
			logger.info(" Navigate to Register/Login");
			cp.clickRegisterLogin();
			
			// Fill in Signup name and email
			LoginPage lp= new LoginPage(driver);
			String name= randomString().toUpperCase();
			String email= randomString()+ "@gmail.com";
			lp.setSignupCredentials(name, email);
			lp.clickSignup();
			
			// Fill in Account Information
			SignupPage sp= new SignupPage(driver);
			String password= randomAlphaNumeric();
			String day= generateDay();
			String month= generateMonth();
			String year= generateYear();
			sp.setAccountInformation(password, day, month, year);
			logger.info("Signed up with email: " + email);
			
			// Fill in Address Information
			String lastName = randomString().toUpperCase();
			String address1 = p.getProperty("address1");
			String country = p.getProperty("country");
			String state = p.getProperty("state");
			String city = p.getProperty("city");
			String zipcode = p.getProperty("zipcode");
			String mobileNumber = p.getProperty("mobileNumber");
			sp.setAddressInformation(name, lastName, address1, country, state, city, zipcode, mobileNumber);

			// Submit form
			sp.clickCreateAccount();
			logger.info("Form submitted");
	
			sp.clickContinue();
			
			logger.info("Navigating to cart");
			hp.clickCart();
			
			logger.info("Navigating to checkout");
			cp.proceedToCheckout();
			
			// Verify address details and place order
			CheckoutPage cop = new CheckoutPage(driver);
			Assert.assertTrue(cop.verifyAddressesDisplayed(), 
					"Address details not displayed");
			logger.info("Address details verified");
			cop.addOrderComment(p.getProperty("orderComment"));
			cop.clickPlaceOrder();
			logger.info("Order placed");
			
			// Enter payment details
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
			
			// Verify order success
			OrderConfirmationPage ocp= new OrderConfirmationPage(driver);
			Assert.assertTrue(ocp.isOrderConfirmed(),"Order not placed successfully");
			logger.info("Order confirmed");		
			
			logger.info("***** TC007_CheckoutRegisterWhileCheckout PASSED *****");
					
		} catch (Exception e) {
			logger.error("TC007_CheckoutRegisterWhileCheckout FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
