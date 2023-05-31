package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage {
	
	public WebDriver driver;
	public confirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder*='Country']")
	WebElement country;
	
	@FindBy(css="span[class='ng-star-inserted']")
	List<WebElement> countryNames;
	
	@FindBy(css="a[class*='ng-star-inserted']")
	WebElement placeOrderBtn;
	
	
	
	
	public void sendCountryName() {
		country.sendKeys("ind");
	}
	
	public List<WebElement> getListOfCountryNames() {
		return countryNames;
	}
	
	public void clickOnSpecificCountry() {
		WebElement mainCountry= getListOfCountryNames().stream().filter(s->s.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		mainCountry.click();
	}
	
	public ThankYou clickOnBtn() {
	
		placeOrderBtn.click();
		return new ThankYou(driver);
	}

}
