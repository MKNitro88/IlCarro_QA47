package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.generateEmail;

public class LoginTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest(){
        loginPage.fillLoginForm("test_mkii@gmail.com","@Password123");
        loginPage.clickBtnLogin();
    }
    @Test
    public void loginPositiveTest_lombok() {
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("@Password123")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.isDialogContainerHasText("Logged in success"),
                "Login failed, dialog container does not contain expected text");

    }
    @Test
    public void loginNegativeTest_unregUser() {
        UserLombok user = UserLombok.builder()
                .username(generateEmail(5))
                .password("Password123!")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.isDialogContainerHasText("Login or Password incorrect"),
                "dialog container does not contain expected text");

    }
    @Test
    public void loginNegativeTest_emptyPass() {
        UserLombok user = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.validateInputErrors("Password is required"));

    }
    @Test
    public void loginNegativeTest_emptyEmail() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("Password123!")
                .build();
        loginPage.fillLoginForm(user.getUsername(),user.getPassword());
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.validateInputErrors("Email is required"));

    }
}
