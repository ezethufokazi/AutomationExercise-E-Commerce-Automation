package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	

// ===== Locators =====	

@FindBy(xpath="//input[@data-qa='login-email']")
WebElement txtLoginEmail;

@FindBy(xpath="//input[@placeholder='Password']")
WebElement txtPassword;

@FindBy(xpath="//button[normalize-space()='Login']")
WebElement btnLogin;

@FindBy(xpath="//input[@placeholder='Name']") 
WebElement txtName;

@FindBy(xpath="//input[@data-qa='signup-email']") 
WebElement txtSignupEmail;

@FindBy(xpath="//button[normalize-space()='Signup']")
WebElement btnSignup;



//===== Action Methods =====

public void setLoginCredentials(String email, String password) {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	
	wait.until(ExpectedConditions.urlContains("login"));
	
	wait.until(ExpectedConditions.elementToBeClickable(txtLoginEmail));
	dismissAds();
	txtLoginEmail.clear();
	txtLoginEmail.sendKeys(email);
	dismissAds();
	txtPassword.clear();
	txtPassword.sendKeys(password);
}

public void clickLogin() {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnLogin);
}


public void setSignupCredentials(String name, String email) {
	txtName.clear();
	txtName.sendKeys(name);
	txtSignupEmail.clear();
	txtSignupEmail.sendKeys(email);
}

public void clickSignup() {
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnSignup);
}


	
	

}
