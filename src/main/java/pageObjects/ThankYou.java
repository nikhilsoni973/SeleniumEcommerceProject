package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYou {
	
	public WebDriver driver;
	public ThankYou(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(tagName="h1")
	WebElement heroText;
	
	public String getHeroText() {
		return heroText.getText();
	}
	
	
	
}
