
package ui_tests;

import data_provider.CarDP;
import dto.Car;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import utils.Fuel;
import utils.HeaderMenuItem;
import utils.TestNGListener;
import static utils.PropertiesReader.getProperty;
import static pages.BasePage.*;
import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)
public class AddNewCarTests extends ApplicationManager {

    LoginPage loginPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver());
        loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(getProperty("login.properties","email"),
                getProperty("login.properties","password"));
        letCarWorkPage = clickButtonsOnHeader(HeaderMenuItem.LET_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest(){
        Car car = Car.builder()
                .city("Haifa")
                .manufacture("Opel")
                .model("Astra")
                .year("2020")
                .fuel(Fuel.HYBRID.getValue())
                .seats(4)
                .carClass("C")
                .serialNumber("Opel-"+generateString(7))
                .pricePerDay(100.77)
                .about("about")
                .image("MyCar.jpg")
                .build();
        letCarWorkPage.typeAddNewCarForm(car);
    }

    @Test(dataProvider = "addNewCarDP", dataProviderClass = CarDP.class)
    public void addNewCarPositiveTestDP(Car car){
        letCarWorkPage.typeAddNewCarForm(car);
    }

    @Test(dataProvider = "addNewCarDPFile", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest(Car car){
        logger.info("test data --> " + car);
        letCarWorkPage.typeAddNewCarForm(car);
        Assert.assertFalse(letCarWorkPage.isEnabledSubmitBtn());
    }
}