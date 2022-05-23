package Homework6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class AutomationTest {
    WebDriver driver;
    Cookie cookie;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriverAndAuthorization() {
        driver = new ChromeDriver();
        driver.get("https://diary.ru/");
        cookie = new Cookie("_identity_", "f1e5363f51f1b93ff3946e041fe2683d0bf01557867c7b2a893f5d88abbb7682a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3566794%2C%223UAxeL8_AMp8qsM9gbo81ZKkEp87WM0Q%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Test
    void searchPeopleOnlineFromAllDiariesTest() {
        new MainPage(driver).
                clickToDropDownMenu().
                searchPeopleOnlineFromAllDiaries();
        assertThat(Integer.parseInt(new DiarySearch(driver).searchPeopleOnlineFromAllDiaries()), lessThan(1000));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
