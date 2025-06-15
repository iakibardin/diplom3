package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import constant.ButtonNameForConstructor;

import java.time.Duration;

public class UserPage {
    private final WebDriver driver;
    private final By profileButton = By.xpath(".//*[text()='Профиль']");
    private final By allUserFields = By.xpath(".//*[@class='text input__textfield text_type_main-default input__textfield-disabled']");
    private final By constructorButton = By.linkText("Конструктор");
    private final By logoButton = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public UserPage waitLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(profileButton).isEnabled()
        ));
        return this;
    }

    public String getUserName() {
        return driver.findElements(allUserFields).get(0).getAttribute("value");
    }

    public String getUserLogin() {
        return driver.findElements(allUserFields).get(1).getAttribute("value");
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    public void clickExit() {
        driver.findElement(exitButton).click();
    }

    public void changeButton(ButtonNameForConstructor buttonName) {
        switch (buttonName) {
            case CONSTRUCTOR:
                clickConstructorButton();
                break;
            case LOGO_STELLAR_BURGER:
                clickLogo();
                break;
        }
    }
}
