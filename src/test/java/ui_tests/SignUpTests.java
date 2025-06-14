package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.RandomUtils.*;

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
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));
    }
    @Test
    public void signUpNegativeTest_NOcheckBox(){
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
    public void signUpNegativeTest_NOfirstName(){
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("testLastName")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is required"));
    }

    @Test
    public void signUpNegativeTest_NOlastName(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Last name is required"));
    }

    @Test
    public void signUpNegativeTest_NOemail(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username("")
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void signUpNegativeTest_NOpassword(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(10))
                .password("")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password is required"));
    }
    @Test
    public void signUpNegativeTest_invalidEmail(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username("invalidEmail")
                .password("Password123!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Wrong email format"));
    }
    @Test
    public void signUpNegativeTest_invalidPasswordShort(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(6))
                .password("P" + "1" + "!" +generateString(3))
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain minimum 8 symbols"));
    }
    @Test
    public void signUpNegativeTest_invalidPasswordNoCap(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(6))
                .password("1"+ "!" + generateString(9))
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter"));
    }
    @Test
    public void signUpNegativeTest_invalidPasswordNoSym(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(6))
                .password("1"+ "P" + generateString(7))
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("one special symbol of [@$#^&*!]"));
    }
    @Test
    public void signUpNegativeTest_invalidPasswordNoDigit(){
        UserLombok user = UserLombok.builder()
                .firstName("testName")
                .lastName("testLastName")
                .username(generateEmail(6))
                .password("Password!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox2();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("1 number"));
    }


}