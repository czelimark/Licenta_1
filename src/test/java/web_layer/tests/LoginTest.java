package web_layer.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import web_layer.pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeClass
    public void setUpLoginPage() {

        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void succesfulloginAndLogoutTest() throws Exception {
        loginPage.login("userr1@gmail.com", "user1234");
        loginPage.logout();
        loginPage.submitCredentials();
    }

    @Test
    public void errorMessagesForInvalidCredentialsTest() throws Exception {
        loginPage.login("userr2@gmail.com", "user1234");

        Assert.assertEquals(true, loginPage.invalidFields());
    }
}