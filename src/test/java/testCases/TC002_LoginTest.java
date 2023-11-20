package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Smoke","Sanity", "Master"})
	public void verifyLogin() {
		try {
			logger.info("**** Starting TC002_LoginTest ****");
			
			// Navigate to Signup/Login page
			HomePage hp= new HomePage(driver);
			hp.clickSignupLogin();
			logger.info("Navigated to Signup/Login page");
			
			// Dismiss any ads before interacting
			dismissAds();
			
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
			
			logger.info("**** TC002_LoginTest PASSED ****");
			
		} catch (Exception e) {
			logger.error("TC002_LoginTest FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());

		}
	}

}
