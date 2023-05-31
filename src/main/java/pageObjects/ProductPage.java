package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract;

public class ProductPage extends Abstract {
	
	public WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".btn.w-10.rounded")
	WebElement addToProductBtn;
	
	
	public List<WebElement> getListOfProducts() {
		return products;
	}
	
	public WebElement getSpecificProduct() {
		WebElement mainProduct = getListOfProducts().stream().filter(s->s.findElement(By.tagName("h5")).getText().contains("ADIDAS")).findFirst().orElse(null);
		return mainProduct;
		
	}
	
	public CartPage addToCart() {
		getSpecificProduct().findElement(By.cssSelector(".btn.w-10.rounded")).click();
		return new CartPage(driver);
	}

}
