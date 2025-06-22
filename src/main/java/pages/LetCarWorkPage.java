package pages;

import dto.CarLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;



public class LetCarWorkPage extends BasePage {
    public LetCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputPickUpPlace;
    @FindBy(xpath = "//button[@class ='dismissButton']")
    WebElement googleMapsBtnOk;
    @FindBy(id = "pickUpPlace")
    WebElement inputCity;
    @FindBy(id = "make")
    WebElement inputManufacturer;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement inputFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPricePerDay;
    @FindBy(id = "about")
    WebElement inputAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    public void fillLetCarWorkForm(CarLombok car) {
        inputCity.sendKeys(car.getCity());
        googleMapsBtnOk.click();
        inputManufacturer.sendKeys(car.getManufacturer());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        inputFuel.sendKeys(car.getFuel());
        inputSeats.sendKeys(String.valueOf(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPricePerDay.sendKeys(String.valueOf(car.getPricePerDay()));
        inputAbout.sendKeys(car.getAbout());
        submitButton.click();




    }

}