package automationproject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import automationproject.pageobjectmodel.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage obj;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//automationproject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");;
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
		
		
	}
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
         
        
        // Use Path.of to create a Path object
        Path path = Path.of(filePath);
        
        // Read the file content as a string
        String content = Files.readString(path, StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String, String>> list = mapper.readValue(new File(filePath), List.class);
        return list;
        

		
	}
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		driver=initializeDriver();
		obj=new LandingPage(driver);
		obj.goTo("https://rahulshettyacademy.com/client");
		return obj;
	}
	@AfterMethod (alwaysRun = true)
	public void Teardown() {
		driver.close();
	}

}
