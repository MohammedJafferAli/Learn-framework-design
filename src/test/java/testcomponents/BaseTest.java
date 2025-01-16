package testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;

    public void initiliazeDriver() throws IOException {

        //Setup properties file to get the browser name to initialise
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
        prop.load(fis);
        //String browserName = prop.getProperty("browser");

        //browser value can be passed at the run time using maven command -Dbrowser replace below line for line 30
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
           WebDriverManager.chromedriver().setup();
           if(browserName.contains("headless"))
           {
               options.addArguments("headless");
           }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public LoginPage launchApplication() throws IOException {
        initiliazeDriver();
        loginPage = new LoginPage(driver);
        loginPage.launchApp();
        return loginPage;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }


    public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
        FileHandler.copy(source,file);
        return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png" ;
    }
}
