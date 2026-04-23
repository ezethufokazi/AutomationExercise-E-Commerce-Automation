package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults extends BasePage{
	public SearchResults(WebDriver driver) {
		super(driver);
	}

	// ===== Locators =====
	
	@FindBy(xpath="//div[@class='productinfo text-center']//p") 
	List <WebElement> products;
	
	@FindBy(xpath="//h2[normalize-space()='Searched Products']") 
	WebElement headingSearchedProducts;
	
	@FindBy(xpath="//div[@class='productinfo text-center']//a")
	WebElement btnAddToCart;
	
	@FindBy(xpath="//u[normalize-space()='View Cart']")
	WebElement lnkviewCart;
	
	// ===== Action Methods =====
	
	// returns number of results found
	public int getResultCount() {
		return products.size();
	}
	
	// returns true if product with matching name exists in results
	public boolean isProductDisplayed(String productName) {
		for(WebElement product: products) {
			if(product.getText().equalsIgnoreCase(productName)) {
				return true;
			}
		}
		return false;
	}
	
	public void addToCart() {
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", btnAddToCart);
		js.executeScript("arguments[0].click();", btnAddToCart);
	}
	
	public void viewCart() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(lnkviewCart));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkviewCart);
	}
}
