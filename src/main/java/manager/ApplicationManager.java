package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListner;

import java.time.Duration;
import java.time.LocalDate;


public class ApplicationManager {
    //public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // new realization WDListner in selenium 4.20.0
        WebDriverListener webDriverListner = new WDListner();
        driver = new EventFiringDecorator<>(webDriverListner).decorate(driver);

    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
