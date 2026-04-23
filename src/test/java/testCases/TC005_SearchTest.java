package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.SearchResults;
import testBase.BaseClass;

public class TC005_SearchTest extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void verifySearch() {
		try {
			logger.info("**** Starting TC005_SearchTest ****");
			
			String product= p.getProperty("searchProdName");
			
			// Navigate to products page
			HomePage hp= new HomePage(driver);
			hp.clickProducts();
			logger.info("Navigated to products page");
			
			// Searching for product from products page
			ProductsPage pp= new ProductsPage(driver);
			logger.info("Searching for product: " + product);
			pp.enterProduct(product);
			pp.clickSearch();
			
			// Verify search results
			SearchResults sr= new SearchResults(driver);
			logger.info("Total results found: " + sr.getResultCount());
			
			Boolean searchStatus= sr.isProductDisplayed(product);
			Assert.assertTrue(searchStatus, 
					"Product not found in results: " + product );
			
			logger.info("**** TC005_SearchTest PASSED ****");
			
		} catch (Exception e) {
			logger.error("TC005_SearchTest FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
			
		}
		
	}
	

}
