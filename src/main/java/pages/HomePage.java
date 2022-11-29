package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By makeAppButton = By.xpath("//a[@id='btn-make-appointment']");
    private By returnHome = By.xpath("//a[@href='https://katalon-demo-cura.herokuapp.com/']");

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage clickMakeApp() {
        driver.findElement(makeAppButton).click();
        return new LoginPage(driver);
    }
    public ConfirmationPage clickRetHomeBtn(){
        driver.findElement(returnHome).click();
        return new ConfirmationPage(driver);
    }
}