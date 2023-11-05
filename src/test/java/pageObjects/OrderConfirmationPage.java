package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage extends BasePage {
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====
	
	@FindBy(xpath="//b[normalize-space()='Order Placed!']")
	WebElement headingOrderPlaced;
	
	@FindBy(xpath="//p[normalize-space()='Congratulations! Your order has been confirmed!']") 
	WebElement msgOrderConfirmed;
	
	// ===== Action Methods =====
	
	public boolean isOrderConfirmed() {
		try {
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(msgOrderConfirmed));
			return msgOrderConfirmed.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
