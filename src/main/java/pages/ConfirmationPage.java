package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private WebDriver driver;
    private By facilityMsg = By.id("facility");
    private By dateMsg = By.xpath("//p[@id='visit_date']");
    private By commentMsg = By.id("comment");

    //Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver; }
    public String getFacilityMsg(){
        return driver.findElement(facilityMsg).getText(); }
    public String getVisitDateMsg(){
        return driver.findElement(dateMsg).getText(); }
    public String getCommentMsg(){
        return driver.findElement(commentMsg).getText(); }


}
