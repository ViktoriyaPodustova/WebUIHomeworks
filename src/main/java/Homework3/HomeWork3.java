package Homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeWork3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        //1. открыть сайт и быть уже авторизованным
        driver.get("https://diary.ru/");
        Cookie cookie=new Cookie("_identity_", "f1e5363f51f1b93ff3946e041fe2683d0bf01557867c7b2a893f5d88abbb7682a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3566794%2C%223UAxeL8_AMp8qsM9gbo81ZKkEp87WM0Q%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //2. нажать на кнопку "меню сайта"
        driver.findElement(By.id("drop-common")).click();
        Thread.sleep(2500);
        //3. Нажать на раздел "Все дневники"
        driver.findElement(By.xpath("//div[@class='dropdown-block col-sm-6']/a[@href='/list/']")).click();

        //4. Поставить фильтр "Искать тех, кто онлайн"
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("listsearchform-online"))));
        driver.findElement(By.id("listsearchform-online")).click();
        Thread.sleep(2500);
        //5. нажать на кнопку "Найти"
        driver.findElement(By.id("form_search_list_submit")).click();
        Thread.sleep(5000);
        // Из тех, кто он-лайн выполнить расширенный поиск.
        //6. Нажать на кнопку "Расширенный поиск"
        driver.findElement(By.id("extend_search")).click();
        //7. Выбрать мужской пол
        driver.findElement(By.xpath("//option[contains(.,'Муж')]")).click();
        Thread.sleep(2500);
        //8. Выбрать возраст от 18 до 30 лет
        driver.findElement(By.id("listsearchform-from_age")).sendKeys("18");
        driver.findElement(By.id("listsearchform-to_age")).sendKeys("30");
        Thread.sleep(2500);
        //9. нажать на кнопку "Найти"
        driver.findElement(By.id("form_search_list_submit")).click();
        Thread.sleep(5000);
        //10. Нажать в первой строке на дневник в колонке "Название"
        driver.findElement(By.xpath("//div[@class='item' and @data-key='0']/div[@class='col-sm-2 no-gutter']")).click();
        //11. Нажать на кнопку "Главная страница"
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='Главная страница']"))));
        driver.findElement(By.xpath("//a[.='Главная страница']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
