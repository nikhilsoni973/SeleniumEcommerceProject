package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CheckOut;

public class Abstract {
	public WebDriver driver;
	public Abstract(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	
	public void waitForInvisibility(WebElement toast) {
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOf(toast));
	}
	
	public CheckOut clickOnCartHeader() {
		cartHeader.click();
		return new CheckOut(driver);
	}
}
