package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UserDetailPage {

    private WebDriver driver;

    private By facilitySelection = By.name("facility");
    private By readmissionSelection = By.xpath("//input[@id='chk_hospotal_readmission']");
    private By radioButton = By.xpath("//input[@name='programs']");
    private By appDate = By.id("txt_visit_date");
    private By commentSet = By.id("txt_comment");
    private By saveButton = By.xpath("//button[@id='btn-book-appointment']");

    private By appMsg = By.xpath("//h2[.='Make Appointment']");

    //Constructor
    public UserDetailPage(WebDriver driver){
        this.driver = driver;
    }
    public void selectFacilityOption(String Facility){
        Select rollDropDown = new Select(driver.findElement(facilitySelection));
        rollDropDown.selectByVisibleText(Facility);}
   public void selectReadmissionOption(Boolean Hospital) {
       if (Hospital == true) {
           driver.findElement(readmissionSelection).click();}
   }
    public void clickRadioButton(Integer ValueOfRadioButton){
        int a = driver.findElements(radioButton).size();
        driver.findElements(radioButton).get(ValueOfRadioButton.intValue()).click();}
    public void enterAppDate(String VisitDate){
        driver.findElement(appDate).sendKeys(VisitDate);
    }
    public void enterComment(String NoteComment){
        driver.findElement(commentSet).sendKeys(NoteComment);
    }
    public ConfirmationPage clickBookAppButton() {
        driver.findElement(saveButton).click();
        return new ConfirmationPage(driver);
    }

    //Get message on appointment page
    public String getAppMsg(){
        return driver.findElement(appMsg).getText(); }

}
