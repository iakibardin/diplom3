import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import user.User;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PersonalAreaTest extends BaseTest {

    public PersonalAreaTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
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
    @DisplayName("Переход в Личный кабинет авторизованным пользователем")
    public void personalAreaButtonWithAuthUser() {
        authorizationLK();
        homePage.clickLk();
        userPage.waitLoadingPage();

        assertEquals(user.getName(), userPage.getUserName());
        assertEquals(user.getEmail(), userPage.getUserLogin());
    }

    @Test
    @DisplayName("Выход из Личного кабинета")
    public void exitFromLk() {
        authorizationLK();
        toLkAfterAuthorization();

        userPage.clickExit();
        loginPage.waitLoadHeader();

        assertEquals(SITE + "/login", driver.getCurrentUrl());
    }

    private void authorizationLK() {
        homePage.clickLk();
        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();
    }

    private void toLkAfterAuthorization() {
        homePage.clickLk();
        userPage.waitLoadingPage();
    }
}