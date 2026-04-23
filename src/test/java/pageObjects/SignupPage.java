package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BasePage {
	public SignupPage(WebDriver driver) {
		super(driver);
	}
	
// ===== Locators =====

@FindBy(xpath="//input[@id='id_gender1']") WebElement btnMr;
@FindBy(xpath="//input[@id='id_gender2']") WebElement btnMrs;
@FindBy(xpath="//input[@id='password']") WebElement txtPassword;
@FindBy(xpath="//select[@id='days']") WebElement drpDay;
@FindBy(xpath="//select[@id='months']") WebElement drpMonth;
@FindBy(xpath="//select[@id='years']") WebElement drpYear;

@FindBy(xpath="//b[normalize-space()='Address Information']") WebElement headingAddressInfo;
@FindBy(xpath="//input[@id='first_name']") WebElement txtfirstName;
@FindBy(xpath="//input[@id='last_name']") WebElement txtlastName;
@FindBy(xpath="//input[@id='address1']") WebElement txtAddress1;
@FindBy(xpath="//select[@id='country']") WebElement drpCountry;
@FindBy(xpath="//input[@id='state']") WebElement txtState;
@FindBy(xpath="//input[@id='city']") WebElement txtCity;
@FindBy(xpath="//input[@id='zipcode']") WebElement txtZipcode;
@FindBy(xpath="//input[@id='mobile_number']") WebElement txtMobileNumber;
@FindBy(xpath="//button[normalize-space()='Create Account']") WebElement btnCreateAccount;
@FindBy(xpath="//b[normalize-space()='Account Created!']") WebElement headingAccCreated;
@FindBy(xpath="//a[normalize-space()='Continue']") WebElement btnContinue;


//===== Action Methods =====
public void setAccountInformation(String password, String day, String month, String year) {
	// Select title
	if (!btnMrs.isSelected()) {
		btnMrs.click();
	}
	
	txtPassword.sendKeys(password);
	Select dayDropdown= new Select(drpDay);
	dayDropdown.selectByVisibleText(day);
	
	Select monthDropdown= new Select(drpMonth);
	monthDropdown.selectByVisibleText(month);
	
	Select yearDropdown= new Select(drpYear);
	yearDropdown.selectByVisibleText(year);
	
}

public void setAddressInformation(String firstname, String lastname, String address1,
		String country, String state, String city, String zipcode, String mobilenumber) {
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true)", headingAddressInfo);
	
	txtfirstName.clear();
	txtfirstName.sendKeys(firstname);
	txtlastName.clear();
	txtlastName.sendKeys(lastname);
	txtAddress1.clear();
	txtAddress1.sendKeys(address1);
	Select countryDropdown= new Select(drpCountry);
	countryDropdown.selectByVisibleText(country);
	txtState.clear();
	txtState.sendKeys(state);
	txtCity.clear();
	txtCity.sendKeys(city);
	txtZipcode.clear();
	txtZipcode.sendKeys(zipcode);
	txtMobileNumber.clear();
	txtMobileNumber.sendKeys(mobilenumber);
	
}

public void clickCreateAccount() {
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnCreateAccount);
}

public boolean isAccCreated() {
	try {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(headingAccCreated));
		return (headingAccCreated.isDisplayed());
	} catch (Exception e) {
		return false;
	}
}

public void clickContinue() {
	
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnContinue);
}







}
