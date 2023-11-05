package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====
	
	@FindBy(xpath="//div[@class='product-information']//h2")
	WebElement headingProductName;
	
	@FindBy(xpath="//p[normalize-space()='Category: Women > Tops']")
	WebElement productCategory;
	
	@FindBy(xpath="//div[@class='product-information']//span//span") 
	WebElement productPrice;
	
	@FindBy(xpath="//b[normalize-space()='Availability:']") 
	WebElement productAvailability;
	
	@FindBy(xpath="//b[normalize-space()='Condition:']") 
	WebElement productCondition;
	
	@FindBy(xpath="//b[normalize-space()='Brand:']") 
	WebElement productBrand;
	
	
	// ===== Action Methods =====
	
	public boolean isProductNameVisible() {
		try {
			return headingProductName.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}
	public boolean isProductPriceVisible() {
		try {
			return productPrice.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean isProductCategoryVisible() {
		try {
			return productCategory.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean isProductAvailabilityVisible() {
		try {
			return productAvailability.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean isProductConditionVisible() {
		try {
			return productCondition.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean isProductBrandVisible() {
		try {
			return productBrand.isDisplayed();
		} catch(Exception e) {
			return false;
		}
		
	}


}
