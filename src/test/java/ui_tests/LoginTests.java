package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import java.lang.reflect.Method;

import static pages.BasePage.clickButtonsOnHeader;
import static utils.RandomUtils.generateEmail;

public class LoginTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage(){
        homePage = new HomePage(getDriver());
        loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
    }

    @Test
    public void loginPositiveTest_lombok(Method method) {
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("@Password123")
                .build();
        logger.info("test data: " + user);
        loginPage.fillLoginForm(user);
        Assert.assertTrue(loginPage.isDialogContainerHasText("Logged in success"),
                "positive login test - Lombok");

    }
    @Test
    public void loginNegativeTest_unregUser(Method method) {
        logger.info("start method " + method.getName());
        UserLombok user = UserLombok.builder()
                .username(generateEmail(5))
                .password("Password123!")
                .build();
        logger.info("test data: " + user);
        loginPage.fillLoginForm(user);
        Assert.assertTrue(loginPage.isDialogContainerHasText("Login or Password incorrect"),
                "negative login test - unregistered user");

    }
    @Test
    public void loginNegativeTest_emptyPass(Method method) {
        logger.info("start method " + method.getName());
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("")
                .build();
        logger.info("test data: " + user);
        loginPage.fillLoginForm(user);
        Assert.assertTrue(loginPage.validateInputErrors("Password is required"),
                "negative login test - empty password");

    }
    @Test
    public void loginNegativeTest_emptyEmail(Method method) {
        logger.info("start method " + method.getName());
        UserLombok user = UserLombok.builder()
                .username("")
                .password("Password123!")
                .build();
        logger.info("test data: " + user);
        loginPage.fillLoginForm(user);
        Assert.assertTrue(loginPage.validateInputErrors("Email is required"),
                "negative login test - empty email");

    }
}
