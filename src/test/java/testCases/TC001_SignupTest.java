package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SignupPage;
import testBase.BaseClass;

public class TC001_SignupTest extends BaseClass {
	
	@Test(groups={"Smoke", "Regression", "Master"})
	public void verifySignup() {
		try {
			logger.info("**** Starting TC001_SignupTest ****");
			
			// Navigate to Signup/Login page
			HomePage hp =new HomePage(driver);
			hp.clickSignupLogin();
			logger.info("Navigated to Signup/Login page");
			
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
			String lastName= randomString().toUpperCase();
			String address1= p.getProperty("address1");
			String country= p.getProperty("country");
			String state= p.getProperty("state");
			String city= p.getProperty("city");
			String zipcode= p.getProperty("zipcode");
			String mobileNumber= p.getProperty("mobileNumber");
			sp.setAddressInformation(name, lastName, address1, country, state, city, zipcode, mobileNumber);
			
			// Submit form
			sp.clickCreateAccount();
			logger.info("Form submitted");
			
			// Validate account is created
			logger.info("Validating confirmation mesage");
			boolean accCreated= sp.isAccCreated();
			Assert.assertTrue(accCreated, "Account signup failed - confirmation message not displayed");
			
			logger.info("**** TC001_SignupTest PASSED **** ");
			
		} catch (Exception e) {
			logger.error("TC001_SignupTest FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
			
		}
		
	}
}
