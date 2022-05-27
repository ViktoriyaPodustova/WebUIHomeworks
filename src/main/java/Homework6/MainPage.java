package Homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "drop-common")
    public WebElement dropCommon;

    @FindBy(xpath = "//div[@class='dropdown-block col-sm-6']/a[@href='/list/']")
    public WebElement allDiaries;

    @Step("Нажать на кнопку 'Меню сайта' и перейти в раздел 'Все дневники' ")
    public DiarySearch clickToDropDownMenuToAllDiary() {
        dropCommon.click();
        allDiaries.click();
        return new DiarySearch(driver);
    }


}
