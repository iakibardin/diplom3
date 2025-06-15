import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import constant.ButtonNameForConstructor;
import static constant.ButtonNameForConstructor.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FromPersonalToConstructorTest extends BaseTest {
    private final ButtonNameForConstructor buttonName;

    public FromPersonalToConstructorTest(ButtonNameForConstructor buttonName, String browserType) {
        this.buttonName = buttonName;
        this.browserType = browserType;
    }

    @Parameterized.Parameters(name = "Кнопка: {0}, Браузер: {1}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {CONSTRUCTOR, "chrome"},
                {LOGO_STELLAR_BURGER, "chrome"},
                {CONSTRUCTOR, "yandex"},
                {LOGO_STELLAR_BURGER, "yandex"}
        };
    }

    @Before
    public void setUp() {
        super.setUp();
        createUser();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @DisplayName("Переход из ЛК в Конструктор")
    public void transitionToConstructorFromLk() {
        super.transitionToLk();

        userPage.waitLoadingPage()
                .changeButton(buttonName);

        String expectedUrl = SITE + "/";
        assertEquals("URL должен соответствовать главной странице",
                expectedUrl, driver.getCurrentUrl());
    }
}