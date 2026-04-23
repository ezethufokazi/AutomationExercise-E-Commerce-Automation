package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====

	@FindBy(xpath = "//td[@class='cart_description']//h4/a")
	WebElement lnkProductName;

	@FindBy(xpath = "//td[@class='cart_price']//p")
	WebElement productPrice;

	@FindBy(xpath = "//td[@class='cart_quantity']//button")
	WebElement productQuantity;

	@FindBy(xpath = "//td[@class='cart_total']//p")
	WebElement priceTotal;

	@FindBy(xpath = "//a[@class='cart_quantity_delete']")
	WebElement lnkDelete;
	
	@FindBy(xpath="//b[normalize-space()='Cart is empty!']")
	WebElement msgEmptyCart;
	
	@FindBy(xpath="//u[normalize-space()='here']")
	WebElement lnkContinueShopping;

	@FindBy(xpath = "//a[normalize-space()='Proceed To Checkout']")
	WebElement btnProceedToCheckout;
	
	@FindBy(xpath="//u[normalize-space()='Register / Login']")
	WebElement lnkRegisterLogin;


	// ===== Action Methods =====
	
	public String getProductNameText() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        wait.until(ExpectedConditions.visibilityOf(lnkProductName));
	        return lnkProductName.getText();
	    } catch(Exception e) {
	        return "Element not found: " + e.getMessage();
	    }
	}
	
	public boolean verifyCorrectProduct(String productName) throws InterruptedException {
		try {
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(lnkProductName));
			return lnkProductName.getText().toLowerCase()
			     .contains(productName.trim().toLowerCase());
			} catch (Exception e) {
			return false;
		}
	}
	
	public String getPrice() {
		return productPrice.getText();
	}
	
	public String getQuantity() {
		return productQuantity.getText();
	}
	
	public String getTotal() {
		return priceTotal.getText();
	}
	
	public boolean verifyTotal() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOf(priceTotal));
			double price= parsePrice(productPrice.getText());
			int quantity = Integer.parseInt(productQuantity.getText().trim());
			double total= parsePrice(priceTotal.getText());
			return total == (price*quantity);
		} catch(Exception e) {
			return false;
		}	
	}
	
	public void deleteProduct() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkDelete);
	}
	
	public boolean isProductDeleted() {
		try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		     wait.until(ExpectedConditions.visibilityOf(msgEmptyCart));
		     return msgEmptyCart.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void continueShopping() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkContinueShopping);
	}
	
	
	public void proceedToCheckout() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnProceedToCheckout);
	}
	
	public void clickRegisterLogin() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkRegisterLogin);
	}
	
	// helper method to parse price string to double
	private double parsePrice(String price) {
		return Double.parseDouble(price.replace("Rs.", "").replace("Rs", "").replace(",", "").trim());
	}
	
	
	



}
