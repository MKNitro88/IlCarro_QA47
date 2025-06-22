package ui_tests;

import dto.CarLombok;
import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.TestNGListner;

import static pages.BasePage.*;
import static utils.RandomUtils.generateString;

@Listeners(TestNGListner.class)

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
                .city("Haifa")
                .manufacturer("Opel")
                .model("Astra")
                .year("2020")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .serialNumber("Opel-"+generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .build();
        letCarWorkPage.fillLetCarWorkForm(car);

    }
}
