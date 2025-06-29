package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.*;
@Listeners(TestNGListener.class)
public class SignUpTests extends ApplicationManager {
    HomePage homePage;
    SignUpPage signUpPage;
    @BeforeMethod
    public void goToLoginPage(){
        homePage = new HomePage(getDriver());
        signUpPage = clickButtonsOnHeader(HeaderMenuItem.SIGN_UP);
    }

    @Test
    public void signUpPositiveTest(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));
    }
    @Test
    public void signUpNegativeTest_WOcheckBox(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickBtnYalla();
        Assert.assertFalse(signUpPage.btnYallaIaEnabled());
    }
    @Test
    public void signUpNegativeTest_WOname(){
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("testLastName")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is required"));
    }

    @Test
    public void signUpNegativeTest_WOlastName(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Last name is required"));
    }

    @Test
    public void signUpNegativeTest_WOemail(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username("")
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void signUpNegativeTest_WOpassword(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(10))
                .password("")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password is required"));
    }
}