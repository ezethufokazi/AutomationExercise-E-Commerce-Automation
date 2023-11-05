package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// ===== Locators =====
	@FindBy(xpath="//a[normalize-space()='Signup / Login']")
	WebElement lnkSignupLogin;
	
	@FindBy(xpath="//a[@href='/products']") 
	WebElement lnkProducts;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li[10]//a")
	WebElement lnkLoggedInAs;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath="(//a[@class='btn btn-default add-to-cart'])[1]")
	WebElement btnAddToCart;
	
	@FindBy(xpath="//u[normalize-space()='View Cart']")
	WebElement lnkViewCart;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']//li[3]//a")
	WebElement lnkCart;
	
	@FindBy(xpath="//a[normalize-space()='Delete Account']")
	WebElement lnkDeleteAccount;
	
	@FindBy(xpath="//b[normalize-space()='Account Deleted!']") 
	WebElement headingAccDeleted;
	
	
	// ===== Action Methods =====
	
	// Click Signup/Login
	public void clickSignupLogin() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(lnkSignupLogin));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkSignupLogin);
	}
	
	public void clickProducts() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkProducts);
	}
	
	public void addProductToCart() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'})", btnAddToCart);
		js.executeScript("arguments[0].click();", btnAddToCart);
	}
	
	public void clickViewCart() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(lnkViewCart));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkViewCart);
	}
	
	public boolean loginSuccess() {
		try {
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(lnkLoggedInAs));
			return lnkLoggedInAs.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkLogout);
	}
	
	public void clickCart() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(lnkCart));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkCart);
	}
	
	public void clickDeleteAccount() {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(lnkDeleteAccount));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkDeleteAccount);
	}
	
	public boolean isAccountDeleted() {
		try {
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(headingAccDeleted));
			return headingAccDeleted.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}


}
