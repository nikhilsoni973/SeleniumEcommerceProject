package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;

public class Base {
	public WebDriver driver;
	public LandingPage page;
	public WebDriver initializeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedrivernik\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		Properties props = new Properties();
		FileInputStream fis =  new FileInputStream("C:\\Users\\sonin\\eclipse-workspace\\MayPractice\\src\\test\\java\\resources\\data.properties");
		props.load(fis);
		String landingURL = props.getProperty("myURL");
		System.out.println(landingURL);
		driver = initializeDriver();
		page = new LandingPage(driver);
		page.goToLandingPage(landingURL);
		return page;
	}
	
	public String takeScreenshotOnFailure(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(System.getProperty("user.dir")+testCaseName+".png"));
		return System.getProperty("user.dir")+testCaseName+".png"
				;
		
	}
	
	//Call in Testcase if needed just provide filepath of json File
	public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {
		//File f1 = new File("C:\\Users\\sonin\\eclipse-workspace\\MayPractice\\src\\test\\java\\resources\\data.json");
		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	//Call in Testcase if needed just provide excel FilePath
	public Object[][] getDataFromExcel(String filePath) throws IOException {
		DataFormatter formatter = new DataFormatter();
		 FileInputStream fis = new FileInputStream(filePath);
		 XSSFWorkbook workbook =  new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheetAt(0);
		 int rowCount = sheet.getPhysicalNumberOfRows();
		 System.out.println(rowCount+" total RowCount");
		 XSSFRow row = sheet.getRow(0);
		 int columnCount = row.getLastCellNum();
		 System.out.println(columnCount+" total columnCount");
		 Object[][] data = new Object[rowCount-1][columnCount];
		 for(int i=0;i<rowCount-1;i++) {
			 row = sheet.getRow(i+1);
			 for(int j=0;j<columnCount;j++) {
				 XSSFCell cell = row.getCell(j);
				 data[i][j] = formatter.formatCellValue(cell); 
			 }
		 }
		 return data;
	}

}
