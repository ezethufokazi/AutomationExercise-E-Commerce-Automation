package pageObjects;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage{
	
	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====
	@FindBy(xpath="//h2[normalize-space()='All Products']") 
	WebElement headingAllProducts;
	
	@FindBy(xpath="//div[@class='features_items']") 
	WebElement productsList;
	
	@FindBy(xpath="(//a[normalize-space()='View Product'])[1]")
	WebElement lnkViewProduct;
	
	@FindBy(xpath="//input[@id='search_product']") 
	WebElement txtSearchProduct;

	@FindBy(xpath="//button[@id='submit_search']") 
	WebElement btnSubmitSearch;
	

	// ===== Action Methods =====
	public boolean isAllProductsPageDisplayed() {
		try {
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(headingAllProducts));
			return headingAllProducts.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isProductsListVisible() {
		try {
			return productsList.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickFirstProduct() { // is this necessary???
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", lnkViewProduct);
		wait.until(ExpectedConditions.elementToBeClickable(lnkViewProduct));
		js.executeScript("arguments[0].click();", lnkViewProduct);
		}
	

	public void enterProduct(String productName) {
		txtSearchProduct.clear();
		txtSearchProduct.sendKeys(productName);
	}
	
	public void clickSearch() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnSubmitSearch);
	}
	
	
}