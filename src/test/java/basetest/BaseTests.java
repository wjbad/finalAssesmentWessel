package basetest;

import net.bytebuddy.asm.Advice;
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

public class BaseTests {

    protected static WebDriver driver;
    protected HomePage homePage;


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
                e.printStackTrace();

            }
        } else {

            TakesScreenshot screenshot = (TakesScreenshot) driver; //create a screenshot
            File source = screenshot.getScreenshotAs(OutputType.FILE); // specify the output type
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshot/Passed/" + testResult.getName() + "_" + testResult.getStartMillis() + ".png");

            try {
                FileHandler.copy(source, destination);// error will printout if file or source is not reached
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @AfterSuite
    public void tearDown() {
      driver.quit();

   }

}
