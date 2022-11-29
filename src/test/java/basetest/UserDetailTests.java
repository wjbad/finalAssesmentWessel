package basetest;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.LoginPage;
import pages.UserDetailPage;

import java.io.File;


public class UserDetailTests extends BaseTests {

    @Test(priority = 2,dataProvider = "AddUserData")
    public void userDetailTest(Boolean SuccessApp,String Facility,Boolean Hospital,Integer ValueOfRadioButton,String DateSet,String UserComment){

        UserDetailPage userDetailTest = new UserDetailPage(driver);
        //Confirm user is on appointment page
        Assert.assertTrue(SuccessApp,userDetailTest.getAppMsg());
        userDetailTest.selectFacilityOption(Facility);
        userDetailTest.selectReadmissionOption(Hospital);
        userDetailTest.clickRadioButton(ValueOfRadioButton);
        userDetailTest.enterAppDate(DateSet);
        userDetailTest.enterComment(UserComment);
        ConfirmationPage getMsg = userDetailTest.clickBookAppButton();
        //Confirm detail of booking
        Assert.assertEquals(Facility,getMsg.getFacilityMsg());
        Assert.assertEquals(DateSet,getMsg.getVisitDateMsg());
        Assert.assertEquals(UserComment,getMsg.getCommentMsg());
        homePage.clickRetHomeBtn();
    }

    @DataProvider()
    public Object[][] AddUserData() {
        Object[][] data2 = new Object[3][6];

        data2[0][0] = true;data2[0][1] = "Hongkong CURA Healthcare Center";data2[0][2] = true;data2[0][3] = 1;data2[0][4] = "12/12/2022";data2[0][5] = "Please let me know if I need my Medical Aid card";
        data2[1][0] = true;data2[1][1] = "Seoul CURA Healthcare Center";data2[1][2] = false;data2[1][3] = 0;data2[1][4] = "03/02/2023";data2[1][5] = "I'm currently in a wheel chair and will need assistance";
        data2[2][0] = true;data2[2][1] = "Tokyo CURA Healthcare Center";data2[2][2] = true;data2[2][3] = 2;data2[2][4] = "26/12/2022";data2[2][5] = "This will be a cash payment";

        return data2;
    }
}
