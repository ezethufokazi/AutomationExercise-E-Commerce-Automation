package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage{
	
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====

	@FindBy(xpath = "//input[@name='name_on_card']")
	WebElement txtNameOnCard;
	
	@FindBy(xpath = "//input[@name='card_number']")
	WebElement txtCardNumber;

	@FindBy(xpath = "//input[@class='form-control card-cvc']")
	WebElement txtCVC;

	@FindBy(xpath = "//input[@class='form-control card-expiry-month']")
	WebElement txtExpiryMonth;

	@FindBy(xpath = "//input[@class='form-control card-expiry-year']")
	WebElement txtExpiryYear;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement btnPayAndConfirmOrder;
	
	
	// ===== Action Methods =====
	
	public void enterPaymentDetails(String name, String number, String cvc, String month, String year ) 
	{
		txtNameOnCard.clear();
		txtNameOnCard.sendKeys(name);
		txtCardNumber.clear();
		txtCardNumber.sendKeys(number);
		txtCVC.clear();
		txtCVC.sendKeys(cvc);
		txtExpiryMonth.clear();
		txtExpiryMonth.sendKeys(month);
		txtExpiryYear.clear();
		txtExpiryYear.sendKeys(year);
	}
	
	public void clickPayAndConfirm() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnPayAndConfirmOrder);
	}
	
}
	
