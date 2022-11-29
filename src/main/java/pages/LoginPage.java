package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By userName = By.name("username");
    private By password = By.id("txt-password");
    private By loginMsg = By.tagName("h2");
    private By loginButton = By.xpath("//button[@id='btn-login']");

    //Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void enterUserName(String UserName){
        driver.findElement(userName).sendKeys(UserName);
    }
    public void enterPassword(String Password){
        driver.findElement(password).sendKeys(Password);
    }

    //Get message from login screen
    public String getLoginMsg(){
        return driver.findElement(loginMsg).getText(); }
    public UserDetailPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new UserDetailPage(driver);
    }

}
