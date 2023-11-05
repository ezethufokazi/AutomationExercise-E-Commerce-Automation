package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.SearchResults;
import testBase.BaseClass;

public class TC006_CartTest extends BaseClass {
	
	@Test(groups= {"Regression","Master"})
	public void verifyCart() {
		try {
			logger.info("**** Starting TC006_CartTest ****");
			
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
			
			// Add product to cart
			SearchResults sr= new SearchResults(driver);
			sr.addToCart();
			logger.info("Product added to cart");
			
			// Navigate to cart
			logger.info("Navigating to cart");
			sr.viewCart();
			
			// Verify correct product in cart
			CartPage cp= new CartPage(driver);
			boolean productInCart= cp.verifyCorrectProduct(product);
			Assert.assertTrue(productInCart, 
				"Wrong product in cart - expected: " + product);
			logger.info("Correct product found in cart: " + product);
			
			// Verify total
			logger.info("Unit price: " + cp.getPrice());
			logger.info("Quantity: "  + cp.getQuantity());
			
			Assert.assertTrue(cp.verifyTotal(), "Total is incorrect");
			logger.info("Total verified successfully");
			
			// Delete product from cart
			logger.info("Deleting product from cart");
			cp.deleteProduct();
			Thread.sleep(3000);
			
			// Verify product is deleted from cart
			Assert.assertTrue(cp.isProductDeleted(),
					"Product is not deleted from cart");
			logger.info("Product deleted from cart successfully");
			
			logger.info("**** TC006_CartTest **** PASSED");
			
		} 	catch(Exception e) {
			logger.error("TC006_CartTest FAILED: " + e.getMessage());
			Assert.fail(e.getMessage());
		
		}
	}
	

}
