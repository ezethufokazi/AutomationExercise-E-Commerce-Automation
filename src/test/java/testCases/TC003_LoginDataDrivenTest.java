package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	@Test(
		dataProvider = "dp",
		dataProviderClass = DataProviders.class,
		groups = {"DataDriven", "Master"}
		)
	public void verifyLoginDDT(String email, String password, String expectedResult, String scenario) {
		try {
			logger.info("**** Starting TC002_LoginDataDrivenTest ****");
			logger.info("Scenario: " + scenario);
			logger.info("Email: " + email
	                + " | Password: " + (password.isEmpty() ? "[empty]" : "[provided]")
	                + " | Expected: " + expectedResult);
		
			// Navigate to Signup/Login page
			HomePage hp = new HomePage(driver);
			hp.clickSignupLogin();
			logger.info("Navigated to Signup/Login page");
			
			// Dismiss any ads before interacting
			dismissAds();
			
			// Enter login credentials
			LoginPage lp= new LoginPage(driver);
			lp.setLoginCredentials(email, password);
			lp.clickLogin();
			logger.info("Login attempted");							
			
            // Check if login was successful
			boolean loginSuccessful = hp.loginSuccess();
			  /*
             * Test logic:
             * Valid credentials + login success   → PASS  → logout
             * Valid credentials + login fails     → FAIL
             * Invalid credentials + login success → FAIL  → logout
             * Invalid credentials + login fails   → PASS
             */
			
			if(expectedResult.equalsIgnoreCase("Valid")) {
                if(loginSuccessful) {
                    logger.info("PASS - " + scenario
                        + ": Login successful with valid credentials");
                    Assert.assertTrue(true);
            
                    hp.clickLogout();
                    logger.info("Logged out successfully");
                } else {
                    logger.error("FAIL - " + scenario
                        + ": Login failed with valid credentials");
                    Assert.fail("Login failed with valid credentials | "
                        + "Scenario: " + scenario
                        + " | Email: " + email);
                }
            } else if(expectedResult.equalsIgnoreCase("Invalid")) {
                if(loginSuccessful) {
                    logger.error("FAIL - " + scenario
                        + ": Login succeeded with invalid credentials");
                    hp.clickLogout();
                    Assert.fail("Login succeeded with invalid credentials | "
                        + "Scenario: " + scenario
                        + " | Email: " + email);
                } else {
                    logger.info("PASS - " + scenario
                        + ": Login correctly rejected");
                    Assert.assertTrue(true);
                }
            } else {
                // handles unexpected values in Excel
                logger.warn("Unknown expected result: " + expectedResult
                    + " | Scenario: " + scenario);
                Assert.fail("Invalid expected result in Excel: "
                    + expectedResult
                    + " - use 'valid' or 'invalid' | Scenario: " + scenario);
            }
			
			logger.info("**** TC002_LoginDataDrivenTest PASSED - "
		                + scenario + " ****");
			
		} catch (Exception e) {
			logger.error("TC002_LoginDataDrivenTest FAILED | Scenario: " + scenario + " | Error: " + e.getMessage());
			Assert.fail(e.getMessage());

		}	
			
	}

}
