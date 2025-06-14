package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

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
    public void loginPositiveTest(){
        loginPage.fillLoginForm("test_mkii@gmail.com","@Password123");
    }
    @Test
    public void loginPositiveTest_lombok() {
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("@Password123")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        Assert.assertTrue(loginPage.isDialogContainerHasText("Logged in success"),
                "positive login test - Lombok");

    }
    @Test
    public void loginNegativeTest_unregUser() {
        UserLombok user = UserLombok.builder()
                .username(generateEmail(5))
                .password("Password123!")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        Assert.assertTrue(loginPage.isDialogContainerHasText("Login or Password incorrect"),
                "negative login test - unregistered user");

    }
    @Test
    public void loginNegativeTest_emptyPass() {
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        Assert.assertTrue(loginPage.validateInputErrors("Password is required"),
                "negative login test - empty password");

    }
    @Test
    public void loginNegativeTest_emptyEmail() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("Password123!")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        Assert.assertTrue(loginPage.validateInputErrors("Email is required"),
                "negative login test - empty email");

    }
}
