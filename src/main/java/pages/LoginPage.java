package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver)  {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnLogin;
    @FindBy(xpath ="//mat-dialog-container/app-error")
    WebElement dialogContainer;
    @FindBy(xpath ="//button[text()='Ok']")
    WebElement btnOkDialog;
    @FindBy(xpath = "//div[@class='error']")
    WebElement errorDiv;


    public void fillLoginForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }
    public boolean isDialogContainerHasText(String text) {
        return isTextElementPreseant(dialogContainer, text);
    }

    public void closeDialogContainer() {
        btnOkDialog.click();

    }
    public boolean validateInputErrors(String text) {
        return isTextElementPreseant(errorDiv, text);
    }
}

