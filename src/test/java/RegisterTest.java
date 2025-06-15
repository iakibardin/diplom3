import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import user.User;
import generator.UserGenerator;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseTest {
    private static final String INCORRECT_PASSWORD_EXCEPTION = "Некорректный пароль";
    private User testUser;

    public RegisterTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[][] browserTypes() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Before
    public void setUp() {
        super.setUp(); // Initialize driver based on browserType
        driver.get(SITE);
    }

    @After
    public void tearDown() {
        // Clean up test user if it was created
        if (testUser != null) {
            try {
                this.user = testUser; // Set current user for deletion
                deleteUser();
            } catch (Exception e) {
                System.out.println("Failed to delete test user: " + e.getMessage());
            }
        }
        super.tearDown();
    }

    @Test
    @DisplayName("Корректная регистрация")
    public void createCorrectUser() {
        testUser = userGenerator.getUser();

        navigateToRegistration();
        registerPage.setName(testUser.getName())
                .setEmail(testUser.getEmail())
                .setPassword(testUser.getPassword())
                .clickRegisterButton();

        loginPage.waitLoadHeader();
        assertEquals("После регистрации должен быть переход на страницу входа",
                SITE + "/login", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем (< 6 символов)")
    public void createUserIncorrectPassword() {
        navigateToRegistration();
        registerPage.setName(userGenerator.getName())
                .setEmail(userGenerator.getEmail())
                .setPassword(userGenerator.getInvalidPassword())
                .clickRegisterButton();

        String errorMessage = registerPage.getTextException();
        assertEquals("Должно отображаться сообщение о некорректном пароле",
                INCORRECT_PASSWORD_EXCEPTION, errorMessage);
    }

    private void navigateToRegistration() {
        homePage.clickLk();
        loginPage.clickRegister();
    }
}
