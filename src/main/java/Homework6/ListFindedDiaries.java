package Homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListFindedDiaries extends BaseView {

    public ListFindedDiaries(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='item' and not(.//span[@class='i-lock'])]/div[@class='col-sm-2 no-gutter']")
    public List<WebElement> listNotPrivateDiaries;

    @Step("Выбрать дневник с открытым доступом")
    public DiaryPage goToFirstNotPrivateDiary() {
        listNotPrivateDiaries.stream().findFirst().get().click();
        return new DiaryPage(driver);
    }
}
