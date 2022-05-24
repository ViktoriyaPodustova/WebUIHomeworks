package Homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class Homework5 {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriverAndAuthorization() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://diary.ru/");
        Cookie cookie = new Cookie("_identity_", "f1e5363f51f1b93ff3946e041fe2683d0bf01557867c7b2a893f5d88abbb7682a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3566794%2C%223UAxeL8_AMp8qsM9gbo81ZKkEp87WM0Q%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Test
    void searchPeopleOnlineFromAllDiariesTest() {
        driver.findElement(By.id("drop-common")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-block col-sm-6']/a[@href='/list/']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("listsearchform-online"))));
        driver.findElement(By.id("listsearchform-online")).click();
        driver.findElement(By.id("form_search_list_submit")).click();
        webDriverWait.until(ExpectedConditions.urlContains("ListSearchForm"));
        String numberOfAllDiaries = driver.findElement(By.xpath("//h1[contains(.,'Найдено')]/span")).getText();
        assertThat(Integer.parseInt(numberOfAllDiaries), lessThan(1568796));
    }

    @Test
    void AdvancedDiarySearchTest() {
        driver.findElement(By.id("drop-common")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-block col-sm-6']/a[@href='/list/']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("listsearchform-online"))));
        driver.findElement(By.id("listsearchform-online")).click();
        driver.findElement(By.id("form_search_list_submit")).click();
        webDriverWait.until(ExpectedConditions.urlContains("ListSearchForm"));
        driver.findElement(By.id("extend_search")).click();
        driver.findElement(By.xpath("//option[contains(.,'Муж')]")).click();
        driver.findElement(By.id("listsearchform-from_age")).sendKeys("18");
        driver.findElement(By.id("listsearchform-to_age")).sendKeys("30");
        driver.findElement(By.id("form_search_list_submit")).click();
        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains("ListSearchForm")));
        String numberOfDiariesOnline = driver.findElement(By.xpath("//h1[contains(.,'Найдено')]/span")).getText();
        assertThat(Integer.parseInt(numberOfDiariesOnline), lessThan(600));
    }

    @Test
    void fromFilterGoToFirstDiaryAndReturnToMainPage() throws InterruptedException {
        driver.findElement(By.id("drop-common")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-block col-sm-6']/a[@href='/list/']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("listsearchform-online"))));
        driver.findElement(By.id("listsearchform-online")).click();
        driver.findElement(By.id("form_search_list_submit")).click();
        webDriverWait.until(ExpectedConditions.urlContains("ListSearchForm"));
        driver.findElement(By.id("extend_search")).click();
        driver.findElement(By.xpath("//option[contains(.,'Муж')]")).click();
        driver.findElement(By.id("listsearchform-from_age")).sendKeys("18");
        driver.findElement(By.id("listsearchform-to_age")).sendKeys("30");
        driver.findElement(By.id("form_search_list_submit")).click();
        Thread.sleep(5000);
        List<WebElement> diaryList = driver.findElements(By.xpath("//div[@class='item' and not(.//span[@class='i-lock'])]/div[@class='col-sm-2 no-gutter']"));
        diaryList.stream().findFirst().get().click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Главная страница']")));
        driver.findElement(By.xpath("//a[.='Главная страница']")).click();
        webDriverWait.until(ExpectedConditions.urlToBe("https://diary.ru/"));
        Assertions.assertEquals(driver.getCurrentUrl(), "https://diary.ru/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
