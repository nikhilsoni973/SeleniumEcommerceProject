package testcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.CartPage;
import pageObjects.CheckOut;
import pageObjects.LandingPage;
import pageObjects.ProductPage;
import pageObjects.ThankYou;
import pageObjects.confirmationPage;

public class StandAloneTest extends Base {

	@Test(dataProvider="getData")
	public void display(String email, String password) {
		ProductPage productPage = page.loginWithCredentials(email, password);
		CartPage cartPage = productPage.addToCart();
		cartPage.waitForElement();
		CheckOut checkout = cartPage.clickOnCartHeader();
		confirmationPage confirmationPage = checkout.clickOnCheckOutBtn();
		confirmationPage.sendCountryName();
		confirmationPage.clickOnSpecificCountry();
		ThankYou thank =confirmationPage.clickOnBtn();
		String orderConfirmationMsg = thank.getHeroText();
		System.out.println(orderConfirmationMsg);
		Assert.assertEquals(orderConfirmationMsg.trim(), "THANKYOU FOR THE ORDER.");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {	
		return getDataFromExcel("C:\\Users\\sonin\\Downloads\\excelDriven2.xlsx"); 
	}
	
	
}
