import io.qameta.allure.junit4.DisplayName;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import constant.SectionName;

import static constant.SectionName.*;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final SectionName sectionName;
    private final String attribute = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    public ConstructorTest(SectionName sectionName, String browserType) {
        this.sectionName = sectionName;
        this.browserType = browserType;
    }

    @Parameterized.Parameters(name = "Раздел: {0}, Браузер: {1}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {BUN, "chrome"},
                {SAUCE, "chrome"},
                {FILLING, "chrome"},
                {BUN, "yandex"},
                {SAUCE, "yandex"},
                {FILLING, "yandex"}
        };
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @DisplayName("Проверка перехода по разделам конструктора")
    public void shouldSwitchSectionsCorrectly() {
        log.info("Тестирование раздела '{}' в браузере '{}'", sectionName, browserType);

        homePage.clickSection(sectionName);

        String actualClass = homePage.getClassName(sectionName);
        assertTrue(
                String.format("Раздел '%s' должен быть активным (класс '%s')", sectionName, attribute),
                actualClass.contains(attribute)
        );
    }
}