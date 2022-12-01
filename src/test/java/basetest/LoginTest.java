package basetest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTest extends BaseTests {

    boolean myScreenshotCapture() { return true; }

    @Test(priority = 1,dataProvider = "LoginData")
    public void addUserTest(boolean SuccessLgn,String uName, String pWord){

        LoginPage addUserTest = homePage.clickMakeApp();
        Assert.assertTrue(SuccessLgn,addUserTest.getLoginMsg());
        addUserTest.enterUserName(uName);
        addUserTest.enterPassword(pWord);
        manualScreenshotTaken();
        addUserTest.clickLoginButton();
    }

    @DataProvider()
    public Object[][] LoginData() {
        Object[][] data = new Object[1][3];

        data[0][0] = true;data[0][1] = "John Doe";data[0][2] = "ThisIsNotAPassword";

        return data;
    }

}
