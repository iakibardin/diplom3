import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import constant.ButtonNameForLogin;
import static constant.ButtonNameForLogin.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    private final ButtonNameForLogin nameButtonLogin;

    public LoginTest(ButtonNameForLogin nameButtonLogin, String browserType) {
        this.nameButtonLogin = nameButtonLogin;
        this.browserType = browserType;
    }

    @Parameterized.Parameters(name = "Кнопка: {0}, Браузер: {1}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {LOGIN_ON_HOME_PAGE, "chrome"},
                {LOGIN_ON_LK, "chrome"},
                {LOGIN_ON_REGISTER_PAGE, "chrome"},
                {LOGIN_ON_RECOVERY_PASSWORD, "chrome"},
                {LOGIN_ON_HOME_PAGE, "yandex"},
                {LOGIN_ON_LK, "yandex"},
                {LOGIN_ON_REGISTER_PAGE, "yandex"},
                {LOGIN_ON_RECOVERY_PASSWORD, "yandex"}
        };
    }

    @Before
    public void setUp() {
        super.setUp();
        createUser();
        driver.get(SITE);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @DisplayName("Авторизация в ЛК")
    public void loginTest() {
        quiteButton(nameButtonLogin);
        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();
        homePage.waitLoadHeader();
        assertEquals("URL должен соответствовать домашней странице",
                SITE + "/", driver.getCurrentUrl());
    }
}