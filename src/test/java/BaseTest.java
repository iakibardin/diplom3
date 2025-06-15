import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import constant.ButtonNameForLogin;
import generator.UserGenerator;
import pages.*;
import user.User;
import util.*;
import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static final String SITE = "https://stellarburgers.nomoreparties.site";
    protected WebDriver driver;
    protected final UserGenerator userGenerator = new UserGenerator();
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected RestorePasswordPage restorePasswordPage;
    protected UserPage userPage;
    protected User user;
    protected String browserType;

    @Before
    public void setUp() {
        driver = WebDriverStarts.createDriver(browserType);
        driver.get(SITE);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        userPage = new UserPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void createUser() {
        user = userGenerator.getUser();
        given().contentType(ContentType.JSON)
                .body(user)
                .post(SITE + "/api/auth/register");
    }
    protected void transitionToLk() {
        homePage.clickLk();
        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();
        homePage.clickLk();
    }
    protected void deleteUser() {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(SITE + "/api/auth/login")
                .body().path("accessToken");
        given().contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .body(user).delete(SITE + "/api/auth/user");
    }

    protected void quiteButton(ButtonNameForLogin buttonName) {
        switch (buttonName) {
            case LOGIN_ON_HOME_PAGE:
                homePage.clickLoginButton();
                break;
            case LOGIN_ON_LK:
                homePage.clickLk();
                break;
            case LOGIN_ON_REGISTER_PAGE:
                homePage.clickLk();
                loginPage.clickRegister();
                registerPage.clickLogin();
                break;
            case LOGIN_ON_RECOVERY_PASSWORD:
                homePage.clickLk();
                loginPage.clickRestorePasswordButton();
                restorePasswordPage.clickLoginButton();
                break;
        }
    }
}