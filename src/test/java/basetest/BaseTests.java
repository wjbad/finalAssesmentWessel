package basetest;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseTests {

    protected static WebDriver driver;
    protected HomePage homePage;

    private String screenMethod = null;

    abstract boolean myScreenshotCapture();

    @BeforeMethod
    public void getTestName(Method method) {
        screenMethod = method.getName();
    }

    @BeforeSuite
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        //Setup chrome driver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");

    }

    @BeforeClass
    public void setupTest(){
        //Driver property is static and can be referenced without having to instantiate it again
        homePage = new HomePage(driver);

    }

    @AfterMethod
    public void captureScreenShots(ITestResult testResult) {

        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshot/Failed/" + testResult.getName() + "_" + testResult.getStartMillis() + ".png");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    //Before the AfterMethod is executed a screenshot is taken
    protected void manualScreenshotTaken() {

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            Date today = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("HH.mm.ss.SSS");
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshot/Passed/" + screenMethod + "_" + dateFormat.format(today) + ".png");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    @AfterSuite
    public void tearDown() {
      driver.quit();

   }

}
