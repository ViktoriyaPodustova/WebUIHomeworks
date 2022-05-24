package Homework6;

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

    public MainPage clickToMainPageButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mainPageButton));
        mainPageButton.click();
        webDriverWait.until(ExpectedConditions.urlToBe("https://diary.ru/"));
        return new MainPage(driver);
    }
}
