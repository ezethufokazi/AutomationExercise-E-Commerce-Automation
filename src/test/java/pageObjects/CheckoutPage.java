package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage{
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		
	}
	
	// ===== Locators =====
	
	@FindBy(xpath="//ul[@id='address_delivery']")
	WebElement deliveryAddress;
	
	@FindBy(xpath="//ul[@id='address_invoice']")
	WebElement billingAddress;
	
	@FindBy(xpath="//textarea[@class='form-control']")
	WebElement txtOrderComment;
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement btnPlaceOrder;
	
	

	// ===== Action Methods =====
	
	public boolean verifyAddressesDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOf(deliveryAddress));
			return deliveryAddress.isDisplayed()
			&& billingAddress.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void addOrderComment(String comment) {
		txtOrderComment.clear();
		txtOrderComment.sendKeys(comment);
	}
	
	public void clickPlaceOrder() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnPlaceOrder);
	}
}
