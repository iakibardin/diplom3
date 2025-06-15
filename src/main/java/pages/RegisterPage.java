package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final By registerHeader = By.xpath(".//h2[text() = 'Регистрация']");
    private final By allFieldsRegistrationForm = By.xpath(".//*[@class='text input__textfield text_type_main-default']");
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By exceptionPassword = By.xpath(".//*[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    private final By buttonLogin = By.linkText("Войти");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitDownloadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(registerHeader).getText() != null
                && !driver.findElement(registerHeader).getText().isEmpty()
        ));
    }

    public RegisterPage setName(String newName) {
        waitDownloadPage();
        getNameField().sendKeys(newName);
        return this;
    }

    public RegisterPage setEmail(String newEmail) {
        getEmailField().sendKeys(newEmail);
        return this;
    }

    public RegisterPage setPassword(String newPassword) {
        getPasswordField().sendKeys(newPassword);
        return this;
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public String getTextException() {
        return driver.findElement(exceptionPassword).getText();
    }

    public void clickLogin() {
        driver.findElement(buttonLogin).click();
    }

    private WebElement getNameField() {
        return driver.findElements(allFieldsRegistrationForm).get(0);
    }

    private WebElement getEmailField() {
        return driver.findElements(allFieldsRegistrationForm).get(1);
    }

    private WebElement getPasswordField() {
        return driver.findElements(allFieldsRegistrationForm).get(2);
    }
}
