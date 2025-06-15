package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Paths;

public class WebDriverStarts {
    private static final String DEFAULT_YANDEX_DRIVER_PATH = "src/test/resources/yandexdriver";

    public static WebDriver createDriver(String browserType) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        if ("yandex".equalsIgnoreCase(browserType)) {
            setDriverPath(DEFAULT_YANDEX_DRIVER_PATH);
        } else {
            WebDriverManager.chromedriver().setup();
        }

        return new ChromeDriver(options);
    }
    private static void setDriverPath(String driverPath) {
        String absolutePath = Paths.get(driverPath).toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", absolutePath);
    }
}