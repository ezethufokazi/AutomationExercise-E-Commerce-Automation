package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC004_ViewProductsTest extends BaseClass {
	@Test(groups= {"Regression", "Master"})
	public void verifyProducts() {
	try {
		logger.info("**** Starting TC004_ViewProductsTest ****");
		
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
		
		
		// Navigate to products page
		hp.clickProducts();
		logger.info("Navigated to products page");
		
		// Verify all products page and list 
		ProductsPage pp= new ProductsPage(driver);
		Assert.assertTrue(pp.isAllProductsPageDisplayed(),
				"All products page not displayed");
		Assert.assertTrue(pp.isProductsListVisible(),
				"Products list not visible");
		logger.info("All products page and list displayed");
		
		// Click view product
		pp.clickFirstProduct();
		logger.info("Clicked first product");
		
		// Verify product details page
		ProductDetailsPage pdp= new ProductDetailsPage(driver);
		Assert.assertTrue(pdp.isProductNameVisible(), 
				"Product name not visible");
		Assert.assertTrue(pdp.isProductCategoryVisible(), 
				"Product category not visible");
		Assert.assertTrue(pdp.isProductPriceVisible(), 
				"Product price not visible");
		Assert.assertTrue(pdp.isProductAvailabilityVisible(), 
				"Product availability not visible");
		Assert.assertTrue(pdp.isProductConditionVisible(), 
				"Product condition not visible");
		Assert.assertTrue(pdp.isProductBrandVisible(), 
				"Product brand not visible");
		
		logger.info("All product details are verified");
		logger.info("**** TC004_ViewProductsTest PASSED ****");
		
		} catch (Exception e) {
			logger.error("TC004_ViewProductsTest FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
		
	}

}
