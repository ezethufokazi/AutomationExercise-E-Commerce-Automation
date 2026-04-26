package pageObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	

	public void dismissAds() {
	   try {
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript(
	           "document.getElementById('ad_position_box')?.remove();" +
	           "document.querySelectorAll('iframe').forEach(el => el.remove());"
	       );
	   } catch (Exception e) {}
	}
	
	}
