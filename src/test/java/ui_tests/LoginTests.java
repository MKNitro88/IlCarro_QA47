package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.PropertiesReader.getProperty;
import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    String validEmail = getProperty("login.properties", "email");
    String validPassword =getProperty("login.properties", "password");

    @BeforeMethod
    public void goToLoginPage(){
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test(enabled = false)
    public void loginPositiveTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(validEmail, validPassword);    }

    @Test
    public void loginPositiveTest_lombok(){
        UserLombok user = UserLombok.builder()
                .username(validEmail)
                .password(validPassword)
                .build();
        logger.info("test data --> " + user.toString());
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"), "loginPositiveTest_lombok");
    }

    @Test
    public void loginNegativeTest_unregUser(){
        UserLombok user = UserLombok.builder()
                .username(generateEmail(10))
                .password(validPassword)
                .build();
        logger.info("test data --> " + user.toString());
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTest_unregUser");
    }

    @Test
    public void loginNegativeTest_EmptyPassword(){
        UserLombok user = UserLombok.builder()
                .username(validEmail)
                .password("")
                .build();
        logger.info("test data --> " + user.toString());
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorPassword(), "loginNegativeTest_EmptyPassword");
    }
}
