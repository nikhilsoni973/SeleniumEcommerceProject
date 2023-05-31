package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract;

public class CheckOut extends Abstract {
	public WebDriver driver;
	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkOutBtn;
	
	public confirmationPage clickOnCheckOutBtn() {
		checkOutBtn.click();
		return new confirmationPage(driver);
	}

}
