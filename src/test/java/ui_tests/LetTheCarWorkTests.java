package ui_tests;

import dto.CarLombok;
import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.RandomUtils.generateString;

public class LetTheCarWorkTests extends ApplicationManager {
    LoginPage loginPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver());
        loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        UserLombok validUser = UserLombok.builder()
                .username("test_mkii@gmail.com")
                .password("@Password123")
                .build();
        loginPage.fillLoginForm(validUser);
        letCarWorkPage = clickButtonsOnHeader(HeaderMenuItem.LET_CAR_WORK);
    }
    @Test
    public void letTheCarWorkPositiveTest() {
        CarLombok car = CarLombok.builder()
                .serialNumber(generateString(7))
                .city("Tel Aviv")
                .manufacturer("Toyota")
                .seats(4)
                .pricePerDay(100.55)
                .about("nothing special")
                .carClass("C")
                .year("2020")
                .build();

    }
}
