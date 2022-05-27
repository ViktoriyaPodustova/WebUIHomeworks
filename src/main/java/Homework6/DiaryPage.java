package Homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DiaryPage extends BaseView {
    public DiaryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Главная страница']")
    public WebElement mainPageButton;

    @Step("Нажать на кнопку 'Главная страница' ")
    public MainPage clickToMainPageButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mainPageButton));
        mainPageButton.click();
        webDriverWait.until(ExpectedConditions.urlToBe("https://diary.ru/"));
        return new MainPage(driver);
    }
}
