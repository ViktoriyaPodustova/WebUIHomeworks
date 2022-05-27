package Homework6;

import Homework7.CustomLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

@Epic("Сайт с дневниками")
public class AutomationTest {
    WebDriver driver;
    Cookie cookie;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriverAndAuthorization() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        driver.get("https://diary.ru/");
        cookie = new Cookie("_identity_", "f1e5363f51f1b93ff3946e041fe2683d0bf01557867c7b2a893f5d88abbb7682a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3566794%2C%223UAxeL8_AMp8qsM9gbo81ZKkEp87WM0Q%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Feature("Поиск дневников")
    @Story("Поиск дневников из тех, кто онлайн")
    @Test
    void searchPeopleOnlineFromAllDiariesTest() {
        new MainPage(driver).
                clickToDropDownMenuToAllDiary().
                searchPeopleOnline().
                amountSum();
        assertThat(new SuccessfulSearch(driver).numberOfAllDiaries, lessThan(1568796));
    }

    @Story("Расширенный поиск дневников из тех, кто онлайн")
    @Test
    void AdvancedDiarySearchTest() throws InterruptedException {
        new MainPage(driver).
                clickToDropDownMenuToAllDiary().
                searchPeopleOnline().
                advancedSearch.advancedSearchFromOnline("Муж", "18", "30").
                amountSum();
        assertThat(new SuccessfulSearch(driver).numberOfAllDiaries, lessThan(600));
    }

    @Feature("Открытие дневника")
    @Story("Открытие незаблокированного дневника и возврат на главную страницу")
    @Test
    void fromFilterGoToFirstDiaryAndReturnToMainPage() throws InterruptedException {
        new MainPage(driver).
                clickToDropDownMenuToAllDiary().
                searchPeopleOnline().
                advancedSearch.advancedSearchFromOnline("Муж", "18", "30").
                listFindedDiaries.goToFirstNotPrivateDiary().
                clickToMainPageButton();
        Assertions.assertTrue(new MainPage(driver).dropCommon.isDisplayed());
    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry logEntry : logEntries) {
            Allure.addAttachment("Элемент лога браузера", logEntry.getMessage());
        }
        driver.quit();
    }
}
